package Ellie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FloatingMenu {
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = getBrowser();
		baseUrl = "http://www.tie3.mypalmbeachpost.com/";
		wait = new WebDriverWait(driver, 30);
	}

	@After
	public void tearDown() throws Exception {
		getBrowser().quit();
	}

	@Test
	public void testFloatingMenu() throws Exception {
		driver.get(baseUrl
				+ "news/entertainment/sushi-impresses-at-juno-beachs-china-tokyo/nXspN/");

		// Sign in
		driver.findElement(By.linkText("Sign In / Register")).click();
		type(driver.findElement(By
				.id("capture_signIn_traditionalSignIn_emailAddress")),
				"cmgtester1@coxinc.com");
		type(driver.findElement(By
				.id("capture_signIn_traditionalSignIn_password")), "cmgtest3r");
		driver.findElement(
				By.id("capture_signIn_traditionalSignIn_signInButton")).click();

		// Make sure you have logged in
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By
				.className("cmUserAuthed"))));
		assertEquals("CMGTester1", driver
				.findElement(By.linkText("CMGTester1")).getText());

		// Locate the floating menu �In This Section� on the right side of the
		// article.
		// Locate the sidebar - floating menu parent element
		// Locate the article
		WebElement floating_menu = driver.findElement(By
				.className("category-list"));
		WebElement article = driver.findElement(By.className("article"));
		WebElement sidebar = driver.findElement(By.className("sidebar"));

		// Scroll the page down, and check that the floating menu is now at the
		// bottom of the sidebar
		scrollDown();

		int side_bar_height = getElementHeight(sidebar);
		int menu_bottom = getElementBottomPosition(floating_menu);
		assertEquals(side_bar_height, menu_bottom);

		// Scroll the page up, and ensure the floating menu is at the top of the
		// article (it will be offset from the top by the ad height + 20 pixels)
		scrollUp();
		int side_bar_top = getElementTopPosition(sidebar);
		int article_top = getElementTopPosition(article);
		assertEquals(article_top, side_bar_top);

		int menu_top = getElementTopPosition(floating_menu);

		if (isElementPresent(By.cssSelector(".sidebar .row-ad"))) {
			int ad_height = getElementHeight(driver.findElement(By
					.cssSelector(".sidebar .row-ad")));
			menu_top -= ad_height;
		}
		assertEquals(0, menu_top);
	}

	private void scrollDown() {
		getJavascriptExecutor().executeScript(
				"window.scrollTo(0, document.body.scrollHeight);");
		pause(2000);
	}

	private void scrollUp() {
		getJavascriptExecutor().executeScript("window.scrollTo(0, 0);");
		pause(2000);
	}

	private int getElementTopPosition(WebElement element) {
		JavascriptExecutor js = getJavascriptExecutor();
		return ((Long) js.executeScript("return arguments[0].offsetTop",
				element)).intValue();
	}

	private int getElementHeight(WebElement element) {
		return ((Long) getJavascriptExecutor().executeScript(
				"return arguments[0].offsetHeight", element)).intValue();
	}

	private int getElementBottomPosition(WebElement element) {
		int top = getElementTopPosition(element);
		int height = getElementHeight(element);
		return (top + height);
	}

	private WebDriver getBrowser() {
		if (driver == null) {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		return driver;
	}

	private JavascriptExecutor getJavascriptExecutor() {
		if (js == null) {
			js = (JavascriptExecutor) getBrowser();
		}
		return js;
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private void type(WebElement element, String str) {
		element.clear();
		element.sendKeys(str);
	}

	private void pause(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			return;
		}
	}
}