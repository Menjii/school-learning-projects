package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Data> listWithDataObjects = new ArrayList<>();

        try {

            listWithDataObjects.add(new Data(15, 12, 2011));
            listWithDataObjects.add(new Data(23, 6, 2025));
            listWithDataObjects.add(new Data(25, 1, 2000));
            listWithDataObjects.add(new Data(26, 10, 1999));
            listWithDataObjects.add(new Data(30, 7, 2030));

            Data[] arrayOfDataObjects = {
                    new Data(15, 12, 2011),
                    new Data(23, 6, 2025),
                    new Data(25, 1, 2000),
                    new Data(26, 10, 1999),
                    new Data(30, 7, 2030),
            };

            System.out.println("Bez funkcji sort");
            System.out.println("####################");
            System.out.println("tablica:");
            for (int i = 0; i < 5; i++) {
                System.out.println(arrayOfDataObjects[i].getDateFormat(Data.DATEFORMAT_CLASSIC));
            }

            System.out.println("####################");
            System.out.println("lista:");

            for (Data item : listWithDataObjects) {
                System.out.println(item.getDateFormat(Data.DATEFORMAT_CLASSIC));
            }

            Arrays.sort(arrayOfDataObjects, Data::compareTo);
            Collections.sort(listWithDataObjects, Data::compareTo);

            System.out.println("Z funkcja sort");
            System.out.println("####################");
            System.out.println("tablica:");
            for (int i = 0; i < 5; i++) {
                System.out.println(arrayOfDataObjects[i].getDateFormat(Data.DATEFORMAT_CLASSIC));
            }

            System.out.println("####################");
            System.out.println("lista:");

            for (Data item : listWithDataObjects) {
                System.out.println(item.getDateFormat(Data.DATEFORMAT_CLASSIC));
            }


            // Testowanie dzialania klas, obiektow, funkcji
            Data today = new Data(5, 1, 2020);
            System.out.println(today.getDateFormat(Data.DATEFORMAT_LONG));
            today.dzienTygodnia();
            System.out.println(today.dzienTygodniaString());

            today.increment();
            System.out.println(today.getDateFormat(Data.DATEFORMAT_SHORT));

            Data luty = new Data(14, 2, 2020);
            System.out.println(luty.getDateFormat(Data.DATEFORMAT_ROMAN));

            luty.dzienTygodnia2();
            System.out.println(luty.dzienTygodniaString());

            Data Marzec = new Data(15, 3, 2021);
            System.out.println(luty.getDateFormat(Data.DATEFORMAT_CLASSIC));

        } catch (IllegalArgumentException exception) {
            System.out.println(exception);
        }
    }
}
