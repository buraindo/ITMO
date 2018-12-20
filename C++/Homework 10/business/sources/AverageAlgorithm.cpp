//
// Created by buraindo on 18.12.18.
//

#include <AverageAlgorithm.h>
#include <numeric>

void AverageAlgorithm::buildPartition() {
    std::ofstream writer(RESULT_FILENAME);
    int metric = mMetric;
    auto binaryOperation = [&metric](int lhs, Order rhs) {return lhs + rhs.getProperty(metric);};
    auto comparator = [&metric](Order lhs, Order rhs) {return lhs.getPrice() > rhs.getProperty(metric);};
    int sum = std::accumulate(mOrders.begin(), mOrders.end(), 0, binaryOperation);
    int average = sum / (int) mOrders.size();
    std::vector<std::vector<Order>> partition;
    std::sort(mOrders.begin(), mOrders.end(), comparator);
    auto iter = mOrders.begin();
    while (true) {
        std::vector<Order> part;
        int localSum = 0;
        int count = 0;
        part.push_back(*iter);
        localSum += iter->getProperty(mMetric);
        count++;
        mOrders.erase(iter);
        if (!mOrders.empty()) {
            iter = mOrders.begin();
            while (localSum < average && !mOrders.empty()) {
                auto it = --mOrders.end();
                if (it->getProperty(mMetric) + localSum < average) {
                    for (auto i = --mOrders.end(); i > mOrders.begin(); i--) {
                        if (i->getProperty(mMetric) + localSum < average) {
                            it = i;
                        }
                    }
                    if (count < mMaxNumber) {
                        localSum += it->getProperty(mMetric);
                        Order o(it->getName(), it->getPrice(), it->getTimeToDeliver(), it->getQuantity());
                        part.push_back(o);
                        mOrders.erase(it);
                        count++;
                    } else break;
                } else break;
            }
        }
        partition.push_back(part);
        if (mOrders.empty()) {
            break;
        }
    }
    int index = 1;
    for (auto& p : partition) {
        writer << "Partition number " << index++ << ":" << std::endl;
        for (auto& o : p) {
            writer << "\t" << o.getName() << " " << o.getPrice() << " " << o.getTimeToDeliver()
                   << " " << o.getQuantity() << std::endl;
        }
    }
    writer.close();
}

