//
// Created by buraindo on 18.12.18.
//

#include <SortAlgorithm.h>

void SortAlgorithm::buildPartition() {
    std::ofstream writer(RESULT_FILENAME);
    bool(*comparator)(Order, Order) = nullptr;
    switch (mMetric) {
        case Util::PRICE:
            comparator = [](Order lhs, Order rhs) {return lhs.getPrice() < rhs.getPrice();};
            break;
        case Util::TIME:
            comparator = [](Order lhs, Order rhs) {return lhs.getTimeToDeliver() < rhs.getTimeToDeliver();};
            break;
        case Util::QUANTITY:
            comparator = [](Order lhs, Order rhs) {return lhs.getQuantity() < rhs.getQuantity();};
            break;
        default:
            break;
    }
    std::sort(mOrders.begin(), mOrders.end(), comparator);
    print(writer);
}

