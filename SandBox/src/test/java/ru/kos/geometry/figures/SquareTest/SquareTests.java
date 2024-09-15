package ru.kos.geometry.figures.SquareTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.kos.geometry.figures.Square;

public class SquareTests {
        @Test
    void CanCalculateArea()
    {
        double Result = Square.Area(5.0);
        Assertions.assertEquals(25.0, Result);
    };
        @Test
    void CanCalculatePerimetr()
        {
            Assertions.assertEquals(20.0, Square.perimetr(5.0));
        }
}
