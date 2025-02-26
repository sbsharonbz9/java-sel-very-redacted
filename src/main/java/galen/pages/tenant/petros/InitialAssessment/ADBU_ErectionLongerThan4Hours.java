package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class ADBU_ErectionLongerThan4Hours extends BasePage {
    public By fourHourHeader = By.className("ErectionLongerThanFourHoursScreen");

    public ADBU_ErectionLongerThan4Hours(WebDriver driver) {
        super(driver);
        titleText = "ERECTION LONGER THAN FOUR HOURS";
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(fourHourHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), "Erection Longer than Four Hours", report);
    }
}