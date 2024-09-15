package ru.kos.geometry.figures;

public class Square {
   public static void printSquareArea(double a)
    {
        System.out.println("Площадь  квадрата со стороной " + a + " = " + SquareArea(a));
    }

    private static double SquareArea(double a) {
        return a * a;
    }
}
