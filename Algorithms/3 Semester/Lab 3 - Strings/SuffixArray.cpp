#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

typedef long long ll;

const ll MAX = 256;

void buildLCP(const string& s, const vector<int>& suffixArray, vector<int>& lcp) {
	int n = s.length();
	vector<int> pos(n);
	for (int i = 0; i < n; i++) {
		pos[suffixArray[i]] = i;
	}
	int k = 0;
	for (int i = 0; i < n; i++) {
		if (k > 0) {
			k--;
		}
		if (pos[i] == n - 1) {
			lcp[n - 1] = -1;
			k = 0;
		}
		else {
			int j = suffixArray[pos[i] + 1];
			while (max(i + k, j + k) < n && s[i + k] == s[j + k]) {
				k++;
			}
			lcp[pos[i]] = k;
		}
	}
}

int main()
{
	freopen("array.in", "r", stdin);
	freopen("array.out", "w", stdout);
	string s;
	cin >> s;
	int n = s.length() + 1;
	vector<int> chars(n);
	for (int i = 0; i < n; i++) {
		chars[i] = s[i];
	}
	vector<int> cnt(MAX);
	for (int i = 0; i < n; i++) {
		cnt[chars[i]]++;
	}
	int k = max(n, (int) MAX);
	vector<int> count(k);
	vector<int> countCopy(k);
	for (int i = 1; i < MAX; i++)
	{
		count[i] = count[i - 1] + cnt[i - 1];
		countCopy[i] = count[i];
	}
	vector<int> suffixArray(n);
	for (int i = 0; i < n; i++) {
		suffixArray[countCopy[chars[i]]++] = i;
	}
	vector<int> suffixArrayCopy(n);
	vector<int> classesCopy(n);
	for (int l = 1; l < n; l <<= 1) {
		for (int i = 0; i < n; i++) {
			int id = (suffixArray[i] - l + n) % n;
			suffixArrayCopy[count[chars[id]]++] = id;
		}
		int size = 0;
		for (int i = 0; i < n; i++) {
			if (i == 0 || chars[suffixArrayCopy[i]] != chars[suffixArrayCopy[i - 1]] || chars[(suffixArrayCopy[i] + l) % n] != chars[(suffixArrayCopy[i - 1] + l) % n]) {
				count[size++] = i;
			}
			classesCopy[suffixArrayCopy[i]] = size - 1;
		}
		suffixArray = suffixArrayCopy;
		chars = classesCopy;
	}
	for (int i = 1; i < n; i++) {
		cout << suffixArray[i] + 1 << " ";
	}
	cout << endl;
	vector<int> lcp(n);
	buildLCP(s + '$', suffixArray, lcp);
	for (int i = 1; i < n - 1; i++) {
		cout << lcp[i] << " ";
	}
	return 0;
}