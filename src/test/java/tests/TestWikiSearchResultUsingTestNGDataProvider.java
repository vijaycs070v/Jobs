package tests;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.SearchWiki;

public class TestWikiSearchResultUsingTestNGDataProvider {
	WebDriver driver;
	
	@BeforeClass
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
		driver=new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://en.wikipedia.org/wiki/Main_Page");
	}
	
	@Test(dataProvider="getSearchData")
	public void testWikiSearchResult(String searchdata) throws InterruptedException {
		
		String content=SearchWiki.using(driver).searchOnWiki(searchdata).getResult();
	//	System.out.println(content);
		String msg="";
		if(content.length()>200) {
			 msg=content.substring(0,200);
		}
			
	//	System.out.println(msg);
		int lastIndex=msg.lastIndexOf(".")+1;
		if(lastIndex>0) {
			msg=msg.substring(0,lastIndex);
		}
		
		System.out.println(msg);
		Thread.sleep(3000);
		
	}
	
	@DataProvider
	public Object[][] getSearchData() {
		String[][] data= {
				{"India"},
				{"jio"},
				{"Flipkart"}
		};
		return data;
		
	}
	
	@AfterClass
	public void teardown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
	
}
