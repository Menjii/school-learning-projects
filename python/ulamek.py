# ZADANIE SKONCZONE

def policz():
    return Ulamek.counter


class Ulamek:
    counter = 0

    def __init__(self, x, y):
        Ulamek.counter += 1
        self.x = x
        self.y = y

    def __del__(self):
        Ulamek.counter -= 1

    def __add__(self, other):
        self.x = self.x * other.y + self.y * other.x
        self.y = self.y * other.y
        Ulamek.nwd(self, self.x, self.y)

    def __mul__(self, other):
        self.x = self.x * other.x
        self.y = self.y * other.y
        Ulamek.nwd(self, self.x, self.y)

    def __sub__(self, other):
        self.x = self.x * other.y - self.y * other.x
        self.y = self.y * other.y
        Ulamek.nwd(self, self.x, self.y)

    def __truediv__(self, other):
        self.x = self.x * other.y
        self.y = self.y * other.x
        Ulamek.nwd(self, self.x, self.y)

    def wypisz(self):
        print((str(self.x)) + "/" + (str(self.y)))

    def nwd(self, a, b):
        while b:
            a, b = b, a % b
        self.x /= a
        self.y /= a


def main():
    Ob1 = Ulamek(1, 2)
    Ob2 = Ulamek(2, 4)
    Ob3 = Ulamek(3, 4)
    Ob5 = Ulamek(2, 4)
    Ob6 = Ulamek(5, 7)
    Ob7 = Ulamek(4, 8)
    Ob8 = Ulamek(1, 1)

    Ob1 + Ob2
    Ob1.wypisz()

    Ob2 * Ob3
    Ob2.wypisz()

    Ob5 / Ob6
    Ob5.wypisz()

    Ob6 - Ob3
    Ob6.wypisz()

    print(policz())

    Ob7 * Ob8
    Ob7.wypisz()


main()
