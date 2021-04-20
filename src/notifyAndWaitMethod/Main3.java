package notifyAndWaitMethod;

import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) throws InterruptedException {
        final Processor processor = new Processor();

        Thread producerThread = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }
}
class Processor{
    public void produce() throws InterruptedException{
        synchronized (this){
            System.out.println("Produce thread is working");
            wait();
            System.out.println("Producer continue");
        }
}

    public void consume() throws InterruptedException {
        Thread.sleep(2000);

        Scanner scanner = new Scanner(System.in);

        synchronized (this) {
            System.out.println("Consumer thread is workin...");
            System.out.print("for working click the ENTER: ");
            scanner.nextLine();
            System.out.println("producer continue..");
            notify();
            Thread.sleep(5000);
            System.out.println("producer after 5 second is continue.");
        }
    }

}
