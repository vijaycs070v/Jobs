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

public class TestWikiResultReadingDataFromArgument {
	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
		String searchdata=null;
		if(args.length==0) {
			throw new RuntimeException("Provide input from command line argument");
		}
		else if(args.length>=1){
			searchdata=args[0];
		}
		
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
		driver=new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://en.wikipedia.org/wiki/Main_Page");
		
		
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

		Thread.sleep(5000);
		driver.quit();
		
		System.out.println("Execution finished");
		
	}


	
}
