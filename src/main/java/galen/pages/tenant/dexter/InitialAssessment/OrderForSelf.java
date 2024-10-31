package galen.pages.tenant.dexter.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

public class OrderForSelf extends BasePage {
    public By title = By.className("SelfOrderScreen");
    public By closeButton = By.xpath("//button[text()='Close']");
    public By moreInfoModalPopup = By.className("more-info-tooltip-overlay");

    public OrderForSelf(WebDriver driver) {
        super(driver);
        headingTitle = title;
        titleText = "Are you buying Zena for yourself?";
        reportText = "Confirm Customer Screen";
        modal=moreInfoModalPopup;
        btnConfirmModal=closeButton;
    }

    public WebElement getCloseButton() {
        return basicHelpers.getWebElement(closeButton);
    }

    public void clickClose(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getCloseButton(), "Close", report);
    }

    public void clickCloseToDismiss(@Nullable GalenReport report) {
        basicHelpers.verifyClickToNavNotDisplayed(closeButton, "Close",
        moreInfoModalPopup, "Tooltip", report);
        verifyAtPage(report);
    }

}