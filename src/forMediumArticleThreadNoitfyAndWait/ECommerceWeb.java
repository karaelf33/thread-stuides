package forMediumArticleThreadNoitfyAndWait;

import java.util.ArrayList;
import java.util.Scanner;

public class ECommerceWeb {

    public static void main(String[] args) {
        ShoppingCard shoppingCard = new ShoppingCard();
        new Thread(new checkProduct(shoppingCard)).start();
        new Thread(new addProductToCard(shoppingCard)).start();
        new Thread(new notifyToCargoCompany(shoppingCard)).start();
    }
}

class ShoppingCard {

    ArrayList<String> products = new ArrayList<>();


    public synchronized void isSoldProductsInBasket() {
        if (products.isEmpty()) {
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

    public synchronized void notifyCargoCompany() {
        if (products.isEmpty()) {
            try {
                System.out.println("Please add product to cart");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("sfdfs");
            System.out.println(products.remove(0) + "product sent to cargo company");
            notifyAll();
        }


    }

    public synchronized void addProductToCard() {
        if (products.isEmpty()) {
            System.out.println("Add Product To Cart");
            Scanner product = new Scanner(System.in);
            String item = product.nextLine();
            products.add(item);
            System.out.println(item+"Product added to Card");
            notifyAll();

        } else {
            try {
                System.out.println("Product already have in card");
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
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

class addProductToCard implements Runnable {
    ShoppingCard shoppingCard;

    public addProductToCard(ShoppingCard shoppingCard) {
        this.shoppingCard = shoppingCard;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shoppingCard.addProductToCard();
    }
}