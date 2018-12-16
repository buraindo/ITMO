//
// Created by buraindo on 16.12.18.
//
#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

typedef long long ll;

void checkFirstAxiom(const vector<bool> &used) {
    if (!used[0]) {
        cout << "NO";
        exit(0);
    }
}

void checkSecondAxiom(int i, int j, const vector<bool> &used) {
    if ((i | j) == i && !used[j]) {
        cout << "NO";
        exit(0);
    }
}

void checkThirdAxiom(int i, int j, int n, const vector<int>& powers, const vector<bool>& used) {
    if (used[j] && powers[i] > powers[j]) {
        int foundX = false;
        for (int k = 0; k < n; k++) {
            int x = 1 << k;
            if ((i & x) != 0 && (j & x) == 0 && used[j | x]) {
                foundX = true;
            }
        }
        if (!foundX) {
            cout << "NO";
            exit(0);
        }
    }
}

int main() {
    freopen("check.in", "r", stdin);
    freopen("check.out", "w", stdout);
    int n, m;
    cin >> n >> m;
    ll allXCount = 1 << n;
    vector<bool> used(static_cast<unsigned long>(allXCount), false);
    vector<int> powers(static_cast<unsigned long>(allXCount), 0);
    for (int i = 0; i < m; i++) {
        int mask = 0;
        int size;
        cin >> size;
        for (int j = 0; j < size; j++) {
            int id;
            cin >> id;
            id--;
            mask |= (1 << id);
        }
        used[mask] = true;
        powers[mask] = size;
    }
    checkFirstAxiom(used);
    for (int i = 0; i < allXCount; i++) {
        if (used[i]) {
            for (int j = 0; j < allXCount; j++) {
                checkSecondAxiom(i, j, used);
                checkThirdAxiom(i, j, n, powers, used);
            }
        }
    }
    cout << "YES";
    return 0;
}
