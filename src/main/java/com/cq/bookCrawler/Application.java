package com.cq.bookCrawler;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Celine Q
 * @create: 2018-09-12 21:13
 **/


@SpringBootApplication
public class Application {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.debug("启动成功");
    }

}