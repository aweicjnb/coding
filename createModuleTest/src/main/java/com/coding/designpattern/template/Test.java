package com.coding.designpattern.template;

public class Test {
    public static void main(String[] args) {
        B b = new B();
        b.template();
    }
}

/**
 * 模板方法是一种行为方法模式，父类定义执行的框架，子类去扩展实现（钩子方法）
 * 优点：符合开闭原则，将不变的逻辑写在父类，将改变的逻辑写在子类
 * 模板方法里面可以有普通方法
 * 不一定非要这种方式，比如jdbcTemplate,redisTemplate的execute方法，都使用到了会调的方式来去干扰模板方法，而不是非要实现钩子方法；
 *  比如AQS里面的release方法就使用到了tryRelease钩子方法
 *      public final boolean release(int arg) {
 *         if (tryRelease(arg)) {}
 */
abstract class A {

    /**
     * 模板方法
     */
    void template() {
        //其他逻辑
        m1();
        //钩子方法，需要扩展到子类去实现
        a();
        //其他放啊
        m2();
    }

    /**
     * 钩子方法：可以影响父类的行为
     */
    protected abstract void a();
    protected abstract void b();

    public void m1() {
        System.out.println("m1");
    }

    public void m2() {
        System.out.println("m2");
    }
}

class B extends A {

    @Override
    protected void a() {
        System.out.println("sub a");
    }

    @Override
    protected void b() {
        System.out.println("sub b");
    }

}
