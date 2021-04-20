package notifyAndWaitMethod;

import colorCode.ThreadColors;

import java.util.HashMap;

public class Main4 {
    public static void main(String[] args) {
        WashingMachine washingMachine=new WashingMachine();
        new Thread(new MachinePrograms(washingMachine)).start();
        new Thread(new AddDetergent(washingMachine)).start();
        new Thread(new StartWashing(washingMachine)).start();
        new Thread(new TightenTheClothes(washingMachine)).start();
        new Thread(new DryTheClothes(washingMachine)).start();
    }
}

class WashingMachine {
    HashMap<Integer, String> machinePrograms = new HashMap<>();

    public synchronized void setTheMachinePrograms() {
      if (machinePrograms.isEmpty()){
          machinePrograms.put(1, "Add detergent");
          machinePrograms.put(2, "Start washing");
          machinePrograms.put(3, "Tighten the clothes");
          machinePrograms.put(4, "Dry the clothes");
          System.out.println("Machine programs ready to run");
          notifyAll();
      }else{
          try {
              wait();
          }catch (InterruptedException e){
              e.printStackTrace();
          }
      }
    }

    public synchronized void addDetergent() {
        System.out.println("1");
       if (machinePrograms.containsKey(1)){
           System.out.println(ThreadColors.Magenta+"Detergent added.Ready to work");
           machinePrograms.remove(1);
           notifyAll();
       }else{
           try {
               System.out.println(ThreadColors.Red+"Please run other programs");
               wait();
           }catch (InterruptedException e){
               e.printStackTrace();
           }
       }
    }

    public synchronized void startWashing() {
        System.out.println("2");
        if (!machinePrograms.containsKey(1) && machinePrograms.containsKey(2)){
            System.out.println(ThreadColors.Blue+"The machine is running........");
            machinePrograms.remove(2);
            notifyAll();
        }else{
            try {
                System.out.println(ThreadColors.Red+"Please add detergent to the machine first....");
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public synchronized void tightenTheClothes() {
        System.out.println("3");
        if (!machinePrograms.containsKey(1) && !machinePrograms.containsKey(2) && machinePrograms.containsKey(3)){
            System.out.println(ThreadColors.Cyan+"The process of squeezing clothes has begun........");
            machinePrograms.remove(3);
            notifyAll();
        }else{
            try {
                System.out.println(ThreadColors.Red+"Previous operations are not finished yet....");
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public synchronized void dryTheClothes() {
        System.out.println("4");
        if (!machinePrograms.containsKey(1) && !machinePrograms.containsKey(2) && !machinePrograms.containsKey(3)){
            System.out.println(ThreadColors.Green+"Drying process is started ........");
            machinePrograms.remove(4);
            notifyAll();
        }else{
            try {
                System.out.println(ThreadColors.Red+"Previous operations are not finished yet....");
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
class MachinePrograms implements Runnable{
    WashingMachine washingMachine;

    public MachinePrograms(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    @Override
    public void run() {
        washingMachine.setTheMachinePrograms();
    }
}
class AddDetergent implements Runnable{
    WashingMachine washingMachine;

    public AddDetergent(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    @Override
    public void run() {
        washingMachine.addDetergent();
    }
}
class StartWashing implements Runnable{
    WashingMachine washingMachine;

    public StartWashing(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    @Override
    public void run() {
        washingMachine.startWashing();
    }
}
class TightenTheClothes implements Runnable{
    WashingMachine washingMachine;

    public TightenTheClothes(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }
    @Override
    public void run() {
        washingMachine.tightenTheClothes();
    }
}
class DryTheClothes implements Runnable{
    WashingMachine washingMachine;

    public DryTheClothes(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }
    @Override
    public void run() {
        washingMachine.dryTheClothes();
    }
}