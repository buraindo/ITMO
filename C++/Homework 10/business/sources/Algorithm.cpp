//
// Created by buraindo on 18.12.18.
//

#include <Algorithm.h>

Algorithm::Algorithm(int metric, int maxNumber, const std::vector<Order> &orders) :
mMetric(metric), mMaxNumber(maxNumber), mOrders(orders) {}

void Algorithm::print(std::ofstream& writer) {
    std::vector<std::vector<Order>> partition;
    int partitionNumber = (int) mOrders.size() / mMaxNumber;
    int remainder = (int) mOrders.size() % mMaxNumber;
    for (int i = 0; i < partitionNumber; i++) {
        std::vector<Order> part;
        part.reserve(static_cast<unsigned long>(mMaxNumber));
        for (int j = 0; j < mMaxNumber; j++) {
            part.push_back(mOrders[i * mMaxNumber + j]);
        }
        partition.push_back(part);
    }
    if (remainder > 0) {
        std::vector<Order> lastPart;
        lastPart.reserve(static_cast<unsigned long>(mMaxNumber));
        for (int i = 0; i < remainder; i++) {
            lastPart.push_back(mOrders[mMaxNumber * partitionNumber + i]);
        }
        partition.push_back(lastPart);
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

std::string Algorithm::RESULT_FILENAME = "../data/result.txt";
