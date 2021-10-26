package functions;

import java.io.DataOutputStream;
import java.io.OutputStream;

public class TabulatedFunctions {
    public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount) throws IllegalArgumentException{
        if (leftX < function.getLeftDomainBorder() || rightX > function.getRightDomainBorder()){
            throw new IllegalArgumentException();
        }
        FunctionPoint[] points = new FunctionPoint[pointsCount];
        points[0] = new FunctionPoint(leftX, function.getFunctionValue(leftX));
        for(int i = 1; i < pointsCount; i++) {
            points[i] = new FunctionPoint(points[i-1].getX() + (rightX - leftX) / (pointsCount - 1), function.getFunctionValue( + (rightX - leftX) / (pointsCount - 1)));
        }
        return new ArrayTabulatedFunction(points);
    }
    public static void outputTabulatedFunction (TabulatedFunction function, OutputStream out){
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        //dataOutputStream.write();
    }
}
