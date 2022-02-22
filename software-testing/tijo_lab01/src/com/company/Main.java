package com.company;

public class Main {

    public static void printFigure(Figure figure) {
        figure.print(5);
    }

    public static void main(String[] args) {
        Figure square = new Square();
        Figure triangle = new Triangle();

        printFigure(square);
        System.out.println("\n");
        printFigure(triangle);
    }
}
