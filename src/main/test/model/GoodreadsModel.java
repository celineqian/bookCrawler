package com.cq.bookCrawler.model;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author: Celine Q
 * @create: 2018-08-26 16:39
 **/
@TargetUrl("https://www\\.goodreads\\.com/book/show/\\w+.*")
@HelpUrl("https://www.goodreads.com/book")
public class GoodreadsModel {

    @ExtractBy(value = "//*[@id='bookTitle']/text()", notNull = true)
    private String title;

    @ExtractBy("//*[@id='bookAuthors']/span[2]/div/a/span/text()")
    private String author;

    @ExtractBy("//div[@class='readable stacked']/span/text()")
    private String descrption;


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

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(100)
                , new ConsolePageModelPipeline(), GoodreadsModel.class)
                .addUrl("https://www.goodreads.com/book").thread(3).run();
    }
}

