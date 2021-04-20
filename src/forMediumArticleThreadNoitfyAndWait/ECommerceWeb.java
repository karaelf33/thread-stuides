package forMediumArticleThreadNoitfyAndWait;

import colorCode.ThreadColors;

import java.util.ArrayList;
import java.util.Scanner;

public class ECommerceWeb {

    public static void main(String[] args) {
        ShoppingCard shoppingCard = new ShoppingCard();
        new Thread(new checkProduct(shoppingCard)).start();
        new Thread(new notifyToCargoCompany(shoppingCard)).start();
    }
}

class ShoppingCard {

    ArrayList<String> productsForSendCargoCompany = new ArrayList<>();
    String[] product={"macbook","toshiba","monster","hp","samsung"};


    public synchronized void isSoldProductsInBasket() {
        System.out.println("1 girdim");

        for (int i=0;i<product.length;i++){
            productsForSendCargoCompany.add(product[i]);
            System.out.println(ThreadColors.Magenta+product[i]+":added basket.please send this to the cargo company");
            notifyAll();
            if (productsForSendCargoCompany.isEmpty()) {
                try {
                    System.out.println("Basket is empty please add  product to cart ");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("please send to cargo company is product");
                notifyAll();
            }
        }


    }

    public synchronized void notifyCargoCompany() {
        System.out.println("2 girdim");

        if (productsForSendCargoCompany.isEmpty()) {
            try {
                System.out.println("Please add product to cart");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(productsForSendCargoCompany.remove(0) + "product sent to cargo company");
            notifyAll();
        }


    }
}

class checkProduct implements Runnable {

    ShoppingCard shoppingCard;

    public checkProduct(ShoppingCard shoppingCard) {
        this.shoppingCard = shoppingCard;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shoppingCard.isSoldProductsInBasket();
    }
}

class notifyToCargoCompany implements Runnable {

    ShoppingCard shoppingCard;

    public notifyToCargoCompany(ShoppingCard shoppingCard) {
        this.shoppingCard = shoppingCard;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shoppingCard.notifyCargoCompany();
    }
}
