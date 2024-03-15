package org.coding.pay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = MyConfig.prefix)
public class MyConfig {
    public static final String prefix = "ali-pay";

    private String appId;
    private String serverUrl;
    private String privateKey;
    private String format;
    private String charset;
    private String alipayPublicKey;
    private String signType;
}
