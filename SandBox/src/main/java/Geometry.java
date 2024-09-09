public class Geometry {
    public static void main(String[] args) {
       printSquareArea ( 7);
       printRectangleArea(3.0, 5.0);
    }

    private static void printRectangleArea(double a, double b) {
        System.out.println("Площадь  прямоугольник со сторонами  " + a + " = " + SquareArea(a))
    }

    static void printSquareArea(double a)
    {
        System.out.println("Площадь  квадрата со стороной " + a + " = " + SquareArea(a));
    }

    private static double SquareArea(double a) {
        return a * a;
    }
}
