package test;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinLoginSubmitPage;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;

@Feature(value = "Auth")
@Story(value = "Login")
public class LinkedinLoginTest extends LinkedinBaseTest {

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "linkedin.tst.yanina@gmail.com", "Mykola123" },
                { "linkedin.TST.yanina@gmail.com", "Mykola123" }
        };
    }

    /**
     * Verify successful user Login.
     *
     * Preconditions:
     * - Open new browser.
     * - Navigate to linkedin.com
     *
     * Scenario:
     * - Verify that login page is loaded.
     * - Enter userEmail.
     * - Enter userPassword.
     * - Click on 'Sign in' button.
     * - Verify Home page is loaded.
     */
    @Severity(BLOCKER)
    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded.");
        LinkedinHomePage linkedinHomePage = linkedinLoginPage.login(userEmail, userPassword);

        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home page is not loaded.");
    }

    @Test
    @Severity(NORMAL)
    public void emptyUserEmailAndUserPasswordTest() {
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
    @Severity(NORMAL)
    public void negativeLoginTest(String userEmail, String userPassword, String userEmailError, String userPasswordError) {
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
