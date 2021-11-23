#ZADANIE SKOŃCZONE

from functools import partial

lista = [1, 3]
lista1 = [1, 3, 5]


def calculateItemMultiplicationByListLengthAndLambda(lista):
    return list(map(lambda item: item * len(lista), lista))


def multiply(item, item2):
    return item * item2


def multipleClosure(array):
    return multiplySec(array)(len(array))


def multiplySec(array):
    def by(value):
        return calculateItemMultiplicationByListLengthAndLambda(array)

    return by


def main():
    print(calculateItemMultiplicationByListLengthAndLambda(lista))
    print(list(map(partial(multiply, len(lista)), lista1)))
    print(multipleClosure(lista))


main()
