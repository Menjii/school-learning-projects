# ZADANIE SKOŃCZONE

def listOfPrimeNumbers(number):
    return list(filter(lambda item: all(list(map(lambda divider: item % divider, list(range(2, item))))),
                       list(range(2, number))))


def main():
    print(listOfPrimeNumbers(100))


main()
