package com.coding.designpattern.singleton;

public class MyInstance {
    public static void main(String[] args) {
        System.out.println(Math.pow(2, 1023));
        System.out.println(Math.pow(10, 308));
    }
}

/**
 * 懒汉(线程不安全)
 */
/*class SingleTon {
    private static SingleTon INSTANCE;

    private SingleTon() {}

    public static SingleTon getINSTANCE() {
        if (INSTANCE ==  null) {
            INSTANCE = new SingleTon();
        }
        return INSTANCE;
    }
}*/


/**
 * 懒汉(线程安全)
 */
/*class SingleTon {
    private static SingleTon INSTANCE;

    private SingleTon() {}

    public static synchronized SingleTon getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new SingleTon();
        }
        return INSTANCE;
    }
}*/




/**
 * 饿汉(线程安全)
 */
/*class SingleTon {
    private static SingleTon INSTANCE = new SingleTon();

    private SingleTon(){}

    public static SingleTon getINSTANCE() {
        return INSTANCE;
    }
}*/




/**
 * DCL（双检锁）
 */
/*class SingleTon {
    private static volatile SingleTon INSTANCE;

    private SingleTon(){}

    public static SingleTon getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (SingleTon.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingleTon();
                }
            }
        }
        return INSTANCE;
    }
}*/


/**
 * 内部类
 */
class Singleton {
    private static Singleton INSTANCE;
    static class innerInstance {
        public static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getINSTANCE() {
        return innerInstance.INSTANCE;
    }
}


/**
 * 枚举
 */
/*enum Singleton {
    INSTANCE;
}*/





