# ZADANIE SKOŃCZONE/POPRAWIONE

list = [1, 2, 3, 4]
list1 = [5, 6, 7, 8, 9]


def calculateAverageAndVariance(lista, dlugosc, suma=0):
    if len(lista) > 0:
        wariancja = calculateAverageAndVariance(lista[1:], dlugosc, suma + lista[0])
        return wariancja[0], wariancja[1] + (lista[0] - wariancja[0]) ** 2 / (dlugosc - 1)
    else:
        return suma / dlugosc, 0


def main():
    print(calculateAverageAndVariance(list, len(list)))
    print(calculateAverageAndVariance(list1, len(list1)))


main()
