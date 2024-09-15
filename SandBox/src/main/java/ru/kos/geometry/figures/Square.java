package ru.kos.geometry.figures;

public class Square {

    private double side;

    public Square(double side) {
        this.side = side;
    }

    public static void printSquareArea(Square s)
    {
        String Text = String.format("Площадь  квадрата со стороной %f = %f",s.side,s.area());
        System.out.println(Text);
    }

    public double area() {
        return this.side * this.side;
    }

    public double perimetr() {
        return 4 * this.side;
    }
}
