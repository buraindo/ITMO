/*Во входном файле заданы числа n, k и m. Выведите в выходной файл m-е в лексикографическом
порядке сочетание по k из чисел от 1 до n. Сочетания занумерованы, начиная с 0. 1 ≤ k ≤ n ≤ 30,
0 ≤ m ≤ C(n_k) - 1*/
#include <utility>
#include <vector>
#include <fstream>
#include <algorithm>
#include <map>
#include <iostream>
 
using namespace std;

vector<int64_t> factors;
 
int64_t Factor(int n) {
    if (n < factors.size())
        return factors[n];
    int64_t f = Factor(n-1)*n;
    factors.push_back(f);
    return f;
}
 
int64_t Choose(int n, int k) {
    if (k == 0) return 1;
    if (k == 1)
        return n;
    int64_t f = Factor(k);
    int64_t p = 1;
    for (int i = n - k + 1; i != n + 1; i++)
        p *= i;
    return p / f;
}
 
void Generator(int n, int k, int64_t m, ofstream &writer) {
    int index = 1;
    while (k > 0) {
        if (m < Choose(n - 1, k - 1)) {
            writer << index << " ";
            k--;
        } else {
            m -= Choose(n - 1, k - 1);
        }
        index++;
        n--;
    }
}
 
int main() {
    int n, k, m;
    factors.push_back(1);
    factors.push_back(1);
    ifstream reader("num2choose.in");
    ofstream writer("num2choose.out");
    reader >> n >> k >> m;
    Generator(n, k, m, writer);
    writer.close();
    return 0;
}