package page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;

public class LinkedinBasePage {
    protected WebDriver driver;
    protected static GMailService gMailService = new GMailService();

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected String getCurrentTitle() {
        return driver.getTitle();
    }

    protected WebElement waitUntilElementVisible(WebElement webElement, int timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected boolean isUrlContains(String partialUrl, int timeOutInSec){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
        try {
            return wait.until(ExpectedConditions.urlContains(partialUrl));
        } catch (TimeoutException e) {
           return false;
        }
    }

    protected void assertElementIsVisible(WebElement webElement, int timeOutInSec , String message) {
        try {
            waitUntilElementVisible(webElement, timeOutInSec);
        } catch (TimeoutException e) {
            throw new AssertionError(message);
        }
    }

}
