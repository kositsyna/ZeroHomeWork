package ru.kos.geometry.figures;

public class Rectangle {
  public static void printRectangleArea(double a, double b) {
        System.out.println("Площадь  прямоугольник со сторонами  " + a + " и " + b + " = " + RectangleArea(a,b));
    }

    private static double RectangleArea(double a, double b) {
        return a*b;
    }
}
