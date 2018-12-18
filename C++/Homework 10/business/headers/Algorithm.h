//
// Created by buraindo on 18.12.18.
//
#pragma once

#include <vector>
#include <fstream>
#include <algorithm>
#include <Order.h>
#include <Util.h>

#ifndef HW10_ALGORITHM_H
#define HW10_ALGORITHM_H

class Algorithm {
public:
    Algorithm(int metric, int maxNumber, const std::vector<Order>& orders);
    virtual void buildPartition() = 0;
    void print(std::ofstream& writer);
protected:
    int mMetric;
    int mMaxNumber;
    std::vector<Order> mOrders;
    static std::string RESULT_FILENAME;
};

#endif //HW10_ALGORITHM_H
