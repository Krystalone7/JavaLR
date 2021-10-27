import functions.*;
import functions.basic.*;

import java.io.File;
import java.io.IOException;


public class Main{
    public static void main(String[] args) {
        Function cos = new Cos();
        System.out.println(cos.getFunctionValue(1));

    }
}
