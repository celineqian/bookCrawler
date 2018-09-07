package com.cq.bookCrawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Celine Q
 * @create: 2018-09-07 19:56
 **/
@RestController
@SpringBootApplication
public class BookApplication {

//    private final static Logger logger = LoggerFactory.getLogger(BookApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
//        logger.info("BooCrawler Application start success!");
    }

}

