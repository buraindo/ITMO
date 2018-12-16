//
// Created by buraindo on 10.12.18.
//
#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

typedef long long ll;

typedef vector<vector<int>> Graph;

Graph graph;
vector<bool> used;
vector<int> matchingNumber;

bool dfs(int u) {
    if (used[u]) {
        return false;
    }
    used[u] = true;
    for (auto v : graph[u]) {
        if (matchingNumber[v] == -1 || dfs(matchingNumber[v])) {
            matchingNumber[u] = v;
            matchingNumber[v] = u;
            return true;
        }
    }
    return false;
}

int main() {
    freopen("matching.in", "r", stdin);
    freopen("matching.out", "w", stdout);
    int n;
    cin >> n;
    graph.resize(static_cast<unsigned long>(n * 2));
    vector<int> weights;
    for (int i = 0; i < n; i++) {
        int w;
        cin >> w;
        weights.push_back(w);
    }
    vector<int> ids;
    for (int i = 0; i < n; i++) {
        ids.push_back(i);
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n - 1; j++) {
            if (weights[ids[j]] < weights[ids[j + 1]]) {
                swap(ids[j], ids[j + 1]);
            }
        }
    }
    for (int i = 0; i < n; i++) {
        int count;
        cin >> count;
        for (int j = 0; j < count; j++) {
            int u;
            cin >> u;
            u += n - 1;
            graph[i].push_back(u);
            graph[u].push_back(i);
        }
    }
    matchingNumber.assign(2 * n, -1);
    for (int i = 0; i < n; i++) {
        used.assign(static_cast<unsigned long>(n), false);
        dfs(ids[i]);
    }
    for (int i = 0; i < n; i++) {
        cout << (matchingNumber[i] == -1 ? 0 : matchingNumber[i] - n + 1);
        cout << " ";
    }
    return 0;
}
