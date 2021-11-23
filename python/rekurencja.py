#ZADANIE SKOŃCZONE

list = [1, 2, 3, 4]
list1 = []


def sumaElementowZListy(lista):
    if len(lista) == 0:
        return 0
    else:
        return lista[len(lista) - 1] + sumaElementowZListy(lista[:len(lista) - 1])


def sumaElementowZListOgon(lista, akumulator=0):
    if len(lista) == 0:
        return akumulator
    return sumaElementowZListOgon(lista[:len(lista) - 1], akumulator + lista[len(lista) - 1])


def main():
    print(sumaElementowZListy(list))
    print(sumaElementowZListOgon(list))

    print(sumaElementowZListy(list1))
    print(sumaElementowZListOgon(list1))


main()
