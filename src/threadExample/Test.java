package threadExample;

import colorCode.ThreadColors;
import sun.security.mscapi.CPublicKey;

public class Test {

    private int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        test.doCount();
    }

    private void doCount() throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            public synchronized void increment() {
                count++;
            }

            @Override
            public void run() {
                System.out.println(ThreadColors.Red + "first operation starting");
                for (int i = 0; i < 2500; i++) {
                    increment();
                }
                System.out.println(ThreadColors.Red + "result" + count);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(ThreadColors.Green + "Second operation starting" + count);
                for (int i = 0; i < 2500; i++) {
                  count++;
                }
                System.out.println(ThreadColors.Green + "result" + count);
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Total:" + count);
    }

}

