package Ellie;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class JavaScriptInSeleniumXPathMy {
	
	WebDriver driver;

	@Before
	public void setUp(){
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.math.com/students/calculators/source/basic.htm");
	}
	
	@Test
	public void testHeight(){
		WebElement buttonOne = driver.findElement(By.xpath("//input[contains(@name,'one')]"));
		Point locationOfButtonOne = buttonOne.getLocation();
		buttonOne.click();
		System.out.println(locationOfButtonOne.getX() + " " + locationOfButtonOne.getY());
		String offset = buttonOne.getAttribute("offsetTop");
		System.out.println(offset);
	}
	
	
}




/*private int getElementTopPosition(WebElement element) {
	JavascriptExecutor js = getJavascriptExecutor();
	return ((Long) js.executeScript("return arguments[0].offsetTop",
			element)).intValue();
}

private int getElementHeight(WebElement element) {
	return ((Long) getJavascriptExecutor().executeScript(
			"return arguments[0].offsetHeight", element)).intValue();
}*/