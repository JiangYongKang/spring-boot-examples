package com.vincent.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Author: vincent
 * Date: 2019/8/29 3:44 下午
 * Comment:
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "wechat")
public class WeChatProperties {

    private String appId;

    private String secretKey;

}
