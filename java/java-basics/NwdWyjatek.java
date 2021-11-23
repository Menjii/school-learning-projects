public class NwdWyjatek {
    static int result;

    public static void main(String[] args) {
        try {
            System.out.println(nwdFunction(5, 10));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    static int nwdFunction(int numberOne, int numberTwo) {
        if (numberOne == 0 || numberTwo == 0)
            throw new IllegalArgumentException("Liczba nie moze byc rowna 0");

        if (numberOne == numberTwo) {
            result = numberOne;
        }

        while (numberOne != numberTwo) {
            if (numberOne > numberTwo) {
                numberOne -= numberTwo;
                result = numberOne;
            } else {
                numberTwo -= numberOne;
                result = numberTwo;
            }
        }
        return result;
    }
}
