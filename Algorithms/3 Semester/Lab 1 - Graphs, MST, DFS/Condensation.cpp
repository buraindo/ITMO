//
// Created by Buraindo on 13.10.2018.
//
#include <iostream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

const int MAX_N = 10000;
bool used[MAX_N];
vector<int> traversal;
int component[MAX_N];
int componentCount;
set<string> edges;
vector<pair<int,int>> allEdges;

vector<vector<int>> graph;
vector<vector<int>> invertedGraph;

void dfs1(int v) {
    used[v] = true;
    for (int u : graph[v]) {
        if (!used[u]) {
            dfs1(u);
        }
    }
    traversal.push_back(v);
}

void dfs2(int v) {
    component[v] = componentCount;
    for (int u : invertedGraph[v]) {
        if (component[u] == -1) {
            dfs2(u);
        }
    }
}

void determineComponents(int N) {
    for (int v = 0; v < N; ++v) {
        if (!used[v]) {
            dfs1(v);
        }
    }
    componentCount = 0;
    reverse(traversal.begin(), traversal.end());
    for(int v : traversal) {
        if (component[v] == -1) {
            dfs2(v);
            componentCount++;
        }
    }
}

int main() {
    int N, M;
    cin >> N >> M;
    graph.resize(static_cast<unsigned long>(N));
    invertedGraph.resize(static_cast<unsigned long>(N));
    for (int i = 0; i < N; i++) {
        used[i] = false;
        component[i] = -1;
    }
    for (int i = 0; i < M; i++) {
        int u, v;
        cin >> u >> v;
        u--, v--;
        graph[u].push_back(v);
        invertedGraph[v].push_back(u);
        allEdges.emplace_back(u, v);
    }
    determineComponents(N);
    for (int i = 0; i < M; i++) {
        int u = allEdges[i].first;
        int v = allEdges[i].second;
        if (component[u] != component[v]) {
            edges.insert(to_string(component[u]) + "/" + to_string(component[v]));
        }
    }
    cout << edges.size();
    return 0;
}