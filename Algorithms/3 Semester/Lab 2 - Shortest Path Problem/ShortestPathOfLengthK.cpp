#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

typedef long long ll;

struct edge {
	int u, v;
	ll w;
	edge(int u, int v, ll w) : u(u), v(v), w(w) {}
};

vector<edge> edges;
vector<vector<int>> graph;
const ll INF = 8000000000000000000ll;

int main() {
	int n, m, k, s;
	cin >> n >> m >> k >> s;
	s--;
	graph.resize(n);
	for (int i = 0; i < m; i++) {
		int u, v;
		ll w;
		cin >> u >> v >> w;
		u--; v--;
		graph[u].push_back(v);
		edges.emplace_back(u, v, w);
	}

	vector<vector<ll>> dists(k + 1);
	for (int i = 0; i <= k; i++) {
		dists[i].assign(n, INF);
	}
	dists[0][s] = 0;
	for (int i = 1; i <= k; i++) {
		for (int j = 0; j < m; ++j) {
			if (dists[i - 1][edges[j].u] < INF) {
				//if (i == k) {
					dists[i][edges[j].v] = min(dists[i][edges[j].v], dists[i - 1][edges[j].u] + edges[j].w);
				//}
				//else {
				//	dists[i][edges[j].v] = dists[i - 1][edges[j].u] + edges[j].w;
				//}
			}
		}
	}

	for (int i = 0; i < n; i++) {
		if (dists[k][i] >= INF) {
			cout << -1;
		}
		else {
			cout << dists[k][i];
		}
		cout << endl;
	}
	int a;
	cin >> a;
	return 0;
}