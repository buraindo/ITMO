#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int NO_EDGE = 100000;

int main() {
	int n;
	cin >> n;
	vector<std::vector<int>> graph(n);
	for (int i = 0; i < n; i++) {
		graph[i].resize(n);
		for (int j = 0; j < n; j++) {
			cin >> graph[i][j];
		}
	}
	vector<long long> dists(n, 0);
	vector<int> parent(n, -1);

	for (int i = 0; i < n; i++) {
		for (int u = 0; u < n; u++) {
			for (int v = 0; v < n; v++) {
				if (graph[u][v] != NO_EDGE && dists[v] > dists[u] + graph[u][v]) {
					dists[v] = dists[u] + graph[u][v];
					parent[v] = u;
				}
			}
		}
	}

	bool found = false;
	vector<int> cycle;
	for (int u = 0; u < n; u++) {
		if (found) {
			break;
		}
		for (int v = 0; v < n; v++) {
			if (graph[u][v] != NO_EDGE && dists[v] > dists[u] + graph[u][v]) {
				for (int i = 0; i < n; i++) {
					v = parent[v];
				}
				u = v;
				cycle.push_back(v);
				while (u != parent[v]) {
					v = parent[v];
					cycle.push_back(v);
				}
				reverse(cycle.begin(), cycle.end());
				found = true;
				break;
			}
		}
	}

	if (cycle.empty()) {
		cout << "NO";
	}
	else {
		cout << "YES\n" << cycle.size() << endl;
		for (int v : cycle) {
			cout << v + 1 << " ";
		}
	}
	return 0;
}