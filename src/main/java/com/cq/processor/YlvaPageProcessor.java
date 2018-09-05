package com.cq.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author: Celine Q
 * @create: 2018-09-03 20:09
 **/
public class YlvaPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    private static int count = 0;


    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("https://www\\.ylva-publishing\\.com/product/\\w+.*/").all());
        String titleString = page.getHtml().xpath("//*[@id='single-product']/div/div[2]/div/h1/text()").toString();
        String priceString = page.getHtml().xpath("//*[@id='single-product']/div/div[2]/div/p[1]/span/text()").toString();
        if(null != titleString && null != priceString){
            int index = titleString.indexOf("by");
            String title = titleString.substring(0,index-1);
            String author = titleString.substring(index+2);
            page.putField("title", title);
            page.putField("author", author);
            page.putField("price",priceString);
            count++;
        } else
            page.setSkip(true);

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String args[]){
    	long startTime, endTime;
        System.out.println("开始爬取...");
        startTime = System.currentTimeMillis();

        Spider.create(new YlvaPageProcessor())
                .addUrl("https://www.ylva-publishing.com/product-category/ylva-publishing")
                .thread(5)
                .run();
        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了" + count + "条记录");
    }
}

