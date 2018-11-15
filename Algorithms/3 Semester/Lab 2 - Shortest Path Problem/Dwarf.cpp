#include <fstream>
#include <vector>
#include <queue>

using namespace std;

typedef long long ll;
typedef pair<ll, ll> mPair;
vector<vector<mPair>> graph;

void findShortestPaths(ll n, vector<ll>& dists) {
	queue<int> q;
	vector<bool> used(n + 1);
	for (int i = 1; i <= n; i++) {
		q.push(i);
	}
	while (!q.empty())
	{
		int current = q.front();
		q.pop();
		used[current] = true;
		for (int i = 0; i < (int)graph[current].size(); i++)
		{
			int v = graph[current][i].first;
			int u = graph[current][i].second;
			if (dists[u] > dists[v] + dists[current])
			{
				dists[u] = dists[current] + dists[v];
				if (used[u]) {
					used[u] = false;
					q.push(u);
				}
			}
		}
	}
}

int main()
{
	ifstream reader("dwarf.in");
	ofstream writer("dwarf.out");
	int n, m;
	reader >> n >> m;
	graph.resize(n + 1);
	vector<ll> dists(n + 1);
	for (int i = 1; i <= n; i++) {
		reader >> dists[i];
	}
	for (int i = 0; i < m; i++)
	{
		int a, x, y;
		reader >> a >> x >> y;
		graph[x].emplace_back(y, a);
		graph[y].emplace_back(x, a);
	}
	findShortestPaths(n, dists);
	writer << dists[1] << endl;
}