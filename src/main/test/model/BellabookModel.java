package com.cq.bookCrawler.model;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.io.Console;

/**
 * @author: Celine Q
 * @create: 2018-09-06 23:27
 **/
@TargetUrl("https://www.bellabooks.com/product/\\w+.*")
@HelpUrl({"https://www.bellabooks.com/category/*",
        "https://www.bellabooks.com/category/publisher-bella-books/\\w+.*",
        "https://www.bellabooks.com/category/browse-by-title-\\w+.*"})
public class BellabookModel {

    @ExtractBy(value = "//h1[@class='product_title entry-title']/strong/text()" , notNull = true)
    private String title;

    @ExtractBy(value = "//p[@class='product_author']/strong/text()" , notNull = true)
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public static void main(String[] args) {

        OOSpider.create(Site.me()
                        .setSleepTime(100)
                        .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36")
                ,new ConsolePageModelPipeline(), BellabookModel.class)
                .addUrl("https://www.bellabooks.com/category/browse-by-title-A").run();
    }
}

