package com.mumu.novel.core.json.serializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 将 Long 转为 String 后再序列化
 * 解决 Long 类型 id 前端解析后精度丢失的问题
 * 
 * @author mumu
 * @date 2022/10/14
 */
@Configuration
public class LongToStringSerializer {

    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder){
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

}
