package com.cq.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author: Celine Q
 * @create: 2018-08-26 16:50
 **/
public class AmazonEbookPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("https://www\\.amazon\\.com/gp/product/\\w+.*").all());
        String title = page.getHtml().xpath("//*[@id='ebooksProductTitle']/text()").toString();
        String author = page.getHtml().xpath("//*[@id='bylineInfo']/span/span[1]/a[1]/text()").toString();
        String priceTag = page.getHtml().xpath("//tr[@class='kindle-price']/td[1]/text()").toString();

        if(null!=title && null!=author && null !=priceTag){
            page.putField("title",title);
            page.putField("author",author);
            if(priceTag.contains("Kindle"))
                page.putField("Kindle Price", page.getHtml().xpath("//tr[@class='kindle-price']/td[2]/text()").toString());
            if(priceTag.contains("Pre-order"))
                page.putField("Pre-order Price ", page.getHtml().xpath("//tr[@class='kindle-price']/td[2]/text()").toString());
        } else
            page.setSkip(true);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String arge[]){
        Spider.create(new AmazonEbookPageProcessor())
                .addUrl("https://www.amazon.com/Kindle-eBooks/b?ie=UTF8&node=154606011")
                .thread(5)
                .run();
    }

}

