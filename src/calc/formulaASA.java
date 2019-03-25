package calc;


//"ASA" is when we know two angles and a side between the angles.
public class formulaASA {

    public double findMissingAngle1( String x, String y) {

        Double angle1 = Double.valueOf(x);
        Double angle2 = Double.valueOf(y);

        Double missingAngle1 = 180 - angle1 - angle2;

        return missingAngle1;
    }

    public double findMissingSide1( String x, String y, String z) {

        Double angle1 = Double.valueOf(x);
        Double angle2 = Double.valueOf(y);
        Double side1 = Double.valueOf(z);

        Double missingSide1 = (side1 * Math.sin(angle1))/Math.sin(angle2);

        return missingSide1;
    }


}
