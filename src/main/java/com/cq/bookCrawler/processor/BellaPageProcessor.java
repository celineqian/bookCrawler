package com.cq.bookCrawler.processor;

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

	//列表页
	//https://www.bellabooks.com/category/browse-by-title-A/
	
	//public static final String URL_LIST = "https://www\\.bellabooks\\.com/category/browse-by-titl\\w+.*";
	
	//https://www.bellabooks.com/category/browse-by-title/page/2/
	public static final String URL_LIST = "https://www\\.bellabooks\\.com/category/browse-by-title/page/\\d+.*";

	//详细页
	//https://www.bellabooks.com/product/9781594936005e/
	public static final String URL_POST = "https://www\\.bellabooks\\.com/product/\\w+.*";
	
    private static int count = 0;

    private Site site = Site
    		.me()
    		.setDomain("bellabooks.com")
            //.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36")
            .setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
    	//列表页
    	if (page.getUrl().regex(URL_LIST).match()) {
            page.addTargetRequests(page.getHtml().xpath("//a[@class='woocommerce-LoopProduct-link woocommerce-loop-product__link']/@href").all());
    		page.addTargetRequests(page.getHtml().links().regex(URL_POST).all());
    	} else {
//    		page.addTargetRequests(page.getHtml().links().regex("https://www\\.bellabooks\\.com/product/\\w+.*").all());
    		page.putField("title", page.getHtml().xpath("//h1[@class='product_title entry-title']/strong/text()").toString());
    		page.putField("author", page.getHtml().xpath("//p[@class='product_author']/strong/text()").toString());
        count++;
    	}
    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String args[]){
    	long startTime, endTime;
        System.out.println("开始爬取...");
        startTime = System.currentTimeMillis();
        Spider.create(new BellaPageProcessor())
                .addUrl("https://www.bellabooks.com/category/browse-by-title/page/2")
                .thread(2)
                .run();
        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了" + count + "条记录");
    }
}

