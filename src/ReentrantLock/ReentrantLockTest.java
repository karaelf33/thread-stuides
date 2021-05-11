package ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.currentThread;

public class ReentrantLockTest {
    public static void main(String[] args) {
        BankAccount bankAccount=new BankAccount(1000,"YUSUF");

    }

}

 class BankAccount{

     private double moneyCount;
     private String ownAccount;

     ReentrantLock lock=new ReentrantLock();

     public BankAccount(double moneyCount, String ownAccount) {
         this.moneyCount = moneyCount;
         this.ownAccount = ownAccount;
     }

     public void pullMoney(double amount){
         lock.lock();
         for (int i=0;i<10;i++){
             moneyCount-=amount;
             System.out.println("thread pulling money"+ currentThread().getName()+"/the remaining amount="+moneyCount);
         }
         lock.unlock();
     }
}
