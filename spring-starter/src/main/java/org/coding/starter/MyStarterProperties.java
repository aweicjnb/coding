package org.coding.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//前缀类似于配置文件里面 server.port=8080, 中的server
//就可以实现在配置文件中定义
//my-starter.name="haha"
//my-starter.age=22
@ConfigurationProperties("my-starter")
public class MyStarterProperties {
    private String name;
    private Integer age;

    public MyStarterProperties() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}