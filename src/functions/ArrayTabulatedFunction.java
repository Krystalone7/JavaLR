package functions;

import java.io.Serializable;
import java.util.Arrays;

public class ArrayTabulatedFunction implements TabulatedFunction, Serializable {
    private FunctionPoint[] arr;
    private int len;
    public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount) throws IllegalArgumentException{
        if(leftX >= rightX || pointsCount < 2){ throw new IllegalArgumentException(); }
        arr = new FunctionPoint[pointsCount + pointsCount / 2];
        arr[0] = new FunctionPoint(leftX, 0);
        for(int i = 1; i < pointsCount; i++) {
            arr[i] = new FunctionPoint(arr[i-1].getX() + (rightX - leftX) / (pointsCount - 1), 0);
        }
        len = pointsCount;
    }
    public ArrayTabulatedFunction(double leftX, double rightX, double[] values) {
        if(leftX >= rightX || values.length < 3){ throw new IllegalArgumentException(); }
        arr = new FunctionPoint[values.length + values.length / 2];
        arr[0] = new FunctionPoint(leftX, values[0]);
        for(int i = 1; i < values.length; i++) {
            arr[i] = new FunctionPoint(arr[i-1].getX() + (rightX - leftX) / (values.length - 1), values[i]);
        }
        len = values.length;
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
    @Override
    public double getLeftDomainBorder() {
        return this.arr[0].getX();
    }
    @Override
    public double getRightDomainBorder() {
        return this.arr[len - 1].getX();
    }
    @Override
    public double getFunctionValue(double x) {
        if(x >= getLeftDomainBorder() && x <= getRightDomainBorder()) {
            int first = 0;
            int last = len;
            int mid = first + (last - first) / 2;
            if (arr.length == 0) { //если точек нет
                return Double.NaN;
            }
            if (x < arr[0].getX()) { //если x выходит за правую границу
                return Double.NaN;
            }
            if (arr[len - 1].getX() < x) { //если x левее левой границы
                return Double.NaN;
            }
            while(first < last){ //бинарный поиск
                if(x <= arr[mid].getX()) { //если x меньше x средней точки
                    last = mid; //значит отбрасываем вторую часть после середины
                } else {
                    first = mid + 1; //отбрасываем первую часть до середины
                }
                mid = first + (last - first) / 2; //устанавливаем середину в оставшейся части(новой области)
            }
            if(x == arr[last].getX()) { //если нашли точку с таким же x, то возвращаем значение фукнции
                return arr[last].getY();
            } else { //уравнение прямой: y=kx+b
                double k = (arr[last].getY() - arr[last - 1].getY()) / (arr[last].getX() - arr[last - 1].getX()); //k = (y1-y2)/(x1-x2)
                double b = arr[last].getY() - k * arr[last].getX(); //b = y-kx
                //System.out.println("k = " + k + ", b = " + b);
                return k * x + b;
            }
        } else {
            return Double.NaN;
        }
    }
    @Override
    public int getPointsCount() {
        return len;
    }
    @Override
    public FunctionPoint getPoint(int index) throws FunctionPointIndexOutOfBoundsException {
        if(index < 0 || index > len) {
            throw new FunctionPointIndexOutOfBoundsException();
        } else {
            return arr[index];
        }
    }
    @Override
    public void setPoint(int index, FunctionPoint point) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
        if (index < 0 || index > len) {
            throw new FunctionPointIndexOutOfBoundsException();
        }
        if(index == 0 && point.getX() < arr[1].getX()){
            arr[index] = point;
        } else if (index == len-1 && point.getX() > arr[len-2].getX()){
            arr[index] = point;
        } else {
            if (point.getX() < arr[index - 1].getX() || point.getX() > arr[index + 1].getX()) {
                throw new InappropriateFunctionPointException();
            } else {
                arr[index] = point;
            }
        }
    }
    @Override
    public double getPointX(int index) throws FunctionPointIndexOutOfBoundsException{
        if(index < 0 || index > len) {
            throw new FunctionPointIndexOutOfBoundsException();
        } else {
            return arr[index].getX();
        }
    }
    @Override
    public void setPointX(int index, double x) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
        if (index < 0 || index > len) {
            throw new FunctionPointIndexOutOfBoundsException();
        }
        if((index == 0 && x < arr[1].getX()) || (index == len-1 && x > arr[len-2].getX())){
            arr[index].setX(x);
        } else {
            if (x < arr[index - 1].getX() || x > arr[index + 1].getX()) {
                throw new InappropriateFunctionPointException();
            } else {
                arr[index].setX(x);
            }
        }
    }
    @Override
    public double getPointY(int index) throws FunctionPointIndexOutOfBoundsException {
        if(index < 0 || index > len) {
            throw new FunctionPointIndexOutOfBoundsException();
        } else {
            return arr[index].getY();
        }
    }
    @Override
    public void setPointY(int index, double y) throws FunctionPointIndexOutOfBoundsException {
        if(index < 0 || index > len) {
            throw new FunctionPointIndexOutOfBoundsException();
        } else {
            arr[index].setY(y);
        }
    }
    @Override
    public void deletePoint(int index) throws FunctionPointIndexOutOfBoundsException, IllegalStateException {
        if(len < 3){
            throw new IllegalStateException();
        } else {
            if(index < 0 || index > len){
                throw new FunctionPointIndexOutOfBoundsException();
            } else {
                System.arraycopy(arr, index+1, arr, index, getPointsCount() - index - 1);
                --len;
            }
        }
    }
    @Override
    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        if(point.getX() <= getLeftDomainBorder() || point.getX() >= getRightDomainBorder()){
            throw new InappropriateFunctionPointException();
        }
        int first = 0;
        int last = getPointsCount();
        int mid = first + (last - first) / 2;
        while (first < last) { //бинарный поиск
            if (point.getX() == arr[mid].getX()) throw new InappropriateFunctionPointException();
            if (point.getX() < arr[mid].getX()) { //если x меньше x средней точки
                last = mid; //значит отбрасываем вторую часть после середины
            } else {
                first = mid + 1; //отбрасываем первую часть до середины
            }
            mid = first + (last - first) / 2; //устанавливаем середину в оставшейся части(новой области)
        }
        if (len >= arr.length) {
            FunctionPoint[] arrNew = new FunctionPoint[len + len / 2];
            System.arraycopy(arr, 0, arrNew, 0, len);
            arr = arrNew;
        }
        System.arraycopy(arr, last, arr, last + 1, getPointsCount() - last);
        arr[last] = point;
        ++len;
    }

    public int hashCode() {
        int hash = 9;
        hash = hash + Arrays.deepHashCode(arr);
        return hash + len;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof TabulatedFunction) {
            if (obj instanceof ArrayTabulatedFunction) {
                if (((ArrayTabulatedFunction) obj).len != len) {
                    return false;
                }
                for (int i = 0; i < len; i++) {
                    if (!(arr[i].getX()!=((ArrayTabulatedFunction)obj).arr[i].getX())) {
                        return false;
                    }
                    if (!(arr[i].getY()!=((ArrayTabulatedFunction)obj).arr[i].getY())) {
                        return false;
                    }
                }
            } else {
                if (((TabulatedFunction) obj).getPointsCount() != len) {
                    return false;
                }
                for (int i = 0; i < len; i++) {
                    if (!(this.getPoint(i).equals(((TabulatedFunction) obj).getPoint(i)))) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Object clone(){
        FunctionPoint[] functionPoints = new FunctionPoint[len];
        for (int i = 0; i < len; i++) {
            functionPoints[i] = arr[i];
        }
        return new ArrayTabulatedFunction(functionPoints);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(arr[i]).append(", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1).deleteCharAt(stringBuilder.length() - 1);
        return ("{" + stringBuilder + "}");
    }
}