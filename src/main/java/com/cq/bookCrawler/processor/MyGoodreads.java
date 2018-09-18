package com.cq.bookCrawler.processor;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Set;

/**
 * @author: Celine Q
 * @create: 2018-09-18 19:57
 **/
public class MyGoodreads implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
    private static WebDriver driver;
    private Set<Cookie> cookies;



    @Override
    public void process(Page page) {
        System.out.println("========================== AWESOME ===========================");
    }


    @Override
    public Site getSite() {
        for(Cookie cookie: cookies){
            site.addCookie(cookie.getName().toString(), cookie.getValue().toString());
        }
        return site;
    }


    private void login(){
        if(System.getProperty("os.name").contains("Mac"))
            System.setProperty("webdriver.chrome.driver","/Users/Celine/Downloads/chromedriver");
        else
            System.setProperty("webdriver.chrome.driver","D:\\Dev\\driver\\chromedriver.exe");
        driver = new ChromeDriver();

        //获得登陆页面
        driver.get("https://www.goodreads.com/user/sign_in?source=home");
        //获得Connect to Amazon 页面
        WebElement e = driver.findElement(By.xpath("//*[@id='choices']/div/button"));
        if(e != null){
            e.click();
            Set handles = driver.getWindowHandles();
            String title;
            for(Object handle:handles){
                title = driver.switchTo().window(handle.toString()).getTitle();
                if(title.contains("Amazon Sign in")){
                    break;
                }
            }
            driver.findElement(By.xpath("//*[@id='ap_email']")).sendKeys("qianyaxue@gmail.com");
            driver.findElement(By.xpath("//*[@id='ap_password']")).sendKeys("mail@123");
            e = driver.findElement(By.xpath("//*[@id='signInSubmit']"));
            if(e != null)
                e.click();
            Set hs = driver.getWindowHandles();
            System.out.println("看看现在有几个 ： "  + hs.size());
            cookies = driver.manage().getCookies();
        }


    }

    public static void main(String arge[]){
        MyGoodreads mgr = new MyGoodreads();
        mgr.login();
        Spider.create(mgr).addUrl("https://www.goodreads.com/user/sign_in?source=home").run();
        driver.close();

    }


}

