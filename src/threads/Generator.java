package threads;

import functions.Function;
import functions.basic.Log;

import javax.swing.text.AbstractDocument;

public class Generator extends Thread{

    private final Semaphore semaphore;
    private final Task task;

    public Generator(Semaphore semaphore, Task task) {
        this.semaphore = semaphore;
        this.task = task;
    }

    @Override
    public void run() {
        for (int i = 0; i < task.getCount(); i++) {
            try {
                semaphore.startGenerator();
                task.setFunction(new Log((int) (Math.random() * 10)));
                task.setLeftX(Math.random() * 100);
                task.setRightX(100 + Math.random() * 100);
                task.setStep(Math.random());
                System.out.println("Source " + task.getLeftX() + " " + task.getRightX() + " " + task.getStep());
                semaphore.finishGenerator();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

