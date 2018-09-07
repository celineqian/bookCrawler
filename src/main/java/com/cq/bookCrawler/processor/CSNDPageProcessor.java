package com.cq.bookCrawler.processor;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class CSNDPageProcessor implements PageProcessor{

	 //http://blog.csdn.net/weixin_35852328/article/list/1
    public static final String URL_LIST = "http://blog\\.csdn\\.net/weixin_35852328/article/list/\\d{1}";

    //http://blog.csdn.net/weixin_35852328/article/details/78144353
    public static final String URL_POST = "http://blog\\.csdn\\.net/weixin_35852328/article/details/\\d{8}";

    private static int count = 0;

    private Site site = Site
            .me()
            .setDomain("blog.csdn.net")
            .setRetryTimes(3).setSleepTime(100);
    
    
	@Override
	public void process(Page page) {
		//列表页
        if (page.getUrl().regex(URL_LIST).match()) {
            page.addTargetRequests(page.getHtml().xpath("//ul[@class='hotArticle-list csdn-tracking-statistics tracking-click']/li/a/@href").all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
            //文章页
        } else {
            count++;
            page.putField("title", page.getHtml().xpath("//h1[@class='title-article']/text()"));
            page.putField("viewCount", page.getHtml().xpath("//span[@class='read-count']/text()"));
            page.putField("date", page.getHtml().xpath("//span[@class='time']/text()"));
        }
		
	}

	public Site getSite() {
        return site;
    }
	
	 public static void main(String[] args) {
	        long startTime, endTime;
	        System.out.println("开始爬取...");
	        startTime = System.currentTimeMillis();
	        Spider.create(new CSNDPageProcessor()).addUrl("http://blog.csdn.net/weixin_35852328/article/list/3").thread(5).run();
	        endTime = System.currentTimeMillis();
	        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了" + count + "条记录");

	 }

}
