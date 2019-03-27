package calc;

//"SAS" is when we know two sides and the angle between them.
public class formulaSAS {

    public double findSideA(String x, String y, String z) {
        Double side1 = Double.valueOf(x);
        Double side2 = Double.valueOf(y);
        Double angle = Double.valueOf(z);
//        angle = angle * Math.PI/180;

//Math.sqrt(Math.pow(b, 2) + Math.pow(c, 2) - 2 * b * c * Math.cos(A))
        return Math.cos(angle);
    }


}
