package galen.pages.common;

import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.GalenReport;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import galen.utils.ConfigLoader;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.LinkedHashMap;

import static java.lang.Thread.sleep;

public class BasePage {
    public String titleText = "";
    public String reportText = "Next page";
    public BasicHelpers basicHelpers;
    public By headingTitle = By.xpath("//h1[contains(@class,'primary')]");
    public String questionText = "";
    public static By question = By.className("question");
    public  By nextBTN = By.xpath("//button[normalize-space()='Next']");
    public static By confirmModalTitle = By.xpath("//h1[@class='secondary']");
    public By modal = By.className("modal");
    public By btnBack = By.xpath("//button[text()='Back']");
    public By btnCancel = By.xpath("//button[text()='Cancel']");
    public By btnYes = By.xpath("//input[@value='yes']/following-sibling::span[1]");
    public By btnNo = By.xpath("//input[@value='no']/following-sibling::span[1]");
    public By btnUnsure = By.xpath("//input[@id='assessment-radio-unsure']/following-sibling::span[1]");
    public By btnYesModal = By.xpath("//button[@value='yes']");
    public By btnNoModal = By.xpath("//button[@value='no']");
    public By btnXModal = By.xpath("//button[@class='btn-close']");
    public By modalCheckbox = By.xpath("//input[@id='confirm']/following-sibling::span[1]");
    public By btnConfirmModal = By.xpath("//button[text()='Confirm']");
    public String moreInfoText="";
    public static By showIcon = By.xpath("//button/*[text()='Show']");
    public static By hideIcon = By.xpath("//button/*[text()='Hide']");
    public static By moreInfoLink = By.xpath("//button[@class='more-info__icon']");
    public By moreInfoContent = By.xpath("//div[@class='assessment-base__more-info-content']");
    public By moreInfoBack = By.xpath("//div[@class='assessment-base__more-info-content']/following-sibling::*/button");
    public static By mainError = By.cssSelector("#form > div.main-form-error > p:not(.hidden)");
    public By assessmentID = By.cssSelector(".assessment-id > span");
    public By exitAssessmentModal = By.className("exit-modal-body");
    public By exitCloseButton = By.cssSelector(".exit-modal-buttons > .button-primary");
    public By exitLeaveButton = By.cssSelector(".exit-modal-buttons > .button-danger");
    public By sapLink = By.tagName("a");
    protected WebDriver driver;
    public Wait<WebDriver> wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.basicHelpers = new BasicHelpers(driver);
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(2))
                .ignoring(TimeoutException.class).ignoring(NoSuchElementException.class);
    }

    public WebElement getHeadingTitle() {
        return basicHelpers.getWebElement(headingTitle);
    }

    public boolean verifyAtPage() {
        return basicHelpers.verifyAtPage(getHeadingTitle()!=null, reportText, null);
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeadingTitle() != null, reportText, report);
    }

    public WebElement getNextButton() {
        return basicHelpers.getWebElement(nextBTN);
    }
    public WebElement getMoreInfoButton() {
        return basicHelpers.getWebElement(moreInfoLink);
    }
    public WebElement getYesBtn() {
        return basicHelpers.getWebElement(btnYes);
    }
    public WebElement getNoBtn() {
        return basicHelpers.getWebElement(btnNo);
    }
    public WebElement getUnsureBtn() {
        return basicHelpers.getWebElement(btnUnsure);
    }
    public WebElement getModal() {
        return basicHelpers.getWebElement(modal);
    }
    public WebElement getXBtn(){
        return basicHelpers.getWebElement(btnXModal);
    }
    public WebElement getCancelBtn(){
        return basicHelpers.getWebElement(btnCancel);
    }
    public WebElement getMoreInfoModal(){
        return basicHelpers.getWebElement(moreInfoContent);
    }
    public WebElement getYesModalBtn() {
        return basicHelpers.getWebElement(btnYesModal);
    }
    public WebElement getNoModalBtn() {
        return basicHelpers.getWebElement(btnNoModal);
    }

    public void load(UrlType type) {
        String thisURL;

        if (type == UrlType.PETROS)
            thisURL =ConfigLoader.getInstance().getPetrosUrl();
         else if (type == UrlType.DEXTER)
            thisURL =ConfigLoader.getInstance().getDexterUrl();
         else if (type == UrlType.STUDY)
            thisURL =ConfigLoader.getInstance().getStudyUrl();
         else
            thisURL =ConfigLoader.getInstance().getMetricUrl();

         try {
            sleep(1000);
            driver.get(thisURL);
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            load(type);
        }
    }

    public void clickCancelButton(@Nullable GalenReport report) {
        basicHelpers.clickFlex(btnCancel, "Cancel Button", report);
    }

    public boolean verifyAssessmentIDDisplayed(@Nullable GalenReport report) {
        return basicHelpers.verifyDisplayedFlex(assessmentID, "Assessment ID", report);
    }

    public boolean clickNextToPage(BasePage toPage, @Nullable GalenReport report) {
        return basicHelpers.verifyClickToPageTransition(toPage, getNextButton(), "Next", report);
    }

    public boolean clickBackToPage(BasePage toPage, @Nullable GalenReport report) {
        return basicHelpers.verifyClickToPageTransition(toPage, driver.findElement(btnBack), "Back", report);
    }

    public void clickYesOrNo(String yesNo, @Nullable GalenReport report) {
        WebElement response = (yesNo.equals("Yes")) ? getYesBtn() : getNoBtn();
        basicHelpers.clickFlex(response, yesNo, report);
    }

    public void clickYesNoNext(String yesNo, @Nullable GalenReport report) {
       clickYesOrNo(yesNo, null);
       clickNext(null);
        if (report!=null) {
            report.addStep("Click "+ yesNo+"\nClick 'Next", yesNo + " is clicked\n" +
                    "Next is clicked", "As expected", true, true);
        }
    }

    public boolean clickYesNoNextToPage(String yesNo, BasePage toPage, @Nullable GalenReport report) {
        clickYesOrNo(yesNo, null);
        boolean result = clickNextToPage(toPage,null);
        String positiveResult = toPage.reportText + " is displayed";
        if (report!=null) {
            report.addStep("Click "+ yesNo+"\nClick Next\nVerify "+ toPage.reportText + " is displayed",
                    yesNo + " is clicked\nNext is clicked\n"+ toPage.reportText + " is displayed",
                    result?positiveResult:"Failed", result, true);
        }
        return result;
    }

    public boolean clickYesNoNextToModal(String yesNo, String modalDesc, @Nullable GalenReport report) {
        clickYesOrNo(yesNo, null);
        boolean result = basicHelpers.verifyClickToNavDisplayed(nextBTN, "Next", modal,
                modalDesc, report);
        String positiveResult = modalDesc + " is displayed";
        if (report!=null) {
            report.addStep("Click "+ yesNo+"\nClick Next\nVerify "+ modalDesc + " is displayed",
                    yesNo + " is clicked\nNext is clicked\n"+ modalDesc + " is displayed",
                    result?positiveResult:"Failed", result, true);
        }
        return result;
    }

    public void clickYesNoUnsure(String yesNoUnsure, @Nullable GalenReport report) {
        WebElement response;
        switch (yesNoUnsure) {
            case "Yes":
                response = getYesBtn();
                break;
            case "No":
                response = getNoBtn();
                break;
            default:
                response = getUnsureBtn();
                break;
        }
        basicHelpers.clickFlex(response, yesNoUnsure, report);
    }

    public boolean verifyYesNoUnsurePresent(@Nullable GalenReport report) {
        LinkedHashMap<String, By> buttons = new LinkedHashMap<String, By>();
        buttons.put("'Yes' button", btnYes);
        buttons.put("'No' button", btnNo);
        buttons.put("'Unsure' button", btnUnsure);
        return basicHelpers.verifyElementsDisplayed(buttons, report);
    }

    public boolean verifyYesNoPresent(@Nullable GalenReport report) {
        LinkedHashMap<String, By> buttons = new LinkedHashMap<String, By>();
        buttons.put("'Yes' button", btnYes);
        buttons.put("'No' button", btnNo);
        return basicHelpers.verifyElementsDisplayed(buttons, report);
    }

    public boolean verifyYesNoUnselected(@Nullable GalenReport report) {
        LinkedHashMap<String, Object> buttons = new LinkedHashMap<String, Object>();
        buttons.put("'Yes' button", basicHelpers.verifyRadioButtonNotSelected("Yes", null));
        buttons.put("'No' button", basicHelpers.verifyRadioButtonNotSelected("No", null));
        return basicHelpers.addMultipleVerificationStep("not selected",buttons, report);
    }

    public boolean verifyNextPresent(@Nullable GalenReport report) {
        return basicHelpers.verifyDisplayedFlex(nextBTN, "Next button", report);
    }

    public void clickConfirm(@Nullable GalenReport report) {
        basicHelpers.clickFlex(btnConfirmModal,"Confirm button",report );
    }

    public void clickConfirmModal(@Nullable GalenReport report) {
        basicHelpers.clickFlex(btnConfirmModal,"Confirm button",report );
    }

    public void clickConfirmCheckbox(@Nullable GalenReport report) {
        basicHelpers.clickFlex(modalCheckbox,"Checkbox",report );
    }

    public void clickNext(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getNextButton(), "Next", report);
    }

    public void clickMoreInfo(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(moreInfoLink), "More Info", report);
    }

    public void clickMoreInfoBack(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(moreInfoBack), "Back", report);
    }

    public boolean clickMoreInfoToModal(@Nullable GalenReport report) {
        return basicHelpers.verifyClickToNavDisplayed(moreInfoLink, "More Info button", moreInfoContent,
                "More Info modal", report);
    }

    public boolean clickBrowserBackToModal(@Nullable GalenReport report) {
        basicHelpers.clickBrowserBack(null);
        return basicHelpers.verifyActionToNavDisplayed( "Click browser back button",
                basicHelpers.getWebElement(exitAssessmentModal), "Exit Assessment Modal",
                report);
    }

    public boolean clickMoreInfoBackToModalDismissed(@Nullable GalenReport report) throws InterruptedException {
        return basicHelpers.verifyClickToNavNotDisplayed(moreInfoBack, "Back button", modal, "Modal",
                report);
    }

    public boolean clickExitCloseToModalDismissed(@Nullable GalenReport report)  {
        return basicHelpers.verifyClickToNavNotDisplayed(exitCloseButton, "Close button", modal, "Exit " +
                "Assessment modal", report);
    }

    public boolean clickExitLeaveToModalDismissed(@Nullable GalenReport report) {
        return basicHelpers.verifyClickToNavNotDisplayed(exitLeaveButton, "Leave button", modal, "Exit " +
                "Assessment modal", report);
    }

    public Boolean verifyMoreInfoLinkDisplayed(@Nullable GalenReport report) {
        return basicHelpers.verifyDisplayedFlex(moreInfoLink, "More Info link", report);
    }

    public Boolean verifyMoreInfoDisplayed(@Nullable GalenReport report) {
        return basicHelpers.verifyDisplayedFlex(moreInfoContent, "More Info modal", report);
    }

    public Boolean verifyMoreInfoNotDisplayed(@Nullable GalenReport report) {
        return basicHelpers.verifyNotDisplayedFlex(moreInfoContent, "More Info modal", report);
    }

    public Boolean verifyNextButtonEnabled(@Nullable GalenReport report) {
        return basicHelpers.verifyButtonEnabled(getNextButton(), true, report);
    }

    public Boolean verifyNextButtonDisabled(@Nullable GalenReport report) {
        return basicHelpers.verifyButtonEnabled(getNextButton(), false, report);
    }

    public void clickYesOrNoModal(String yesNo, @Nullable GalenReport report) {
        WebElement response = (yesNo.equals("Yes")) ? getYesModalBtn() : getNoModalBtn() ;
        wait.until(ExpectedConditions.elementToBeClickable(response));
        basicHelpers.clickFlex(response, yesNo, report);
        verifyModalDismissed(null);
    }

    public boolean clickConfirmModalToPage( BasePage page, @Nullable GalenReport report) {
        WebElement response = basicHelpers.getWebElement(btnConfirmModal);
        wait.until(ExpectedConditions.elementToBeClickable(btnConfirmModal));
        return basicHelpers.verifyClickToPageTransition(page,response, "Confirm", report);
    }

    public boolean clickYesNoModalToPage(String yesNo, BasePage page, @Nullable GalenReport report) {
        WebElement response = (yesNo.equals("Yes")) ? getYesModalBtn() : getNoModalBtn() ;
        wait.until(ExpectedConditions.elementToBeClickable(response));
        return basicHelpers.verifyClickToPageTransition(page,response, yesNo, report);
    }

    public boolean verifyModalDismissed(@Nullable GalenReport report) {
        return basicHelpers.verifyNotDisplayedFlex(modal, "Modal", report);
    }

    public boolean verifyModalDisplayed(@Nullable GalenReport report) {
        return basicHelpers.verifyDisplayedFlex(modal, "Modal", report);
    }

    public Boolean verifyQuestionTextMatches(@Nullable GalenReport report) {
        return basicHelpers.verifyText(basicHelpers.getWebElement(question), "Assessment question",
                questionText, report);
    }

    public Boolean verifyMoreInfoTextMatches(@Nullable GalenReport report) {
        return basicHelpers.verifyText(basicHelpers.getWebElement(moreInfoContent), "More info text",
                moreInfoText, report);
    }

    public void clickXButton(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getXBtn(), "X", report);
    }

    public boolean clickYesNoToOpenModal(String response, String modalText, @Nullable GalenReport report) {
        By responseBy = (response.equalsIgnoreCase("Yes")?btnYesModal:btnNoModal);
        return basicHelpers.verifyClickToNavDisplayed(responseBy, response, modal, modalText,report );
    }

    public void addressOpenModalConfirmations( @Nullable GalenReport report)  {
        try {
            clickConfirmCheckbox(report);
            clickConfirmModal(report);
            Thread.sleep(1000);
        } catch (Exception ignored) {
        }
    }

}
