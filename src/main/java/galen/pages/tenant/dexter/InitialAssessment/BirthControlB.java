package galen.pages.tenant.dexter.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;

public class BirthControlB extends BirthControl {

    public By title = By.className("BirthControlB");

    public BirthControlB(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Are you using a hormonal injection, implant or IUD for birth control?";
        reportText="Hormonal BC (B) Screen";
    }

    public void addressConfirmationsAndProgress(BasePage page, @Nullable GalenReport report) throws InterruptedException {
        clickYesToOpenRiskModal(report);
        clickConfirmCheckbox(report);
        clickConfirmModalToPage(page,report);
    }

    public boolean clickYesToOpenRiskModal(@Nullable GalenReport report) {
        String expected =  "Hormonal BC Risk modal is displayed";
        clickYesNoNext("Yes", null);
        boolean confirm = verifyConfirmModalOpen(null);
        if (report != null) {
            report.addStep("Click 'Yes'\nClick 'Next'\nVerify " + expected, expected,
                    confirm?expected:"Hormonal BC Risk Modal is not displayed", confirm, false);
        }
        return confirm;
    }
}
