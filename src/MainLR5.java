import functions.ArrayTabulatedFunction;
import functions.FunctionPoint;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;

public class MainLR5 {
    public static void main(String[] args) {
        FunctionPoint[] functionPoints = new FunctionPoint[5];
        functionPoints[0] = new FunctionPoint(2,5);
        functionPoints[1] = new FunctionPoint(4,7);
        functionPoints[2] = new FunctionPoint(6,-3);
        functionPoints[3] = new FunctionPoint(8,3);
        functionPoints[4] = new FunctionPoint(10,9);
        TabulatedFunction tab1 = new LinkedListTabulatedFunction(functionPoints);
        TabulatedFunction tab2 = new ArrayTabulatedFunction(functionPoints);
        System.out.println(tab1);
        System.out.println(tab1.equals(tab2));
    }
}
