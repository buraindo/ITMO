//
// Created by Buraindo on 12.10.2018.
//
#include <iostream>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

const int WHITE = 228;
const int GREY = 1337;
const int BLACK = 322;
const int MAX = 100000;

bool hasCycle = false;
int color[MAX];
bool used[MAX];

vector<vector<int>> graph;
vector<int> ans;

void dfs2(int v) {
    color[v] = GREY;
    for (int u : graph[v]) {
        if (color[u] == WHITE) {
            dfs2(u);
        } else
        if (color[u] == GREY) {
            hasCycle = true;
        }
    }
    color[v] = BLACK;
}

void checkCycle(int N) {
    for (int v = 0; v < N; v++) {
        if (!used[v]) {
            dfs2(v);
        }
    }
}

void dfs(int v) {
    used[v] = true;
    for (int u : graph[v]) {
        if (!used[u]) {
            dfs(u);
        }
    }
    ans.push_back(v);
}

void topSort(int N) {
    for (int v = 0; v < N; v++) {
        if (!used[v]) {
            dfs(v);
        }
    }
    reverse(begin(ans), end(ans));
}

int main() {
    int N, M;
    cin >> N >> M;
    graph.resize(static_cast<unsigned long>(N));
    for (int i = 0; i < N; i++) {
        color[i] = WHITE;
        used[i] = false;
    }
    for (int i = 0; i < M; i++) {
        int u, v;
        cin >> u >> v;
        u--, v--;
        graph[u].push_back(v);
    }
    checkCycle(N);
    if (hasCycle) {
        cout << -1;
        return 0;
    }
    topSort(N);
    for (int v : ans) {
        cout << v + 1 << " ";
    }
    return 0;
}