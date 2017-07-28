package swtestacademy.Synchronization;
 
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
 
/**
 * Created by ONUR on 03.01.2016.
 */
public class CustomExpConWithNamedClass {
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
    public void CustomExpectedConditionWithNamedClassTest() {
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
 
        //Find 3rd July on the calendar
        WebElement thirdOfJuly = driver.findElement(By.xpath("//a[contains(.,'3')]"));
 
        //Click 3rd July
        thirdOfJuly.click();
 
        //Instead of using below ExpectedConditions, we will use our custom ExpectedCondition
        /*//Wait until invisibility of loader
        new WebDriverWait(driver,10).until(
                ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".raDiv")));
 
        //Wait until visibility of selected date text
        new WebDriverWait(driver,10).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ctl00_ContentPlaceholder1_Label1")));*/
 
        //Use custom ExpectedCondition
        wait.until(new ElementContainsText(By.cssSelector("#ctl00_ContentPlaceholder1_Label1"),"2017"));
 
        //Find Selected Dates Text
        String selectedDateTextAfterAjaxCall = driver.findElement(
                By.cssSelector("#ctl00_ContentPlaceholder1_Label1")).getText().trim();
 
        //Print selectedDateTextAfterAjaxCall to the console
        System.out.println("selectedDateTextAfterAjaxCall: " + selectedDateTextAfterAjaxCall +"\n" );
 
        //Check the Actual and Expected Text
        assertThat(selectedDateTextAfterAjaxCall, is("Monday, July 03, 2017"));
    }
 
    //Custom Expected Condition
    //Returns boolean value (True or False)
    private class ElementContainsText implements ExpectedCondition<Boolean> {
        private String textToFind;
        private By findBy;
 
        //Constructor (Set the given values)
        public ElementContainsText (final By elementFindBy, final String textToFind) {
            this.findBy = elementFindBy;
            this.textToFind = textToFind;
        }
 
        //Override the apply method with your own functionality
        @Override
        public Boolean apply(WebDriver webDriver) {
            //Find the element with given By method (By CSS, XPaht, Name, etc.)
            WebElement element = webDriver.findElement(this.findBy);
 
                //Check that the element contains given text?
                if(element.getText().contains(this.textToFind)) {
                    return true;
                } else {
                    return false;
                }
        }
 
        //This is for log message. I override it because when test fails, it will give us a meaningful message.
        @Override
        public String toString() {
            return ": \"Does " + this.findBy + " contain " + this.textToFind + "?\"";
        }
    }
 
    //Close Driver
    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}