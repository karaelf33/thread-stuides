package notifyAndWaitMethod;

import colorCode.ThreadColors;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    public static void main(String[] args) {
        List<Integer> tasks = new ArrayList<>();
        int TASK_CAPACITY = 10;
        Thread taskProducer = new Thread(new TaskProducer(tasks, TASK_CAPACITY), "Task Producer");
        Thread taskConsumer = new Thread(new TaskConsumer(tasks), "Task Consumer");
        taskProducer.start();
        System.out.println(ThreadColors.Yellow + taskConsumer.getName() + ":Thread is starting for producer new task");
        taskConsumer.start();
        System.out.println(ThreadColors.Yellow + taskConsumer.getName() + ":Thread is starting for delete closed task");
    }
}

class TaskProducer implements Runnable {
    private final List<Integer> tasks;
    private final int MAX_TASK_CAPACITY;

    public TaskProducer(List<Integer> tasks, int maxTaskCapacity) {
        this.tasks = tasks;
        this.MAX_TASK_CAPACITY = maxTaskCapacity;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                taskProducer(counter++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void taskProducer(int newTaskId) throws InterruptedException {
        synchronized (tasks) {
            while (tasks.size() == MAX_TASK_CAPACITY) {
                System.out.println(ThreadColors.Red + "The maximum task an employee can take is complete.Please wait for our employee to solve taks");
                tasks.wait();
            }
            Thread.sleep(2000);
            tasks.add(newTaskId);
            System.out.println(ThreadColors.Cyan + "New task added:" + newTaskId);
            tasks.notifyAll();
        }
    }
}

class TaskConsumer implements Runnable {
    private List<Integer> task;

    public TaskConsumer(List<Integer> task) {
        this.task = task;
    }

    @Override
    public void run() {
        while (true) {
            try {
                taskConsume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void taskConsume() throws InterruptedException {
        synchronized (task) {
            while (task.isEmpty()) {
                System.out.println("Please do  send new tasks to our employee");
                task.wait();
            }
            Thread.sleep(3000);
            int closedTask = task.remove(0);
            System.out.println(ThreadColors.Reset + "task with closed are deleted: " + closedTask);
            task.notifyAll();
        }
    }
}