# ZADANIE SKOŃCZONE

kontakty = {('Jan', 'Kowalski'): "123456789",
            ('Adam', 'Nowak'): "987654321",
            ('Adam', 'Kowalski'): "600300900"}

print(kontakty[('Jan', 'Kowalski')])


def wypiszNumery(nazwisko):
    for klucz in kontakty:
        if klucz[1] == nazwisko:
            print(kontakty[klucz])


wypiszNumery('Kowalski')
