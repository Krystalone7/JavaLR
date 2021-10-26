package functions;

import sun.security.x509.OtherName;

public class ArrayTabulatedFunction implements TabulatedFunction {

    protected FunctionPoint[] arr;
    protected int len;
    public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount) throws IllegalArgumentException{
        if (leftX >= rightX || pointsCount<2){
            throw new IllegalArgumentException();
        }
        arr = new FunctionPoint[pointsCount + pointsCount/2];
        len = pointsCount;
        arr[0] = new FunctionPoint(leftX,0);
        for (int i = 1; i < pointsCount; i++) {
            arr[i] = new FunctionPoint(arr[i-1].x + (rightX - leftX) / (pointsCount - 1), 0);
        }
    }
    public ArrayTabulatedFunction(double leftX, double rightX, double[] values) {
        if (leftX >= rightX || values.length<2){
            throw new IllegalArgumentException();
        }
        int count = values.length;
        arr = new FunctionPoint[count + count/2];
        len = count;
        arr[0] = new FunctionPoint(leftX,values[0]);
        for (int i = 1; i < count; i++) {
            arr[i] = new FunctionPoint(arr[i-1].x + (rightX - leftX) / (count - 1), values[i]);
        }
    }
    public ArrayTabulatedFunction(FunctionPoint[] mass) throws IllegalArgumentException {
        if (mass.length < 2) {
            throw new IllegalArgumentException();
        }
        len = mass.length;
        for (int i = 1; i < mass.length; i++) {
            if (mass[i-1].getX() >= mass[i].getX()) {
                throw new IllegalArgumentException();
            }
        }
        arr = new FunctionPoint[mass.length + mass.length / 2];
        System.arraycopy(mass, 0, arr, 0, mass.length);
    }
    //444
    public double getLeftDomainBorder() {
        return arr[0].x;
    }
    public double getRightDomainBorder() {
        return arr[len - 1].x;
    }
    public double getFunctionValue(double x) {
        if (x >= getLeftDomainBorder() && x <= getRightDomainBorder()) {
            for (int i = 0; i < len; i++) {
                if (arr[i].x < x && arr[i + 1].x > x) {
                    System.out.println(i + " " + (i + 1));
                    double k = (arr[i + 1].y - arr[i].y) / (arr[i + 1].x - arr[i].x);
                    double b = (arr[i + 1].y - k * arr[i + 1].x);
                    System.out.println(k+ " "+b);
                    return (k*x + b);
                }
            }
        }
        return Double.NaN;
    }
    //555
    public int getPointsCount(){
        return len;
    }
    public FunctionPoint getPoint(int index) throws FunctionPointIndexOutOfBoundsException{
        if (index > len || index < 0){
            throw new FunctionPointIndexOutOfBoundsException();
        }
        else{
            return arr[index];
        }
    }
    public void setPoint(int index, FunctionPoint point) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException{
        if (index < 0 || index >= len){
            throw new FunctionPointIndexOutOfBoundsException();
        }
        else if (index == 0 && point.x< arr[1].x){
            arr[0] = point;
        }
        else if (index == len-1 && point.x > arr[len-1].x){
            arr[index] = point;
        }
        else if (point.x>= arr[index-1].x && point.x <= arr[index+1].x){
            arr[index] = point;
        }
        else{
            throw new InappropriateFunctionPointException();
        }
    }
    public double getPointX (int index) throws FunctionPointIndexOutOfBoundsException {
        if (index > len-1 && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException();
        }
        else{
            return arr[index].x;
        }
    }
    public void setPointX(int index, double x) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException{
        if (index < 0 || index >= len){
            throw new FunctionPointIndexOutOfBoundsException();
        }
        else if (index == 0 && x< arr[1].x){
            arr[0].x = x;
        }
        else if (index == len-1 && x > arr[len-1].x){
            arr[index].x = x;
        }
        else if (x>= arr[index-1].x && x <= arr[index+1].x){
            arr[index].x = x;
        }
        else{
            throw new InappropriateFunctionPointException();
        }
    }
    public double getPointY (int index) {
        if (index > len-1 && index < 0) {
            throw new FunctionPointIndexOutOfBoundsException();
        }
        else{
            return arr[index].y;
        }
    }
    public void setPointY(int index, double y){
        if (index < 0 && index >= len){
            throw new FunctionPointIndexOutOfBoundsException();
        }
        else{
            arr[index].y = y;
        }
    }
    //666
    public void deletePoint(int index) throws FunctionPointIndexOutOfBoundsException{
        if (len < 3){
            throw new IllegalArgumentException();
        }
        else if (index < 0 || index >= len){
            throw new FunctionPointIndexOutOfBoundsException();
        }
        else {
            System.arraycopy(arr, index + 1, arr, index, len - index - 1);
            len--;
        }
    }
    public void addPoint (FunctionPoint point) throws InappropriateFunctionPointException{
        for (int i = 0; i < len; i++) {
            if (arr[i].x == point.x){
                throw new InappropriateFunctionPointException();
            }
            else if (arr[i].x < point.x && arr[i + 1].x > point.x) {
                if (arr.length > len){
                    System.arraycopy(arr, i+1, arr, i+2, len-i-1);
                    arr[i+1] = point;
                    len++;
                }
                else{
                    FunctionPoint[] f3 = new FunctionPoint[len + len/2];
                    System.arraycopy(arr, 0, f3, 0, len);
                    System.arraycopy(f3, i+1, f3, i+2, len-i-1);
                    arr = f3;
                    arr[i+1] = point;
                    len++;
                    }
            }
        }
    }
}
