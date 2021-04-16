package threadExample;

import colorCode.ThreadColors;

public class AnotherThread  extends Thread{

    @Override
    public void run() {
        System.out.println(ThreadColors.Blue+"Hello from"+currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(ThreadColors.Blue +"Another thread woke me up");
            return;
        }

        System.out.println(ThreadColors.Blue+"Three seconds have passed and ı am awake");
    }
}

