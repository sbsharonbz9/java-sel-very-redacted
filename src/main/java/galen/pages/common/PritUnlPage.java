package galen.pages.common;

import galen.enums.framework.UrlType;
import galen.utils.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

public class PritUnlPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(PritUnlPage.class);

    public By usernameAndPasswordBtn = By.xpath("//button[@id='auth-local-btn']");
    public By usernameField = By.xpath("//input[@id='username']");
    public By passwordField = By.xpath("//input[@id='password']");
    public By loginButton = By.xpath("//input[@id='submit']");

    public PritUnlPage(WebDriver driver) {
        super(driver);
        headingTitle = usernameAndPasswordBtn;
    }

    public WebElement getUserNameAndPwdButton() {
        return basicHelpers.getWebElement(usernameAndPasswordBtn);
    }

    public WebElement getUserNameTextField() {
        return basicHelpers.getWebElement(usernameField);
    }

    public WebElement getPasswordTextField() {
        return basicHelpers.getWebElement(passwordField);
    }

    public WebElement getLoginButton() {
        return basicHelpers.getWebElement(loginButton);
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
