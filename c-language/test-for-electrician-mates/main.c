#include <stdio.h>
#include <stdlib.h>


double sredniaArytmetycznaOrazWariancja(double *tablica, int r);

int main()
{
    int dlugoscTablicy;

    printf("Podaj dlugosc tablicy: ");
    scanf("%d", &dlugoscTablicy);

    double* tab;
    tab = malloc(dlugoscTablicy * sizeof(*tab));

    printf("Wariancja wynosi: %lf", sredniaArytmetycznaOrazWariancja(tab, dlugoscTablicy));

    return 0;
}

double sredniaArytmetycznaOrazWariancja(double *tablica, int r) {

    double value;

    double srednia = 0;
    double wariancja;
    double wariancjaSum = 0;

    printf("Podaj %d wartosci do tablicy z ktorych chcesz obliczyc wariancje i srednia: \n", r);

    for(int i = 0; i < r; i++) {
        scanf("%lf", &value);
        tablica[i] = value;
    }

    for(int i = 0; i < r; i++) {
        srednia += tablica[i];
    }

    srednia = srednia / r;

    printf("Srednia wynosi: %lf\n", srednia);

    for(int i = 0; i < r; i++) {
        double tmp = (tablica[i] - srednia);
        wariancjaSum += (pow(tmp, 2));
    }

    wariancja = 1 / ((r - 1) * wariancjaSum);

    return wariancja;

}

