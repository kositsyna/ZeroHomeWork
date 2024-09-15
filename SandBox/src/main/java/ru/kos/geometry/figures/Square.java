package ru.kos.geometry.figures;

public class Square {
   public static void printSquareArea(double a)
    {
        String Text = String.format("Площадь  квадрата со стороной %f = %f", a, Area(a));
        System.out.println(Text);
    }

    public static double Area(double a) {
        return a * a;
    }

    public static double perimetr(double a) {
       return 4*a;
    }
}
