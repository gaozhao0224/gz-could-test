package com.common.autoconfigure;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.common.handler.FillFieldHandler;
import com.common.properties.SnowflakeIdProperties;
import com.common.uuid.SnowflakeIdWorker;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 和mybatisplus相关的配置，包括分页、分布式主键生成、公共字段自动填充等  TODO没好使
 */
@Configuration
@EnableConfigurationProperties(SnowflakeIdProperties.class)
public class MybatisPlusAutoConfiguration {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        // 开启 count 的 join 优化,只针对 left join !!!
        return new PaginationInterceptor().setCountSqlParser(new JsqlParserCountOptimize(true));
    }

    /**
     * 推特的全局id生成器
     * @return
     */
    @Bean
    public SnowflakeIdWorker snowflakeIdWorker(SnowflakeIdProperties snowflakeIdProperties) {
        return new SnowflakeIdWorker(snowflakeIdProperties.getWorkerId(), snowflakeIdProperties.getDatacenterId());
    }

    /**
     * mybatisplus的字段填充处理器
     * @return
     */
    @Bean
    public FillFieldHandler fillFieldHandler(SnowflakeIdWorker snowflakeIdWorker) {
        return new FillFieldHandler(snowflakeIdWorker);
    }

    /**
     * 乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
