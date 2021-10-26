package functions.meta;

import functions.Function;

public class Composition implements Function{
    private Function a;
    private Function b;

    public Composition(Function a, Function b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getLeftDomainBorder() {
        return Math.min(a.getLeftDomainBorder(),b.getLeftDomainBorder());
    }

    @Override
    public double getRightDomainBorder() {
        return Math.min(a.getRightDomainBorder(),b.getRightDomainBorder());
    }

    @Override
    public double getFunctionValue(double x) {
        return a.getFunctionValue(b.getFunctionValue(x));
    }
}
