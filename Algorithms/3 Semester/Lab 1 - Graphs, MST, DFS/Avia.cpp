//
// Created by Buraindo on 13.10.2018.
//
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Edge {
    int u, v, w;

    Edge(int u, int v, int w) : u(u), v(v), w(w) {}
};

const int MAX_N = 1000;

bool used[MAX_N];
vector<Edge> Graph;

void dfs(int v, const vector<vector<int>> &g) {
    used[v] = true;
    for (const auto &u : g[v])
        if (!used[u])
            dfs(u, g);
}

bool check(int middle, int n) {
    vector<vector<int>> graph, reversedGraph;
    graph.resize(static_cast<unsigned long>(n));
    reversedGraph.resize(static_cast<unsigned long>(n));

    for (int i = 0; i < middle; i++) {
        graph[Graph[i].u].push_back(Graph[i].v);
        reversedGraph[Graph[i].v].push_back(Graph[i].u);
    }

    for (int i = 0; i < n; i++) {
        used[i] = false;
    }
    dfs(0, graph);
    for (int v = 0; v < n; v++) {
        if (!used[v]) {
            return false;
        }
    }
    for (int i = 0; i < n; i++) {
        used[i] = false;
    }
    dfs(0, reversedGraph);
    for (int v = 0; v < n; v++) {
        if (!used[v]) {
            return false;
        }
    }
    return true;
}

bool compare(const Edge &lhs, const Edge &rhs) {
    return lhs.w < rhs.w;
}

int main() {
    //freopen("lab2-1/InputFiles/avia.in", "r", stdin);
    //freopen("lab2-1/OutputFiles/avia.out", "w", stdout);
    freopen("avia.in", "r", stdin);
    freopen("avia.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    int n;
    cin >> n;
    if (n == 1) {
        cout << 0;
        return 0;
    }
    for (int u = 0; u < n; u++) {
        for (int v = 0; v < n; v++) {
            int w;
            cin >> w;
            if (u != v) {
                Graph.emplace_back(u, v, w);
            }
        }
    }
    sort(Graph.begin(), Graph.end(), compare);
    int l = -1, r = n * (n - 1);
    while (r - l > 1) {
        int mid = l + (r - l) / 2;
        if (check(mid, n))
            r = mid;
        else
            l = mid;
    }
    cout << Graph[l].w;
    return 0;
}