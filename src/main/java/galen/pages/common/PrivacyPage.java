package galen.pages.common;

import galen.helpers.common.GalenReport;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.HashMap;
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
    private static final Logger logger = LoggerFactory.getLogger(PrivacyPage.class);

    public PrivacyPage(WebDriver driver) {
        super(driver);
        titleText = "PRIVACY NOTICE";
        reportText= "Privacy Page";
        headingTitle=privacyHeader;
    }

    public WebElement getIAcceptBtn() {
        return basicHelpers.getWebElement(iAcceptBtn);
    }

    public void clickIAcceptBtn(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getIAcceptBtn(),"I Accept", report);
        logger.info("Clicked the 'I Accept' button.");
    }

    public boolean clickIAcceptBtnToPage(BasePage toPage, @Nullable GalenReport report) {
        return basicHelpers.verifyClickToPageTransition(toPage, getIAcceptBtn(),"I Accept", report);
    }

    public boolean isNoticeExpanded(@Nullable GalenReport report) throws Exception {
        return basicHelpers.verifyCondition(()->driver.findElement(fullPrivacyNotice).isDisplayed(), "Privacy " +
                "notice is expanded and full Privacy Notice is visible", false, report);
    }

    public boolean verifyAllPageElementsPresent(@Nullable GalenReport report) {
        LinkedHashMap<String, By> resultMap = new LinkedHashMap<String, By>();
        resultMap.put("Privacy Notice",privacyParagraph);
        resultMap.put("I Accept Button", iAcceptBtn);
        return basicHelpers.verifyElementsDisplayed(resultMap, report);
    }

    public void clickExpandedArrow(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(privacyDropdownButton);
        basicHelpers.clickFlex(element, "Extend Privacy Arrow",report);
    }

    public void clickCloseArrow(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(privacyDropdownButton);
        basicHelpers.clickFlex(element, "Close Privacy Arrow",report);
    }

    public void scrollToElement() {
        WebElement element = driver.findElement(clickHereButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToElementBack() {
        WebElement element = driver.findElement(privacyParagraph);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }



    public boolean isNoticeNotExpanded(@Nullable GalenReport report) throws Exception {
        return basicHelpers.verifyCondition(
                () -> {
                    try {
                        return wait.until(ExpectedConditions.invisibilityOfElementLocated(fullPrivacyNotice));
                    } catch (Exception e) {
                        return false;
                    }
                },
                "Privacy notice is not expanded, full Privacy Notice is not visible",
                true,
                report
        );
    }


}
