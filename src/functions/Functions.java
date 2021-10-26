package functions;
import functions.meta.*;

public class Functions {
    private Functions(){}
    private static Function shift(Function f, double shiftX, double shiftY){
        return new Shift(f , shiftX, shiftY);
    }
    private static Function scale(Function f, double scaleX, double scaleY){
        return new Scale(f, scaleX, scaleY);
    }
    private static Function power(Function f, double power){
        return new Power(f, power);
    }
    private static Function sum(Function f1, Function f2){
        return new Sum(f1, f2);
    }
    private static Function mult(Function f1, Function f2){
        return new Mult(f1,f2);
    }
    private static Function composition(Function f1, Function f2){
        return new Composition(f1,f2);
    }
}
