package org.coding.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyDemo implements InvocationHandler {

    public Object target;

    public JDKProxyDemo(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("切面逻辑a");
/*         使用 proxy 参数调用原始代理对象的方法
         这里有死循环
         暂时还搞不懂 这里是干什么的 这个proxy 对象
        ((MyService) proxy).doSome();*/
        Object invoke = method.invoke(target, args);
        System.out.println("切面逻辑b");
        return invoke;
    }

    public static Object getProxy() {
        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{MyService.class},
                new JDKProxyDemo(new MyServiceImpl()));
    }


    public static void main(String[] args) {
        MyService proxy = (MyService) JDKProxyDemo.getProxy();
        proxy.doSome();
    }
}
