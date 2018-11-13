#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

typedef long long ll;
typedef pair<ll, ll> mPair;
const ll INF = 8000000000000000000ll;

vector<bool> used;
vector<vector<mPair>> graph;

void dfs(ll u) {
	used[u] = true;
	for (auto v : graph[u]) {
		if (!used[v.first]) {
			dfs(v.first);
		}
	}
}

void findShortestPaths(ll s, ll n, vector<ll>& dists) {
	for (ll i = 0; i < n; i++) {
		dists[i] = INF;
	}

	dists[s] = 0;
	priority_queue<pair<ll, ll>> q;
	q.emplace(0, s);

	while (!q.empty()) {
		ll u = q.top().second;
		ll dist = -q.top().first;
		q.pop();
		if (dist <= dists[u]) {
			for (auto e : graph[u]) {
				ll v = e.first;
				ll w = e.second;
				if (dists[v] > dists[u] + w)
				{
					dists[v] = dists[u] + w;
					q.emplace(-dists[v], v);
				}
			}
		}
	}
}

int main() {
	ll n, m;
	cin >> n >> m;
	graph.resize(static_cast<unsigned long>(n));
	used.resize(static_cast<unsigned long>(n));
	for (ll i = 0; i < m; i++) {
		ll u, v;
		ll w;
		cin >> u >> v >> w;
		u--, v--;
		graph[u].emplace_back(v, w);
		graph[v].emplace_back(u, w);
	}
	ll a, b, c;
	cin >> a >> b >> c;
	a--, b--, c--;
	dfs(a);
	if (!used[b] || !used[c]) {
		cout << -1;
		return 0;
	}
	for (int i = 0; i < n; i++) {
		used[i] = false;
	}
	dfs(b);
	if (!used[a] || !used[c]) {
		cout << -1;
		return 0;
	}
	for (int i = 0; i < n; i++) {
		used[i] = false;
	}
	dfs(c);
	if (!used[a] || !used[b]) {
		cout << -1;
		return 0;
	}
	vector<ll> distsA(n);
	vector<ll> distsB(n);
	vector<ll> distsC(n);
	findShortestPaths(a, n, distsA);
	findShortestPaths(b, n, distsB);
	findShortestPaths(c, n, distsC);
	ll aToB = distsA[b];
	ll aToC = distsA[c];
	ll bToC = distsB[c];
	ll bToA = distsB[a];
	ll cToA = distsC[a];
	ll cToB = distsC[b];
	vector<ll> paths = { aToB + bToC, aToC + cToB, bToA + aToC, bToC + cToA, cToA + aToB, cToB + bToA };
	sort(paths.begin(), paths.end());
	cout << paths[0];
	return 0;
}