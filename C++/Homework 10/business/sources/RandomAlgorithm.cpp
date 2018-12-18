#include <random>

//
// Created by buraindo on 18.12.18.
//

#include <RandomAlgorithm.h>

void RandomAlgorithm::buildPartition() {
    std::ofstream writer(RESULT_FILENAME);
    std::shuffle(mOrders.begin(), mOrders.end(), std::mt19937(std::random_device()()));
    print(writer);
}
