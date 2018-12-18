//
// Created by buraindo on 18.12.18.
//
#pragma once

#include <Algorithm.h>

#ifndef HW10_RANDOMALGORITHM_H
#define HW10_RANDOMALGORITHM_H

class RandomAlgorithm : public Algorithm {
public:
    RandomAlgorithm(int metric, int maxNumber, const std::vector<Order>& orders) : Algorithm(metric, maxNumber, orders) {}
    virtual void buildPartition() override;
};

#endif //HW10_RANDOMALGORITHM_H
