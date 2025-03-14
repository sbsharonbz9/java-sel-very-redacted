package galen.pages.tenant.dx.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

public class EnterBP extends BasePage {
    public By title = By.className("BPNumbersScreen");
    public By confirmBPModal = By.className("check-answer");
    public By modalChangeButton = By.xpath("//div[@class='check-modal-btn-group']/button");
    public By inputSystolic = By.xpath("//input[@id='systolic']");
    public By inputSystolicError = By.xpath("//input[@id='systolic'][@aria-invalid='true']");
    public By inputDiastolic = By.xpath("//input[@id='diastolic']");
    public  By inputDiastolicError = By.xpath("//input[@id='diastolic'][@aria-invalid='true']");


    public EnterBP(WebDriver driver) {
        super(driver);
        titleText="Enter your blood pressure";
        headingTitle=title;
        reportText= "Enter BP Screen";
        modal=confirmBPModal;
    }

    public void enterBP(DxUser user, @Nullable GalenReport report) {
        String reportResponse="As expected";
        try {
            basicHelpers.sendTextFlex(inputSystolic, user.systolic, "Systolic BP", null);
            basicHelpers.sendTextFlex(inputDiastolic, user.diastolic, "Diastolic BP", null);
        } catch(Exception e) {
            reportResponse=e.getMessage();
        }
        if (report!=null) {
            report.addStep("Input BP:\n\tSystolic:"+user.systolic+"\n\tDiastolic:"+user.diastolic,
                    "Values entered", reportResponse, reportResponse.equals("As expected"));
        }
    }

    public void verifyHasSystolicError(@Nullable GalenReport report) {
        basicHelpers.verifyDisplayedFlex(inputSystolicError, "Systolic BP Validation Error", report);
    }

    public void verifyHasDiastolicError(@Nullable GalenReport report) {
        basicHelpers.verifyDisplayedFlex(inputDiastolicError, "Diastolic BP Validation Error", report);
    }

    public void clickChangeButton(@Nullable GalenReport report) {
        basicHelpers.verifyClickToPageTransition(this, modalChangeButton, "Change button",report);
    }

    public boolean enterBPAndProgress(DxUser user, BasePage page, @Nullable GalenReport report) {
        enterBP(user, report);
        return clickNextToPage(page,report);
    }

}
