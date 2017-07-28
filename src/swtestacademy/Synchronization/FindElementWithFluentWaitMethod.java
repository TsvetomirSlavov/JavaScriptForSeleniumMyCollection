package swtestacademy.Synchronization;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class FindElementWithFluentWaitMethod {

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
	
	
}
}
