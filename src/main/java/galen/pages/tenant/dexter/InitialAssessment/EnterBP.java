package galen.pages.tenant.dexter.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterUser;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

public class EnterBP extends BasePage {
    public By title = By.className("BPNumbersScreen");
    public By confirmBPModal = By.className("check-answer");
    public static String confirmBPHeader="It looks like you may have mis-typed your blood pressure";
    public By modalChangeButton = By.xpath("//div[@class='check-modal-btn-group']/button");
    public By inputSystolic = By.xpath("//input[@id='systolic']");
    public By inputSystolicError = By.xpath("//input[@id='systolic'][@aria-invalid='true']");
    public By inputDiastolic = By.xpath("//input[@id='diastolic']");
    public  By inputDiastolicError = By.xpath("//input[@id='diastolic'][@aria-invalid='true']");
    public static String errorText= "Please enter valid number";


    public EnterBP(WebDriver driver) {
        super(driver);
        titleText="Enter your blood pressure";
        headingTitle=title;
        reportText= "Enter BP Screen";
        modal=confirmBPModal;
    }

    public WebElement getInputSystolic() {
        return basicHelpers.getWebElement(inputSystolic);
    }

    public WebElement getInputDiastolic() { return basicHelpers.getWebElement(inputDiastolic);}

    public void enterBP(DexterUser user, @Nullable GalenReport report) {
        String reportResponse="As expected";
        try {
            basicHelpers.sendTextFlex(getInputSystolic(), user.systolic, "Systolic BP", null);
            basicHelpers.sendTextFlex(getInputDiastolic(), user.diastolic, "Diastolic BP", null);
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
        basicHelpers.verifyClickToPageTransition(this, basicHelpers.getWebElement(modalChangeButton),
                "Change button",report);
    }

    public boolean enterBPAndProgress(DexterUser user, BasePage page,@Nullable GalenReport report) {
        enterBP(user, report);
        return clickNextToPage(page,report);
    }

}
