/*Во входном файле задано число n (2 ≤ n ≤ 40). Выведите в выходной файл все разбиения
числа n на слагаемые по одному в строке. Слагаемые следует выводить в возрастающем порядке.
Разбиения отличающиеся только порядком слагаемых считаются одинаковыми.*/
﻿#include <utility>
#include <vector>
#include <fstream>
#include <algorithm>
#include <map>
#include <iostream>
 
using namespace std;

vector<string> combinations;
 
void Calculate(int x, int k, vector<int> s) {
    if (x == 0) {
        string str;
        for (int i = (int) s.size() - 1; i != 0; i--) {
            str += to_string(s[i]) + "+";
        }
        str += to_string(s[0]);
        combinations.push_back(str);
    } else
        for (int i = 1; i != min(x, k) + 1; i++) {
            vector<int> vec = s;
            vec.push_back(i);
            Calculate(x - i, i, vec);
        }
}
 
 
int main() {
    ifstream reader("partition.in");
    ofstream writer("partition.out");
    int s;
    reader >> s;
    vector<int> combination;
    Calculate(s, s, combination);
    sort(combinations.begin(), combinations.end());
    for (auto comb : combinations) {
        writer << comb << "\n";
    }
    writer.close();
    return 0;
}