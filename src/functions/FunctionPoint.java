package functions;

public class FunctionPoint {
    protected double x;
    protected double y;
    public FunctionPoint(){ x = 0; y = 0; }
    public FunctionPoint(double x, double y){
        this.x = x; this.y=y; }
    public FunctionPoint(FunctionPoint point) {
        this.x = point.x;
        this.y = point.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
