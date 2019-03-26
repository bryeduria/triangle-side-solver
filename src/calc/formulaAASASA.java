package calc;

public class formulaAASASA {

    public double findMissingAngle( String x, String y) {

        Double angle1 = Double.valueOf(x);
        Double angle2 = Double.valueOf(y);

        Double missingAngle = 180 - angle1 - angle2;

        return missingAngle;
    }

    public double findMissingSide1( String x, String y, String z) {

        Double angle1 = Double.valueOf(x);
        Double angle2 = Double.valueOf(y);
        Double side1 = Double.valueOf(z);

        Double missingSide1 = (side1 * Math.sin(angle1))/Math.sin(angle2);

        return missingSide1;
    }

    public double findMissingSide2(String x, String y, String z) {

        Double angle1 = Double.valueOf(x);
        Double angle2 = Double.valueOf(y);
        Double side1 = Double.valueOf(z);

        //Formulas for SAA
        Double missingAngle = 180 - angle1 - angle2;
        Double converted = angle2 * Math.PI / 180;
        Double missingSide2 = (Math.sin(missingAngle) * side1);

        return missingSide2 / Math.sin(converted);
    }


    // c =







}
