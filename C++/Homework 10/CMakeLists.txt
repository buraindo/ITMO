cmake_minimum_required(VERSION 3.13)
project(HW10)

set(CMAKE_CXX_STANDARD 14)

include_directories(business/headers)
include_directories(domain/headers)

add_executable(HW10 main.cpp business/sources/Algorithm.cpp
        business/sources/AverageAlgorithm.cpp business/sources/SortAlgorithm.cpp
        business/sources/RandomAlgorithm.cpp domain/sources/Order.cpp domain/sources/Util.cpp)