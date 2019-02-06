//
// Created by Buraindo on 12.10.2018.
//
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

const int MAX_N = 20000;
bool used[MAX_N];
int currentTime = 0;
int enter[MAX_N];
int low[MAX_N];

vector<vector<int>> graph;
vector<int> ans;
map<string, int> indexOfEdge;

void dfs(int v, int parent = -1) {
    enter[v] = low[v] = ++currentTime;
    used[v] = true;
    for (int u : graph[v]) {
        if (u == parent) continue;
        if (used[u]) {
            low[v] = min(low[v], enter[u]);
        } else {
            dfs(u, v);
            low[v] = min(low[v], low[u]);
            if (low[u] > enter[v]) {
                int b = min(u,v);
                int e = max(u,v);
                ans.push_back(indexOfEdge[to_string(b) + " " + to_string(e)] + 1);
            }
        }

    }
}

void determineBridges(int N) {
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
        int b, e;
        b = min(u,v);
        e = max(u,v);
        indexOfEdge[to_string(b) + " " + to_string(e)] = i;
    }
    determineBridges(N);
    sort(ans.begin(), ans.end());
    cout << ans.size() << endl;
    for(int v : ans) {
        cout << v << endl;
    }
    return 0;
}