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

    //书列表地址
    public static final String BOOKLIST_URL = "";

    public static final String DOMAIN = "goodreads.com";
    //初始地址
    public static final String ENTRY_URL = "https://www.goodreads.com/book";

    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31";


    private Site site = Site.me()
            .setDomain(DOMAIN)
            .setRetryTimes(3)
            .setSleepTime(100)
            .setUserAgent(USER_AGENT);


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
        Spider.create(new GoodreadsPageProcessor()).addUrl(ENTRY_URL).thread(5).run();
    }

}

