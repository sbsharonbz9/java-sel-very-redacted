package galen.pages.sp;

import galen.helpers.common.GalenReport;
import galen.utils.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

public class Login extends SPBasePage {

    public By emailTextField = By.xpath("//input[@id='email']");
    public By passwordTextField = By.xpath("//input[@id='password']");
    public By submitButton =  By.xpath("//a[@href='/?forgot=1']/following-sibling::button");
    public String titleText = "SYSTEM ADMINISTRATION";

    public Login(WebDriver driver) {
        super(driver);
        headingTitle=emailTextField;
        reportText="Study Portal Sign In Screen";
    }

    public void logIn(String email, @Nullable GalenReport report) {
        String steps = "Email and password entered\nClick 'Submit'";
        String actual;
        boolean result;

        try {
            basicHelpers.sendTextFlex(emailTextField, email, "Email", null);
            enterPassword(null);
            basicHelpers.verifyClickToPageTransition(new Participants(driver), submitButton, "Submit", null);
            actual = "As Expected";
            result = true;
        } catch (Exception e) {
            actual = e.getMessage();
            result = false;
        }
        if (report != null) {
            report.addStep(steps, "User " + email + " is logged in", actual, result, true);
        }
    }

    public void enterPassword(@Nullable GalenReport report) {
        basicHelpers.sendTextFlex(passwordTextField, ConfigLoader.getInstance().getSPPassword(),
                "Password", report);
    }
}