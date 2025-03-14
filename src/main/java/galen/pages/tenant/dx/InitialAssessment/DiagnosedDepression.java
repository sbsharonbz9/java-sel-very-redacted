package galen.pages.tenant.dx.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        btnConfirm=btnModalConfirm;
        modal=modalPopup;
        btnBack = btnModalBack;
    }

    public boolean clickYesAndAddressModalToPage(BasePage toPage,@Nullable GalenReport report) {
        clickYesNoNextToModal("Yes", "Clinical depression confirmation", report);
        return clickConfirmModalToPage(toPage, report);
    }

    public void clickYesAndOpenModal( @Nullable GalenReport report) {
        clickYesNoNextToModal("Yes", "Clinical depression confirmation", report);
    }
}
