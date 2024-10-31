package galen.pages.tenant.dexter.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

public class DiagnosedDepression extends BasePage {
    public By title = By.className("DiagnosedDepressionScreen");
    public static By modalPopup = By.className("confirm-answers-modal");
    public static By btnModalConfirm = By.className("confirm-modal-button");
    public static By btnModalBack = By.className("go-back-button");

    public DiagnosedDepression(WebDriver driver) {
        super(driver);
        headingTitle = title;
        titleText = "Is your depression clinically diagnosed?";
        reportText = "Clinical Depression Screen";
        btnConfirmModal=btnModalConfirm;
        modal=modalPopup;
    }

    public void clickBackButton(@Nullable GalenReport report) {
        basicHelpers.clickFlex(btnModalBack, "Back button", report);
    }

    public Boolean verifyConfirmModalOpen(@Nullable GalenReport report) {
        return verifyModalDisplayed(report);
    }

    public void verifyConfirmButton(@Nullable GalenReport report) {
        basicHelpers.verifyDisplayedFlex(btnModalConfirm, "Confirm Button", report);
    }

    public void verifyBackButton(@Nullable GalenReport report) {
        basicHelpers.verifyDisplayedFlex(btnModalBack, "Back Button", report);
    }

    public boolean clickYesAndAddressModalToPage(BasePage toPage,@Nullable GalenReport report) {
        clickYesNoNext("Yes", report);
        verifyConfirmModalOpen(report);
        return clickConfirmModalToPage(toPage, report);
    }
}
