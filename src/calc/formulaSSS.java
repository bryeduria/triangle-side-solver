package calc;

public class formulaSSS {

    public double findAngleA(String x, String y, String z) {

        Double a = Double.valueOf(x);
        Double b = Double.valueOf(y);
        Double c = Double.valueOf(z);

        Double findCosA = (Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2));

        Double cosA = findCosA / (2 * b * c);


        return Math.acos(cosA) * 180/Math.PI;
    }

    public double findAngleB(String x, String y, String z) {

        Double a = Double.valueOf(x);
        Double b = Double.valueOf(y);
        Double c = Double.valueOf(z);

        Double findCosB = (Math.pow(c, 2) + Math.pow(a, 2) - Math.pow(b, 2));
        Double cosB = findCosB / (2 * c * a);


        return Math.acos(cosB) * 180/Math.PI;
    }

    public double findAngleC(String x, String y, String z) {

        Double a = Double.valueOf(x);
        Double b = Double.valueOf(y);
        Double c = Double.valueOf(z);

        Double findCosA = (Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2));
        Double cosA = findCosA / (2 * b * c);
        Double A = Math.acos(cosA) * 180/Math.PI;

        Double findCosB = (Math.pow(c, 2) + Math.pow(a, 2) - Math.pow(b, 2));
        Double cosB = findCosB / (2 * c * a);
        Double B = Math.acos(cosB) * 180/Math.PI;

        return 180 - A - B;
    }


}
