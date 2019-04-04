//
// Created by Buraindo on 12.10.2018.
//
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int MAX_N = 20000;
bool used[MAX_N];
int currentTime = 0;
int enter[MAX_N];
int low[MAX_N];
bool isCutVertice[MAX_N];

vector<vector<int>> graph;

void dfs(int v, int parent = -1) {
    enter[v] = low[v] = ++currentTime;
    used[v] = true;
    int childrenCount = 0;
    for (int u : graph[v]) {
        if (u == parent) continue;
        if (used[u]) {
            low[v] = min(low[v], enter[u]);
        } else {
            dfs(u, v);
            childrenCount++;
            low[v] = min(low[v], low[u]);
            if (low[u] >= enter[v] && parent != -1) {
                isCutVertice[v] = true;
            }
            if (parent == -1 && childrenCount > 1) {
                isCutVertice[v] = true;
            }
        }
    }
}

void determineCutVertices(int N) {
    for (int v = 0; v < N; ++v) {
        if (!used[v]) {
            dfs(v);
        }
    }
}

int main() {
    int N, M;
    cin >> N >> M;
    graph.resize(static_cast<unsigned long>(N));
    for (int i = 0; i < N; i++) {
        used[i] = false;
        low[i] = 0;
        enter[i] = 0;
    }
    for (int i = 0; i < M; i++) {
        int u, v;
        cin >> u >> v;
        u--, v--;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }
    determineCutVertices(N);
    int cnt = 0;
    for (int i = 0; i < N; i++) {
        if (isCutVertice[i]) cnt++;
    }
    cout << cnt << endl;
    for (int i = 0; i < N; i++) {
        if (isCutVertice[i]) cout << i + 1 << " ";
    }
    return 0;
}