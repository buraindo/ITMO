/*Рассмотрим все разбиения числа n на слагаемые, в каждом разбиении упорядочим их в порядке
не убывания. Будем считать, что разбиение a1+a2+...+an лексикографически меньше b1+b2+...+bm,
если для некоторого k ∀j ≤ k : aj = bj и либо k = n, либо a_k+1 < b_k+1. Во входном файле заданы
числа n и r. 1 ≤ n ≤ 100, разбиение с номером r — существует. Выведите r-ое разбиение числа n на
слагаемые, разбиения нумеруются с 0.*/
#include <utility>
#include <vector>
#include <fstream>
#include <algorithm>
#include <list>
#include <iostream>
 
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
    ifstream reader("num2part.in");
    ofstream writer("num2part.out");
    int s;
    int cnt;
    reader >> s >> cnt;
    vector<int> combination;
    for (int i = 0; i != s; i++)
        combination.push_back(1);
    for (int i = 0; i != cnt; i++) {
        nextPartition(combination);
    }
    k = SearchFwd(k);
    for (int elem = 0; elem != combination.size() - 1; elem++)
        writer << combination[elem] << "+";
    writer << combination[combination.size() - 1];
    writer.close();
    return 0;
}