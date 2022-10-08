package com.mumu.novel.core.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 跨域配置属性
 *
 * @author mumu
 * @date 2022/5/17
 */
@ConfigurationProperties(prefix = "novel.cors")
public record CorsProperties(List<String> allowOrigins) {

}
