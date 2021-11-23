# ZADANIE SKONCZONE
def my_range(a, b=0.0, k=0.0):
    tablica = []
    if k > 0:
        while a < b:
            tablica.append(a)
            a += k

    if k < 0:
        while a > b:
            tablica.append(a)
            a -= k

    if k == 0 and b != 0:
        tablica.append(a)
        tablica.append(b - 0.1)

    if b == 0:
        while a >= b:
            tablica.append(b)
            b += 1

    return tablica


def main():
    try:
        print(my_range(1.1, 2.2))
    except ValueError as err:
        print(err.args)


main()
