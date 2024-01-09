package org.coding.starter;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//将配置导入
@EnableConfigurationProperties({MyStarterProperties.class})
public class MyStarterAutoConfiguration {
    private MyStarterProperties starterProperties;
    //其他starter也是这样定义的，照抄
    public MyStarterAutoConfiguration(MyStarterProperties starterProperties) {
        this.starterProperties = starterProperties;
    }

    @Bean
    public MyService myService() {
        //将配置文件的配置定义的值构造一个实力对象
        return new MyService(this.starterProperties.getName(), this.starterProperties.getAge());
    }
}
