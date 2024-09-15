package ru.kos.geometry.figures.SquareTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.kos.geometry.figures.Square;

public class SquareTests {
        @Test
    void CanCalculateArea()
    {
        var s = new Square(5.0);
        double Result = s.area();
        Assertions.assertEquals(25.0, Result);
    };
        @Test
    void CanCalculatePerimetr()
        {
            Assertions.assertEquals(20.0, new Square(5.0).perimetr());
        }
}
