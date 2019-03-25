package calc;

//"SAS" is when we know two sides and the angle between them.
public class formulaSAS {

    public double findMissingSide(String x, String y, String z) {
        Double side1 = Double.valueOf(x);
        Double side2 = Double.valueOf(y);
        Double angle = Double.valueOf(z);

        Double missingSide = Math.pow(side1, 2) + Math.pow(side2, 2) - 2 * side1 * side2 * Math.cos(angle);

        return missingSide;
    }


}
