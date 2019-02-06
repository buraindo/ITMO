//
// Created by Buraindo on 13.10.2018.
//
#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

const int INF = 1337228322;

double distance(const pair<short, short> &u, const pair<short, short> &v) {
    auto firstD = (v.first - u.first);
    auto secondD = (v.second - u.second);
    return sqrt(firstD * firstD + secondD * secondD);
}

int main() {
    short N;
    cin >> N;
    pair<short, short> vertices[N];
    int span[N];
    double min[N];
    bool used[N];
    for (short i = 0; i < N; i++) {
        short u, v;
        cin >> u >> v;
        vertices[i] = make_pair(u, v);
        used[i] = false;
        span[i] = -1;
        min[i] = INF;
    }
    double ans = 0.0;
    for (short i = 0; i < N; i++) {
        int v = -1;
        for (int j = 0; j < N; ++j) {
            if (!used[j] && (v == -1 || min[j] < min[v])) {
                v = j;
            }
        }
        used[v] = true;
        if (span[v] != -1) {
            ans += distance(vertices[span[v]], vertices[v]);
        }
        for (int u = 0; u < N; ++u) {
            double dist = distance(vertices[u], vertices[v]);
            if (dist < min[u]) {
                min[u] = dist;
                span[u] = v;
            }
        }
    }
    cout << ans;
    return 0;
}