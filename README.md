# JavaScriptForSeleniumMyCollection

How to use JavaScript in Selenium. Multiple examples.




Note: Some web pages are displayed with javascript, the elements of that page already present in the browser DOM, but are not visible. Implicit wait only waits for an element to appear in the DOM, so it returns immediately and at that time, if that element is not visible and if you try to interact with it, you will get NoSuchElementException. You can test any element is visible or clickable with below methods.

getWhenVisibleJava

public WebElement getWhenVisible(By locator, int timeout) {
    WebElement element = null;
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    return element;
}
1
2
3
4
5
6
public WebElement getWhenVisible(By locator, int timeout) {
    WebElement element = null;
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    return element;
}
clickWhenReadyJava

public void clickWhenReady(By locator, int timeout) {
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    element.click();
}
1
2
3
4
5
public void clickWhenReady(By locator, int timeout) {
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    element.click();
}




Synchronization Suggestions
Wrap custom wait in a method.
If you want to see meaningful messages when your custom wait fails, override toString method to implement more meaningful synchronization error messages.
If you use same WebDriver and WebDriverWait then create a wait variable and use it all the test script. Do not repeat to use “new WebDriverWait”. Make your test code more readable.
Before interacting any element, please wait it. 
Try to use explicit wait more rather than implicit wait. It is more robust and less risky.
“Right synchronization” makes your test robust and durable! Please pay attention on synchronization while you are coding your automation scripts.
