//
// Created by buraindo on 18.12.18.
//
#pragma once

#include <RandomAlgorithm.h>
#include <SortAlgorithm.h>
#include <AverageAlgorithm.h>
#include <fstream>

#ifndef HW10_ALGORITHMFACTORY_H
#define HW10_ALGORITHMFACTORY_H

class AlgorithmFactory {
public:
    static Algorithm* getAlgorithm();
private:
    static std::string DATA_FILENAME;
    static std::string ORDERS_FILENAME;
};

std::string AlgorithmFactory::DATA_FILENAME = "../data/algorithm.txt";
std::string AlgorithmFactory::ORDERS_FILENAME = "../database/orders.txt";

Algorithm* AlgorithmFactory::getAlgorithm() {
    std::ifstream reader(DATA_FILENAME);
    std::string algorithm, metric;
    int maxNumber, orderNumber;
    reader >> algorithm >> metric >> maxNumber >> orderNumber;
    int metricNumber = Util::metricToInt[metric];
    int algorithmNumber = Util::algorithmToInt[algorithm];
    std::vector<Order> orders;
    std::ifstream orderReader(ORDERS_FILENAME);
    for (int i = 0; i < orderNumber; i++) {
        std::string name;
        int price, time, quantity;
        orderReader >> name >> price >> time >> quantity;
        Order order (name, price, time, quantity);
        orders.push_back(order);
    }
    reader.close();
    orderReader.close();
    Algorithm* result;
    switch (algorithmNumber) {
        case Util::RANDOM:
            result = new RandomAlgorithm(metricNumber, maxNumber, orders);
            break;
        case Util::SORT:
            result = new SortAlgorithm(metricNumber, maxNumber, orders);
            break;
        case Util::AVERAGE:
            result = new AverageAlgorithm(metricNumber, maxNumber, orders);
            break;
        default:
            result = new RandomAlgorithm(metricNumber, maxNumber, orders);
    }
    return result;
}


#endif //HW10_ALGORITHMFACTORY_H
