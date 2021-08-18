package com.crc.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("snowflake")
@Data
public class SnowflakeIdProperties {

    private long workerId = 0L;

    private long datacenterId = 0L;

}
