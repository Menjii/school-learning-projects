package com.company;
/*
 * <code>class Month</code><br>
 * auxiliary class for Date, defines each Month in {@link Months} class
 */
public class Miesiac {
    private int iloscDni, miesiac, sumaWszystkichDni;
    private String nazwa, numberMonthRoman;

    /*
     * <code>public Month(int number, String roman_number, String name, int how_many_days)</code>
     * Constructor for Month class
     * @param number number representation of month
     * @param roman_number roman number representation of month
     * @param name String name of month
     * @param how_many_days defines how many days are in each month in <b>non leap year</b>
     */
    public Miesiac(int iloscDni, int sumaWszystkichDni, int miesiac, String numberMonthRoman, String nazwa){
        this.iloscDni = iloscDni;
        this.miesiac = miesiac;
        this.numberMonthRoman = numberMonthRoman;
        this.nazwa = nazwa;
        this.sumaWszystkichDni = sumaWszystkichDni;
    }

    public String getName() {
        return nazwa;
    }

    public int getNumberOfDays() {
        return iloscDni;
    }

    public int getAllNumberOfDays() {
        return sumaWszystkichDni;
    }

    public int getMiesiac() { return miesiac; }

    public String getNumberMonthRoman() { return numberMonthRoman; }

}
