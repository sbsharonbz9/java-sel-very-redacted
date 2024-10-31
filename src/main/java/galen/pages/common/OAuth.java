package galen.pages.common;

import galen.helpers.common.GalenReport;
import galen.helpers.common.GalenUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

public class OAuth extends BasePage {
    public By title = By.xpath("//h1[text()[contains(.,'create an account.')]]");
    public static By headingTitle = By.xpath("//h1[@class='secondary']");
    public static By btnContinueAsGuest = By.xpath("//button[@testid='guest-sign-in']");
    public static By btnContinueWithGoogle = By.xpath("//button[@testid='google-sign-in']");
    public static By btnContinueWithApple = By.xpath("//button[@testid='apple-sign-in']");
    public By oauthHeader = By.className("SignUpScreen");
    public static By confirmModal   = By.className("guest-modal-content");
    public static By goBackButton = By.xpath("//*[@class='guest-modal-buttons']/button[1]");
    public static By confirmButton = By.xpath("//*[@class='guest-modal-buttons']/button[2]");

    public OAuth(WebDriver driver) {
        super(driver);
        headingTitle = oauthHeader;
        reportText = "Sign In Screen";
        modal=confirmModal;
        btnConfirmModal=confirmButton;
    }

    public WebElement getConfirmButton() {
        return basicHelpers.getWebElement(confirmButton);
    }

    public void chooseAccountType(GalenUser user, @Nullable GalenReport report) {
        basicHelpers.clickFlex(user.accountType.nav, user.accountType.name(), report);
        if (verifyConfirmDisplayed(null)) {
            clickConfirmModal(report);
        }
    }

    public void chooseAccountTypeAndProgress(GalenUser user, BasePage page, @Nullable GalenReport report) {
        basicHelpers.clickFlex(user.accountType.nav, user.accountType.name(), report);
        if (verifyConfirmDisplayed(null)) {
            clickConfirmToPage(page,report);
        }
    }

    public boolean verifyConfirmDisplayed(@Nullable GalenReport report) {
        return basicHelpers.verifyDisplayedFlex(confirmModal,"Guest Sign In confirm modal", report);
    }

    public void clickGuestButton(@Nullable GalenReport report) {
        basicHelpers.clickFlex(btnContinueAsGuest, "Click the Continue as a Guest button ",report);
    }

    public boolean clickConfirmToPage(BasePage page,@Nullable GalenReport report) {
        return basicHelpers.verifyClickToPageTransition(page,getConfirmButton(), "Confirm button", report);
    }
}
