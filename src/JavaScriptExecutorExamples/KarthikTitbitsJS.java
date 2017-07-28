package JavaScriptExecutorExamples;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class KarthikTitbitsJS {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://www.amazon.in");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript(arg0, arg1)
		

	}

}
