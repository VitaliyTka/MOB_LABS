#include <cstdlib>
#include <iostream>
#include <cstdio>
#include <ctime>
#include <algorithm>
#include <mpi.h>

using namespace std;
int ProcRank; // Ранг текущего процесса
int ProcNum; // Количество процессов 

const double InfinitiesPercent = 50.0;
const double RandomDataMultiplier = 10;

int Min(int A, int B) {
    int Result = (A < B) ? A : B;
    if ((A < 0) && (B >= 0)) Result = B;
    if ((B < 0) && (A >= 0)) Result = A;
    if ((A < 0) && (B < 0)) Result = -1;
    return Result;
}

void ProcessInitialization(int*& pMatrix, int*& pProcRows, int& Size, int& RowNum);
void ProcessTermination(int* pMatrix, int* pProcRows);
void DummyDataInitialization(int* pMatrix, int Size);
void RandomDataInitialization(int* pMatrix, int Size);
void DataDistribution(int* pMatrix, int* pProcRows, int Size, int RowNum);
void ResultCollection(int* pMatrix, int* pProcRows, int Size, int RowNum);
void ParallelFloyd(int* pProcRows, int Size, int RowNum);
void RowDistribution(int* pProcRows, int Size, int RowNum, int k, int* pRow);
void ParallelPrintMatrix(int* pProcRows, int Size, int RowNum);
void PrintMatrix(int* pMatrix, int RowCount, int ColCount);

int main(int argc, char* argv[]) {
    int* pMatrix; // Матрица смежности
    int Size; // Размер матрицы смежности
    int* pProcRows; // Строки обработки
    int RowNum; // Количество технологических рядов
    double start, finish;
    double duration = 0.0;
    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &ProcNum);
    MPI_Comm_rank(MPI_COMM_WORLD, &ProcRank);
    if (ProcRank == 0)
        printf("Parallel Floyd algorithm\n");
    // Инициализация процесса
    ProcessInitialization(pMatrix, pProcRows, Size, RowNum);
    start = MPI_Wtime();
    // Распределение исходных данных между процессами
    DataDistribution(pMatrix, pProcRows, Size, RowNum);
    // Параллельный алгоритм Флойда
    ParallelFloyd(pProcRows, Size, RowNum);
    //    ParallelPrintMatrix(pProcRows, Size, RowNum);
    // Сбор данных о процессе
    ResultCollection(pMatrix, pProcRows, Size, RowNum);
    finish = MPI_Wtime();
    duration = finish - start;
    if (ProcRank == 0)
        printf("Time of execution: %f\n", duration);
    // Прекращение процесса
    ProcessTermination(pMatrix, pProcRows);
    MPI_Finalize();
    return 0;
}
// Функция выделения памяти и установки начальных значений
void ProcessInitialization(int*& pMatrix, int*& pProcRows, int& Size, int& RowNum) {
    setvbuf(stdout, 0, _IONBF, 0);
    if (ProcRank == 0) {
        do {
            printf("Enter the number of vertices: ");
            scanf_s("%d", &Size);
            if (Size < ProcNum)
                printf("The number of vertices should be greater then number of processes\n");
        } while (Size < ProcNum);
        printf("Using graph with %d vertices\n", Size);
    }
    // Расскажите о количестве вершин
    MPI_Bcast(&Size, 1, MPI_INT, 0, MPI_COMM_WORLD);
    // Количество строк для каждого процесса
    int RestRows = Size;
    for (int i = 0; i < ProcRank; i++)
        RestRows = RestRows - RestRows / (ProcNum - i);
    RowNum = RestRows / (ProcNum - ProcRank);
    // Выделить память для строк текущего процесса 
    pProcRows = new int[Size * RowNum];
    if (ProcRank == 0) {
        // Выделить память для матрицы смежности
        pMatrix = new int[Size * Size];
        // Инициализация данных
        //DummyDataInitialization(pMatrix, Size);
        RandomDataInitialization(pMatrix, Size);
    }
}
// Функция завершения вычислительного процесса
void ProcessTermination(int* pMatrix, int* pProcRows) {
    if (ProcRank == 0)
        delete[]pMatrix;
    delete[]pProcRows;
}
// Функция для простой настройки исходных данных
void DummyDataInitialization(int* pMatrix, int Size) {
    for (int i = 0; i < Size; i++)
        for (int j = i; j < Size; j++) {
            if (i == j) pMatrix[i * Size + j] = 0;
            else
                if (i == 0) pMatrix[i * Size + j] = j;
                else pMatrix[i * Size + j] = -1;
            pMatrix[j * Size + i] = pMatrix[i * Size + j];
        }
}
// Функция установки данных генератором случайных чисел
void RandomDataInitialization(int* pMatrix, int Size) {
    srand((unsigned)time(0));

    for (int i = 0; i < Size; i++)
        for (int j = 0; j < Size; j++)
            if (i != j) {
                if ((rand() % 100) < InfinitiesPercent)
                    pMatrix[i * Size + j] = -1;
                else
                    pMatrix[i * Size + j] = rand() + 1;
            }
            else
                pMatrix[i * Size + j] = 0;
}
// Распределение данных между процессами
void DataDistribution(int* pMatrix, int* pProcRows, int Size, int RowNum) {
    int* pSendNum; // Количество элементов, отправленных в процесс
    int* pSendInd; // Индекс первого элемента данных, отправленного процессу
    int RestRows = Size; // Количество рядов, которые еще не распределены
    // Выделить память для временных объектов
    pSendInd = new int[ProcNum];
    pSendNum = new int[ProcNum];
    // Определите расположение строк матрицы для текущего процесса
    RowNum = Size / ProcNum;
    pSendNum[0] = RowNum * Size;
    pSendInd[0] = 0;
    for (int i = 1; i < ProcNum; i++) {
        RestRows -= RowNum;
        RowNum = RestRows / (ProcNum - i);
        pSendNum[i] = RowNum * Size;
        pSendInd[i] = pSendInd[i - 1] + pSendNum[i - 1];
    }
    // Разбросайте ряды
    MPI_Scatterv(pMatrix, pSendNum, pSendInd, MPI_INT, pProcRows, pSendNum[ProcRank], MPI_INT, 0, MPI_COMM_WORLD);
    // Свободная выделенная память
    delete[]pSendNum;
    delete[]pSendInd;
}
// Функция сбора результатов процесса
void ResultCollection(int* pMatrix, int* pProcRows, int Size, int RowNum) {
    int* pReceiveNum; // Количество элементов, которые отправляет текущий процесс.
    int* pReceiveInd; // Индекс первого элемента текущего процесса в векторе результатов
    int RestRows = Size; // Количество рядов, которые еще не собраны
    // Выделить память для временных объектов
    pReceiveNum = new int[ProcNum];
    pReceiveInd = new int[ProcNum];
    // Определите расположение блока вектора результатов текущего процесса
    RowNum = Size / ProcNum;
    pReceiveInd[0] = 0;
    pReceiveNum[0] = RowNum * Size;
    for (int i = 1; i < ProcNum; i++) {
        RestRows -= RowNum;
        RowNum = RestRows / (ProcNum - i);
        pReceiveNum[i] = RowNum * Size;
        pReceiveInd[i] = pReceiveInd[i - 1] + pReceiveNum[i - 1];
    }
    // Собрать всю матрицу по процессу с рангом 0
    MPI_Gatherv(pProcRows, pReceiveNum[ProcRank], MPI_INT, pMatrix, pReceiveNum, pReceiveInd, MPI_INT, 0, MPI_COMM_WORLD);
    // Свободная выделенная память
    delete[]pReceiveNum;
    delete[]pReceiveInd;
}
// Параллельный алгоритм Флойда
void ParallelFloyd(int* pProcRows, int Size, int RowNum) {
    int* pRow = new int[Size];
    int t1, t2;
    for (int k = 0; k < Size; k++) {
        // Распределить строку между всеми процессами
        RowDistribution(pProcRows, Size, RowNum, k, pRow);
        /*
        if (ProcRank == 0) {
            printf("Row %d after distribution:", k);
            PrintMatrix(pRow, Size, 1);
        }
        */
        // Обновить элементы матрицы смежности
        for (int i = 0; i < RowNum; i++)
            for (int j = 0; j < Size; j++)
                if ((pProcRows[i * Size + k] != -1) &&
                    (pRow[j] != -1)) {
                    t1 = pProcRows[i * Size + j];
                    t2 = pProcRows[i * Size + k] + pRow[j];
                    pProcRows[i * Size + j] = Min(t1, t2);
                }
    }

    delete[]pRow;
}
// Функция рассылки строк между всеми процессами
void RowDistribution(int* pProcRows, int Size, int RowNum, int k, int* pRow) {
    int ProcRowRank; // Ранг процесса со строкой k
    int ProcRowNum; // Номер строки процесса
    // Нахождение ранга процесса по строке k
    int RestRows = Size;
    int Ind = 0;
    int Num = Size / ProcNum;

    for (ProcRowRank = 1; ProcRowRank < ProcNum + 1; ProcRowRank++) {
        if (k < Ind + Num) break;
        RestRows -= Num;
        Ind += Num;
        Num = RestRows / (ProcNum - ProcRowRank);
    }
    ProcRowRank = ProcRowRank - 1;
    ProcRowNum = k - Ind;
    if (ProcRowRank == ProcRank)
        // Скопируйте строку в массив pRow
        copy(&pProcRows[ProcRowNum * Size], &pProcRows[(ProcRowNum + 1) * Size], pRow);
    // Передать строку всем процессам
    MPI_Bcast(pRow, Size, MPI_INT, ProcRowRank, MPI_COMM_WORLD);
}
// Функция для форматированного вывода всех полос
void ParallelPrintMatrix(int* pProcRows, int Size, int RowNum) {
    for (int i = 0; i < ProcNum; i++) {
        MPI_Barrier(MPI_COMM_WORLD);
        if (ProcRank == i) {
            printf("ProcRank = %d\n", ProcRank);
            fflush(stdout);
            printf("Proc rows:\n");
            fflush(stdout);
            PrintMatrix(pProcRows, RowNum, Size);
            fflush(stdout);
        }
        MPI_Barrier(MPI_COMM_WORLD);
    }
}
// Функция для вывода форматированной матрицы
void PrintMatrix(int* pMatrix, int RowCount, int ColCount) {
    for (int i = 0; i < RowCount; i++) {
        for (int j = 0; j < ColCount; j++) {
            printf("%7d", pMatrix[i * ColCount + j]);
            fflush(stdout);
        }
        printf("\n");
        fflush(stdout);
    }
}