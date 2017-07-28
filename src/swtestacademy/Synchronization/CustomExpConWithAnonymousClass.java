package swtestacademy.Synchronization;
 
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
 
public class CustomExpConWithAnonymousClass {
    static WebDriver driver;
    private static String url = "http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx";
 
    //Setup Driver
    @BeforeClass
    public static void setupTest() {
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
    }
 
    @Test
    public void CustomExpectedConditionWithAnonymousClassTest() {
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
        WebElement thirdOfJanuary = driver.findElement(By.xpath("//a[contains(.,'3')]"));
 
        //Click 3rd January
        thirdOfJanuary.click();
 
        //AdHoc Wait with Anonymous Class (Synchronization)
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                //Implement your functionality(logic) here (Synchronization code should be implemented in here)
                //We are checking that Selected Text Pane contains "2016"?
                return webDriver.findElement(By.cssSelector("#ctl00_ContentPlaceholder1_Label1")).getText().contains("2017");
            }
        });
 
        //Find Selected Dates Text
        String selectedDateTextAfterAjaxCall = driver.findElement(
                By.cssSelector("#ctl00_ContentPlaceholder1_Label1")).getText().trim();
 
        //Print selectedDateTextAfterAjaxCall to the console
        System.out.println("selectedDateTextAfterAjaxCall: " + selectedDateTextAfterAjaxCall +"\n" );
 
        //Check the Actual and Expected Text
        assertThat(selectedDateTextAfterAjaxCall, is("Monday, July 03, 2017"));
    }
 
    //Close Driver
    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}