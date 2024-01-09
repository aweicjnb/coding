package com.coding.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectTest {
    /**
     * 我开始以为动态代理有问题，结果是我自己的问题；
     * 错误的原因是：动态代理需要依靠Spring容器来实现，所以我们访问类和类里面的方法只能通过spring的方式来调用，如果是
     * 自己创建实例是不能获取生成代理对象执行代理的逻辑的；
     * 还用类方法也是不能走我们代理的逻辑的，因为它并不需要bean一样可以调用；
     */
    @Before("execution(* com.coding.aspect.test.StopClass.*(..))")
    public void stopUsingLogInfo() {
        String INFO = "INAPISettings已经废弃，请使用INAPISettingsFacade";
        Logger logger = LoggerFactory.getLogger(AspectTest.class);
//        Utils.logDubboCtx(INFO, logger);
        throw new RuntimeException("INAPISettings接口已经迁移至dt-settings项目下的INAPISettingsFacade");
    }

}
