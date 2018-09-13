package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

/**
 * LinkedinLogin Page Object class.
 */
public class LinkedinLoginPage extends LinkedinBasePage {

    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    /**
     * Constructor for LinkedinLoginPage.
     *
     * @param driver - driver instance from tests.
     */
    public LinkedinLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * User login by username/password.
     *
     * @param userEmail - String with userEmail.
     * @param userPassword - String with userPassword.
     * @param <T> - generic type to return different PageObjects.
     * @return one of corresponding PageObjects LinkedinLoginPage/LinkedinHomePage/LinkedinLoginSubmitPage.
     */
    public <T> T login(String userEmail, String userPassword) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (getCurrentUrl().contains("/feed")) {
            return (T) new LinkedinHomePage(driver);
        }
        if (getCurrentUrl().contains("/login-submit")) {
            return (T) new LinkedinLoginSubmitPage(driver);
        }
        else {
            return (T) this;
        }
    }

    public LinkedinRequestPasswordResetPage clickOnForgotPasswordLink() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        forgotPasswordLink.click();
        return new LinkedinRequestPasswordResetPage(driver);
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().equals("https://www.linkedin.com/")
                && getCurrentTitle().equals("LinkedIn: Log In or Sign Up")
                && signInButton.isDisplayed();
    }

}
