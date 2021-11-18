package threads;

public class Semaphore {

    private volatile boolean ability = true;

    public synchronized void startGenerator() throws InterruptedException {
        while(!ability){
            wait();
        }
    }
    public synchronized void finishGenerator(){
        ability = false;
        notifyAll();
    }

    public synchronized void startIntegrator() throws InterruptedException {
        while(ability){
            wait();
        }
    }
    public synchronized void finishIntegrator(){
        ability = true;
        notifyAll();
    }
}
