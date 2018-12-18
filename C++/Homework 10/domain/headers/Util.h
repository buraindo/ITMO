//
// Created by buraindo on 18.12.18.
//
#pragma once

#include <map>

#ifndef HW10_UTIL_H
#define HW10_UTIL_H

class Util {
public:
    static std::map<std::string, int> metricToInt;
    static std::map<std::string, int> algorithmToInt;
    static void init();
    static const int PRICE = 0;
    static const int TIME = 1;
    static const int QUANTITY = 2;
    static const int RANDOM = 0;
    static const int SORT = 1;
    static const int AVERAGE = 2;
};

#endif //HW10_UTIL_H
