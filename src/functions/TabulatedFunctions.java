package functions;

import java.io.*;
import java.nio.charset.StandardCharsets;

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
        try(DataOutputStream dataOutputStream = new DataOutputStream(out)) {
            dataOutputStream.write(function.getPointsCount());
            for (int i = 0; i < function.getPointsCount(); i++) {
                dataOutputStream.write((" " + function.getPointX(i) + " " + function.getPointY(i)).getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static TabulatedFunction inputTabulatedFunction(InputStream inputStream) {
        FunctionPoint[] list = new FunctionPoint[0];
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            int size = dataInputStream.readInt();
            list = new FunctionPoint[size];
            for (int j = 0; j < size; j++) {
                int x = dataInputStream.readInt();
                int y = dataInputStream.readInt();
                list[j] = new FunctionPoint(x,y);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayTabulatedFunction(list);
    }

}
