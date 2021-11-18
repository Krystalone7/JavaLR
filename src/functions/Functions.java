package functions;

import functions.meta.*;

public class Functions {
    private Functions() {}

    public static Function shift(Function f, double shiftX, double shiftY) {
        return new Shift(f, shiftX, shiftY);
    }
    public static Function scale(Function f, double scaleX, double scaleY) {
        return new Scale(f, scaleX, scaleY);
    }
    public static Function power(Function f, double power) {
        return new Power(f, power);
    }
    public static Function sum(Function f1, Function f2) {
        return new Sum(f1, f2);
    }
    public static Function mult(Function f1, Function f2) {
        return new Mult(f1, f2);
    }
    public static Function composition(Function f1, Function f2) {
        return new Composition(f1, f2);
    }
    public static double integral(Function function, double leftX, double rightX, double step) throws IllegalArgumentException{
        if (leftX < function.getLeftDomainBorder() || rightX > function.getRightDomainBorder() || leftX > rightX){
            throw new IllegalArgumentException();
        }
        double left = leftX;
        double count = (rightX - left) / step;
        double integral = 0;
        for (int i = 0; i < count; i++) {
            double right = Math.min(rightX, left + step);
            integral += (function.getFunctionValue(left) + function.getFunctionValue(right)) / 2 * ((right - left) / count);
            left += step;
        }
        return integral;
    }
}
