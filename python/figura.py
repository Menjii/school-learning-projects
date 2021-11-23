# ZADANIE SKOŃCZONE

class Figura:
    def __init__(self, name):
        self.name = name

    def getName(self):
        return self.name


class Kolo(Figura):
    def __init__(self, name, radius):
        super().__init__(name)
        self.radius = radius

    def obwod(self):
        obwod = 2 * 3.14 * self.radius
        return obwod


class Prostokat(Figura):
    def __init__(self, name, sideA, sideB):
        super().__init__(name)
        self.sideA = sideA
        self.sideB = sideB

    def obwod(self):
        obwod = 2 * self.sideA + 2 * self.sideB
        return obwod


class Kwadrat(Prostokat):
    def __init__(self, name, sideA):
        super().__init__(name, sideA, sideA)


def main():
    obiekt = Kolo("Kolo", 6)
    obiekt1 = Prostokat("Prostokacik", 5, 10)
    obiekt2 = Kwadrat("Kwadracik", 5)

    lista = [obiekt, obiekt1, obiekt2]
    for item in lista:
        print(item.getName())
        print(item.obwod())


main()
