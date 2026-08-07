package com.hmall.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;

/**
 * JSON 序列化配置
 * <p>将 {@link Long} 与 {@link BigInteger} 类型的值序列化为字符串，
 * 避免前端 JavaScript 对超长数字（如雪花算法生成的 id）的精度丢失问题。</p>
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
public class JsonConfig {
    /**
     * 配置 Jackson：Long 与 BigInteger 统一序列化为字符串
     *
     * @return Jackson 序列化配置定制器
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            // Long/BigInteger 序列化为字符串
            jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(BigInteger.class, ToStringSerializer.instance);
        };
    }
}