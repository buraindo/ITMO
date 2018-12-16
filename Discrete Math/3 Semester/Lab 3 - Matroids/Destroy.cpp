//
// Created by buraindo on 10.12.18.
//
#include <iostream>
#include <algorithm>
#include <vector>
#include <set>

using namespace std;

typedef long long ll;

struct edge {
    int u, v, id;
    ll w;
    edge() = default;
    edge(int u, int v, int id, ll w) : u(u), v(v), id(id), w(w) {}
};

bool compare(const edge &x, const edge &y) {
    return x.w > y.w;
}

vector<int> parent;

int findSet(int v) {
    if (parent[v] != v) {
        parent[v] = findSet(parent[v]);
    }
    return parent[v];
}

void unite(int u, int v) {
    u = findSet(u);
    v = findSet(v);
    if (u == v) {
        return;
    }
    parent[u] = v;
}

int main() {
    freopen("destroy.in", "r", stdin);
    freopen("destroy.out", "w", stdout);
    int n, m;
    ll s;
    cin >> n >> m >> s;
    vector<edge> edges;
    for (int i = 0; i < m; i++) {
        int u, v;
        ll w;
        cin >> u >> v >> w;
        edges.emplace_back(u - 1, v - 1, i, w);
    }
    sort(edges.begin(), edges.end(), compare);
    for (int i = 0; i < n; i++) {
        parent.push_back(i);
    }
    vector<bool> inTree(static_cast<unsigned long>(m), false);
    for (int i = 0; i < m; i++) {
        if (findSet(edges[i].u) != findSet(edges[i].v)) {
            unite(edges[i].u, edges[i].v);
            inTree[edges[i].id] = true;
        }
    }
    ll sum = 0;
    vector<int> edgesToDestroy;
    reverse(edges.begin(), edges.end());
    for (int i = 0; i < m && sum <= s; i++) {
        if (!inTree[edges[i].id]) {
            sum += edges[i].w;
            if (sum <= s) {
                edgesToDestroy.push_back(edges[i].id);
            }
        }
    }
    cout << edgesToDestroy.size() << endl;
    sort(edgesToDestroy.begin(), edgesToDestroy.end());
    for (auto& edgeId : edgesToDestroy) {
        cout << edgeId + 1 << " ";
    }
    return 0;
}