/*Кузнечик прыгает по столбикам, расположенным на одной линии на равных расстояниях друг
от друга. Столбики имеют порядковые номера от 1 до N. В начале Кузнечик сидит на столбике с
номером 1. Он может прыгнуть вперед на расстояние от 1 до K столбиков, считая от текущего.
На каждом столбике Кузнечик может получить или потерять несколько золотых монет (для
каждого столбика это число известно). Определите, как нужно прыгать Кузнечику, чтобы собрать
наибольшее количество золотых монет. Учитывайте, что Кузнечик не может прыгать назад.
*/
#include <utility>
#include <vector>
#include <fstream>
#include <string>
#include <algorithm>
#include <map>
#include <iostream>
 
using namespace std;
 
int main() {
    ifstream reader("input.txt");
    ofstream writer("output.txt");
    int n, k;
    reader >> n >> k;
    vector<int> init;
    vector<int> path = {1};
    for (int i = 0; i != n - 2; i++) {
        int v;
        reader >> v;
        init.push_back(v);
    }
    int jumps = 1;
    int pos = 0;
    int reward = 0;
    int sections = (n - 2) / k + 1;
    while (true) {
        int localSum = 0;
        int localNegativeMax = -10001;
        int localNegativeIndex = -1;
        int lastPositiveIndex = -1;
        if (init.size() - pos - 1 < k) {
            for (int j = pos; j != n - 2; j++) {
                if (init[j] > 0) {
                    localSum += init[j];
                    path.push_back(j + 2);
                    jumps++;
                }
                if (init[j] < 0 && init[j] > localNegativeMax) {
                    localNegativeMax = init[j];
                    localNegativeIndex = j;
                }
            }
            if (localSum == 0) {
                jumps++;
            } else {
                reward += localSum;
            }
            break;
        } else {
            for (int j = pos; j != pos + k; j++) {
                if (init[j] > 0) {
                    localSum += init[j];
                    path.push_back(j + 2);
                    jumps++;
                    lastPositiveIndex = j;
                }
                if (init[j] < 0 && init[j] > localNegativeMax) {
                    localNegativeMax = init[j];
                    localNegativeIndex = j;
                }
            }
            if (localSum == 0) {
                reward += localNegativeMax;
                jumps++;
                pos = localNegativeIndex + 1;
                path.push_back(pos + 1);
            } else {
                reward += localSum;
                pos = lastPositiveIndex + 1;
            }
        }
    }
    path.push_back(n);
    writer << reward << "\n" << jumps << "\n";
    for (int i = 0; i != path.size() - 1; i++)
        writer << path[i] << " ";
    writer << path[path.size() - 1];
    writer.close();
    return 0;
}