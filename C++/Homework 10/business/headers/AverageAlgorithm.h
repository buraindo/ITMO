//
// Created by buraindo on 18.12.18.
//
#pragma once

#include <Algorithm.h>

#ifndef HW10_AVERAGEALGORITHM_H
#define HW10_AVERAGEALGORITHM_H

class AverageAlgorithm : public Algorithm {
public:
    AverageAlgorithm(int metric, int maxNumber, const std::vector<Order>& orders) : Algorithm(metric, maxNumber, orders) {}
    void buildPartition() override;
};

#endif //HW10_AVERAGEALGORITHM_H
