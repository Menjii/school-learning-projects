# ZADANIE SKOŃCZONE

def liczbaPierwsza(liczba):
    for value in range(2, liczba):
        if liczba % value == 0:
            return False
    else:
        return True


for item in range(2, 101):
    if liczbaPierwsza(item):
        print(item)
