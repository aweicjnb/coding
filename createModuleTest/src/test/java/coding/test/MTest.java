package coding.test;

import java.util.ArrayList;
import java.util.List;

public class MTest {
    public static void main(String[] args) {
       /* B b = new B("aa");
        b.m("111");*/

        //创建一个文件夹
        B b = new B("文件夹B");

        //文件
        C c = new C("视频文件");
        D d = new D("图片文件");
        b.add(c);
        b.add(d);

        B b1 = new B("文件夹sub_B");
        b1.add(new C("视频文件"));
        b.add(b1);

        //遍历文件夹
        b.display();
    }
}


/**
 * 文件接口
 */
abstract class A {
    /**
     * 子类可以直接访问这个属性的
     */
    public String name;

    /**
     * 这里很有意思，如果这里定义了构造方法，那么子类就必须要实现一个有参数的构造方法，并调用super()来实现父类的这个构造方法；
     * 因为子类实例化之前必须要先进行父类的实例化，但是父类只有一个构造方法，所以必须先这么写；
     */
    A(String name) {
        this.name = name;
    }

    /**
     * 如果是文件夹，那么这个方法就是遍历文件夹所有的文件
     * 如果是文件，那么这个方法就是查看文件
     */
    abstract void display();

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                '}';
    }
}


/**
 * 文件夹抽象类
 */
class B extends A {
/**
 * 如果子类定义了一个于父类相同的属性，那么这两个属性是不相同的
 * 可以使用this.name,以及super.name来区分
 */
/*    public String name;*/
    List<A> list = new ArrayList<>();
    B(String name) {
        super(name);
    }

    @Override
    void display() {
        list.forEach(item -> {
            System.out.println(item.toString());
        });
/*        list.stream().forEach(it -> {

        });*/
    }

    void add(A a) {
        list.add(a);
    }

    void remove(A a) {
        list.remove(a);
    }

/*    public void m(String name) {
        this.name = name;
        System.out.println(this.name);
        System.out.println(name);
        System.out.println(super.name);
    }*/
}

class C extends A {
    C(String name) {
        super(name);
    }

    @Override
    void display() {
        System.out.println(this);
    }
}



class D extends A {
    D(String name) {
        super(name);
    }

    @Override
    void display() {
        System.out.println(this);
    }
}
