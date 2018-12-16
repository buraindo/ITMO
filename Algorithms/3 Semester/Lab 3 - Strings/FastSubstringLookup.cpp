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
    string prefix, input;
    cin >> prefix >> input;
    input = prefix + '#' + input;
    vector<ll> prefixFunction (input.length());
    calculatePrefixFunction(prefixFunction, input);
    vector<int> occurrences;
    int length = (int) prefix.length() + 1;
    for (int i = length; i < (int) prefixFunction.size(); i++) {
        if (prefixFunction[i] == length - 1) {
            occurrences.push_back(i - 2 * (length - 1) + 1);
        }
    }
    cout << (int) occurrences.size() << endl;
    for (int i = 0; i < (int) occurrences.size(); i++) {
        cout << occurrences[i] << " ";
    }
    return 0;
}