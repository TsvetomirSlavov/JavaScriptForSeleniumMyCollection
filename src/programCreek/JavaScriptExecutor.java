package programCreek;

public class JavaScriptExecutor {

}




Java Code Examples for org.openqa.selenium.JavascriptExecutor
The following are top voted examples for showing how to use org.openqa.selenium.JavascriptExecutor. These examples are extracted from open source projects. You can vote up the examples you like and your votes will be used in our system to product more good examples. 
+ Save this class to your library
Example 1
Project: thucydides   File: JavascriptExecutorFacade.java View source code	8 votes	vote down vote up
/**
 * Execute some Javascript in the underlying WebDriver driver.
 * @param script
 */
public Object executeScript(final String script) {
    if (javascriptIsSupportedIn(driver)) {
        JavascriptExecutor js = getJavascriptEnabledDriver();
        return js.executeScript(script);
    } else {
        return null;
    }
}
 
Example 2
Project: edx-app-android   File: NativeAppDriver.java View source code	8 votes	vote down vote up
/**
 * Switch between data and wifi(specific to android).
 * 
 * @param wifi
 * @param data
 * @throws InterruptedException
 */
public void setNetworkConnection(boolean wifi, boolean data,
		boolean airplane) throws InterruptedException {
	if (deviceOS.equalsIgnoreCase("android")) {
		NetworkConnectionSetting connectionSetting = new NetworkConnectionSetting(
				0);
		connectionSetting.setWifi(wifi);
		connectionSetting.setData(data);
		connectionSetting.setData(airplane);
		((AndroidDriver) appiumDriver)
				.setNetworkConnection(connectionSetting);
		connectionSetting = ((AndroidDriver) appiumDriver)
				.getNetworkConnection();
	} else {
		HashMap<String, Double> coords = new HashMap<String, Double>();
		JavascriptExecutor js = (JavascriptExecutor) appiumDriver;
		Dimension devicediam = appiumDriver.manage().window().getSize();
		int height = devicediam.getHeight();
		// int width = devicediam.getWidth();
		if (deviceName.equals("iPhone 6")) {
			appiumDriver.swipe(100, height, 100, 100, 500);
			Thread.sleep(3 * 1000);
			coords.put("x", new Double("" + 120));
			coords.put("y", new Double("" + 290));
			coords.put("duration", 0.5);
			js.executeScript("mobile: tap", coords);
			Thread.sleep(3 * 1000);
			coords.put("x", new Double("" + 100));
			coords.put("y", new Double("" + 100));
			coords.put("duration", 0.5);
			js.executeScript("mobile: tap", coords);

		} else {// For iPhone 5
			appiumDriver.swipe(100, height, 100, 100, 500);
			Thread.sleep(3 * 1000);
			coords.put("x", new Double("" + 100));
			coords.put("y", new Double("" + 180));
			coords.put("duration", 0.5);
			js.executeScript("mobile: tap", coords);
			Thread.sleep(3 * 1000);
			coords.put("x", new Double("" + 100));
			coords.put("y", new Double("" + 100));
			coords.put("duration", 0.5);
			js.executeScript("mobile: tap", coords);
		}
	}
}
 
Example 3
Project: seleniumQuery   File: RemoveAttrFunction.java View source code	8 votes	vote down vote up
public static SeleniumQueryObject removeAttr(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements,
											String attributeNames) {
	JavascriptExecutor js = (JavascriptExecutor) seleniumQueryObject.getWebDriver();
	String[] attributes = attributeNames.split(" ");
	for (WebElement webElement : elements) {
		for (String attributeName : attributes) {
			js.executeScript("arguments[0].removeAttribute(arguments[1])", webElement, attributeName);
		}
	}
	return seleniumQueryObject;
}
 
Example 4
Project: serenity-core   File: JavascriptExecutorFacade.java View source code	7 votes	vote down vote up
/**
 * Execute some Javascript in the underlying WebDriver driver.
 * @param script
 */
public Object executeScript(final String script) {
    if (javascriptIsSupportedIn(driver)) {
        JavascriptExecutor js = getJavascriptEnabledDriver();
        return js.executeScript(script);
    } else {
        return null;
    }
}
 
Example 5
Project: jazzautomation_source   File: WebUIManager.java View source code	7 votes	vote down vote up
public static void loadJQuery(JavascriptExecutor jsDriver)
{
  Object jquery = jsDriver.executeScript(" if ( typeof $ != 'undefined') { return 1;} else { return null; }");

  if (jquery == null)
  {
    URL    jqueryUrl  = Resources.getResource("jquery-1.8.0.min.js");
    String jqueryText = "";

    try
    {
      jqueryText = Resources.toString(jqueryUrl, Charsets.UTF_8);
    }
    catch (IOException e)
    {
      LOG.warn("Error obtaining jquery library.", e);
    }

    LOG.info("\tEnable Jquery");
    jsDriver.executeScript(jqueryText);
  }
}
 
Example 6
Project: dextranet   File: PaginaPrincipal.java View source code	7 votes	vote down vote up
public PaginaPrincipal scrollAteFim() {
	JavascriptExecutor js = (JavascriptExecutor) driver;

	// desce ate o fim do scroll
	js.executeScript("window.scrollTo(0, 100000);");
	this.waitingForLoading();
	// volta o scroll para poder descer novamente
	js.executeScript("window.scrollTo(0, -100000);");

	return this;
}
 
Example 7
Project: Wicket-Page-Test   File: WicketSelenium.java View source code	7 votes	vote down vote up
public void subscribeAjaxDoneHandler() {
	JavascriptExecutor jsExec = (JavascriptExecutor) selenium;
	String defineAjaxDoneIndicatorExpr = "if (typeof wicketPageTestAjaxDone === 'undefined') { var wicketPageTestAjaxDone = false;"
			+ "Wicket.Event.subscribe('/ajax/call/complete', function(jqEvent, attributes, jqXHR, errorThrown, textStatus) { window.wicketPageTestAjaxDone = true; });"
			+ " }";
	jsExec.executeScript(defineAjaxDoneIndicatorExpr);
}
 
Example 8
Project: richfaces   File: Utils.java View source code	6 votes	vote down vote up
/**
 * Executes jQuery command on input element. E.g. to trigger click use jQ("click()", element).
 *
 * @param cmd command to be executed
 * @param element element on which the command will be executed
 */
public static void jQ(JavascriptExecutor executor, String cmd, WebElement element) {
    Preconditions.checkNotNull(executor, "The executor cannot be null.");
    Preconditions.checkNotNull(cmd, "The command cannot be null.");
    Preconditions.checkNotNull(element, "The element cannot be null.");
    String jQueryCmd = String.format("jQuery(arguments[0]).%s", cmd);
    executor.executeScript(jQueryCmd, unwrap(element));
}
 
Example 9
Project: automation-test-engine   File: SendTextSlashHandling.java View source code	6 votes	vote down vote up
/**
 * F.
 *
 * @throws InterruptedException the interrupted exception
 */
//@Test
public void func() throws InterruptedException {
 MyChromeDriver.setChromeDriverSystemEnv();
 WebDriver driver = new ChromeDriver();
 driver.get("file:///C:/index.html");
 //driver.switchTo().frame(driver.findElement(new By.ById("iframeResult")));
 WebElement textbox = driver.findElement(new By.ByTagName("textarea"));
 //textbox.clear();
 textbox.getAttribute("name");
 
 String text = "abcdikdkdkdkdkkdkdkdkdkdkkdkdkdkkdkdkdkdkdkdk https://github.com/bigtester/automation-test-engine";
 JavascriptExecutor jst = (JavascriptExecutor) driver;
 jst.executeScript("arguments[1].value = arguments[0]; ", text, textbox );
 
 Thread.sleep(6000);
 driver.quit();
}
 
Example 10
Project: keycloak   File: ProfileTest.java View source code	6 votes	vote down vote up
private String[] doGetProfileJs(String token) {
    StringBuilder sb = new StringBuilder();
    sb.append("var req = new XMLHttpRequest();\n");
    sb.append("req.open('GET', '" + getAccountURI().toString() + "', false);\n");
    if (token != null) {
        sb.append("req.setRequestHeader('Authorization', 'Bearer " + token + "');\n");
    }
    sb.append("req.setRequestHeader('Accept', 'application/json');\n");
    sb.append("req.send(null);\n");
    sb.append("return req.status + '///' + req.responseText;\n");

    JavascriptExecutor js = (JavascriptExecutor) driver;
    String response = (String) js.executeScript(sb.toString());
    return response.split("///");
}
 
Example 11
Project: qxwebdriver-java   File: Calendar.java View source code	6 votes	vote down vote up
public void testCalendar(String id) throws InterruptedException {
	By calHeaderLoc = By.xpath("descendant::td[contains(@class, 'qx-calendar-month')]");
	By prevMonthLoc = By.xpath("descendant::button[contains(@class, 'qx-calendar-prev')]");
	By nextMonthLoc = By.xpath("descendant::button[contains(@class, 'qx-calendar-next')]");
	
	String[] monthNames;
	if (id.contains("custom")) {
		monthNames = monthNamesCustom;
	} else {
		monthNames = monthNamesDefault;
	}
	
	WebElement calendar = webDriver.findElement(By.id(id));
	WebElement calHeader = calendar.findElement(calHeaderLoc);
	Assert.assertEquals(monthNames[month] + " " + year, calHeader.getText());
	
	String getValue = "return qxWeb('#" + id + "').getValue()";
	JavascriptExecutor exec = (JavascriptExecutor) webDriver;
	String valueBefore = (String) exec.executeScript(getValue);
	
	WebElement prevMonth = calendar.findElement(prevMonthLoc);
	prevMonth.click();
	// refresh the elements because the calendar re-renders itself if the displayed month changes
	calendar = webDriver.findElement(By.id(id));
	calHeader = calendar.findElement(calHeaderLoc);
	
	int prevMonthIdx;
	int prevYear = year;
	if (month == 0) {
		prevMonthIdx = 11;
		prevYear = prevYear - 1;
	} else {
		prevMonthIdx = month - 1;
	}
	String prevMonthName = monthNames[prevMonthIdx];
	Assert.assertEquals(prevMonthName + " " + prevYear, calHeader.getText());
	
	WebElement nextMonth = calendar.findElement(nextMonthLoc);
	nextMonth.click();
	nextMonth = calendar.findElement(nextMonthLoc);
	nextMonth.click();
	// refresh the elements because the calendar re-renders itself if the displayed month changes
	calendar = webDriver.findElement(By.id(id));
	calHeader = calendar.findElement(calHeaderLoc);
	
	int nextMonthIdx;
	int nextYear = year;
	if (month == 11) {
		nextMonthIdx = 0;
		nextYear = nextYear + 1;
	} else {
		nextMonthIdx = month + 1;
	}
	String nextMonthName = monthNames[nextMonthIdx];
	Assert.assertEquals(nextMonthName + " " + nextYear, calHeader.getText());
	
	WebElement day = calendar.findElement(By.xpath("descendant::button[contains(@class, 'qx-calendar-day') and text() = '17']"));
	day.click();
	Thread.sleep(250);
	String getDateString = getValue + ".toString()";
	String valueAfter = (String) exec.executeScript(getDateString);
	Assert.assertNotEquals(valueBefore, valueAfter);
	String nextMonthNameEn = monthNamesDefault[nextMonthIdx];
	nextMonthNameEn = nextMonthNameEn.substring(0, 3);
	System.out.println("valueAfter " + valueAfter);
	System.out.println(" " + nextMonthNameEn + " 17 " + nextYear);
	Assert.assertTrue(valueAfter.contains(" " + nextMonthNameEn + " 17 " + nextYear));
}
 
Example 12
Project: selenide   File: Navigator.java View source code	6 votes	vote down vote up
protected void collectJavascriptErrors(JavascriptExecutor webdriver) {
  try {
    webdriver.executeScript(
        "window._selenide_jsErrors = [];\n" +
            "if (!window.onerror) {\n" +
            "  window.onerror = function (errorMessage, url, lineNumber) {\n" +
            "    var message = errorMessage + ' at ' + url + ':' + lineNumber;\n" +
            "    window._selenide_jsErrors.push(message);\n" +
            "    return false;\n" +
            "  };\n" +
            "}\n"
    );
  } catch (UnsupportedOperationException cannotExecuteJsAgainstPlainTextPage) {
    log.warning(cannotExecuteJsAgainstPlainTextPage.toString());
  } catch (WebDriverException cannotExecuteJs) {
    log.severe(cannotExecuteJs.toString());
  }
}