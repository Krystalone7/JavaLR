package threads;

import functions.Functions;
import functions.basic.Log;

public class Main7 {

    private static void nonThread(){
        Task task = new Task(100);
        for (int i = 0; i < task.getCount(); i++) {
            task.setFunction(new Log((int)(Math.random() * 10)));
            task.setLeftX(Math.random() * 100);
            task.setRightX(100 + Math.random() * 100);
            task.setStep(Math.random());
            System.out.println("Source " + task.getLeftX() + " " + task.getRightX() + " " + task.getStep());
            double res = Functions.integral(task.getFunction(), task.getLeftX(), task.getRightX(), task.getStep());
            System.out.println("Result " + task.getLeftX() + " " + task.getRightX() + " " + task.getStep() + " " + res);
        }
    }

    private static void simpleThreads(){
        Task task = new Task(100);
        SimpleGenerator simpleGenerator = new SimpleGenerator(task);
        SimpleIntegrator simpleIntegrator = new SimpleIntegrator(task);
        Thread thread1 = new Thread(simpleGenerator);
        Thread thread2 = new Thread(simpleIntegrator);
        thread1.start();
        thread2.start();
    }

    private static void complicatedThreads(){
        Task task = new Task(100);
        Semaphore semaphore = new Semaphore();
        Thread thread1 = new Generator(semaphore, task);
        Thread thread2 = new Integrator(semaphore, task);
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
        thread2.interrupt();
    }

    public static void main(String[] args) {
        complicatedThreads();

    }
}
