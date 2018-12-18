//
// Created by buraindo on 18.12.18.
//
#pragma once

#include <Algorithm.h>

#ifndef HW10_SORTALGORITHM_H
#define HW10_SORTALGORITHM_H

class SortAlgorithm : public Algorithm {
public:
    SortAlgorithm(int metric, int maxNumber, const std::vector<Order>& orders) : Algorithm(metric, maxNumber, orders) {}
    void buildPartition() override;
};

#endif //HW10_SORTALGORITHM_H
