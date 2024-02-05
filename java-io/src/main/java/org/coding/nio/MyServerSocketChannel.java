package org.coding.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class MyServerSocketChannel {
    public static void main(String[] args) {
        MyServerSocketChannel myServerSocketChannel = new MyServerSocketChannel();
        new Thread(() -> {
            try {
                myServerSocketChannel.server();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "server").start();

        new Thread(() -> {
            try {
                myServerSocketChannel.client();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "client").start();
    }

    public void server() throws IOException {
        //定义一个服务端的socket
        ServerSocketChannel socket = ServerSocketChannel.open();
        //将这个socket绑定端口
        socket.socket().bind(new InetSocketAddress(8001));
        //创建一个selector实例
        Selector selector = Selector.open();
        //将我们的socket的连接事件注册到selector（注册感兴趣的事件，因为selector他就是一个多路复用IO的监听器）
        socket.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //如果是连接事件 那么就创建连接
                if (key.isAcceptable()) {
                    //用户服务端的ServerSocketChannel创建一个可以连接的socket
                    SocketChannel socketChannel = socket.accept();
                    //设置不阻塞
                    socketChannel.configureBlocking(false);
                    //这个这个已连接socket的读事件注册到selector
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer attachment = (ByteBuffer) key.attachment();
                    channel.read(attachment);
                    System.out.println(new String(attachment.array()));
                }

                iterator.remove();
            }
        }
    }


    public void client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8001);
        socketChannel.connect(inetSocketAddress);
        socketChannel.write(ByteBuffer.wrap("my data".getBytes()));
        socketChannel.close();
    }



}



