#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct edge {
	int u, v;
	long long w;
	edge(int u, int v, long long w) : u(u), v(v), w(w) {}
};

bool used[10000];
bool used2[10000];
vector<edge> edges;
vector<vector<int>> graph;
const long long INF = 8000000000000000000ll;

void dfs(int u) {
	used[u] = true;
	for (int v : graph[u]) {
		if (!used[v]) {
			dfs(v);
		}
	}
}

void dfs2(int u) {
	used2[u] = true;
	for (int v : graph[u]) {
		if (!used2[v]) {
			dfs2(v);
		}
	}
}

int main() {
	int n, m, s;
	cin >> n >> m >> s;
	s--;
	graph.resize(n);
	for (int i = 0; i < m; i++) {
		int u, v;
		long long w;
		cin >> u >> v >> w;
		u--; v--;
		graph[u].push_back(v);
		edges.emplace_back(u, v, w);
	}

	for (int i = 0; i < n; i++) {
		used[i] = false;
		used2[i] = false;
	}

	dfs(s);

	vector<long long> dists(n, INF + 100000000000000000ll);
	dists[s] = 0;
	for (int i = 0; i < n; i++) {
		for (edge e : edges) {
			if (used[e.v] && dists[e.u] < INF && dists[e.v] > dists[e.u] + e.w) {
				dists[e.v] = max(-INF, dists[e.u] + e.w);
			}
		}
	}

	vector<long long> lastRelaxation(n);
	for (int i = 0; i < n; i++) {
		lastRelaxation[i] = dists[i];
	}

	for (edge e : edges) {
		if (used[e.v] && lastRelaxation[e.u] < INF && lastRelaxation[e.v] > lastRelaxation[e.u] + e.w) {
			lastRelaxation[e.v] = lastRelaxation[e.u] + e.w;
		}
	}

	for (int i = 0; i < n; i++) {
		if (used[i] && lastRelaxation[i] != dists[i]) {
			dfs2(i);
		}
	}

	for (int i = 0; i < n; i++) {
		if (dists[i] >= INF) {
			cout << "*";
		}
		else if (used2[i]) {
			cout << "-";
		}
		else {
			cout << dists[i];
		}
		cout << endl;
	}
	return 0;
}