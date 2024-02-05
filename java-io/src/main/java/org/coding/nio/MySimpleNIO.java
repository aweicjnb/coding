package org.coding.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MySimpleNIO {
    public static void main(String[] args) throws IOException {
        //这里模拟的是将数据写入到本地（前提是数据已经 写入到ByteBuffer里面了）
        FileOutputStream file = new FileOutputStream("/Users/duitang/IdeaProjects/uCreateTest/java-io/src/main/resources/nio1.txt");
        FileChannel channel = file.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(1024* 1024* 1024);
        allocate.put("hello NIO".getBytes());
        allocate.flip();
        channel.write(allocate);
        System.out.println(allocate.array().length);
        channel.close();
        file.close();
    }
}
