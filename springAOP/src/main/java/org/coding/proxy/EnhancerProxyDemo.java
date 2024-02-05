package org.coding.proxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class EnhancerProxyDemo implements MethodInterceptor {

    private Object target;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before a");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("after b");
        return object;
    }

    public Object getProxy(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }


    public static void main(String[] args) {
        EnhancerProxyDemo enhancerProxyDemo = new EnhancerProxyDemo();
        MyServiceImpl proxy = (MyServiceImpl) enhancerProxyDemo.getProxy(new MyServiceImpl());
        proxy.doSome();
    }
}
