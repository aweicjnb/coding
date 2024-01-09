package com.coding.designpattern.way;

public class Test {
    public static void main(String[] args) {
        //策略实现类
        B b = new B();
        C c = new C();

        //环境类
        Env e = new Env(b);
        e.handle();
        e.setA(c);
        e.handle();
    }
}

/**
 * 策略模式注重的是行为，她能动态的在多种策略中选取一种来执行；
 * （这个和工厂模式有区别，工厂模式是创建型设计模式，针对的是对象，根据不同的工厂生成不同对象，并不关系她怎么生成的；
 * 策略模式也可以根据不同的策略生成不同对象，但是他们的意义不同，策略模式更加注重过程，在这个过程中发生了什么，虽然他们的功能可能相似，但是关注点不同）
 */

/**
 * 策略接口（因为不同策略代表不同行为，所以使用接口）
 */
interface A {
    void handle();
}



/**
 * 策略实现类
 */
class B implements A {
    @Override
    public void handle() {
        System.out.println("B handle");
    }
}

class C implements A {
    @Override
    public void handle() {
        System.out.println("C handle");
    }
}


/**
 * 环境类，来调用策略实现类的方法的，将各种策略类联系起来
 */
class Env {
    A a;
    Env(A a){
        this.a = a;
    }

    void setA(A a) {
        this.a = a;
    }

    void handle() {
        this.a.handle();
    }
}
