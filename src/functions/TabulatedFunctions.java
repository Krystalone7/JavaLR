package functions;

import java.io.*;

public class TabulatedFunctions {
    private TabulatedFunctions() {}

    public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount) throws IllegalArgumentException {
        ArrayTabulatedFunction arr = new ArrayTabulatedFunction(leftX, rightX, pointsCount);
        for(int i = 1; i < pointsCount; i++) {
            arr.setPointY(i, function.getFunctionValue(arr.getPointX(i)));
        }
        return arr;
    }
    public static void outputTabulatedFunction(TabulatedFunction function, OutputStream out) {
        try {
            int count = function.getPointsCount();
            DataOutputStream data = new DataOutputStream(out);
            data.writeInt(count);
            for (int i = 0; i < count; i++) {
                data.writeDouble(function.getPointX(i));
                data.writeDouble(function.getPointY(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static TabulatedFunction inputTabulatedFunction(InputStream in) {
        FunctionPoint[] points = new FunctionPoint[0];
        try(DataInputStream data = new DataInputStream(in)) {
            int count = data.readInt();
            points = new FunctionPoint[count];
            for (int i = 0; i < count; i++) {
                points[i] = new FunctionPoint(data.readDouble(), data.readDouble());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayTabulatedFunction(points);
    }
    public static void writeTabulatedFunction(TabulatedFunction function, Writer out) {
            PrintWriter writer = new PrintWriter(out);
            writer.println(function.getPointsCount());
            for (int i = 0; i < function.getPointsCount(); i++) {
                writer.println(function.getPointX(i) + " " + function.getPointY(i));
            }
    }
    public static TabulatedFunction readTabulatedFunction(Reader in) {
        FunctionPoint[] points = new FunctionPoint[0];
        try {
            StreamTokenizer tokenizer = new StreamTokenizer(in);
            tokenizer.nextToken();
            int count = (int) tokenizer.nval;
            points = new FunctionPoint[count];
            double x, y;
            for (int i = 0; i < count; i++) {
                tokenizer.nextToken();
                x = tokenizer.nval;
                tokenizer.nextToken();
                y = tokenizer.nval;
                points[i] = new FunctionPoint(x, y);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayTabulatedFunction(points);
    }
}
