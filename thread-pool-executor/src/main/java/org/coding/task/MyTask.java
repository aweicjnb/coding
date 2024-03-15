package org.coding.task;

public class MyTask implements Runnable{

    static Integer count = 0;
    @Override
    public void run() {
        try {
            synchronized (MyTask.class) {
                count++;
                System.out.println("task start -----" + count);
                Thread.sleep(100 * 5);
                System.out.println("task end ----" + count);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
