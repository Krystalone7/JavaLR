import functions.*;
import functions.basic.Cos;
import functions.basic.Exp;
import functions.basic.Log;
import functions.basic.Sin;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            Sin sin = new Sin();
            Cos cos = new Cos();
            System.out.println("Синус:");
            for (double i = 0; i < 2 * Math.PI; i += 0.1) {
                System.out.println("sin(" + i + ") = " + sin.getFunctionValue(i));
            }
            System.out.println("Косинус:");
            for (double i = 0; i < 2 * Math.PI; i += 0.1) {
                System.out.println("cos(" + i + ") = " + cos.getFunctionValue(i));
            }
            System.out.println("Табулированный синус:");
            TabulatedFunction tabSin, tabCos;
            tabSin = TabulatedFunctions.tabulate(sin, 0, 2 * Math.PI, 10);
            for (double i = 0; i < 2 * Math.PI; i += 0.1) {
                System.out.println("tabSin(" + i + ") = " + tabSin.getFunctionValue(i));
            }
            System.out.println("Табулированный косинус:");
            tabCos = TabulatedFunctions.tabulate(cos, 0, 2 * Math.PI, 10);
            for (double i = 0; i < 2 * Math.PI; i += 0.1) {
                System.out.println("tabCos(" + i + ") = " + tabCos.getFunctionValue(i));
            }
            System.out.println("Табулированная сумма:");
            Function f = Functions.sum(tabSin, tabCos);
            for (double i = 0; i < 2 * Math.PI; i += 0.1) {
                System.out.println("sum(" + i + ") = " + f.getFunctionValue(i));
            }
            System.out.println("Табулированная экспонента:");
            Exp exp = new Exp();
            TabulatedFunction tabExp = TabulatedFunctions.tabulate(exp, 0, 10, 11);
            FileWriter writer = new FileWriter("src/exp");
            TabulatedFunctions.writeTabulatedFunction(tabExp, writer);
            writer.flush();
            writer.close();
            FileReader reader = new FileReader("src/exp");
            TabulatedFunction tabExp1 = TabulatedFunctions.readTabulatedFunction(reader);
            reader.close();
            for (double i = 0; i < 11; i++) {
                System.out.println("tabExp("+i+") = "+ tabExp1.getFunctionValue(i));
            }
            System.out.println("Табулированный логарифм:");
            Log log = new Log(Math.E);
            TabulatedFunction tabLog = TabulatedFunctions.tabulate(log, 0, 10, 11);
            OutputStream out = new FileOutputStream("src/log"); // для отправки данных в файл
            TabulatedFunctions.outputTabulatedFunction(tabLog, out);
            out.flush();
            out.close();
            for (double i = 0; i < 11; i++ ) {
                System.out.println("log("+i+") = "+tabLog.getFunctionValue(i));
            }
            InputStream inp = new FileInputStream("src/log");
            TabulatedFunction log1 = TabulatedFunctions.inputTabulatedFunction(inp);
            inp.close();
            for (double i = 0; i < 11; i++ ) {
                System.out.println("log1("+i+") = "+log1.getFunctionValue(i));
            }
            System.out.println("Табулированный логарифм от экспоненты:");
            Log ln = new Log(Math.E);
            Exp ex = new Exp();
            Function co = Functions.composition(ln, ex);
            TabulatedFunction tabLn = TabulatedFunctions.tabulate(co, 0, 10, 11);
            for (double i = 0; i < 11; i++ ) {
                System.out.println("tabLn("+i+") = "+tabLn.getFunctionValue(i));
            }
            OutputStream fos = new FileOutputStream("src/ln"); // для записи в файл
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(tabLn);
            os.close();
            InputStream fis = new FileInputStream("src/ln");
            ObjectInputStream is = new ObjectInputStream(fis);
            TabulatedFunction co1 = (TabulatedFunction) is.readObject();
            is.close();
            for (int i = 0; i < 11; i++ ) {
                System.out.println("comp("+i+") = "+co1.getFunctionValue(i));
            }
        }
        catch(FunctionPointIndexOutOfBoundsException errorIOB){
            System.out.print("IndexOutOfBounds");
        } catch(IllegalStateException errorISE){
            System.out.print("IllegalStateException");
        } catch (IOException  |ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}