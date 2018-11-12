/*Во входном файле задано число n. Выведите в выходной файл все подмножества множества
{1, 2, . . . , n} в лексикографическом порядке. 1 ≤ n ≤ 10.*/
﻿#include <utility>
#include <vector>
#include <fstream>
#include <algorithm>
#include <map>
#include <iostream>
 
using namespace std;
 
vector<string> combinations;

void Calculate(int x, int n, vector<int> s) {
    for (int i = x; i != n + 1; i++) {
        vector<int> vec = s;
        vec.push_back(i);
        string str;
        for (int k = 0; k != vec.size() - 1; k++) {
            str += to_string(vec[k]) + " ";
        }
        str += to_string(vec[vec.size() - 1]);
        combinations.push_back(str);
        Calculate(i+1, n, vec);
    }
}
 
 
int main() {
    ifstream reader("subsets.in");
    ofstream writer("subsets.out");
    int s;
    reader >> s;
    vector<int> combination;
    Calculate(1, s, combination);
    writer << "\n";
    for (const string& comb : combinations) {
        writer << comb << "\n";
    }
    writer.close();
    return 0;
}