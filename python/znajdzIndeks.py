# ZADANIE SKOŃCZONE

napis = (input("Napisz dowolny napis "))

for iteracja, value in enumerate(napis):
    if value == "A" or value == "a":
        print("Iteracja nr.", iteracja, "Wartosc: ", value)
