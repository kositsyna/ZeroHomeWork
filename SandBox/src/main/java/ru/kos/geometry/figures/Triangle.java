package ru.kos.geometry.figures;

public record Triangle(double sideone, double sidetwo, double sidethree)
    {
        public Triangle {
            if (sideone <0 || sidetwo < 0 || sidethree <0 || (sideone + sidetwo) < sidethree ||(sideone + sidethree) < sidetwo ||(sidetwo + sidethree) < sideone) {
                throw new IllegalArgumentException ("Такой треугольник не может существовать");
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

}
