package functions.meta;

import functions.Function;

public class Scale implements Function {
    private Function a;
    private double x1;
    private double y;


    public Scale(Function a, double x1, double y) {
        this.x1 = x1;
        this.y = y;
        this.a = a;
    }

    @Override
    public double getLeftDomainBorder() {
        if (x1 >= 0){
            return a.getLeftDomainBorder()*x1;
        } else{
            return a.getLeftDomainBorder()/Math.abs(x1);
        }
    }

    @Override
    public double getRightDomainBorder() {
        if (x1 >= 0){
            return a.getRightDomainBorder()*x1;
        } else{
            return a.getRightDomainBorder()/Math.abs(x1);
        }
    }

    @Override
    public double getFunctionValue(double x) {
        if (y >=0 ){
            return a.getFunctionValue(x) * y;
        } else {
            return a.getFunctionValue(x) / Math.abs(y);
        }
    }
}
