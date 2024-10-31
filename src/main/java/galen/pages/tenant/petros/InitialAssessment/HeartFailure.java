package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class HeartFailure extends BasePage {

    public By heartHeader = By.className("HeartFailureScreen");

    public HeartFailure(WebDriver driver) {
        super(driver);
        titleText="HEART PROBLEMS";
    }

    public WebElement getHeader() {
        return basicHelpers.getWebElement(heartHeader);
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader()!=null, "Heart Failure", report);
    }
}
