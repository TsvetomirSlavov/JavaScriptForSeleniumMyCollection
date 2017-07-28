package swtestacademy.Synchronization;
 
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
 
/**
 * Created by ONUR on 05.01.2016.
 */
public class CustomExpConWithWrappedAnonymousClass {
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
    public void CustomExpectedConditionWithWrappedAnonymousClassTest() {
        //Declare a Webdriver Wait
        WebDriverWait wait = new WebDriverWait(driver, 10);
 
        //Wait until presence of container
        wait.until(ExpectedConditions.presenceOfElementLocated
                (By.cssSelector(".demo-container.size-narrow")));
 
        //Get the selected date text before AJAX call
        String selectedDateTextBeforeAjaxCall = driver.findElement
                (By.cssSelector("#ctl00_ContentPlaceholder1_Label1")).getText().trim();
 
        //Print selectedDateTextBeforeAjaxCall to the console
        System.out.println("selectedDateTextBeforeAjaxCall: " + selectedDateTextBeforeAjaxCall +"\n" );
 
        //Find 3rd January on the calendar
        WebElement thirdOfJanuary = driver.findElement(By.xpath(".//*[contains(@class, 'rcWeekend')]/a[.='3']"));
 
        //Click 3rd January
        thirdOfJanuary.click();
 
        //Wrapped Anonymous Class (Synchronization)
        wait.until(textDisplayed(By.cssSelector("#ctl00_ContentPlaceholder1_Label1"),"2016"));
 
        //Find Selected Dates Text
        String selectedDateTextAfterAjaxCall = driver.findElement
                (By.cssSelector("#ctl00_ContentPlaceholder1_Label1")).getText().trim();
 
        //Print selectedDateTextAfterAjaxCall to the console
        System.out.println("selectedDateTextAfterAjaxCall: " + selectedDateTextAfterAjaxCall +"\n" );
 
        //Check the Expected and Actual Text
        assertEquals("Expected and Actual Text are not Equal!","Sunday, January 03, 2016", selectedDateTextAfterAjaxCall);
    }
 
    //Wrapped Anonymous Class
    private ExpectedCondition<Boolean> textDisplayed (final By elementFindBy, final String text){
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(elementFindBy).getText().contains(text);
            }
        };
    }
 
    //Extra Info: The below code is Lambda Expression of above method. It is only working with JAVA 8
    /*private ExpectedCondition<Boolean> textDisplayed (final By elementFindBy, final String text){
        return webDriver -> webDriver.findElement(elementFindBy).getText().contains(text);
    }*/
 
    //Close Driver
    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}