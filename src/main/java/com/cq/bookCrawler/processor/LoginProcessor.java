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
		
//		page.addTargetRequests(page.getHtml().links().regex("https://www\\.fijiairways\\.com/tabua-club/\\w+.+[+member+]\\w+.*").all());
        page.addTargetRequests(page.getHtml().links().regex("https://www\\.fijiairways\\.com/tabua-club/member-login/").all());
        page.addTargetRequests(page.getHtml().links().regex("https://www\\.fijiairways\\.com/tabua-club/membership-activity").all());
        page.addTargetRequests(page.getHtml().links().regex("https://www\\.fijiairways\\.com/tabua-club/your-membership").all());
        
        String name = page.getHtml().xpath("//*[@id='cpContent_itemContentCtrl_ProfileDashboard_20_lblFirstName']/b/text()").toString();
        String scb = page.getHtml().xpath("//*[@id='cpContent_itemContentCtrl_ProfileDashboard_20_lblStatusCreditBalanceValue']/b/text()").toString();
		String ucb = page.getHtml().xpath("//span[@id='cpContent_itemContentCtrl_ProfileDashboard_20_lblUpgradeCreditBalanceValue']/b/text()").toString();
		String expireDate =  page.getHtml().xpath("//*[@id='cpContent_itemContentCtrl_ProfileDashboard_20_lblExpiryDateValue']/b/text()").toString();
		String lastActivity = page.getHtml().xpath("//*[@id='main']/table[3]/tbody/tr[3]/td/div/text()").toString();
		if(name != null)
			page.putField("Name: ", name);
		if(scb != null)
			page.putField("Status Credit Balance: ", scb);
		if(ucb != null)
			page.putField("Upgrade Credit Balance: ", ucb);
		if(expireDate != null)
			page.putField("Membership Expiry Date: ", expireDate);
		if(lastActivity != null){
			page.putField("last Ativity: ", lastActivity);
		}
	}

	public void login(){
        if(System.getProperty("os.name").contains("Mac"))
            System.setProperty("webdriver.chrome.driver","/Users/Celine/Downloads/chromedriver");
        else
            System.setProperty("webdriver.chrome.driver","D:\\Dev\\driver\\chromedriver.exe");

        driver = new ChromeDriver();
        //获得登陆页面
        driver.get("https://www.fijiairways.com/tabua-club/member-login");
        //确认访问地点
        WebElement e = driver.findElement(By.xpath("//div[@class='terms-of-use-accept-button Button Color_5d4b3f']"));
        if(e != null){
        	e.click();
        	//填写登陆信息
        	driver.findElement(By.xpath("//*[@id='cpContent_itemContentCtrl_TabuaLogin_19_txtMembershipNumber']")).sendKeys("CVWGPM");
        	driver.findElement(By.xpath("//*[@id='cpContent_itemContentCtrl_TabuaLogin_19_txtPassword']")).sendKeys("llhtb123");
        	e = driver.findElement(By.xpath("//*[@id='cpContent_itemContentCtrl_TabuaLogin_19_ibtnLogin']"));
            e.click();
            //页面切换
            e = driver.findElement(By.xpath("//*[@id='main']/table[2]/tbody/tr[4]/td[1]/a"));
            if(e != null)
            	e.click();
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
