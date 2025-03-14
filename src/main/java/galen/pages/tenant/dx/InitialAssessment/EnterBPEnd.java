package galen.pages.tenant.dx.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;

public class EnterBPEnd extends EnterBP {
    public By title = By.className("bloodPressureInputs");
    public static By modalTitle = By.className("yesno-confirm");
    public By inputSystolicNew = By.xpath("//input[@id='finalSystolic']");
    public By inputDiastolicNew = By.xpath("//input[@id='finalDiastolic']");

    public EnterBPEnd(WebDriver driver) {
        super(driver);
        titleText="Enter your blood pressure";
        headingTitle=title;
        inputSystolic=inputSystolicNew;
        inputDiastolic=inputDiastolicNew;
        reportText= "Enter BP Screen";
        modal=modalTitle;
    }

    public void enterAndVerifyToModal(DxUser user, @Nullable GalenReport report) {
        enterBP(user, report);
        clickNext(report);
        verifyModalDisplayed(report);
    }

    public void enterAndVerifyToPage(DxUser user, BasePage toPage, @Nullable GalenReport report) {
        enterAndVerifyToModal(user, report);
        clickYesOrNoModal("Yes", report);
        verifyModalDismissed(null);
        toPage.verifyAtPage(report);
    }
}
