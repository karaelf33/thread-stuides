package threadExample;
class FirstThread extends Thread{
    private volatile boolean running=true;

    @Override
    public void run(){
        while (running){
            System.out.println("Hello from Thread");
        }
    }

    public void shutDown(){
        System.out.println("starting");
        this.running=false;
    }
}
public class VolatileExample {

    public static void main(String[] args) throws InterruptedException{
        FirstThread t1=new FirstThread();
        t1.start();
        Thread.sleep(1000);
        t1.shutDown();
    }
}
