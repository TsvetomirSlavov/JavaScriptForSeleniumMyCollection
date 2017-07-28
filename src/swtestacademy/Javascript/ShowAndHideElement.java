package swtestacademy.Javascript;

public class ShowAndHideElement {

	
	Hide and Show ElementJava

	JavascriptExecutor js = (JavascriptExecutor) driver;
	//Hide an element
	js.executeScript("document.getElementsByName('five')[0].style.display='none'");
	//Show an element
	js.executeScript("document.getElementsByName('five')[0].style.display='block'");

	JavascriptExecutor js = (JavascriptExecutor) driver;
	//Hide an element
	js.executeScript("document.getElementsByName('five')[0].style.display='none'");
	//Show an element
	js.executeScript("document.getElementsByName('five')[0].style.display='block'");
	
}
