package functions.basic;

import functions.Function;

public abstract class TrigonometricFunction implements Function {
    @Override
    public double getLeftDomainBorder() {
        return -1;
    }

    @Override
    public double getRightDomainBorder() {
        return 1;
    }
}
