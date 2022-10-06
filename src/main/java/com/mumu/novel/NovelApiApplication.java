package com.mumu.novel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mumu.novel.dao.mapper")
public class NovelApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelApiApplication.class, args);
    }

}
