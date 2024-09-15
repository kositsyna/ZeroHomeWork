package ru.kos.geometry;

import ru.kos.geometry.figures.Rectangle;
import ru.kos.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
       Square.printSquareArea (new Square(7.0));
       Rectangle.printRectangleArea(3.0, 5.0);
       Rectangle.printRectangleArea(7.0, 9.0);
    }

}
