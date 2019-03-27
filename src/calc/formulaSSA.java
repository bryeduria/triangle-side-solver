package calc;


public class formulaSSA {


    public double findFirstAngle(String x, String y, String z) {

        Double side1 = Double.valueOf(x);
        Double side2 = Double.valueOf(y);
        Double angle = Double.valueOf(z);

        angle = angle * Math.PI/180;
        Double sinAngle = (side2 * Math.sin(angle)) / side1;

        Double firstAngle = Math.asin(sinAngle);

        return Math.toDegrees(firstAngle);
    }

    public double findSecondAngle(String x, String y, String z) {

        Double secondAngle = 180 - Double.valueOf(findFirstAngle(x, y, z)) ;

        secondAngle = secondAngle - Double.valueOf(z);

        return secondAngle;
    }

    public double findMissingSide(String x, String y, String z) {

        Double angle2 = Double.valueOf(findSecondAngle(x, y, z));

        Double missingSide = (Math.sin(angle2) * Double.valueOf(x));

        Double mside = missingSide / Math.sin(Double.valueOf(z));

        return mside;
    }

}
