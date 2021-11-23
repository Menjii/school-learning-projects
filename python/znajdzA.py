# ZADANIE SKOŃCZONE

napis = (input("Napisz dowolny napis "))
counter = 0

for letter in napis:
    if letter == "A" or letter == "a":
        counter = counter + 1

print(counter)
