//
// Created by buraindo on 21.12.18.
//
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
    freopen("count.in", "r", stdin);
    freopen("count.out", "w", stdout);
    string s;
    cin >> s;
    int n = static_cast<int>(s.length() + 1);
    vector<int> chars(static_cast<unsigned long>(n));
    for (int i = 0; i < n; i++) {
        chars[i] = s[i];
    }
    vector<int> cnt(MAX);
    for (int i = 0; i < n; i++) {
        cnt[chars[i]]++;
    }
    int k = max(n, (int) MAX);
    vector<int> count(static_cast<unsigned long>(k));
    vector<int> countCopy(static_cast<unsigned long>(k));
    for (int i = 1; i < MAX; i++)
    {
        count[i] = count[i - 1] + cnt[i - 1];
        countCopy[i] = count[i];
    }
    vector<int> suffixArray(static_cast<unsigned long>(n));
    for (int i = 0; i < n; i++) {
        suffixArray[countCopy[chars[i]]++] = i;
    }
    vector<int> suffixArrayCopy(static_cast<unsigned long>(n));
    vector<int> classesCopy(static_cast<unsigned long>(n));
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
    cout << endl;
    vector<int> lcp(static_cast<unsigned long>(n));
    buildLCP(s + '$', suffixArray, lcp);
    ll ans = 0;
    ll sum = 0;
    for (int i = 1; i < n; ++i)
    {
        ans += n - suffixArray[i] - 1;
        sum += (i == n - 1 ? 0 : lcp[i]);
    }
    cout << ans - sum;
    return 0;
}