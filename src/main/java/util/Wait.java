package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {
	public static void waitForElement(WebDriver driver, WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,10);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		catch(Exception e) {
			System.out.println("Element not clicable "+element);
		}
		
		
	}

}
