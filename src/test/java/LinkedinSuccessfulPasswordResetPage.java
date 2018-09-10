import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinSuccessfulPasswordResetPage extends LinkedinBasePage{

    @FindBy(xpath = "//button[text()='Go to homepage']")
    private WebElement goToHomepageButton;

    public LinkedinSuccessfulPasswordResetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LinkedinHomePage clickOnGoToHomeButton() {
        goToHomepageButton.click();
        return new LinkedinHomePage(driver);
    }

    public boolean isLoaded() {
        return goToHomepageButton.isDisplayed();
    }
}
