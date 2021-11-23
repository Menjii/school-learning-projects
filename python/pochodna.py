# ZADANIE SKOŃCZONE

import math


def square(x):
    return x ** 2


def derivative(f, x, h=0.0001):
    return (f(x + h) - f(x)) / h


print(derivative(math.sin, 1))
print(derivative(math.sin, 0))
print(derivative(square, 1, 0.0001))
