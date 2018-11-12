/*Во входном файле задано число n и затем перестановка чисел от 1 до n. Выведите в выходной
файл номер заданной перестановки в лексикографическом порядке всех перестановок чисел от 1 до
n. Перестановки занумерованы, начиная с 0. 1 ≤ n ≤ 18.*/
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
int64_t Generator(vector<int> combination) {
    int64_t result = 0L;
    auto n = combination.size();
    bool isFalse = usedOnes[0];
    for (int i = 0; i != n; i++)
    {
        int64_t factorN = Factor(n - i - 1);
        int64_t usedAmount = n / factorN;
        int nonUsedAmount = 0;
        for (int w = 1; w != combination[i]; w++)
        {
            if (!usedOnes[w])
            {
                result += Factor(n - i - 1);
            }
        }
        usedOnes[combination[i]] = true;
    }
    return result;
}
 
int main() {
    int n;
    int64_t k;
    ifstream reader("perm2num.in");
    ofstream writer("perm2num.out");
    vector<int> combination;
    reader >> n;
    for (int i = 0; i != n; i++) {
        int m;
        reader >> m;
        combination.push_back(m);
    }
    auto permutations = Generator(combination);
    writer << permutations;
    writer.close();
    return 0;
}