package StackOverFlowJS;

public class StackOverFlowJavaScriptUseInWebDriver {

//	The executeScript() takes function calls and raw JS, too. You can return a value from it and you can pass lots of complicated arguments to it, some random examples:

		// returns the right WebElement
		// it's the same as driver.findElement(By.id("someId"))
		js.executeScript("return document.getElementById('someId');");
		// draws a border around WebElement
		WebElement element = driver.findElement(By.anything("tada"));
		js.executeScript("arguments[0].style.border='3px solid red'", element);
		// changes all input elements on the page to radio buttons
		js.executeScript(
		        "var inputs = document.getElementsByTagName('input');" +
		        "for(var i = 0; i < inputs.length; i++) { " +
		        "    inputs[i].type = 'radio';" +
		        "}" );
		
		
		
		
		
		
		
		
		 public static WebDriver driver;
		    public static void main(String[] args) {
		        driver = new FirefoxDriver(); // This opens a window    
		        String url = "----";


		        /*driver.findElement(By.id("username")).sendKeys("yashwanth.m");
		        driver.findElement(By.name("j_password")).sendKeys("yashwanth@123");*/

		        JavascriptExecutor jse = (JavascriptExecutor) driver;       
		        if (jse instanceof WebDriver) {
		            //Launching the browser application
		            jse.executeScript("window.location = \'"+url+"\'");
		jse.executeScript("document.getElementById('username').value = \"yash\";");
		// Tag having name then
		driver.findElement(By.xpath(".//input[@name='j_password']")).sendKeys("admin");


		//Opend Site and click on some links. then you can apply go(-1)--> back  forword(-1)--> front.
		//Refresheing the web-site. driver.navigate().refresh();            
		jse.executeScript("window.history.go(0)");
		            jse.executeScript("window.history.go(-2)");
		            jse.executeScript("window.history.forward(-2)");

		            String title = (String)jse.executeScript("return document.title");
		            System.out.println(" Title Of site : "+title);

		            String domain = (String)jse.executeScript("return document.domain");
		            System.out.println("Web Site Domain-Name : "+domain);

		            // To get all NodeList[1052] document.querySelectorAll('*');  or document.all
		            jse.executeAsyncScript("document.getElementsByTagName('*')");

		            String error=(String) jse.executeScript("return window.jsErrors");
		            System.out.println("Windowerrors  :   "+error);



		            System.out.println("To Find the input tag position from top"); 
		            ArrayList<?> al =  (ArrayList<?>) jse.executeScript(
		                    "var source = [];"+
		                    "var inputs = document.getElementsByTagName('input');"+
		                    "for(var i = 0; i < inputs.length; i++) { " +
		                       "   source[i] = inputs[i].offsetParent.offsetTop" +      //"    inputs[i].type = 'radio';"
		                    "}"+
		                    "return source"                 
		                    );//inputs[i].offsetParent.offsetTop     inputs[i].type

		            System.out.println("next");
		            System.out.println("array : "+al);

		            // (CTRL + a) to access keyboard keys. org.openqa.selenium.Keys 
		            Keys k = null;
		            String selectAll = Keys.chord(Keys.CONTROL, "a");
		            WebElement body = driver.findElement(By.tagName("body"));
		            body.sendKeys(selectAll);

		            // Search for text in Site. Gets all ViewSource content and checks their.
		            if (driver.getPageSource().contains("login")) {
		                System.out.println("Text present in Web Site");
		            }

		        Long clent_height = (Long) jse.executeScript("return document.body.clientHeight");
		        System.out.println("Client Body Height : "+clent_height);

		        // using selenium we con only execute script but not JS-functions.

		    }
		    driver.quit(); // to close browser
		}
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    Documentation
		    
		    java.lang.Object executeScript(java.lang.String script,
                    java.lang.Object... args)
Executes JavaScript in the context of the currently selected frame or window. The script fragment provided will be executed as the body of an anonymous function.
Within the script, use document to refer to the current document. Note that local variables will not be available once the script has finished executing, though global variables will persist.

If the script has a return value (i.e. if the script contains a return statement), then the following steps will be taken:

For an HTML element, this method returns a WebElement
For a decimal, a Double is returned
For a non-decimal number, a Long is returned
For a boolean, a Boolean is returned
For all other cases, a String is returned.
For an array, return a List<Object> with each object following the rules above. We support nested lists.
Unless the value is null or there is no return value, in which null is returned
Arguments must be a number, a boolean, a String, WebElement, or a List of any combination of the above. An exception will be thrown if the arguments do not meet these criteria. The arguments will be made available to the JavaScript via the "arguments" magic variable, as if the function were called via "Function.apply"

Parameters:
script - The JavaScript to execute
args - The arguments to the script. May be empty
Returns:
One of Boolean, Long, Double, String, List or WebElement. Or null.
executeAsyncScript
java.lang.Object executeAsyncScript(java.lang.String script,
                         java.lang.Object... args)
Execute an asynchronous piece of JavaScript in the context of the currently selected frame or window. Unlike executing synchronous JavaScript, scripts executed with this method must explicitly signal they are finished by invoking the provided callback. This callback is always injected into the executed function as the last argument.
The first argument passed to the callback function will be used as the script's result. This value will be handled as follows:

For an HTML element, this method returns a WebElement
For a number, a Long is returned
For a boolean, a Boolean is returned
For all other cases, a String is returned.
For an array, return a List<Object> with each object following the rules above. We support nested lists.
Unless the value is null or there is no return value, in which null is returned
The default timeout for a script to be executed is 0ms. In most cases, including the examples below, one must set the script timeout WebDriver.Timeouts.setScriptTimeout(long, java.util.concurrent.TimeUnit) beforehand to a value sufficiently large enough.

Example #1: Performing a sleep in the browser under test.


long start = System.currentTimeMillis();
((JavascriptExecutor) driver).executeAsyncScript(
"window.setTimeout(arguments[arguments.length - 1], 500);");
System.out.println(
"Elapsed time: " + System.currentTimeMillis() - start);

Example #2: Synchronizing a test with an AJAX application:


WebElement composeButton = driver.findElement(By.id("compose-button"));
composeButton.click();
((JavascriptExecutor) driver).executeAsyncScript(
"var callback = arguments[arguments.length - 1];" +
"mailClient.getComposeWindowWidget().onload(callback);");
driver.switchTo().frame("composeWidget");
driver.findElement(By.id("to")).sendKeys("bog@example.com");

Example #3: Injecting a XMLHttpRequest and waiting for the result:


Object response = ((JavascriptExecutor) driver).executeAsyncScript(
"var callback = arguments[arguments.length - 1];" +
"var xhr = new XMLHttpRequest();" +
"xhr.open('GET', '/resource/data.json', true);" +
"xhr.onreadystatechange = function() {" +
"  if (xhr.readyState == 4) {" +
"    callback(xhr.responseText);" +
"  }" +
"};" +
"xhr.send();");
JsonObject json = new JsonParser().parse((String) response);
assertEquals("cheese", json.get("food").getAsString());

Script arguments must be a number, a boolean, a String, WebElement, or a List of any combination of the above. An exception will be thrown if the arguments do not meet these criteria. The arguments will be made available to the JavaScript via the "arguments" variable.

Parameters:
script - The JavaScript to execute.
args - The arguments to the script. May be empty.
Returns:
One of Boolean, Long, String, List, WebElement, or null.
See Also:
WebDriver.Timeouts.setScriptTimeout(long, java.util.concurrent.TimeUnit)
		    
		    
	
}
