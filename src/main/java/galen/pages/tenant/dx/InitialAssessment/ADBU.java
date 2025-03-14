package galen.pages.tenant.dx.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;

public class ADBU extends BasePage {
    public By adbuHeader = By.className("ADBUScreen");
    public By assessmentNumber = By.className("assessment-base__assessment-number");
    public String medConditionPt1 = "you are taking";
    public String medConditionPt2 = ", which may interact with Zena, so it is important to talk to a " +
            "doctor before using them together";
    public String askADoctor = "Ask a doctor if Zena is safe for you.";
    public String highCholesterolCondition = "high cholesterol";
    public String obesityCondition = "you have a BMI greater than 35";
    public String gallBladderCondition = "gallbladder";
    public String otherCancerCondition = "you have or have had other cancer";
    public String depressionCondition = "clinically diagnosed depression";
    public By docMayDecide = By.xpath("//ul[@class='adbuList']/following-sibling::p[1]");
    public String docMayDecideText="Your doctor may decide that it is OK for you to use Zena or may prescribe an " +
            "alternative birth control product.";
    public String isZenaSafeText="Have you talked to a doctor and they said Zena is safe for you?";
    public By isZenaSafe = By.xpath("//ul[@class='adbuList']/following-sibling::p[2]");
    public String modalText="By clicking the checkmark box, I confirm that I will need to ask a doctor if Zena is safe " +
            "for me based on my information.";
    public String checkboxText = "I have read and understand";
    public By conditions = By.className("adbuList");
    public By confirmModal = By.className("adbu-confirm");
    public By confirmButton = By.cssSelector("#attestationConfirmed > button.button.button-primary");

    public ADBU(WebDriver driver) {
        super(driver);
        headingTitle=adbuHeader;
        assessmentID=assessmentNumber;
        titleText="Ask a doctor if Zena is safe for you.";
        reportText="ADBU Screen";
        modal = confirmModal;
        btnConfirm=confirmButton;
    }

    public WebElement getConditions() {
        return basicHelpers.getWebElement(conditions);
    }

    public boolean clickYesNoToOpenModal(String response, @Nullable GalenReport report) {
        return clickYesNoToOpenModal(response, "ADBU confirm modal", report);
    }

    public boolean verifyConditionIsListed(String condition, @Nullable GalenReport report) {
        return basicHelpers.verifyText(conditions, "ADBU Condition", condition, report);
    }

    public boolean verifyConditionsListed(ArrayList<String> expectedConditions, @Nullable GalenReport report) {
        HashMap<String, Object> results = new HashMap<String, Object>();
        String verification = "the following ADBU conditions are listed:";
        for (String c : expectedConditions) {
            results.put(c, basicHelpers.verifyText(conditions, c, c, null));
        }
        if (report!=null) {
            report.addMultipleVerificationStep(verification, results, false);
        }
        return !results.containsValue(false);
    }

    public boolean verifyOtherMedIsListed(String condition, @Nullable GalenReport report) {
        if (condition.equals("St. John's Wort")) {
            condition = "St John's Wort";
        }
        String fullCondition = medConditionPt1 + " " + condition.toLowerCase()+ medConditionPt2;
        return basicHelpers.verifyText(conditions, "Other Medication: "+ condition, fullCondition, report);
    }

    public Boolean verifyDocDecide( @Nullable GalenReport report) {
        return basicHelpers.verifyText(docMayDecide, reportText, docMayDecideText,report);
    }

    public Boolean verifyZenaSafe(@Nullable GalenReport report) {
        return basicHelpers.verifyText(isZenaSafe,  reportText, isZenaSafeText,report);
    }

    public void addressConfirmations(String response, @Nullable GalenReport report)  {
        try {
            clickYesNoToOpenModal(response, "ADBU Confirmation modal", report);
            addressOpenModalConfirmations(report);
            Thread.sleep(1000);
        } catch (Exception ignored) {
        }
    }

    public void addressConfirmationsAndProgress(String response, BasePage page,@Nullable GalenReport report) {
        clickYesNoToOpenModal(response, "ADBU Confirmation modal", report);
        clickConfirmCheckbox(report);
        clickConfirmModalToPage(page,report);
    }
}
