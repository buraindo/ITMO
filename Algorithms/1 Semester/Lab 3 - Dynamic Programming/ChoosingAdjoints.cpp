/*Дан граф, являющийся деревом. Множество вершин графа называется допустимым, если ни-
какие две вершины этого множества не соединены ребром.
Рассмотрим все допустимые множества вершин графа. Для каждого такого множества посчитаем
количество вершин в нём. Каково максимальное из этих количеств?*/
#include <utility>
#include <vector>
#include <fstream>
#include <algorithm>
#include <list>
#include <iostream>
#include <sstream>
 
using namespace std;
vector<int> Cash;
int size = 0;
 
int Calculate(int root, vector<int>* childrenList) {
    if (Cash[root] != -1)
        return Cash[root];
    int maximumForChildren = 0;
    int maximumForGrandChildren = 0;
    for (int i = 0; i != childrenList[root].size(); i++) {
        maximumForChildren += Calculate(childrenList[root][i], childrenList);
    }
    for (int i = 0; i != childrenList[root].size(); i++) {
        for (int j = 0; j != childrenList[childrenList[root][i]].size(); j++) {
            maximumForGrandChildren += Calculate(childrenList[childrenList[root][i]][j], childrenList);
        }
    }
    int result;
    if (maximumForChildren > maximumForGrandChildren + 1) {
        result = maximumForChildren;
    } else {
        result = maximumForGrandChildren + 1;
    }
    Cash[root] = result;
    return result;
}
 
int main() {
    int n;
    cin >> n;
    int root = -1;
    for (int i = 0; i != n + 1; i++)
        Cash.push_back(-1);
    vector<int> ancestors [n+1];
    for (int i = 1; i != n + 1; i++) {
        int ancestor;
        cin >> ancestor;
        if (ancestor == 0)
            root = i;
        else
            ancestors[ancestor].push_back(i);
    }
    int result = Calculate(root, ancestors);
    cout << result;
    return 0;
}