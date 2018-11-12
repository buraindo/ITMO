/*Во входном файле задано числа n и k. Выведите в выходной файл k-ю в лексикографическом
порядке перестановку чисел от 1 до n. Перестановки занумерованы от 0 до n! − 1. 1 ≤ n ≤ 18,
0 ≤ k ≤ n! − 1*/
#include <utility>
#include <vector>
#include <fstream>
#include <algorithm>
#include <map>
#include <iostream>
 
using namespace std;

vector<bool> usedOnes(18, false);
 
int64_t Factor(int n) {
    if (n == 0 || n == 1) return 1;
    return Factor(n - 1) * n;
}
 
vector<int> Generator(int n, int64_t k) {
    vector<int> combination(n);
    for (int i = 0; i != n; i++) {
        int64_t factorN = Factor(n - i - 1);
        int64_t usedAmount = k / factorN;
        k %= factorN;
        int nonUsedAmount = 0;
        for (int q = 0; q != n; q++) {
            if (!usedOnes[q]) {
                nonUsedAmount++;
                if (nonUsedAmount == usedAmount + 1) {
                    combination[i] = q + 1;
                    usedOnes[q] = true;
                }
            }
        }
    }
    return combination;
}
 
int main() {
    int n;
    int64_t k;
    ifstream reader("num2perm.in");
    ofstream writer("num2perm.out");
    reader >> n >> k;
    vector<int> combination = Generator(n, k);
    for (int elem : combination)
        writer << elem << " ";
    writer.close();
    return 0;
}