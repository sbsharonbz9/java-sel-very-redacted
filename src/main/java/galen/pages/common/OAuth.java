package galen.pages.common;

import galen.helpers.common.GalenReport;
import galen.helpers.common.GalenUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;

public class OAuth extends BasePage {
    public By title = By.xpath("//h1[text()[contains(.,'create an account.')]]");
    public static By headingTitle = By.xpath("//h1[@class='secondary']");
    public static By btnContinueAsGuest = By.xpath("//button[@testid='guest-sign-in']");
    public By oauthHeader = By.className("SignUpScreen");
    public static By confirmModal   = By.className("guest-modal-content");
    public static By confirmButton = By.xpath("//*[@class='guest-modal-buttons']/button[2]");

    public OAuth(WebDriver driver) {
        super(driver);
        headingTitle = oauthHeader;
        reportText = "Sign In Screen";
        modal=confirmModal;
        btnConfirm=confirmButton;
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
            clickConfirmModalToPage(page,report);
        }
    }

    public boolean verifyConfirmDisplayed(@Nullable GalenReport report) {
        return basicHelpers.verifyDisplayedFlex(confirmModal,"Guest Sign In confirm modal", report);
    }

    public void clickGuestButton(@Nullable GalenReport report) {
        basicHelpers.clickFlex(btnContinueAsGuest, "Click the Continue as a Guest button ",report);
    }

    public void clickGuestButtonToModal(@Nullable GalenReport report) {
        basicHelpers.verifyClickToNavDisplayed(btnContinueAsGuest, "Continue as Guest",
                confirmModal,"Confirmation modal",report);
    }
}
