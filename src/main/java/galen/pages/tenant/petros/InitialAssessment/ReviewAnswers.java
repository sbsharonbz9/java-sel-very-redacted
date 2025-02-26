package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

public class ReviewAnswers extends BasePage {

    By answerCheckedButton = By.className("summary-button");
    By uniqueID = By.className("assessment-content");
    By confirmModal = By.className("confirm-answers-modal");
    By authModal = By.className("summary-auth-modal");

    By hivMedicineEditLink = By.xpath("//button[@value='31']");
    By allergicRectionEditLink = By.xpath("//button[@value='33']");
    By selfPurchaseEdit = By.xpath("//button[@value='1']");
    By pulmonaryHypertensionEdit=By.xpath("//button[@value='26']");
    By nSFSEdit = By.xpath("//button[@value='20']");
    By nitratePoppersEdit=By.xpath("//button[@value='17']");
    By dateOfBirthEditButton=By.xpath("//div[3]//button[1]//img[1]");
    By antiBioticsEdit=By.xpath("//button[@value='29']");

    public ReviewAnswers(WebDriver driver) {
        super(driver);
        titleText = "Review";
        headingTitle = uniqueID;
        modal = confirmModal;
    }

    public WebElement getAnswersCheckedButton() {
        return basicHelpers.getWebElement(answerCheckedButton);
    }

    public void addressConfirmationsAndProgress(BasePage nextPage, @Nullable GalenReport report) {
        addressOpenModalConfirmations(report);
        clickConfirmModalToPage(nextPage, report);
    }

    public void clickSelfPurchaseEdit(@Nullable GalenReport report) {
        basicHelpers.clickFlex(selfPurchaseEdit, "Self Purchase Edit link", report);
    }

    public void clickPulmonaryHypertensionEdit(@Nullable GalenReport report) {
        basicHelpers.clickFlex(pulmonaryHypertensionEdit, "Pulmonary Hypertension Edit link", report);
    }

    public void clickNSFSEdit(@Nullable GalenReport report) {
        basicHelpers.clickFlex(nSFSEdit, "Not Safe For Sex Edit", report);
    }

    public void clickNitratePoppersEdit(@Nullable GalenReport report) {
        basicHelpers.clickFlex(nitratePoppersEdit, "Nitrate Poppers Use Edit", report);
    }

    public boolean verifyConfirmModalOpen(@Nullable GalenReport report) {
         return verifyModalDisplayed(report);
    }

    public void clickAnswersChecked(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getAnswersCheckedButton(), getAnswersCheckedButton().getText(), report);
    }

    public void clickDateOfBirthEditButton(@Nullable GalenReport report) {
        basicHelpers.clickFlex(dateOfBirthEditButton, "Edit Sex or DOB",report);
    }

    public void clickHivMedicineEditLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(hivMedicineEditLink, "Edit HIV",report);
    }

    public void clickAllergicRectionEditLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(allergicRectionEditLink, "Edit Allergic Reaction",report);
    }

    public void clickAntiBioticsEditLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(antiBioticsEdit, "Antibiotics Edit",report);
    }
}
