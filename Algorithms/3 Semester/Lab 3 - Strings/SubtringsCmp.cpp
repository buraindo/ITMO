#include <iostream>
#include <string>
#include <vector>

using namespace std;

typedef long long ll;

const ll p = 31;

void countPrefixHash(vector<ll>& degrees, vector<ll>& prefixHash, const string& input) {
    degrees[0] = 1;
    for (int i = 1; i < (int) degrees.size(); i++) {
	    degrees[i] = degrees[i-1] * p;
    }
    for (int i = 0; i < (int) prefixHash.size(); i++)
    {
    	prefixHash[i] = (input[i] - 'a' + 1) * degrees[i];
    	if (i > 0) {
    	    prefixHash[i] += prefixHash[i-1];
    	}
    }
}

int main()
{
    //freopen(filename, "r", stdin);
    //freopen(filename, "w", stdout);
    string input;
    cin >> input;
    vector<ll> degrees(input.length());
    vector<ll> prefixHash(input.length());
    countPrefixHash(degrees, prefixHash, input);
    int m;
    cin >> m;
    for (int i = 0; i < m; i++) {
        int a, b, c, d;
        cin >> a >> b >> c >> d;
        if (b - a != d - c) {
			cout << "No" << endl;
			continue;
		}
        ll zeroToB = prefixHash[b - 1];
        ll zeroToD = prefixHash[d - 1];
        ll aToB = zeroToB;
        if (a > 1) aToB -= prefixHash[a - 2];
        ll cToD = zeroToD;
        if (c > 1) cToD -= prefixHash[c - 2];
        if (a <= c && aToB * degrees[c - a] == cToD ||
	    a > c && aToB == cToD * degrees[a - c]) {
	        cout << "Yes" << endl;
	    } else {
	        cout << "No" << endl;
	    }
    }
    return 0;
}