/*Во входном файле задано число n. Выведите в выходной файл все правильные скобочные после-
довательности с n открывающимися скобками в лексикографическом порядке, «(» < «)». 1 ≤ n ≤ 10.*/
﻿#include <utility>
#include <vector>
#include <fstream>
#include <algorithm>
#include <map>
#include <iostream>
 
using namespace std;
 
int64_t Catalan(int n) {
    int64_t i = 1;
    int nfactor = 1;
    int64_t n2factor = 1;
    for (int k = 2; k != n + 1; k++)
        nfactor *= k;
    for (int k = n + 1; k != 2 * n + 1; k++)
        n2factor *= k;
    i = n2factor / ((n + 1) * nfactor);
    return i;
}
 
int main() {
    ifstream reader("brackets.in");
    ofstream writer("brackets.out");
    int s;
    reader >> s;
    string first;
    vector<string> combinations;
    for (int i = 0; i != s; i++)
        first += '(';
    for (int i = 0; i != s; i++)
        first += ')';
    combinations.push_back(first);
    int64_t catalan = Catalan(s);
    for (int i = 1; i != catalan; i++) {
        string str = combinations[i - 1];
        int localIndex = (int) str.length();
        auto lastIndex = str.rfind("()");
        if ((int) str.length() - (int) lastIndex - 2 == 0) {
            localIndex -= 2;
            int openBrackets = 2;
            lastIndex = str.rfind("()", lastIndex - 1);
            while (localIndex - (int) lastIndex - 2 == 0) {
                lastIndex = str.rfind("()", lastIndex - 1);
                localIndex -= 2;
                openBrackets++;
            }
            int closeBrackets = (int) str.length() - (int) lastIndex - openBrackets - 1;
            str = str.substr(0, lastIndex) + ")" + string(openBrackets, '(') + string(closeBrackets, ')');
        } else {
            int openBrackets = 1;
            int closeBrackets = (int) str.length() - (int) lastIndex - openBrackets - 1;
            str = str.substr(0, lastIndex) + ")" + string(openBrackets, '(') + string(closeBrackets, ')');
        }
        combinations.push_back(str);
    }
    for (const string &str : combinations)
        writer << str << "\n";
    writer.close();
    return 0;
}