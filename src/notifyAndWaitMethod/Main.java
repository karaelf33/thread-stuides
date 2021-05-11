package notifyAndWaitMethod;


import colorCode.ThreadColors;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Messages message = new Messages();
        Thread threadRead = new Thread(new doRead(message));
        Thread threadWrite = new Thread(new doWrite(message));
        threadRead.start();
        threadWrite.start();


    }
}

class Messages {
    String message = "";
    Scanner scanner = new Scanner(System.in);

    public synchronized void read() {
        if (!this.message.isEmpty()) {
            System.out.println(ThreadColors.White + "written text:" + this.message);
            this.message = "";
        } else {
            try {
                System.out.println(ThreadColors.Blue + "Wait for reading  write a message");
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public synchronized void write(String message){
        if (this.message.isEmpty()){
            System.out.println(ThreadColors.Magenta+"Write a Message for Screen Writing");
            this.message=scanner.nextLine();
            System.out.println(ThreadColors.Cyan+"A new value has been assigned to the message object=>"+this.message);
            notifyAll();
        }else {
            try {
                System.out.println(ThreadColors.Red+"I was writed a message waiting for read");
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
class doRead implements Runnable{
    Messages messages;

    public doRead(Messages messages) {
        this.messages = messages;
    }

    @Override
    public void run() {
        while (true){
            messages.read();
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
               e.printStackTrace();
            }
        }
    }
}
class doWrite implements Runnable{
    Messages messages;

    public doWrite(Messages messages) {
        this.messages = messages;
    }

    @Override
    public void run() {
        while (true){
            messages.write(messages.message);
        }

    }

}