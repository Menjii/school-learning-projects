# ZADANIE SKOŃCZONE

dodawanaCyfra = 1
tab = []
tab0 = [3, 3, 3, 3]
tab1 = [5, 6, 7, 8, 9]

while dodawanaCyfra != 0:
    dodawanaCyfra = int(input("Podaj cyfre ktora chcesz dodac do tablicy "))
    if dodawanaCyfra != 0:
        tab.append(dodawanaCyfra)


def sredniaAndWariancja(tablica):
    srednia = 0
    wariancjaSum = 0

    for item in tablica:
        srednia += item
    srednia = 1 / len(tablica) * srednia

    for item in tablica:
        wariancjaSum += (item - srednia) ** 2
    wariancja = 1 / (len(tablica) - 1) * wariancjaSum

    return srednia, wariancja


print("Srednia wynosi: " + str(sredniaAndWariancja(tab)[0]))
print("Wariancja wynosi: " + str(sredniaAndWariancja(tab)[1]))

print("TABLICA [3, 3, 3, 3]")
print("Srednia wynosi: " + str(sredniaAndWariancja(tab0)[0]))
print("Wariancja wynosi: " + str(sredniaAndWariancja(tab0)[1]))

print("TABLICA [5, 6, 7, 8, 9]")
print("Srednia wynosi: " + str(sredniaAndWariancja(tab1)[0]))
print("Wariancja wynosi: " + str(sredniaAndWariancja(tab1)[1]))
