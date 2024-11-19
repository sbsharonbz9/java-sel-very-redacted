package galen.pages.common;

import galen.helpers.common.GalenReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;

public class PrivacyPage extends BasePage {
    public String importanceText="Your privacy is very important to us.";
    public String termsAndConditions= "By clicking the \"I Accept\" button, you agree to the terms and conditions stated in the " +
            "Privacy Notice.";
    public String acceptButtonText= "I Accept";
    public String clickHereText= "(Optional) Click here to review the full privacy notice";
    public By  privacyHeader = By.className("PrivacyNotice");
    public By title = By.xpath("//section[@class='title-banner']");
    public By importance = By.xpath("//div[@class='PrivacyNotice']/div/h2");
    public By privacyParagraph = By.xpath("//div[contains(@class,'PrivacyNotice')]/div/p[1]");
    public  By clickHereButton=By.xpath("//a[contains(text(),'here')]");
    public By iAcceptBtn = By.className("accept-btn");
    public By privacyDropdownButton = By.className("privacy-drop-btn");
    public By fullPrivacyNotice = By.id("privacy-notice-full");

    public PrivacyPage(WebDriver driver) {
        super(driver);
        titleText = "PRIVACY NOTICE";
        reportText= "Privacy Page";
        headingTitle=privacyHeader;
    }

    public boolean clickIAcceptBtnToPage(BasePage toPage, @Nullable GalenReport report) {
        return basicHelpers.verifyClickToPageTransition(toPage, iAcceptBtn,"I Accept", report);
    }

    public boolean isNoticeExpanded(@Nullable GalenReport report)  {
        return basicHelpers.verifyActionToNavDisplayed("Verify privacy notice is expanded", fullPrivacyNotice,
                "Full privacy Notice", report);
    }

    public boolean verifyAllPageElementsPresent(@Nullable GalenReport report) {
        LinkedHashMap<String, By> resultMap = new LinkedHashMap<String, By>();
        resultMap.put("Privacy Notice",privacyParagraph);
        resultMap.put("I Accept Button", iAcceptBtn);
        return basicHelpers.verifyElementsDisplayed(resultMap, report);
    }

    public void clickExpandedArrow(@Nullable GalenReport report) {
        if (!isNoticeExpanded(null)) {
            basicHelpers.clickFlex(privacyDropdownButton, "Extend Privacy Arrow", report);
        }
    }

    public void clickCloseArrow(@Nullable GalenReport report) {
        if (isNoticeExpanded(null)) {
            basicHelpers.clickFlex(privacyDropdownButton, "Close Privacy Arrow", report);
        }
    }

    public void scrollToElement() {
        basicHelpers.scrollToElement(clickHereButton, null);
    }

    public void scrollToElementBack() {
        basicHelpers.scrollToElement(privacyParagraph, null);
    }

    public boolean isNoticeNotExpanded(@Nullable GalenReport report) {
        return basicHelpers.verifyNotDisplayedFlex(fullPrivacyNotice, "Full Privacy Notice", report);
    }
}
