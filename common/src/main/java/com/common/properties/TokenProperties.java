package com.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义属性取代security.oauth2.client属性，防止oauth2框架创建scope为request或者session范围的bean
 */
@ConfigurationProperties("token")
@Data
public class TokenProperties {

    private String clientId;

    private String clientSecret;

    private String grantType;

}
