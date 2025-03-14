package galen.pages.tenant.dx.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;

public class OrderForSelf extends BasePage {
    public By title = By.className("SelfOrderScreen");
    public By moreInfoModalPopup = By.className("more-info-tooltip-overlay");

    public OrderForSelf(WebDriver driver) {
        super(driver);
        headingTitle = title;
        titleText = "Are you buying Zena for yourself?";
        reportText = "Confirm Customer Screen";
        modal=moreInfoModalPopup;
    }

    public void clickCloseToDismiss(@Nullable GalenReport report) {
        basicHelpers.verifyClickToNavNotDisplayed(btnClose, "Close", moreInfoModalPopup,
                "Tooltip", report);
        verifyAtPage(report);
    }

}