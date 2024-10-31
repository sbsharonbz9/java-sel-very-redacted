package galen.pages.common;

import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosReport;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class WelcomePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(WelcomePage.class);

    public By btnSignIn = By.xpath("//header/a");
    public By btnBegin = By.cssSelector(".cta > button");

    String tenant="";
    public By logo = By.xpath("//header/img[@alt="+tenant+"']");
    public By welcomeParagraph = By.xpath("//main/section[0]/p");
    public By fouo = By.xpath("//main/section[1]div/p");

    String haveAccountText = "Already have an account? Sign in";
    String welcomeHeaderText = "Welcome to the " + tenant +" Self-Check";
    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public WelcomePage(WebDriver driver, String tenant) {
        super(driver);
        this.tenant=tenant;
        this.reportText="Welcome Page";
    }

    public WebElement getBeginButton() {
        return basicHelpers.getWebElement(btnBegin);
    }

    public void clickBegin(@Nullable GalenReport report) {
        basicHelpers.verifyClickToPageTransition(new PrivacyPage(driver), getBeginButton(),
                getBeginButton().getText() +" button", report );
    }

    public void verifyBeginButtonPresent(@Nullable GalenReport report) {
        basicHelpers.verifyDisplayedFlex( btnBegin, getBeginButton().getText() + " button", report );
    }

    public String getAssessmentID() {
        try {
            return basicHelpers.getWebElement(assessmentID).getText();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}
