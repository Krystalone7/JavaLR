package threads;

import functions.Function;
import functions.Functions;

public class Integrator extends Thread{

    private final Semaphore semaphore;
    private final Task task;

    public Integrator(Semaphore semaphore, Task task) {
        this.semaphore = semaphore;
        this.task = task;
    }

    @Override
    public void run() {
        for (int i = 0; i < task.getCount(); i++) {
            try {
                semaphore.startIntegrator();
                double res = Functions.integral(task.getFunction(), task.getLeftX(), task.getRightX(), task.getStep());
                System.out.println("Result " + task.getLeftX() + " " + task.getRightX() + " " + task.getStep() + " " + res);
                semaphore.finishIntegrator();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
