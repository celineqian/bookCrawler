package com.cq.bookCrawler.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author: Celine Q
 * @create: 2018-08-24 20:15
 **/
public class GoodreadsPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("https://www\\.goodreads\\.com/book/show/\\w+.*").all());
        page.putField("title",page.getHtml().xpath("//*[@id='bookTitle']/text()").toString());
        page.putField("author",page.getHtml().xpath("//*[@id='bookAuthors']/span[2]/div/a/span/text()").toString());
        page.putField("description",page.getHtml().xpath("//div[@class='readable stacked']/span/text()").toString());
        if (page.getResultItems().get("title") == null) {
            page.setSkip(true);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String arge[]){
        Spider.create(new GoodreadsPageProcessor()).addUrl("https://www.goodreads.com/book").thread(5).run();
    }

}

