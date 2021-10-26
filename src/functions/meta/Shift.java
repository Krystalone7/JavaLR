package functions.meta;

import functions.Function;

public class Shift implements Function {
    private Function a;
    private double x1;
    private double y1;
    public Shift(Function a, double x1, double y1) {
        this.a = a;
        this.x1 = x1;
        this.y1 = y1;
    }

    @Override
    public double getLeftDomainBorder() {
        return a.getLeftDomainBorder() + x1;
    }

    @Override
    public double getRightDomainBorder() {
        return a.getRightDomainBorder() + x1;
    }

    @Override
    public double getFunctionValue(double x) {
        return a.getFunctionValue(x) + y1;
    }
}
