package com.mumu.novel.core.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * 跨域配置属性
 *
 * @author mumu
 * @date 2022/10/06
 */
@Data
@ConfigurationProperties(prefix = "novel.cors")
public class CorsProperties {

    /**
     * 允许跨域的域名
     */
    private List<String> allowOrigins;

}
