package functions;

import java.io.Serializable;

public class FunctionPoint implements Serializable {
    private double x;
    private double y;

    public double getX(){ return x; }
    public double getY(){ return y; }

    public void setX(double tempX){ x = tempX; }
    public void setY(double tempY){ y = tempY; }

    public FunctionPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public FunctionPoint(FunctionPoint point) {
        this.x = point.x;
        this.y = point.y;
    }
    public FunctionPoint() {
        this.x = 0.0;
        this.y = 0.0;
    }

    @Override
    public int hashCode() {
        int hash = (int) (Double.doubleToLongBits(x) * (Double.doubleToLongBits(x) >>> 32));
        return hash + (int) (Double.doubleToLongBits(y) * (Double.doubleToLongBits(y) >>> 32));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof FunctionPoint && obj.hashCode() == this.hashCode()) {
            return (((FunctionPoint) obj).x == x) && ((FunctionPoint) obj).y == y;
        }
        return false;
    }

    @Override
    protected Object clone() {
        return new FunctionPoint(this);
    }

    @Override
    public String toString() {
        return ("(" + x + "; " + y + ")");
    }
}