# ZADANIE POPRAWIONE Dostałem uwagę, że zakres który podaje jest zły, w poleceniu jest napisane, "przy czym
# początkowy z przedziału rok ma być uwzględniany, a końcowy nie." Zatem poprawnym zakresem jest range(1900,2000),
# ponieważ zostana uwzględnione liczby od 1900-1999.

def rokPrzestepny(range0, range1):
    return [x for x in range(range0, range1) if x % 4 == 0 and x % 100 != 0 or x % 400 == 0]


print(rokPrzestepny(1900, 2000))
