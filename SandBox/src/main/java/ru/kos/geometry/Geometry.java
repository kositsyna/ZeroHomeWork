package ru.kos.geometry;

import ru.kos.geometry.figures.Rectangle;
import ru.kos.geometry.figures.Square;
import ru.kos.geometry.figures.Triangle;

import java.util.List;
import java.util.function.Consumer;

public class Geometry {
    public static void main(String[] args) {
        var squares = List.of(new Square(7.0),new Square(5.0),new Square(3.0));
//        for (Square square : squares) {
//            Square.printSquareArea(square);
//        }
        Consumer<Square> print = (square -> {Square.printSquareArea(square);
        }
        );
        squares.forEach(print);
//       Rectangle.printRectangleArea(3.0, 5.0);
//       Rectangle.printRectangleArea(7.0, 9.0);
//       Triangle.printTriPerimetr(new Triangle(3.0,4.0,5.0));
//       Triangle.printTriangleArea(new Triangle(1.0,2.0,3.0));
    }

}
