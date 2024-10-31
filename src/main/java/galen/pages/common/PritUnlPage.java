package galen.pages.common;

import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.utils.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;

import static java.lang.Thread.sleep;

public class PritUnlPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(PritUnlPage.class);

    public By usernameAndPasswordBtn = By.xpath("//button[@id='auth-local-btn']");
    public By usernameField = By.xpath("//input[@id='username']");
    public By passwordField = By.xpath("//input[@id='password']");
    public By loginButton = By.xpath("//input[@id='submit']");

    public PritUnlPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getUserNameAndPwdButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(usernameAndPasswordBtn));
    }

    public WebElement getUserNameTextField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
    }

    public WebElement getPasswordTextField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
    }

    public WebElement getLoginButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    public boolean verifyAtPage() {
        return basicHelpers.getWebElement(usernameAndPasswordBtn)!=null;
    }

    public void authenticateUserIfRequired()  {
        authenticateUserIfRequired(UrlType.DEXTER);
    }

    public void authenticateUserIfRequired(UrlType type)  {
        try {
            load(type);
            sleep(1000);
            if (verifyAtPage()) {
                login();
            }
            sleep(1000);
        } catch(Exception e) {
            logger.info("No PritUnl page");
        }
    }

    public void getWelcomePage(@Nullable GalenReport report) {
        load(UrlType.PETROS);
        boolean result = driver.getTitle().contains("Avanafil OTC");
        report.addStep("Navigate to the Petros Web Application Welcome Screen\n" +
                        "Verify Petros Web Application Welcome Screen is displayed",
                "Petros Web Application Welcome Screen is displayed",
                "Petros Web Application Welcome Screen is displayed", result);
    }

    public void login() {
        ConfigLoader configLoader = new ConfigLoader();
        String username = configLoader.getUsername();
        String password = configLoader.getPassword();
        getUserNameAndPwdButton().click();
        getUserNameTextField().sendKeys(username);
        getPasswordTextField().sendKeys(password);
        getLoginButton().click();
    }
}
