package com.cq.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author: Celine Q
 * @create: 2018-09-03 22:57
 **/
public class BellaPageProcessor implements PageProcessor {

    private Site site = Site.me()
            .setDomain("bellabooks.com")
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36")
            .setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("https://www\\.bellabooks\\.com/product/\\w+.*").all());
        page.putField("title", page.getHtml().xpath("//h1[@class='product_title entry-title']/strong/text()").toString());
        page.putField("author", page.getHtml().xpath("//p[@class='product_author']/strong/text()").toString());
        if(page.getResultItems().get("title")==null){
            page.setSkip(true);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String args[]){
        Spider.create(new BellaPageProcessor())
                .addUrl("https://www.bellabooks.com/browse-by-publisher")
                .thread(2)
                .run();
    }
}

