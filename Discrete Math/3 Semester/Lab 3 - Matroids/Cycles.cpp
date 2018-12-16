//
// Created by buraindo on 16.12.18.
//
#include <iostream>
#include <algorithm>
#include <vector>
 
using namespace std;
 
typedef long long ll;
 
int main()
{
    freopen("cycles.in", "r", stdin);
    freopen("cycles.out", "w", stdout);
    int n, m;
    cin >> n >> m;
    ll allXCount = 1 << n;
    vector<bool> used(static_cast<unsigned long>(allXCount), false);
    vector<int> powers(static_cast<unsigned long>(allXCount), 0);
    vector<int> weights(static_cast<unsigned long>(n));
    for (int i = 0; i < n; i++) {
        cin >> weights[i];
    }
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
    ll result = 0L;
    for (int i = 0; i < allXCount; i++) {
        if (used[i]) {
            for (int j = 0; j < n; j++) {
                int x = 1 << j;
                used[x | i] = true;
            }
        }
        else {
            ll temp = 0L;
            for (int j = 0; j < n; j++){
                int x = 1 << j;
                if ((x & i) != 0) {
                    temp += weights[j];
                }
            }
            result = max(result, temp);
        }
    }
    cout << result << endl;
    return 0;
}