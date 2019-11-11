package Greatminds;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Js {
	public void readProgeress(WebDriver driver) {
		driver.navigate().to(driver.getCurrentUrl());
	    ((JavascriptExecutor) driver).executeScript("$(\".sort\").eq(1).next().show(); $(\".sort\").eq(1).next().find(\"a\").each(function(){ if($(this).text().trim()==\"Show activities about Progress\") $(this).trigger(\"click\") })");
	  
	}
	public void scrollTop( WebDriver driver) {
		driver.navigate().to(driver.getCurrentUrl());
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,700)");
	}
}
