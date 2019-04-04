#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

typedef long long ll;

const ll p = 31;

void calculateZFunction (vector<ll>& zFunction, string input) {
	int n = (int) input.length();
	ll l = 0;
	ll r = 0;
	for (int i = 1; i < n; i++) {
		if (i <= r) {
			zFunction[i] = min (r - i + 1, zFunction[i - l]);
		}
		while (i + zFunction[i] < n && input[zFunction[i]] == input[i + zFunction[i]]) {
			zFunction[i]++;
		}
		if (i + zFunction[i] - 1 > r) {
			l = i;
			r = i + zFunction[i] - 1;
		}
	}
}

int main()
{
    //freopen(filename, "r", stdin);
    //freopen(filename, "w", stdout);
    string input;
    cin >> input;
    vector<ll> zFunction (input.length());
    calculateZFunction(zFunction, input);
    for (int i = 1; i < (int) zFunction.size(); i++) {
        ll item = zFunction[i];
        if ((int) input.length() % i == 0 && i + item == (int) input.length()) {
            cout << i;
            return 0;
        }
    }
    cout << (int) input.length();
    return 0;
}