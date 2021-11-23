# ZADANIE SKOŃCZONE
from cmath import sqrt
from functools import reduce

lista1 = [1, 3, 5]
lista2 = [[1, 2, 3], [5], [8, 9]]
wspolrzedneList = [[5, 6], [5, 7]]


def averageListCalc(array):
    return reduce(lambda a, b: a + b, array) / len(array)


def maxElementInList(array):
    return reduce(lambda a, b: a if a > b else b, array)


def splaszcz(array):
    return reduce(lambda a, b: a + b, array)


def odlegloscTaksowkowa(array):
    return reduce(lambda v1, v2: abs(v1[0] - v2[0]) + abs(v1[1] - v2[1]), array)


def main():
    print(averageListCalc(lista1))
    print(maxElementInList(lista1))
    print(splaszcz(lista2))
    print(odlegloscTaksowkowa(wspolrzedneList))


main()
