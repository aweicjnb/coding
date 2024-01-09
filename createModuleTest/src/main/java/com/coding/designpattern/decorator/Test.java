package com.coding.designpattern.decorator;

import org.omg.CORBA.portable.ObjectImpl;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        String path = "/Users/duitang/IdeaProjects/uCreateTest/createModuleTest/src/main/java/com/coding/designpattern/decorator/test.txt";
        String target = "/Users/duitang/IdeaProjects/uCreateTest/createModuleTest/src/main/java/com/coding/designpattern/decorator/test1.txt";
        byte[] buf = new byte[1024 * 3];
        int n = 0;

        /**
         * 字节输入流读取并打印出来
         */
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(path))) {
            while ((n = inputStream.read(buf)) > 0) {
                System.out.println(String.format("%x", n));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /**
         * 字符输入流读取，然后字符输出流写到目标文件
         */
        char[] bu = new char[2];
        try (BufferedReader in = new BufferedReader(new FileReader(path));
            FileWriter writer = new FileWriter(target)

        ) {
            while ((n = in.read(bu)) > 0) {
//                System.out.println(String.format("%c", n));
                System.out.println(n);
                System.out.println(bu);
                writer.write(bu);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /**
         * 字符输入流 --> 字符输出流
         */
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target), "utf-8"))
        ) {
            while ((n = in.read(buf)) > 0) {
                System.out.println(new String(buf, 0, n, "utf-8"));
                out.write(new String(buf, 0, n, "utf-8"));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /**
         * 写入和读取对象
         */
        try (ObjectOutputStream out = new ObjectOutputStream(new DataOutputStream(new FileOutputStream(target)));
             ObjectInputStream in = new ObjectInputStream(new FileInputStream(target));
        ) {
            out.writeObject(new A("大海"));
            out.writeChar('a');
            out.writeObject(new A("小海"));
            Object o = (A) in.readObject();
            System.out.println(in.readChar());
            Object oj = (A) in.readObject();
            if (o instanceof A) {
                System.out.println(((A) o).name);
            }
            if (o instanceof A) {
                System.out.println(((A) oj).name);
            }

        } catch (IOException e) {
//            throw new Exception("in or out error");
            throw new RuntimeException("");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


/*        //被装饰对象
        F f = new F();

        //装饰器对象和过程
        D d = new D(f);
        d.handle();
        E e = new E(d);
        e.handle();*/


    }
}



class A implements Serializable{
    private static final Long serializationVersionId = 1L;
    String name;

    A(String name) {this.name = name;}
}



/**
 * 装饰器模式可以动态的添加功能，做到了对扩展开发，对修改关闭
 * 比如我们常用的输入输出流，有一个顶级接口FileInputStream,下面的许多实现类
 * 比如：DataInputStream，能够读取Java基本数据类型，readDouble(double d),readBoolean(boolean b)
 * ObjectInputStream, 可以将对象读取出来,
 * FileInputStream, 底层是一个字节一个字节的读取，他也可以把数据读到内存的Byte[] buf中一块出处理，适合二进制数据的场景（图片，视频）
 * BufferedInputStream, 可以建立一个缓冲区（可以一次读取多个字节的数据，也就是读取一块数据），BufferedInputStream.read(Byte[] buf) 只是将读到的数据保存在buf一起处理
 * Reader.read(Char[] buf) 不一样，并不是每次读取一个字符放到buf,也是可以读取多个，但是底层读取到的数据会进行编码解码，适合文本数据
 *
 * 我下面写到装饰器感觉有问题，应该还有更好理解的方式
 */

/**
 * 被装饰对象
 */
interface B {
    void handle();
}

class F implements B {

    @Override
    public void handle() {
        System.out.println("被装饰对象想要装饰一下");
    }
}


/**
 * 装饰器抽象类
 */
abstract class C implements B {
    B b;
    C(B b){
        this.b = b;
    }

    @Override
    public void handle() {
        b.handle();
    }
}



/**
 * 装饰器实现类
 */
class D extends C {
    D(B b) {
        super(b);
    }

    @Override
    public void handle() {
        super.handle();
        m();
    }

    public void m() {
        System.out.println("D handle");
    }
}

class E extends C {
    E(B b) {
        super(b);
    }

    @Override
    public void handle() {
        super.handle();
        m();
    }

    public void m() {
        System.out.println("E handle");
    }
}






