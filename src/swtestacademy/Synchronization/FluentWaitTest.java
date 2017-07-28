package swtestacademy.Synchronization;
 
import com.google.common.base.Function;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
 
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
 
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
 
public class FluentWaitTest {
    static WebDriver driver;
    private static String url = "http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx";
 
    //Setup Driver
    @BeforeClass
    public static void setupTest() {
        driver = new FirefoxDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
    }
 
    @Test
    public void FluentWaitTest() {
        //Using findElement method to find selectedText element until timeout period.
        WebElement selectedTextElement = findElement(driver,By.cssSelector("#ctl00_ContentPlaceholder1_Label1"),5);
 
        //SelectedDateElement
        String selectedDateBeforeAjaxCall = selectedTextElement.getText();
 
        //Print selectedDateTextBeforeAjaxCall to the console
        System.out.println("selectedDateTextBeforeAjaxCall: " + selectedDateBeforeAjaxCall +"\n" );
 
        //Find 3rd January on the calendar with findElement method which
        //comprises FluentWait implementation and returns Webelement.
        WebElement thirdOfJanuary = findElement(driver, By.xpath(".//*[contains(@class, 'rcWeekend')]/a[.='3']"), 5);
 
        //Click 3rd January
        thirdOfJanuary.click();
 
        //Call method which comprises of FluentWait implementation
        //It will wait until period time and checks the given locator's text contains "2016"
        //If it contains then it will return whole text
        By locator = By.cssSelector("#ctl00_ContentPlaceholder1_Label1");
        String selectedDateAfterAjaxCall= textContainsKeyword(driver, locator, 10,"2016");
 
        //Print selectedDateTextAfterAjaxCall to the console
        System.out.println("selectedDateTextAfterAjaxCall: " + selectedDateAfterAjaxCall +"\n" );
 
        //Check the Expected and Actual Text
        assertThat(selectedDateAfterAjaxCall, is("Sunday, January 03, 2016"));
    }
 
    //Using FulentWait in a method with ExpectedCondition
    private String textContainsKeyword(final WebDriver driver, final By locator, final int timeoutSeconds, String keyword) {
        //FluentWait Decleration
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeoutSeconds, TimeUnit.SECONDS) //Set timeout
                .pollingEvery(100, TimeUnit.MILLISECONDS) //Set query/check/control interval
                .withMessage("Timeout occured!") //Set timeout message
                .ignoring(NoSuchElementException.class); //Ignore NoSuchElementException
 
        //ExpectedCondition: Wait until text contains keyword until timeout period and return the whole text
        //If text does not contains keyword until timeout period, return null.
        return wait.until(new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver driver) {
                if(driver.findElement(locator).getText().contains(keyword)){
                    return driver.findElement(locator).getText();
                } else{
                    return null;
                }
            }
        });
    }
 
    //Find element with FluentWait
    private static WebElement findElement(final WebDriver driver, final By locator, final int timeoutSeconds) {
        //FluentWait Decleration
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeoutSeconds, TimeUnit.SECONDS) //Set timeout
                .pollingEvery(100, TimeUnit.MILLISECONDS) //Set query/check/control interval
                .withMessage("Timeout occured!") //Set timeout message
                .ignoring(NoSuchElementException.class); //Ignore NoSuchElementException
 
        //Wait until timeout period and when an element is found, then return it.
        return wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(locator);
            }
        });
 
        //This is lambda expression of below code block. It is only for JAVA 8
        //return wait.until((WebDriver webDriver) -> driver.findElement(locator));
    }
 
    //Close Driver
    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}