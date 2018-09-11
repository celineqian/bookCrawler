package com.cq.bookCrawler;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

	@Test
	 public void testSelenium() {
	   if(System.getProperty("os.name").contains("Mac"))
	       System.setProperty("webdriver.chrome.driver","/Users/Celine/Downloads/chromedriver");
	   else
	       System.setProperty("webdriver.chrome.driver","D:\\Dev\\driver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.fijiairways.com/tabua-club/your-membership/");
        System.out.println("Page title is: " + driver.getTitle());
        WebElement element = driver.findElement(By.xpath("//div[@class='terms-of-use-accept-button Button Color_5d4b3f']"));
        System.out.println(element);
        element.click();
        driver.close();
    }
}
	