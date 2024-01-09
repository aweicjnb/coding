package com.coding.designpattern.chain;


/**
 * 责任链模式是一种链式处理任务的设计模式：
 * 优点：每一个handler分工明确，每个handler只需要关系自己的handle方法，方便扩展（添加和删除）
 * 缺点L：可能会成环
 * 分为两种角色：handler和被处理对象实体
 * handler有两种方法，success的逻辑和失败的逻辑，一般成功则传递，失败则返回，或者结束
 * 通过组合建立链式关系（当然还可以用其他方式，比如按照指定规则全部放在队列里面，这样就知道执行handler的先后顺序）
 */
public class Chain {
    public static void main(String[] args) {
        //handler处理者之间的链式关系
        C c = new C();
        D d = new D();
        E e = new E();
        c.setSuccess(d);
        d.setSuccess(e);


        //处理对象实体
        A a = new A("e");

        //开启调用
        c.handle(a);


    }
}

/**
 * 被调用对象
 */
class A {
    String name;
    A(String name){
        this.name = name;
    }
}

/**
 * 处理者接口
 */
abstract class B {
/*
    String name;
*/
    B b;

    abstract void handle(A a);

    void setSuccess(B b){
        this.b = b;
    }
}


/**
 * 调用者实现类
 */
class C extends B {

    @Override
    void handle(A a) {
        if (a.name.equals("a") ) {
            //fail
            // 是一种窗口，不一定代表失败
            System.out.println("c handle fail");
        } else {
            System.out.println("c handle success");
            this.b.handle(a);
        }
    }
}

class D extends B {

    @Override
    void handle(A a) {
        if (a.name.equals("d") ) {
            //
            System.out.println("d handle fail");
        } else {
            System.out.println("d handle success");
            this.b.handle(a);
        }
    }
}

class E extends B {

    @Override
    void handle(A a) {
        if (a.name.equals("e") ) {
            System.out.println("e handle fail");
        } else {
            System.out.println("e handle success");
            this.b.handle(a);
        }
    }
}

