import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "linkedin.tst.yanina@gmail.com", "Mykola123" },
                { "linkedin.TST.yanina@gmail.com", "Mykola123" }
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {
        //navigate to linkedin.com
        //Verify that login page is loaded.
        //Enter userEmail.
        //Enter userPassword.
        //Click on 'Sign in' button.
        //Verify Home page is loaded.

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver);

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded.");
        LinkedinHomePage linkedinHomePage = linkedinLoginPage.login(userEmail, userPassword);

        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home page is not loaded.");
    }

    @Test
    public void emptyUserEmailAndUserPasswordTest() {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver);

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded.");
        linkedinLoginPage = linkedinLoginPage.login("", "");

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded.");
    }


    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
                { "a@b.c", "wrong", "Please enter a valid email address.", "The password you provided must have at least 6 characters."},
        };
    }

    @Test(dataProvider = "invalidDataProvider")
    public void negativeLoginTest(String userEmail, String userPassword, String userEmailError, String userPasswordError) {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded.");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = linkedinLoginPage.login(userEmail, userPassword);

        Assert.assertTrue(linkedinLoginSubmitPage.isPageLoaded(), "LoginSubmitPage is not loaded.");

        Assert.assertEquals(linkedinLoginSubmitPage.getAlertMessageText(), "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert message text is wrong.");

        Assert.assertEquals(linkedinLoginSubmitPage.getUserEmailAlertText(), userEmailError,
                "userEmail alert text is wrong.");

        Assert.assertEquals(linkedinLoginSubmitPage.getUserPasswordAlertText(), userPasswordError,
                "userPassword alert text is wrong.");

    }
}
