package org.coding.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 这个是传统的Java IO （同步阻塞式）
 * 一个连接需要一个线程来处理,就算数据client 数据没准备好，server的线程也会一直阻塞，不会取干其他事情造成资源浪费
 * 通过流的方式来进行数据的传输,是单双工
 */
public class MyBIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        MyBIO myBIO = new MyBIO();
        new Thread(() -> {
            try {
                myBIO.server();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "server").start();

//        Thread.sleep(1000 * 2);

        new Thread(() -> {
            try {
                myBIO.client();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "client").start();

    }

    public void server() throws IOException {
        //server
        ServerSocket serverSocket = new ServerSocket(8001);
        Socket socket = serverSocket.accept();
        byte[] bytes = new byte[1024];
        InputStream inputStream = socket.getInputStream();
        int count = 0;
        while ((count = inputStream.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, count));
        }
        socket.close();
    }

    public void client() throws IOException {
        Socket socket = new Socket("127.0.0.1", 8001);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello world".getBytes());
        socket.close();
    }


}
