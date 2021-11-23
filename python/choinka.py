# ZADANIE SKOŃCZONE

wiersz = 0

while wiersz != 7:
    wiersz = int(input("Podaj ilość wierszy "))

    for item in range(1,wiersz+1):

        if wiersz % 2 == 0:
            print(" "*(wiersz-item)+"*"*(item*2-1))
        elif wiersz % 2 == 1:
            print(" "*(wiersz-item)+"#"*(item*2-1))
