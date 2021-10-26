import functions.*;
public class Main{
    public static void main(String[] args) {
        double[] b = new double[5];
        b[0] = 1.5;
        b[1] = 4;
        b[2] = 6;
        b[3] = 5.7;
        b[4] = 9;
        ArrayTabulatedFunction f2 = new ArrayTabulatedFunction(1, 10, b);
        /*for (int i = 0; i < f2.getPointsCount(); i++) {
            System.out.println(f2.getPointX(i) + "   " + f2.getPointY(i));
        }*/
        FunctionPoint po = new FunctionPoint(5.5, 7);
        try{
            TabulatedFunction tab = new LinkedListTabulatedFunction();


        }
        catch(Exception k){
            System.out.println("Error");
        }
    }
}
