#include <iostream>
#include <algorithm>
#include <vector>
#include <set>

using namespace std;

typedef long long ll;
typedef pair<ll, ll> job;

bool compare(const job& lhs, const job& rhs) {
    return lhs.second > rhs.second;
}

int main()
{
    freopen("schedule.in", "r", stdin);
    freopen("schedule.out", "w", stdout);
    int n;
    cin >> n;
    vector<job> jobs;
    set<ll> timing;
    for (int i = 0; i < n; i++) {
        ll d, w;
        cin >> d >> w;
        jobs.emplace_back(d, w);
        timing.insert(i);
    }
    sort(jobs.begin(), jobs.end(), compare);
    ll result = 0L;
    for (auto& job : jobs)
    {
        if (*timing.begin() >= job.first)
        {
            result += job.second;
        }
        else
        {
            timing.erase(--timing.lower_bound(job.first));
        }
    }
    cout << result << endl;
    return 0;
}