//
// Created by Buraindo on 12.10.2018.
//
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int MAX_N = 20000;

bool used[MAX_N];
vector<bool> result;
int enter[MAX_N];
int component[MAX_N];
int componentCount = 0;

vector<vector<pair<int, int>>> graph;

int dfs(int v, int e, int depth) {
    int minimum = depth;
    enter[v] = depth;
    used[v] = true;
    for (auto &u : graph[v]) {
        if (!used[u.first]) {
            minimum = min(minimum, dfs(u.first, u.second, depth + 1));
        } else if (e != u.second) {
            minimum = min(minimum, enter[u.first]);
        }
    }
    if (minimum == depth && e != -1) {
        result[e - 1] = true;
    }
    return minimum;
}

void setComponentNumber(int v, int number) {
    component[v] = number;
    for (auto &u : graph[v]) {
        if (component[u.first] == 0) {
            if (result[u.second - 1]) {
                setComponentNumber(u.first, ++componentCount);
            } else {
                setComponentNumber(u.first, number);
            }
        }
    }
}

int main() {
    int N, M;
    cin >> N >> M;
    graph.resize(static_cast<unsigned long>(N));
    result.resize(static_cast<unsigned long>(M));
    for (int i = 0; i < N; ++i) {
        enter[i] = 0;
        used[i] = false;
        component[i] = 0;
    }
    for (int i = 0; i < M; i++) {
        int u, v;
        cin >> u >> v;
        u--, v--;
        graph[u].emplace_back(v, i + 1);
        graph[v].emplace_back(u, i + 1);
    }
    for (int i = 0; i < N; i++) {
        if (!used[i]) {
            dfs(i, -1, 0);
        }
    }
    for (int i = 0; i < N; i++) {
        if (component[i] == 0) {
            setComponentNumber(i, ++componentCount);
        }
    }
    cout << componentCount << endl;
    for (int i = 0; i < N; i++) {
        cout << component[i] << " ";
    }
}