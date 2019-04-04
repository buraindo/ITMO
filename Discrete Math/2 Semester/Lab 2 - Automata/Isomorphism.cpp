#include <iostream>
#include <map>
 
using namespace std;
 
bool* visited;
const int devil;
 
struct vertex {
    bool terminal = false;
    map <char, int> transitions;
     
    void add (char c, int q) {
        transitions[c] = q;
    }
     
    int next (char c) {
        if (transitions.find(c) == transitions.end()) return devil;
        return transitions[c];
    }
};
 
vertex* first;
vertex* second;
 
bool dfs (int u, int v) {
    visited[u] = true;
    auto u_vertex = first[u];
    auto v_vertex = second[v];
    if (u_vertex.terminal != v_vertex.terminal)
        return false;
    bool result = true;
    for (auto item : u_vertex.transitions) {
        auto t1 = u_vertex.next(item.first);
        auto t2 = v_vertex.next(item.first);
        if ((t1 == devil) ^ (t2 == devil)) {
            return false;
        }
        if (!visited[t1]) {
            result = result && dfs(t1, t2);
        }
    }
    return result;
}
 
int main () {
    freopen ("isomorphism.in", "r", stdin);
    freopen ("isomorphism.out", "w", stdout);
    int n, m, k;
    cin >> n >> m >> k;
    visited = new bool[n + 1];
    first = new vertex[n + 1];
    second = new vertex[n + 1];
    devil = n;
    for (int i = 0; i < k; i++) {
        int point;
        cin >> point;
        first[point - 1].terminal = true;
    }
    for (int i = 0; i < m; i++) {
        int a, b;
        char key;
        cin >> a >> b >> key;
        a--;
        b--;
        first[a].add(key, b);
    }
    cin >> n >> m >> k;
    for (int i = 0; i < k; i++) {
        int point;
        cin >> point;
        second[point - 1].terminal = true;
    }
    for (int i = 0; i < m; i++) {
        int a, b;
        char key;
        cin >> a >> b >> key;
        a--;
        b--;
        second[a].add(key, b);
    }
    bool yes = dfs (0, 0);
    string ans = yes ? "YES\n" : "NO\n";
    cout << ans;
    return 0;
}