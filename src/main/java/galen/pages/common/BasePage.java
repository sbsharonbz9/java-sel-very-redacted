package galen.pages.common;

import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.GalenReport;
import galen.utils.ConfigLoader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.LinkedHashMap;

import static java.lang.Thread.sleep;

public class BasePage {
    public String titleText = "";
    public String reportText = "Next page";
    public BasicHelpers basicHelpers;
    public By headingTitle = By.xpath("//h1[contains(@class,'primary')]");
    public static By question = By.className("question");
    public  By nextBTN = By.xpath("//button[normalize-space()='Next']");
    public By modal = By.className("modal");
    public By btnBack = By.xpath("//button[text()='Back']");
    public By btnCancel = By.xpath("//button[text()='Cancel']");
    public By btnYes = By.xpath("//input[@value='yes']/following-sibling::span[1]");
    public By btnNo = By.xpath("//input[@value='no']/following-sibling::span[1]");
    public By btnUnsure = By.xpath("//input[@id='assessment-radio-unsure']/following-sibling::span[1]");
    public By btnYesModal = By.xpath("//button[@value='yes']");
    public By btnNoModal = By.xpath("//button[@value='no']");
    public By btnXModal = By.xpath("//button[@class='btn-close']");
    public By btnClose = By.xpath("//button[text()='Close']");
    public By modalCheckbox = By.xpath("//input[@id='confirm']/following-sibling::span[1]");
    public By btnConfirm = By.xpath("//button[text()='Confirm']");
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
    public WebElement getModal() {
        return basicHelpers.getWebElement(modal);
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

    public void clickCancelToDismiss(String modalText, @Nullable GalenReport report) {
        basicHelpers.verifyClickToNavNotDisplayed(btnCancel, "Cancel Button", modal, modalText, report);
    }

    public void verifyAssessmentIDDisplayed(@Nullable GalenReport report) {
        basicHelpers.verifyDisplayedFlex(assessmentID, "Assessment ID", report);
    }

    public boolean clickNextToPage(BasePage toPage, @Nullable GalenReport report) {
        return basicHelpers.verifyClickToPageTransition(toPage, nextBTN, "Next", report);
    }

    public boolean clickBackToPage(BasePage toPage, @Nullable GalenReport report) {
        return basicHelpers.verifyClickToPageTransition(toPage, btnBack, "Back", report);
    }

    public void clickYesOrNo(String yesNo, @Nullable GalenReport report) {
        By response = (yesNo.equals("Yes")) ? btnYes: btnNo;
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
        clickYesNoNext(yesNo, null);
        return basicHelpers.verifyActionToNavDisplayed("Click "+ yesNo+"\nClick Next\nVerify "+ modalDesc + " is " +
                "displayed", nextBTN,modalDesc, report);
    }

    public void clickYesNo_NextEnabled(String yesNo, @Nullable GalenReport report) {
        boolean result;
        clickYesOrNo(yesNo, null);
        result = verifyNextButtonEnabled(null);
        if (report!=null) {
            report.addStep("Click "+ yesNo+"\nVerify ' Next' button is enabled",
                    yesNo + " is clicked\n'Next' button is enabled",
                    result?"'Next' button is enabled":"'Next' button is disabled", result, true);
        }
    }

    public void clickYesNo_NextDisabled(String yesNo, @Nullable GalenReport report) {
        boolean result;
        clickYesOrNo(yesNo, null);
        result = verifyNextButtonDisabled(null);
        if (report!=null) {
            report.addStep("Click "+ yesNo+"\nVerify ' Next' button is disabled",
                    yesNo + " is clicked\n'Next' button is disabled",
                    result?"'Next' button is disabled":"'Next' button is enabled", result, true);
        }
    }

    public void clickYesNoUnsure(String yesNoUnsure, @Nullable GalenReport report) {
        By response;
        switch (yesNoUnsure) {
            case "Yes":
                response = btnYes;
                break;
            case "No":
                response = btnNo;
                break;
            default:
                response = btnUnsure;
                break;
        }
        basicHelpers.clickFlex(response, yesNoUnsure, report);
    }

    public void verifyYesNoUnsurePresent(@Nullable GalenReport report) {
        LinkedHashMap<String, By> buttons = new LinkedHashMap<>();
        buttons.put("'Yes' button", btnYes);
        buttons.put("'No' button", btnNo);
        buttons.put("'Unsure' button", btnUnsure);
        basicHelpers.verifyElementsDisplayed(buttons, report);
    }

    public boolean verifyYesNoPresent(@Nullable GalenReport report) {
        LinkedHashMap<String, By> buttons = new LinkedHashMap<>();
        buttons.put("'Yes' button", btnYes);
        buttons.put("'No' button", btnNo);
        return basicHelpers.verifyElementsDisplayed(buttons, report);
    }

    public void verifyYesNoUnselected(@Nullable GalenReport report) {
        LinkedHashMap<String, Object> buttons = new LinkedHashMap<>();
        buttons.put("'Yes' button not selected", basicHelpers.verifyRadioButtonNotSelected("Yes", null));
        buttons.put("'No' button not selected", basicHelpers.verifyRadioButtonNotSelected("No", null));
        basicHelpers.addMultipleVerificationStep("Yes and No not selected",buttons, report);
    }

    public void verifyNextPresent(@Nullable GalenReport report) {
        basicHelpers.verifyDisplayedFlex(nextBTN, "Next button", report);
    }

    public void clickConfirmModal(@Nullable GalenReport report) {
        basicHelpers.clickFlex(btnConfirm,"Confirm button",report );
    }

    public void clickConfirmCheckbox(@Nullable GalenReport report) {
        basicHelpers.clickFlex(modalCheckbox,"Checkbox",report );
    }

    public void clickNext(@Nullable GalenReport report) {
        basicHelpers.clickFlex(nextBTN, "Next", report);
    }

    public void clickMoreInfo(@Nullable GalenReport report) {
        basicHelpers.clickFlex(moreInfoLink, "More Info", report);
    }

    public void clickMoreInfoBack(@Nullable GalenReport report) {
        basicHelpers.clickFlex(moreInfoBack, "Back", report);
    }

    public boolean clickMoreInfoToModal(@Nullable GalenReport report) {
        return basicHelpers.verifyClickToNavDisplayed(moreInfoLink, "More Info button", moreInfoContent,
                "More Info modal", report);
    }

    public boolean clickConfirmToOpenModal(@Nullable GalenReport report) {
        return basicHelpers.verifyClickToNavDisplayed(btnConfirm, "Confirm", modal, "Confirm Modal",
                report);
    }

    public void clickBrowserBackToModal(@Nullable GalenReport report) {
        basicHelpers.clickBrowserBack(null);
        basicHelpers.verifyActionToNavDisplayed( "Click browser back button",
                 exitAssessmentModal, "Exit Assessment Modal", report);
    }

    public void clickMoreInfoBackToModalDismissed(@Nullable GalenReport report)  {
        basicHelpers.verifyClickToNavNotDisplayed(moreInfoBack, "Back button", modal, "Modal",
                report);
    }

    public void clickExitCloseToModalDismissed(@Nullable GalenReport report)  {
        basicHelpers.verifyClickToNavNotDisplayed(exitCloseButton, "Close button", modal, "Exit " +
                "Assessment modal", report);
    }

    public void clickExitLeaveToModalDismissed(@Nullable GalenReport report) {
        basicHelpers.verifyClickToNavNotDisplayed(exitLeaveButton, "Leave button", modal, "Exit " +
                "Assessment modal", report);
    }

    public boolean verifyMoreInfoLinkDisplayed(@Nullable GalenReport report) {
        return basicHelpers.verifyDisplayedFlex(moreInfoLink, "More Info link", report);
    }

    public void verifyMoreInfoDisplayed(@Nullable GalenReport report) {
        basicHelpers.verifyDisplayedFlex(moreInfoContent, "More Info modal", report);
    }

    public void verifyMoreInfoNotDisplayed(@Nullable GalenReport report) {
        basicHelpers.verifyNotDisplayedFlex(moreInfoContent, "More Info modal", report);
    }

    public void verifyBackButtonNotDisplayed(@Nullable GalenReport report) {
        basicHelpers.verifyNotDisplayedFlex(btnBack, "Back Button", report);
    }

    public boolean verifyNextButtonEnabled(@Nullable GalenReport report) {
        return basicHelpers.verifyButtonEnabled(nextBTN, true, report);
    }

    public boolean verifyNextButtonDisabled(@Nullable GalenReport report) {
        return basicHelpers.verifyButtonEnabled(nextBTN, false, report);
    }

    public void verifyConfirmButtonEnabled(@Nullable GalenReport report) {
        basicHelpers.verifyButtonEnabled(btnConfirm, true, report);
    }

    public void verifyConfirmButtonDisabled(@Nullable GalenReport report) {
        basicHelpers.verifyButtonEnabled(btnConfirm, false, report);
    }

    public void clickYesOrNoModal(String yesNo, @Nullable GalenReport report) {
        By response = (yesNo.equals("Yes")) ? btnYesModal : btnNoModal;
        basicHelpers.verifyClickToNavNotDisplayed(response, yesNo, modal, "modal",report);
    }

    public boolean clickConfirmModalToPage( BasePage page, @Nullable GalenReport report) {
        return basicHelpers.verifyClickToPageTransition(page,btnConfirm, "Confirm", report);
    }

    public boolean clickYesNoModalToPage(String yesNo, BasePage page, @Nullable GalenReport report) {
        By response = (yesNo.equals("Yes")) ? btnYesModal : btnNoModal ;
        return basicHelpers.verifyClickToPageTransition(page,response, yesNo, report);
    }

    public boolean verifyModalDismissed(@Nullable GalenReport report) {
        return basicHelpers.verifyNotDisplayedFlex(modal, "Modal", report);
    }

    public boolean verifyModalDisplayed(@Nullable GalenReport report) {
        return basicHelpers.verifyDisplayedFlex(modal, "Modal", report);
    }

    public void clickXButton(@Nullable GalenReport report) {
        basicHelpers.clickFlex(btnXModal, "X", report);
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

    public void clickSAPLinkToHandle(String originalHandle, @Nullable GalenReport report) {
        try {
            basicHelpers.clickFlex(sapLink, "Study Portal link", report);
            sleep(2000);
            driver.switchTo().window(originalHandle);
        } catch (Exception e) {
            if (report != null) {
                report.addStep("Click SAP link", "Return to original window", e.getMessage(),
                        false, true);
            }
        }
    }
}
