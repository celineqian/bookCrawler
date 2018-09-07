package com.cq.bookCrawler.model;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author: Celine Q
 * @create: 2018-08-26 16:21
 **/
@TargetUrl("http://www\\.amazon\\.com/gp/product/\\w+.*")
@HelpUrl("")
public class AmazonEbook {

    @ExtractBy(value = "//*[@id='ebooksProductTitle']/text()" , notNull = true)
    private String title;

    @ExtractBy("//*[@id='bylineInfo']/span/span[1]/a[1]/text()")
    private String author;

    @ExtractBy("//*[@id='iframeContent']/text()")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static void main(String args[]){
        OOSpider.create(Site.me().setSleepTime(100)
                , new ConsolePageModelPipeline(), AmazonEbook.class)
                .addUrl("https://www.amazon.com/Kindle-eBooks/b?ie=UTF8&node=154606011").thread(3).run();
    }
}

