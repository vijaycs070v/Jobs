package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.Wait;

public class SearchWiki {
	
	@FindBy(how=How.CSS, using="input#searchInput")
	WebElement searchTextBox;
	
	@FindBy(how=How.XPATH, using="//input[@type='submit']")
	WebElement searchIcon;
	
	@FindBy(how=How.XPATH, using="//table[contains(@class,'vcard')]//following-sibling::p")
	List<WebElement> searchRresult;
	
	WebDriver driver;


	public SearchWiki(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public static SearchWiki using(WebDriver driver) {
		return new SearchWiki(driver);
	}

	public SearchWiki searchOnWiki(String data) {
		Wait.waitForElement(driver, searchTextBox);
		searchTextBox.clear();
		searchTextBox.sendKeys(data);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

		}
		Wait.waitForElement(driver, searchIcon);
		searchIcon.click();
		return this;
	}
	
	public String getResult() {
		List<WebElement> eleResults=driver.findElements(By.xpath("//table[contains(@class,'vcard')]//following-sibling::p"));
		StringBuffer content=new StringBuffer();
		eleResults.forEach(e->{
			if(content.length()<200) {
				content.append(e.getText());
			}
		});
	
		return content.toString();
	}
}
