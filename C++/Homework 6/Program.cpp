#include "Human.h"
#include <ctime>

int main() {
	srand(time(NULL));
	Human mamka_odmena("Lyudmila", "Mukhosransk", 45, 160, 2281337322, Human::FEMALE, 18000, { 0,0,1,1,0,0,1 });
	std::cout << mamka_odmena;
	Human sych("Slava", "Yebenya", 20, 179, 60, Human::MALE, 0, { 1,0,1,1,0,0,0 });
	std::cout << sych;
	Human sychPlusMamka = sych + mamka_odmena;
	std::cout << sychPlusMamka;
	int a;
	std::cin >> a;
}