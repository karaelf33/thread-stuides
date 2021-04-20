package notifyAndWaitMethod;

import colorCode.ThreadColors;

import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) {
        Product appleProduct=new Product();
        new Thread(new produceThread(appleProduct)).start();
        new Thread(new sellThread(appleProduct)).start();

    }
}

class Product {
    ArrayList<String> products = new ArrayList<>();
    String[] producer = {"Apple", "Pencil", "Pan", "Table", "Paper"};

    public synchronized void produce() {
        for (int i = 0; i < producer.length; i++) {
            products.add(producer[i]);
            System.out.println(ThreadColors.Green + producer[i] + "=>The product is produced");
            System.out.println("notify the sales department");
            notifyAll();
            try {
                System.out.println(ThreadColors.Cyan + "Production stopped the produced product needs to be sold");
                System.out.println(ThreadColors.Red+"Sales transactions are expected........");
                wait();
                System.out.println(ThreadColors.Red + "The product is sold and the product is produced again");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void sell() {
        while (true) {
            if (products.isEmpty()) {
                try {
                    System.out.println(ThreadColors.Blue + "Production is required to sell");
                    wait();
                    System.out.println(ThreadColors.Magenta + "Production has been done.Now available for sale");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println();
                System.out.println(ThreadColors.Reset + "******  " + products.remove(0) + "=>Product sold, reproduce****");
                System.out.println("Notify production department");
                notifyAll();
            }
        }
    }
}

class produceThread implements Runnable {
    Product product;

    public produceThread(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        try {
            System.out.println("I am just sleeping 500ms");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        product.produce();
    }
}

class sellThread implements Runnable {
    Product product;

    public sellThread(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        try {
            System.out.println("I am just sleeping 1000ms");
            Thread.sleep(1000);
        } catch (InterruptedException e)  {
            e.printStackTrace();
        }
        product.sell();
    }
}