package seleniumOrgJQuery;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JQUeryElementsLocators {
	
	WebDriver driver = new ChromeDriver();

//	Using JavaScript
//	You can execute arbitrary javascript to find an element and as long as you return a DOM Element, it will be automatically converted to a WebElement object.

//	Simple example on a page that has jQuery loaded:

//	java
	WebElement element = (WebElement) ((JavascriptExecutor)driver).executeScript("return $('.cheese')[0]");
//	csharp ruby python perl
//	Finding all the input elements for every label on a page:

//	java
	List<WebElement> labels = driver.findElements(By.tagName("label"));
	List<WebElement> inputs = (List<WebElement>) ((JavascriptExecutor)driver).executeScript(
	    "var labels = arguments[0], inputs = []; for (var i=0; i < labels.length; i++){" +
	    "inputs.push(document.getElementById(labels[i].getAttribute('for'))); } return inputs;", labels);
	
	
}
