#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int INF = INT32_MAX / 2;

int main() {
	vector<vector<pair<int, int>>> graph;
	int n, m;
	cin >> n >> m;
	graph.resize(n);
	vector<int> dists(n);

	for (int i = 0; i < m; i++) {
		int u, v, w;
		cin >> u >> v >> w;
		u--, v--;
		graph[u].emplace_back(v, w);
		graph[v].emplace_back(u, w);
	}

	for (int i = 0; i < n; i++) {
		dists[i] = INF;
	}

	dists[0] = 0;
	priority_queue<pair<int,int>> q;
	q.emplace(0, 0);

	while (!q.empty()) {
		int u = q.top().second;
		int dist = -q.top().first;
		q.pop();
		if (dist <= dists[u]) {
			for (auto e : graph[u]) {
				int v = e.first;
				int w = e.second;
				if (dists[v] > dists[u] + w)
				{
					dists[v] = dists[u] + w;
					q.emplace(-dists[v], v);
				}
			}
		}
	}

	for (int i = 0; i < n; i++) {
		cout << dists[i] << " ";
	}
	return 0;
}