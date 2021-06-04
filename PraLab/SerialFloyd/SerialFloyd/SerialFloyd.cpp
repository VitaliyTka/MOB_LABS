#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <ctime>
#include <algorithm> 

using namespace std;

const double InfinitiesPercent = 50.0;
const double RandomDataMultiplier = 10;

int Min(int A, int B) {
    int Result = (A < B) ? A : B;
    if ((A < 0) && (B >= 0)) Result = B;
    if ((B < 0) && (A >= 0)) Result = A;
    if ((A < 0) && (B < 0)) Result = -1;
    return Result;
}
void ProcessInitialization(int*& pMatrix, int& Size);
void ProcessTermination(int* pMatrix);
void DummyDataInitialization(int* pMatrix, int Size);
void RandomDataInitialization(int* pMatrix, int Size);
void SerialFloyd(int* pMatrix, int Size);
void PrintMatrix(int* pMatrix, int RowCount, int ColCount);

int main(int argc, char* argv[]) {
    int* pMatrix; // Матрица смежности
    int Size; // Размер матрицы смежности
    time_t start, finish;
    double duration = 0.0;
    printf("Serial Floyd algorithm\n");
    // Инициализация процесса
    ProcessInitialization(pMatrix, Size);
    printf("The matrix before Floyd algorithm\n");
    ///PrintMatrix(pMatrix, Size, Size);
    start = clock();
    // Параллельный алгоритм Флойда
    SerialFloyd(pMatrix, Size);
    finish = clock();
    printf("The matrix after Floyd algorithm\n");
   // PrintMatrix(pMatrix, Size, Size);
    duration = (finish - start) / double(CLOCKS_PER_SEC);

    printf("Time of execution: %f\n", duration);
    // Окончание обработки
    ProcessTermination(pMatrix);
    return 0;
}
// Функция выделения памяти и установки начальных значений
void ProcessInitialization(int*& pMatrix, int& Size) {
    do {
        printf("Enter the number of vertices: ");
        scanf_s("%d", &Size);
        if (Size <= 0)
            printf("The number of vertices should be greater then zero\n");
    } while (Size <= 0);
    printf("Using graph with %d vertices\n", Size);
    // Выделить память для матрицы смежности
    pMatrix = new int[Size * Size];
    // Инициализация данных
    //DummyDataInitialization(pMatrix, Size);
    RandomDataInitialization(pMatrix, Size);
}
// Функция завершения вычислительного процесса
void ProcessTermination(int* pMatrix) {
    delete[]pMatrix;
}
// Функция для простой инициализации исходных данных
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
// Функция инициализации данных генератором случайных чисел
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
// Последовательный алгоритм Флойда
void SerialFloyd(int* pMatrix, int Size) {
    int t1, t2;
    for (int k = 0; k < Size; k++)
        for (int i = 0; i < Size; i++)
            for (int j = 0; j < Size; j++)
                if ((pMatrix[i * Size + k] != -1) && (pMatrix[k * Size + j] != -1)) {
                    t1 = pMatrix[i * Size + j];
                    t2 = pMatrix[i * Size + k] + pMatrix[k * Size + j];
                    pMatrix[i * Size + j] = Min(t1, t2);
                }
}

// Функция для вывода форматированной матрицы
void PrintMatrix(int* pMatrix, int RowCount, int ColCount) {
    for (int i = 0; i < RowCount; i++) {
        for (int j = 0; j < ColCount; j++)
            printf("%7d", pMatrix[i * ColCount + j]);
        printf("\n");
    }
}
