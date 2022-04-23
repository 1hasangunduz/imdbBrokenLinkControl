package com.basepages;

import com.driver.DriverManager;
import com.utility.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.Driver;
import java.util.List;

public class SeleniumBasePage {

    public WebDriver driver=DriverManager.getDriver();


    //Elementeleri merkeze alarak scroll eder.
    public void scrollToElementBlockCenter(WebElement element, String whereToScroll) {

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            Log.pass("Scroll işleminin başarıyla gerçekleştiği obje :  " + whereToScroll);
        } catch (Exception e) {
            Log.fail("Error while scrolling to the element : ", e);
        }

    }

    public void switchToWindow() {
        for (String handle : DriverManager.getDriver().getWindowHandles()) {
            DriverManager.getDriver().switchTo().window(handle);
        }
    }

    public String getTextOfElement(WebElement elem) {

        String text = null;
        try {
            text = elem.getText();
            Log.pass(text);
        } catch (Exception exception) {
            Log.fail("Error while getting text of element: ", exception);
        }
        return text;
    }

    public void scrollToElement (WebElement element){
        scrollToElement(element,getTextOfElement(element));
    }

    private void scrollToElement(WebElement element, String textOfElement) {
    }

    public void getLinkContent(String hrefControl) {
        //first get all the <a> elements
        List<WebElement> linkList = driver.findElements(By.tagName("a"));

        //now traverse over the list and check
        for (int i = 0; i < linkList.size(); i++) {
            if (linkList.get(i).getAttribute("href").contains(hrefControl)) {
                linkList.get(i).click();
                break;
            }
        }
    }
}
