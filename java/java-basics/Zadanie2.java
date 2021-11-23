public class Zadanie2 {
    public static void main(String[] args) {

        Equation rownanie = new Equation(10, 2, 1);
        double[] result = rownanie.quadraticEquationCalc();

        if (result.length == 0) {
            System.out.println("Brak rozwiazania w przedziale liczb rzeczywistych.");
        } else if (result.length == 2) {
            System.out.println(result[0] + " " + result[1]);
        } else {
            System.out.println(result[0]);
        }

    }
}

class Equation {
    private double a, b, c;

    public Equation(double ca, double cb, double cc) {
        this.a = ca;
        this.b = cb;
        this.c = cc;
    }

    public double[] quadraticEquationCalc() {
        double delta = b * b - 4 * a * c;
        double x1, x2;

        if (delta < 0) {
            return new double[] {};
        } else if (delta == 0) {
            x1 = -b / 2 * a;
            return new double[] { x1 };
        } else {
            x1 = (-b - Math.sqrt(delta)) / (2 * a);
            x2 = (-b + Math.sqrt(delta)) / (2 * a);
            return new double[] { x1, x2 };
        }
    }
}
