# ZADANIE SKONCZONE
import numpy as np


def my_range(a, b=0.0, k=0.0):
    if k == 0 and b != 0:
        for i in np.arange(a, b):
            yield i
    elif k == 0 and b == 0:
        for i in np.arange(0.0, a):
            yield i
    else:
        for i in np.arange(a, b, k):
            yield i


def main():
    try:
        tablica = []
        for range in my_range(2.2):
            tablica.append(range)
        print(tablica)
    except ValueError as err:
        print(err.args)


main()
