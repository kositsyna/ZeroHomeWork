package ru.kos.geometry.figures;

import java.util.Objects;

public record Rectangle (double a, double b) {

     public static void printRectangleArea(double a, double b) {
      var Text = String.format("Площадь  прямоугольник со сторонами %f и %f = %f", a, b, RectangleArea(a,b));
        System.out.println(Text);
    }

    private static double RectangleArea(double a, double b) {
        return a*b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return (Double.compare(a, rectangle.a) == 0 && Double.compare(b, rectangle.b) == 0)
                ||(Double.compare(a, rectangle.b) == 0 && Double.compare(b, rectangle.a) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
