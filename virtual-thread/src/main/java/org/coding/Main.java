package org.coding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        submit(executorService);
        System.out.println("-------");
        ExecutorService executorService1 = Executors.newVirtualThreadPerTaskExecutor();
        submit(executorService1);

    }

    public static void submit(ExecutorService executorService) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; ++i) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executorService.close();
        System.out.println(System.currentTimeMillis() - start);
    }
}