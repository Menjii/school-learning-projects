package com.company;

public class Miesiace {

/*
 * <code>class Months</code><br>
 * auxiliary class for Date, used to hold months array
 */
    private static Miesiac[] miesiace = {
            new Miesiac(31, 0, 1, "I","Styczen"), //January
            new Miesiac(28, 31, 2, "II","Luty"), // February
            new Miesiac(31, 59, 3, "III","Marzec"), // March
            new Miesiac(30, 90, 4, "IV","Kwiecien"), // April
            new Miesiac(31, 120, 5, "V","Maj"), // May
            new Miesiac(30, 151, 6, "VI","Czerwiec"), // June
            new Miesiac(31, 181, 7, "VII","Lipiec"), // July
            new Miesiac(31, 212, 8, "VIII","Siepien"), // August
            new Miesiac(30, 243, 9, "IX","Wrzesien"), // September
            new Miesiac(31, 273, 10, "X","Pazdziernik"), // October
            new Miesiac(30, 304, 11, "XI","Listopad"), //November
            new Miesiac(31, 334, 12, "XII","Grudzien"), // December
    };
    
    /**
     * <code>public static Month getMonth (int monthNumber)</code>
     * Method return object Month of our index given in argument.
     */
    public static Miesiac getMiesiac(int nrMiesiaca) { //getMonth monthNumber
        return miesiace[nrMiesiaca-1];
    }
}
