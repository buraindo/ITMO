#include <iostream>
#include <string>
#include <vector>

using namespace std;

typedef long long ll;

const ll p = 31;

void calculatePrefixFunction (vector<ll>& prefixFunction, string input) {
	int n = (int) input.length();
	for (int i = 1; i < n; i++) {
		ll j = prefixFunction[i - 1];
		while (j > 0 && input[i] != input[j]) {
			j = prefixFunction[j - 1];
		}
		if (input[i] == input[j]) {
		    j++;
		}
		prefixFunction[i] = j;
	}
}

int main()
{
    //freopen(filename, "r", stdin);
    //freopen(filename, "w", stdout);
    string input;
    cin >> input;
    vector<ll> prefixFunction (input.length());
    calculatePrefixFunction(prefixFunction, input);
    for (ll item : prefixFunction) {
        cout << item << " ";
    }
    return 0;
}