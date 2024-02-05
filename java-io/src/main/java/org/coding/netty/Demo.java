package org.coding.netty;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(16*16*16);
        Object arr = new Object[Integer.MAX_VALUE];
        new Thread(() -> {
            try {
                MyNettyServer.server();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        Thread.sleep(2000);

        new Thread(() -> {
            try {
                MyNettyClient.client();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}
