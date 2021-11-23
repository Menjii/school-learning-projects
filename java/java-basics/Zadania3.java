public class Zadania3 {

    public static void main(String[] args) throws ZerowyMianownik {

        Ulamek u1 = new Ulamek(1, 2);
        Ulamek u2 = new Ulamek(2, 4);
        u1.dodawanie(u2);
        System.out.println(u1.getLicznik() + "/" + u1.getMianownik());
        System.out.println(u2.getLicznik() + "/" + u2.getMianownik());

        Ulamek c = Ulamek.add(1, 4, 3, 4);
        System.out.println(c.toString(c));
    }
}

class Ulamek {
    private int licznik, mianownik;

    public Ulamek(int licznik, int mianownik) {

        // if (mianownik == 0)
        // throw ZerowyMianownik();

        this.licznik = licznik;
        this.mianownik = mianownik;
        skrocUlamek();
    }

    public int getLicznik() {
        return this.licznik;
    }

    public int getMianownik() {
        return this.mianownik;
    }

    public void dodawanie(Ulamek b) {
        this.licznik = this.licznik * b.mianownik + this.mianownik * b.licznik;
        this.mianownik = this.mianownik * b.mianownik;
        skrocUlamek();
    }

    public void odejmowanie(Ulamek b) {
        this.licznik = this.licznik * b.mianownik - this.mianownik * b.licznik;
        this.mianownik = this.mianownik * b.mianownik;
        skrocUlamek();
    }

    public void skrocUlamek() {
        for (int i = this.licznik; i > 0; i--) {
            if (this.mianownik % i == 0 && this.licznik % i == 0) {
                this.mianownik /= i;
                this.licznik /= i;
            }
        }
    }

    public static Ulamek add(int licznik1, int mianownik1, int licznik2, int mianownik2) {
        licznik1 = licznik1 * mianownik2 + licznik2 * mianownik1;
        mianownik1 *= mianownik2;
        Ulamek u3 = new Ulamek(licznik1, mianownik1);
        return u3;
    }

    public static Ulamek subtract(int licznik1, int mianownik1, int licznik2, int mianownik2) {
        licznik1 = licznik1 * mianownik2 - licznik2 * mianownik1;
        mianownik1 *= mianownik2;
        Ulamek u4 = new Ulamek(licznik1, mianownik1);
        return u4;
    }

    public String toString(Ulamek object) {
        String licznik = Integer.toString(object.licznik);
        String mianownik = Integer.toString(object.mianownik);
        String result = licznik + "/" + mianownik;
        return result;
    }
}
