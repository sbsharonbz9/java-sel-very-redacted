package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

public class ADOPBU extends BasePage {

    public String bphText = "You take a prescription medicine for an enlarged prostate (BPH)";
    public By thisBtnYes = By.xpath("//button[@value='yes']");
    public By thisBtnNo = By.xpath("//button[@value='no']");
    public By ADOPBUHeader = By.className("AskedDoctorOrPharmacistScreen");
    public By conditionsList = By.xpath("//ul[@class='adbuList']");
    public By conditions = By.xpath("//ul[@class='adbuList']/li");
    By confirmCheckbox = By.className("confirm-button");
    By confirmModal = By.className("confirm-answers");

    public ADOPBU(WebDriver driver) {
        super(driver);
        btnYes=thisBtnYes;
        btnNo=thisBtnNo;
        titleText="TALK TO A DOCTOR";
        reportText="Ask Doctor or Pharmacist";
    }

    public WebElement getHeader() {
        return basicHelpers.getWebElement(ADOPBUHeader);
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader()!=null, reportText, report);
    }

    public WebElement getConfirmCheckbox() {
        return basicHelpers.getWebElement(confirmCheckbox);
    }

    public Boolean verifyConfirmModalOpen(@Nullable GalenReport report) {
        WebElement confirm = basicHelpers.getWebElement(confirmModal);
        if (report != null) {
            report.verifyActionToNavDisplayed("Verify 'Confirm' modal appears", confirm,
                    "'Confirm' modal", confirm != null);
        }
        return confirm != null;
    }

    public boolean verifyBPHCondition(@Nullable GalenReport report) {
        return basicHelpers.verifyDisplayedFlex(By.xpath("//ul[@class='adbuList']/li[text()='"+bphText+"']"),
                bphText, report);
    }
}
