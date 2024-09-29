package ru.kos.geometry.figures;

import ru.kos.geometry.figures.Triangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void CanCalculateTriArea()
    {
        var s = new Triangle(3.0, 4.0, 5.0);
        double Result = s.TriArea();
        Assertions.assertEquals(6.0, Result);
    };
    @Test
    void CanCalculateTriPerimetr()
    {
        Assertions.assertEquals(12.0, new Triangle(3.0,4.0,5.0).TriPerimetr());
    }
    @Test
    void CantCreateTriangle() {
        try {
            new Triangle(1.0,2.0,3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){

        }
    }
    @Test
    void testEquality(){
        var r1 = new Triangle(5.0,4.0,8.0);
        var r2 = new Triangle(5.0,4.0,8.0);
        Assertions.assertEquals(r1,r2);
    }
    @Test
    void testEquality2(){
        var r1 = new Triangle(3.0,4.0,5.0);
        var r2 = new Triangle(4.0,5.0,3.0);
        Assertions.assertEquals(r1,r2);
    }

    }