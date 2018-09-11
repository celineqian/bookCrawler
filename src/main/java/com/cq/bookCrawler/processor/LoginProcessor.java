package com.cq.bookCrawler.processor;


import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class LoginProcessor implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
			.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
	private static WebDriver driver;
    private Set<Cookie> cookies;

	@Override
	public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("https://www\\.fijiairways\\.com/tabua-club/member-login/\\w+.*").all());

		page.putField("Welcome back: ", page.getHtml().xpath("//*[@id='cpContent_itemContentCtrl_ProfileDashboard_20_lblFirstName']/b/text()").toString());
//		page.putField("Status Credit Balance", page.getHtml().xpath("//span[@id='cpContent_itemContentCtrl_ProfileDashboard_20_lblStatusCreditBalanceValue']/b").toString());
//		page.putField("Upgrade Credit Balance", page.getHtml().xpath("//span[@id='cpContent_itemContentCtrl_ProfileDashboard_20_lblUpgradeCreditBalanceValue']/b").toString());

	}

	
	public void login(){
    	System.setProperty("webdriver.chrome.driver","D:\\Dev\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.fijiairways.com/tabua-club/member-login/");
        WebElement e = driver.findElement(By.xpath("//div[@class='terms-of-use-accept-button Button Color_5d4b3f']"));
        if(e!=null){
        	e.click();
        	driver.findElement(By.xpath("//*[@id='cpContent_itemContentCtrl_TabuaLogin_19_txtMembershipNumber']")).sendKeys("82DKT6");
        	driver.findElement(By.xpath("//*[@id='cpContent_itemContentCtrl_TabuaLogin_19_txtPassword']")).sendKeys("tcktb123");
        	WebElement element = driver.findElement(By.xpath("//*[@id='cpContent_itemContentCtrl_TabuaLogin_19_ibtnLogin']"));
            element.click();
            cookies = driver.manage().getCookies();
        }
        
	}
	
	@Override
	public Site getSite() {
		for(Cookie cookie: cookies){
			site.addCookie(cookie.getName().toString(), cookie.getValue().toString());
		}
		return site;
	}

	public static void main(String args[]){
		LoginProcessor lp = new LoginProcessor();
		lp.login();
		Spider.create(lp).addUrl("https://www.fijiairways.com/tabua-club/member-login/").run();
		driver.close();
	}
}
