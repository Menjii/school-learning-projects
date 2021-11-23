package com.company;

public class Data implements Comparable<Data> {
    /*
        Value of the {@link #getDateFormat(int)} method, return long format of our date, example: Sunday, 1 October 2020
    */
    public static final int DATEFORMAT_LONG = 12369504;
    /*
        Value of the {@link #getDateFormat(int)} method, return short format of our date, example: Sun., 1-Oct-2020
    */
    public static final int DATEFORMAT_SHORT = 99504954;
     /*
        Value of the {@link #getDateFormat(int)} method, return classic format of our date, example: 1 October 2020
    */
    public static final int DATEFORMAT_CLASSIC = 69634698;
     /*
        Value of the {@link #getDateFormat(int)} method, return roman format of our date, example: 1.X.2020
    */
    public static final int DATEFORMAT_ROMAN = 53255255;
     

    Miesiace miesiace = new Miesiace(); 
    private int dzien, rok, nrMiesiaca; // day year monthNumber
    private Miesiac miesiac; 
    private String dniTygodnia[] = {"poniedzialek", "wtorek", "sroda", "czwartek", "piatek", "sobota", "niedziela"}; // daysOfTheWeekArray
    private int indexWeekDay;

    /*
     * <code>Date(int day, int month, int year)</code>
     * Constructs a <code>Date</code> object for specified date.
     * @param day: day of the date
     * @param month: month number
     * @param year: year of the date
     * @throws IllegalArgumentException
     *      <br>when month is out of 1 to 12 range <br>
     *      when day doesn`t exist on calendar with given month and year
     * indexWeekDay: holds our month number
    */
    public Data(int dzien, int miesiac, int rok) throws IllegalArgumentException {
        if (miesiac < 1 || miesiac > 12) {
            throw new IllegalArgumentException("Please enter correct month, in range: 1-12");
        }
        this.nrMiesiaca = miesiac;
        this.rok = rok;

        korektaLutego();

        if ((dzien < 1 || dzien > this.miesiac.getNumberOfDays())) {
            throw new IllegalArgumentException("Prosze podac poprawny dzień");
        }
        ;
        this.dzien = dzien;
        this.indexWeekDay = dzienTygodnia();
    }

    /*
     * <Code>public void incrementDataByWeek()</Code><br>
     * Moves date in <code>Date</code> object one week forward.
     */
    public void increment() { // incrementDataByWeek
        dzien += 7;
        if (miesiac.getNumberOfDays() < dzien) {
            nrMiesiaca++;
            if (nrMiesiaca == 13) {
                dzien -= miesiac.getNumberOfDays();
                miesiac = miesiace.getMiesiac(1);
                rok++;
            } else {
                dzien -= miesiac.getNumberOfDays();
                miesiac = miesiace.getMiesiac(nrMiesiaca);
            }
            if (nrMiesiaca == 2) {
                korektaLutego();
            }
        }
    }

    /*
     * <Code>public void decrementDataByWeek()</Code><br>
     * Moves date in <code>Date</code> object one week backward.
     */
    public void decrement() { // decrementDataByWeek
        dzien -= 7;
        if (0 >= dzien) {
            nrMiesiaca--;
            if (nrMiesiaca == 0) {
                miesiac = miesiace.getMiesiac(12);
                dzien = miesiac.getNumberOfDays() - Math.abs(dzien);
                rok--;
            } else {
                miesiac = miesiace.getMiesiac(nrMiesiaca);
                dzien = miesiac.getNumberOfDays() - Math.abs(dzien);
            }
            if (nrMiesiaca == 2) {
                korektaLutego();
            }
        }
    }

    /*
     * <Code>public boolean ifLeapYear()</Code><br>
     * Method checking if our year is leap year - returning true, else returning false.
     */
    public boolean isRokPrzystepny() { // ifLeapYear
        if ((rok % 4 == 0) && (rok % 100 != 0) || (rok % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * <Code>public void februaryCorrection()</Code><br>
     * Method changing our february month to 29 days if method ifLeapYear returns true.
     */
    public void korektaLutego() { // februaryCorrection
        if (isRokPrzystepny() && nrMiesiaca == 2) {
            this.miesiac = new Miesiac(29, 31, 2, "II",  "Luty");
        } else {
            this.miesiac = miesiace.getMiesiac(nrMiesiaca);
        }
    }

    /*
     * <Code>public int dayOfWeekModulo()</Code><br>
     * Method calculates weekday for any <code>Date</code> object using modulo algorithm.
     * @return int in range 0 - 6, from monday to sunday.
     */
    public int dzienTygodnia() { // dayOfWeekModulo
        int result, sumaDni, yy, c, g;

        sumaDni = dzien + miesiac.getAllNumberOfDays();
        if(isRokPrzystepny() && nrMiesiaca != 2 && nrMiesiaca != 1) {
            sumaDni++;
        }

        yy = (rok - 1) % 100;
        c = (rok - 1) - yy;
        g = yy + (yy / 4);
        result = ( ( ( ( (c/100) % 4) * 5) + g) % 7);
        result += sumaDni - 1;
        result %= 7;

        this.indexWeekDay = result;

        return result;
    }

    /*
     * <Code>public String dayOfWeekString()</Code><br>
     * Method return string value of our month, for example if number of our month is 5, it will return "May".
     */
    public String dzienTygodniaString() { //dayOfWeekString
        return dniTygodnia[indexWeekDay];
    }

    /*
     * <code>public int compareTo(Date date)</code>
     * Method compares <b>this</b> <code>Date</code> object to <code>Date</code> object sent as <b>parameter</b>.
     * @param date <code>Date</code> object to compare with this
     * @return -1 if <code>this</code> is <b>later</b> date than <code>date</code><br>
     *          0 if they are equal<br>
     *          1 if <code>this</code> is <b>earlier</b> date than <code>date</code>
     */
    public int compareTo(Data data) {
        if (this.rok > data.rok) {
            return -1;
        } else if (this.rok < data.rok) {
            return 1;
        } else if (this.miesiac.getMiesiac() > data.miesiac.getMiesiac()) {
            return -1;
        } else if (this.miesiac.getMiesiac() < data.miesiac.getMiesiac()) {
            return 1;
        } else if (this.dzien < data.dzien) {
            return -1;
        } else if (this.dzien > data.dzien) {
            return 1;
        }
        return 0;
    }

// metoda zwraca -1 dla dat wiekszych, 1 dla dat mniejszych;

    public int dzienTygodnia2() { //dayOfWeekRef
        Data ref = new Data(1,1,2020);
        int difference = 0;


        if(compareTo(ref) == 1) {
            while(compareTo(ref) == 1) {
                ref.decrement();
            }
            difference = (this.dzien - ref.dzien) % 7;
        } else if (compareTo(ref) == -1) {

            while(compareTo(ref) == -1) {
                ref.increment();
            }
            difference = (this.dzien - ref.dzien) % 7;
        }
        this.indexWeekDay = (ref.indexWeekDay + difference) % 7;

         return (ref.indexWeekDay + difference) % 7;
    }

    /**
     * <code>public String getDateFormat(int formatType)</code>
     * @param par parameter that defines date format returned by method.<br>
     * Allowed parameters are:
     * <ul>
     *            <li>{@link #DATEFORMAT_ROMAN}</li> 
     *            <li>{@link #DATEFORMAT_LONG}</li>
     *            <li>{@link #DATEFORMAT_CLASSIC}</li>
     *            <li>{@link #DATEFORMAT_SHORT}</li>
     * </ul>
     * @return String value of <code>Date</code> object, with format specified by parameter.
     * @exception IllegalArgumentException
     * if par is not one of constants defined by class.
     */
    public String getDateFormat (int formatType) {
        String date = "";

        switch(formatType){
            case 12369504:
                date = dzienTygodniaString() + "," + this.dzien + " " + this.miesiac.getName() + " " + this.rok;
                break;
            case 99504954:
                date = dzienTygodniaString().substring(0,2) + "., " + this.dzien + "-" + this.miesiac.getName().substring(0,3) + "-" + this.rok;
                break;
            case 69634698:
                date = this.dzien + " " + this.miesiac.getName() + " " + this.rok;
                break;
            case 53255255:
                date = this.dzien + "." + this.miesiac.getNumberMonthRoman() + "." + this.rok;
                break;
            default:
                throw new IllegalArgumentException("Prosze podac date w odpowiednim formacie");
        }
        return date;
    }
}
