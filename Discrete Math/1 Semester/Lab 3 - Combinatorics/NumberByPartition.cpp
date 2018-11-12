/*Рассмотрим все разбиения числа n на слагаемые, в каждом разбиении упорядочим их в порядке
не убывания. Будем считать, что разбиение a1+a2+...+an лексикографически меньше b1+b2+...+bm,
если для некоторого k ∀j ≤ k : aj = bj и либо k = n, либо a_k+1 < b_k+1. Во входном файле задано
разбиение на слагаемые. Выведите номер этого разбиения, среди всех разбиений упорядоченных
лексикографически. Разбиения нумеруются с 0. Гарантируется, что в разбиении слагаемые упоря-
дочены в порядке не убывания, и 1 ≤ n ≤ 100.*/
#include <utility>
#include <vector>
#include <fstream>
#include <algorithm>
#include <list>
#include <iostream>
#include <sstream>
 
using namespace std;

void nextPartition(vector<int> &partition) {
    partition[partition.size() - 1]--;
    partition[partition.size() - 2]++;
    if (partition[partition.size() - 2] > partition[partition.size() - 1]) {
        partition[partition.size() - 2] += partition[partition.size() - 1];
        partition.pop_back();
    } else {
        while (partition[partition.size() - 2] * 2 <= partition[partition.size() - 1]) {
            partition.push_back(partition[partition.size() - 1] - partition[partition.size() - 2]);
            partition[partition.size() - 2] = partition[partition.size() - 3];
        }
    }
}
 
int main() {
    ifstream reader("part2num.in");
    ofstream writer("part2num.out");
    string s;
    vector<int> init;
    reader >> s;
    stringstream ss(s);
    int cnt = 0;
    int cc= 0;
    int num;
    while (ss >> num) {
        ss.ignore();
        init.push_back(num);
        cnt += num;
    }
    vector<int> combination;
    for (int i = 0; i != cnt; i++)
        combination.push_back(1);
    int count = 0;
    while (init != combination) {
        nextPartition(combination);
        count++;
    }
    writer << count;
    writer.close();
    return 0;
}