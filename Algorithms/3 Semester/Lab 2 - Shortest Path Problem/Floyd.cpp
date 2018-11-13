#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const long long INF = INT64_MAX / 2;

int main() {
	int n;
	cin >> n;
	long long dists[100][100];

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			long long w;
			cin >> w;
			dists[i][j] = w;
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < n; k++) {
				dists[j][k] = min(dists[j][k], dists[j][i] + dists[i][k]);
			}
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << dists[i][j] << " ";
		}
		cout << endl;
	}
	return 0;
}