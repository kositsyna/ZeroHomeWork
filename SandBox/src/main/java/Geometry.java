public class Geometry {
    public static void main(String[] args) {
       printSquareArea ( 7);
    }
    static void printSquareArea(double a)
    {
        System.out.println("Площадь  квадрата со стороной " + a + " = " + SquareArea(a));
    }

    private static double SquareArea(double a) {
        return a * a;
    }
}
