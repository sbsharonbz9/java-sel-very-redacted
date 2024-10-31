package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class ReviewAnswers extends BasePage {

    By reviewHeader = By.className("summary-header");
    By answerCheckedButton = By.className("summary-button");
    By uniqueID = By.className("assessment-content");
    By confirmModal = By.className("confirm-answers-modal");
    By authModal = By.className("summary-auth-modal");
    By confirmCheckbox = By.className("confirm-button");

    By confirmLabel = By.id("confirm-button-label");
    By otherInformation = By.id("other-information");
    By continueBuy = By.className("continue-button");
    By confirmDrugFactsLabel = By.id("confirmation");
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
    }

    public WebElement getHeader() {
        return basicHelpers.getWebElement(uniqueID);
    }

    public WebElement getAnswersCheckedButton() {
        return basicHelpers.getWebElement(answerCheckedButton);
    }

    public WebElement getConfirmModal() {
        return basicHelpers.getWebElement(confirmModal);
    }

    public WebElement getAuthModal() {
        return basicHelpers.getWebElement(authModal);
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader()!=null, "Review Page", report);
    }

    public WebElement getConfirmCheckbox() {
        return basicHelpers.getWebElement(confirmCheckbox);
    }

    public void addressConfirmations(@Nullable GalenReport report) {
        clickAnswersChecked(report);
        verifyConfirmModalOpen(report);
        clickConfirmCheckbox(report);
    }

    public void clickSelfPurchaseEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(selfPurchaseEdit);
        basicHelpers.clickFlex(element, "Self Purchase Edit link", report);
    }

    public void clickPulmonaryHypertensionEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(pulmonaryHypertensionEdit);
        basicHelpers.clickFlex(element, "Pulmonary Hypertension Edit link", report);
    }

    public void clickNSFSEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(nSFSEdit);
        basicHelpers.clickFlex(element, "Not Safe For Sex Edit", report);
    }

    public void clickNitratePoppersEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(nitratePoppersEdit);
        basicHelpers.clickFlex(element, "Nitrate Poppers Use Edit", report);
    }

    public Boolean verifyConfirmModalOpen(@Nullable GalenReport report) {
        WebElement confirm = getConfirmModal();
        if (report != null) {
            report.verifyActionToNavDisplayed("Verify 'Confirm' modal appears", confirm,
                    "'Confirm' modal", confirm != null);
        }
        return confirm != null;
    }

    public Boolean verifyAuthModalOpen(@Nullable GalenReport report) {
        WebElement auth = getAuthModal();
        if (report != null) {
            report.verifyActionToNavDisplayed("Verify 'Create Account' modal appears", auth,
                    "'Create Account' modal", auth != null);
        }
        return auth != null;
    }

    public void clickAnswersChecked(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getAnswersCheckedButton(), getAnswersCheckedButton().getText(), report);
    }

    public void clickDateOfBirthEditButton(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(dateOfBirthEditButton);
        basicHelpers.clickFlex(element, "Edit Sex or DOB",report);
    }

    public void clickHivMedicineEditLink(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(hivMedicineEditLink);
        basicHelpers.clickFlex(element, "Edit HIV",report);
    }

    public void clickAllergicRectionEditLink(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(allergicRectionEditLink);
        basicHelpers.clickFlex(element, "Edit Allergic Reaction",report);
    }

    public void clickAntiBioticsEditLink(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(antiBioticsEdit);
        basicHelpers.clickFlex(element, "Antibiotics Edit",report);
    }
}
