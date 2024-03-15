package org.coding.pool;

import org.coding.task.MyTask;

import java.util.concurrent.*;

public class MyExecutor {
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            5,
            10,
            3,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void MyPool() {
        for (int i = 0; i < 5; i++) {
            executor.execute(new MyTask());
        }
//        executor.shutdown();
    }


    public static void main(String[] args) {
//        MyPool();
        System.out.println(Integer.toBinaryString(1<< 31));
        System.out.println(Integer.toBinaryString((1<< 29) - 1));
        System.out.println((1<< 30));
        System.out.println(1 - Long.MAX_VALUE);
    }

}
