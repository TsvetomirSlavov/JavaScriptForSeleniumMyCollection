package swtestacademy.Synchronization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

//Custom Expected Condition
//Returns boolean value (True or False)
public class ElementContainsText implements ExpectedCondition<Boolean> {
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