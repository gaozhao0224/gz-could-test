package com.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("snowflake")
@Data
@Component
public class SnowflakeIdProperties {

    private long workerId = 0L;

    private long datacenterId = 0L;

}
