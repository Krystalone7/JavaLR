package threads;

import functions.Functions;
import functions.basic.Log;

public class SimpleGenerator implements Runnable{

    private final Task task;

    public SimpleGenerator (Task task){
        this.task = task;
    }

    @Override
    public void run() {
        for (int i = 0; i < task.getCount(); i++) {
            synchronized (task) {
                task.setFunction(new Log((int)(Math.random() * 10)));
                task.setLeftX(Math.random() * 100);
                task.setRightX(100 + Math.random() * 100);
                task.setStep(Math.random());
            }
            System.out.println("Source " + task.getLeftX() + " " + task.getRightX() + " " + task.getStep());
            try {
                Thread.currentThread().sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
