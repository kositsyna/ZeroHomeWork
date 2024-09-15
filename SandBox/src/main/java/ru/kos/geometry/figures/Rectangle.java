package ru.kos.geometry.figures;

public record Rectangle (double a, double b) {

     public static void printRectangleArea(double a, double b) {
      var Text = String.format("Площадь  прямоугольник со сторонами %f и %f = %f", a, b, RectangleArea(a,b));
        System.out.println(Text);
    }

    private static double RectangleArea(double a, double b) {
        return a*b;
    }
}
