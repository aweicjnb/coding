package org.coding.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MyTransferFrom {
    public static void main(String[] args) throws IOException {
        simpleTransFor();
//        transFor();
    }

    public static void simpleTransFor() throws IOException {
        FileInputStream in = new FileInputStream("/Users/duitang/IdeaProjects/uCreateTest/java-io/src/main/resources/fromData.txt");
        FileChannel inChannel = in.getChannel();
        FileOutputStream out = new FileOutputStream("/Users/duitang/IdeaProjects/uCreateTest/java-io/src/main/resources/toData.txt");
        FileChannel outChannel = out.getChannel();
        MappedByteBuffer map = inChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);

        ByteBuffer allocate = ByteBuffer.allocate(1024);

        inChannel.read(map);
//        System.out.println(map.toString().getBytes());
        allocate.flip();
        outChannel.write(map);

        inChannel.close();
        outChannel.close();
    }

    public static void transFor() throws IOException {
        FileInputStream in = new FileInputStream("/Users/duitang/IdeaProjects/uCreateTest/java-io/src/main/resources/fromData.txt");
        FileChannel inChannel = in.getChannel();
        FileOutputStream out = new FileOutputStream("/Users/duitang/IdeaProjects/uCreateTest/java-io/src/main/resources/toData.txt");
        FileChannel outChannel = out.getChannel();

        inChannel.transferTo(0, inChannel.size(), outChannel);
//        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
    }

    public static void transForMap() {
        ByteBuffer allocate = MappedByteBuffer.allocate(1024);

    }
}
