//
// Created by Buraindo on 13.10.2018.
//
#include <iostream>
#include <vector>
#include <algorithm>
#include <random>

using namespace std;

const int MAX = 200000;

std::random_device rd;
std::mt19937 mt(rd());
std::uniform_real_distribution<double> dist(1.0, MAX);

vector<pair<int, pair<int, int>>> graph;
vector<int> parent;

int find(int v) {
    return v == parent[v] ? v : (parent[v] = find(parent[v]));
}

void unite(int u, int v) {
    u = find(u);
    v = find(v);
    if ((int) dist(mt) & 1) {
        swap(u, v);
    }
    if (u != v) {
        parent[u] = v;
    }
}

int main() {
    int N, M;
    cin >> N >> M;
    parent.resize(static_cast<unsigned long>(N));
    for (int i = 0; i < M; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        u--, v--;
        graph.emplace_back(w, make_pair(u, v));
    }
    sort(graph.begin(), graph.end());
    long long ans = 0;
    for (int i = 0; i < N; ++i) {
        parent[i] = i;
    }
    for (int i = 0; i < M; ++i) {
        int w = graph[i].first;
        int u = graph[i].second.first;
        int v = graph[i].second.second;
        if (find(u) != find(v)) {
            ans += w;
            unite(u, v);
        }
    }
    cout << ans;
    return 0;
}