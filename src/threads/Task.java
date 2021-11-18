package threads;

import functions.*;

public class Task {
    private Function function;
    private double leftX;
    private double rightX;
    private double step;
    private int count;

    public Function getFunction() {
        return function;
    }

    public double getLeftX() {
        return leftX;
    }

    public double getRightX() {
        return rightX;
    }

    public double getStep() {
        return step;
    }

    public int getCount() {
        return count;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public void setLeftX(double leftX) {
        this.leftX = leftX;
    }

    public void setRightX(double rightX) {
        this.rightX = rightX;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public Task(int count) {
        this.count = count;
    }

}
