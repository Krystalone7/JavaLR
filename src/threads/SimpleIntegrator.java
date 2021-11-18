package threads;

import functions.Function;
import functions.Functions;

public class SimpleIntegrator implements Runnable{

    private final Task task;

    public SimpleIntegrator(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        try {
                Thread.currentThread().sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < task.getCount(); i++) {
            Function fun;
            double left;
            double right;
            double step;
            synchronized (task) {
                fun = task.getFunction();
                left = task.getLeftX();
                right = task.getRightX();
                step = task.getStep();
            }
            double res = Functions.integral(fun, left, right, step);
            System.out.println("Result " + left + " " + right + " " + step + " " + res);
        }
    }
}
