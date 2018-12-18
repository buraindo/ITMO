#include <iostream>
#include <AlgorithmFactory.h>

int main() {
    Util::init();
    Algorithm* algorithm = AlgorithmFactory::getAlgorithm();
    algorithm->buildPartition();
    return 0;
}