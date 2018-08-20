import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedinLoginTest {

    @Test
    public void successfulLoginTest() {
        //navigate to linkedin.com
        //Verify that login page is loaded.
        //Enter userEmail.
        //Enter userPassword.
        //Click on 'Sign in' button.
        //Verify Home page is loaded.

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/", "Login page URL is wrong.");






    }
}
