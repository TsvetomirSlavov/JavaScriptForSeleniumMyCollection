package swtestacademy.Javascript;
 
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
 
public class CalculatorExampleTest {
    static WebDriver driver;
    private static String url = "http://www.anaesthetist.com/mnm/javascript/calc.htm";
 
    //Setup Driver
    @BeforeClass
    public static void setupTest() {
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
        openNetworkTabMethod();
    }
 
    @Test
    public void calculatorJavaScriptTest() {
        //1-) Click "9"
        driver.findElement(By.name("nine")).click();
 
        //2-) Click "+"
        driver.findElement(By.name("add")).click();
 
        //3-) Click "3"
        driver.findElement(By.name("three")).click();
 
        //4-) Declare JavaScriptExecutor and call "Calculate()" function
        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("Calculate();");
 
        //5-) Assert that result is 12
        WebElement result = driver.findElement(By.name("Display"));
        assertThat(result.getAttribute("value"), is("12"));
    }
 
    //Close Driver
    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
    
    //Open Network Tab
    public static void openNetworkTabMethod(){
    	Actions openNetworkTab = new Actions(driver);
        openNetworkTab.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("q").perform();
        openNetworkTab.keyUp(Keys.CONTROL).keyUp(Keys.SHIFT).perform();
    }
}