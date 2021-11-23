#ZADANIE SKOŃCZONE

liczba = input("Podaj liczbe: ")
slownik = {'0': "zero", '1': "jeden", '2': "dwa", '3': "trzy",
           '4': "cztery", '5': "piec", '6': "szesc",
           '7': "siedem", '8': "osiem", '9': "dziewiec", }


def zamianaCyfryNaZapisSlowny(liczba):
    napis = ""
    for i in liczba:
        napis = napis + " " + slownik[i]
    return napis


print(zamianaCyfryNaZapisSlowny(liczba))
