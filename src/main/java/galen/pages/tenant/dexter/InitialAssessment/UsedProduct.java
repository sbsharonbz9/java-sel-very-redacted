package galen.pages.tenant.dexter.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;


public class UsedProduct extends BasePage {

    public By title = By.className("PriorUseScreen");
    public By moreInfoModalPopup = By.className("more-info-tooltip-overlay");
    public static By btnClose = By.xpath("//button[text()='Close']");

    public UsedProduct(WebDriver driver) {
        super(driver);
        reportText="Prior Use Screen";
        titleText="Have you used this product before?";
        headingTitle=title;
    }

    public WebElement getCloseButton() {return basicHelpers.getWebElement(btnClose);}

    public void clickCloseButton(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getCloseButton(), "Close on tooltip", report);
    }

    public boolean verifyTooltipDisplayed(@Nullable GalenReport report) {
        return basicHelpers.verifyDisplayedFlex(moreInfoModalPopup, "Tooltip", report);
    }
    public boolean verifyTooltipDismissed(@Nullable GalenReport report) {
        return basicHelpers.verifyNotDisplayedFlex(moreInfoModalPopup, "Tooltip", report);
    }

}
