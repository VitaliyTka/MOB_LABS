#include <cstdlib>
#include <iostream>
#include <cstdio>
#include <ctime>
#include <algorithm>
#include <omp.h>


using namespace std;
const double InfinitiesPercent = 50.0;
const double RandomDataMultiplier = 10;

double Min(double A, double B) {
    double Result = (A < B) ? A : B;
    if ((A < 0) && (B >= 0)) Result = B;
    if ((B < 0) && (A >= 0)) Result = A;
    if ((A < 0) && (B < 0)) Result = -1;
    return Result;
}
void ProcessInitialization(double*& pMatrix, int& Size);
void ParallelFloyd(double* pMatrix, int Size);
void ProcessTermination(double* pMatrix);
void DummyDataInitialization(double* pMatrix, int Size);
void RandomDataInitialization(double* pMatrix, int Size);


#define THREAD_NUM 12

int main(int argc, char* argv[])
{
    omp_set_num_threads(THREAD_NUM);
    double* pMatrix; // Матрица смежности
    int Size; // Количество вершин
    time_t start, finish;
    double duration = 0.0;
    // Иницализация данных
    ProcessInitialization(pMatrix, Size);
    // Выполнение параллельного алгоритма Флойда
    start = clock();
    ParallelFloyd(pMatrix, Size);
    finish = clock();
    // Завершение вычислений
    ProcessTermination(pMatrix);
    duration = (finish - start) / double(CLOCKS_PER_SEC);
    printf("Time of execution: %f\n", duration);
    return 0;
}
// Функция выделения памяти и инициализации данных
void ProcessInitialization(double*& pMatrix, int& Size) {
    do {
        printf("Enter the number of vertices: ");
        scanf_s("%d", &Size);
        if (Size <= 2)
            printf("The number of vertices should be greater then zero\n");
    } while (Size <= 2);
    printf("Using graph with %d vertices\n", Size);
    // Выделение памяти для матрицы смежности
    pMatrix = new double[Size * Size];
    // Инициализация данных
    RandomDataInitialization(pMatrix, Size);
}
// Функция для параллельного алгоритма Флойда
void ParallelFloyd(double* pMatrix, int Size) {
    double t1, t2;
    for (int k = 0; k < Size; k++)
#pragma omp parallel for private (t1, t2)
        for (int i = 0; i < Size; i++)
            for (int j = 0; j < Size; j++)
                if ((pMatrix[i * Size + k] != -1) &&
                    (pMatrix[k * Size + j] != -1)) {
                    t1 = pMatrix[i * Size + j];
                    t2 = pMatrix[i * Size + k] +
                        pMatrix[k * Size + j];
                    pMatrix[i * Size + j] = Min(t1, t2);
                }
}
// Функция завершения вычислительного процесса
void ProcessTermination(double* pMatrix) {
    delete[]pMatrix;
}
// Функция для простой настройки исходных данных
void DummyDataInitialization(double* pMatrix, int Size) {
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
void RandomDataInitialization(double* pMatrix, int Size) {
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