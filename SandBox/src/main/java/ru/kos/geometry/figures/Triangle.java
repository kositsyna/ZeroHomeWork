package ru.kos.geometry.figures;

import java.util.Objects;

public record Triangle(double sideone, double sidetwo, double sidethree)
    {
        public Triangle {
            if (sideone <0 || sidetwo < 0 || sidethree <0){
                throw new IllegalArgumentException ("Треугольник с отрицательными сторонами не может сущестовать");
            };
            if ((sideone + sidetwo) < sidethree ||(sideone + sidethree) < sidetwo ||(sidetwo + sidethree) < sideone) {
                throw new IllegalArgumentException ("Такой треугольник не может существовать. Нарушено правило существования треугольника: Треугольник существует только тогда, когда сумма двух его сторон больше третьей");
            }
        }

    public static void printTriangleArea(Triangle t)
    {
        var Text = String.format("Площадь  треугольника со сторонами %f, %f и %f = %f", t.sideone,t.sidetwo,t.sidethree,t.TriArea());
        System.out.println(Text);
    }
    public static void printTriPerimetr(Triangle t)
    {
        var Text = String.format("Периметр треугольника со сторонами %f, %f и %f = %f",t.sideone,t.sidetwo,t.sidethree,t.TriPerimetr());
        System.out.println(Text);
    }

    public  double TriPerimetr()
    {
        double PerResult = this.sideone + this.sidetwo + this.sidethree;
        return PerResult;
    }

    public double TriArea()
    {
        double HalphPerimetr = (this.sideone + this.sidetwo + this.sidethree)/2;
        return Math.sqrt(HalphPerimetr*(HalphPerimetr - this.sideone)*(HalphPerimetr-this.sidetwo)*(HalphPerimetr-this.sidethree));
    }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Triangle triangle = (Triangle) o;
            return (Double.compare(sideone, triangle.sideone) == 0 && Double.compare(sidetwo, triangle.sidetwo) == 0 && Double.compare(sidethree, triangle.sidethree) == 0)
                    ||(Double.compare(sideone, triangle.sidethree) == 0 && Double.compare(sidetwo, triangle.sideone) == 0 && Double.compare(sidethree, triangle.sidetwo) == 0)
                    ||(Double.compare(sideone, triangle.sidetwo) == 0 && Double.compare(sidetwo, triangle.sidethree) == 0 && Double.compare(sidethree, triangle.sideone) == 0);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sideone, sidetwo, sidethree);
        }
    }
