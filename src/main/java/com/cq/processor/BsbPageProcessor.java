package com.cq.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author: Celine Q
 * @create: 2018-09-03 20:48
 **/
public class BsbPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("https://www\\.boldstrokesbooks\\.com/books/\\w+.*").all());
        String title = page.getHtml().xpath("//h1[@class='title']/text()").toString();
        String author = page.getHtml().xpath("//span[@class='by-authors']/a/text()").toString();

        if(null!=title && null != author){
            page.putField("title" , title);
            page.putField("author", author);
            page.putField("price",page.getHtml().xpath("//div[@class='col-xs-6 col-sm-12 col-md-12 price-box']/span/meta[2]/@content").toString());
        }else
            page.setSkip(true);



    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String args[]){
        Spider.create(new BsbPageProcessor())
                .addUrl("https://www.boldstrokesbooks.com/books/lesbian-fiction-1-c/all")
                .thread(5)
                .run();
    }
}

