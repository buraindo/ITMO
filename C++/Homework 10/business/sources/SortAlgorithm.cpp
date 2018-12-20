//
// Created by buraindo on 18.12.18.
//

#include <SortAlgorithm.h>

void SortAlgorithm::buildPartition() {
    std::ofstream writer(RESULT_FILENAME);
    int metric = mMetric;
    auto comparator = [&metric](Order lhs, Order rhs) {return lhs.getPrice() < rhs.getProperty(metric);};
    std::sort(mOrders.begin(), mOrders.end(), comparator);
    print(writer);
}

