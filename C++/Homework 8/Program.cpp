#include <iostream>

using namespace std;

typedef int(*otherFunction)(double);
typedef int* (*resultFunction)(const char*);
typedef resultFunction(*complexFunction)(int, otherFunction);

void testComplexFunctions() {
	cout << "======= COMPLEX FUNCTION =======\n";
	complexFunction complex = [](int x, otherFunction f) -> resultFunction {
		cout << "In complex\n";

		cout << f(x) << "\n";

		resultFunction result = [](char const * x) -> int* {
			cout << "In result\n";
			return const_cast<int*>(reinterpret_cast<const int*>(x));
		};

		return result;
	};

	otherFunction other = [](double x) -> int {
		cout << "In parameter: ";
		return (int)x;
	};
	cout << (complex(4, other))("3") << "\n";
}

template<class T, class R>
bool compare(T* lhs, T* rhs, R (T::*predicate)() const) {
	return (lhs->*predicate)() < (rhs->*predicate)();
}

template<class T>
bool isSameObject(T* lhs, T* rhs) {
	return dynamic_cast<void*>(lhs) == dynamic_cast<void*>(rhs);
}

int main() {
	testComplexFunctions();
	string elf("Elf");
	string archer("Archer");
	cout << compare(&elf, &archer, &string::size) << endl;
	int dontCloseConsolePlease;
	cin >> dontCloseConsolePlease;
	return 0;
}