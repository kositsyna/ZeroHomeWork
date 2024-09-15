package ru.kos.geometry.figures;

public class Square {
   public static void printSquareArea(double a)
    {
        String Text = String.format("Площадь  квадрата со стороной %f = %f", a, SquareArea(a));
        System.out.println(Text);
    }

    private static double SquareArea(double a) {
        return a * a;
    }
}
