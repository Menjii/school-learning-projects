# ZADANIE SKOŃCZONE

import random

liczbaWylosowana = random.randint(1, 10)
counter = 0

while counter < 3:
    liczbaZgadywana = int(input("Zgadnij wylosowana liczbe z zakresu 1-10 "))

    if(liczbaZgadywana == liczbaWylosowana):
        print("Gratulacje, zgadleś liczbe ")
        break
    elif(liczbaZgadywana > liczbaWylosowana):
        print("Liczba przez ciebie podana jest wieksza, od liczby wylosowanej")
        counter = counter + 1
    elif(liczbaZgadywana < liczbaWylosowana):
        print("Liczba przez ciebie podana jest mniejsza, od liczby wylosowanej")
        counter = counter + 1
else:
    print("Przegrałeś w grę! (przekroczyłeś limit trzech prób")
