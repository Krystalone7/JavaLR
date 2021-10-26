package functions.meta;

import functions.Function;

public class Power implements Function {
    private double p;
    private Function a;

    public Power (Function a, double p){
        this.a = a;
        this.p = p;
    }

    @Override
    public double getLeftDomainBorder() {
        return a.getLeftDomainBorder();
    }

    @Override
    public double getRightDomainBorder() {
        return a.getRightDomainBorder();
    }

    @Override
    public double getFunctionValue(double x) {
        return Math.pow(a.getFunctionValue(x) , p);
    }
}
