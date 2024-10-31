package galen.pages.sp;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import galen.utils.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;

public class Login extends SPBasePage {

    public By emailTextField = By.xpath("//input[@id='email']");
    public By passwordTextField = By.xpath("//input[@id='password']");
    public By submitButton =  By.xpath("//a[@href='/?forgot=1']/following-sibling::button");
    public String titleText = "SYSTEM ADMINISTRATION";
    public By forgotPasswordLink = By.xpath("//a[@href='/?forgot=1']");

    public Login(WebDriver driver) {
        super(driver);
        headingTitle=emailTextField;
        reportText="Study Portal Sign In Screen";
    }

    public WebElement getSubmitButton() { return basicHelpers.getWebElement(submitButton); }

    public WebElement getEmailField() { return basicHelpers.getWebElement(emailTextField); }

    public WebElement getPasswordField() { return basicHelpers.getWebElement(passwordTextField); }

    public void logIn(String email, @Nullable GalenReport report) {
        String steps = "Email and password entered\nClick 'Submit'";
        try {
            basicHelpers.sendTextFlex(getEmailField(), email, "Email", null);
            enterPassword(null);
            basicHelpers.verifyClickToPageTransition(new Participants(driver),getSubmitButton(), "Submit", null);
            if (report != null) {
                report.addStep(steps, "User " + email + " is logged in", "As expected", true, true);
            }
        } catch (Exception e) {
            if (report != null) {
                report.addStep(steps, "User is logged in",
                        e.getMessage(), false, true);
            }
        }
    }

    public void enterPassword(@Nullable GalenReport report) {
        basicHelpers.sendTextFlex(getPasswordField(), ConfigLoader.getInstance().getSPPassword(),
                "Password", report);
    }
}