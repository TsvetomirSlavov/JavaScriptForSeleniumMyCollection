package swtestacademy.Synchronization;
 
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
 
public class ImplicitWaitTest {
    static WebDriver driver;
    private static String url = "http://the-internet.herokuapp.com/dynamic_loading/2";
 
    //Setup Driver
    @BeforeClass
    public static void setupTest() {
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
    }
 
    @Test
    public void ImplicitWaitTest() {
        // wait for 10 seconds implicitly
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 
        //Get the selected date text before AJAX call
        WebElement startButton = driver.findElement(By.cssSelector("#start>button"));
 
        //Click the button
        startButton.click();
 
        //Find the result text
        WebElement resultText = driver.findElement(By.cssSelector("#finish>h4"));
 
        //Check the Expected and Actual Text
        assertThat(resultText.getText().trim(), is("Hello World!"));
    }
 
    //Close Driver
    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}