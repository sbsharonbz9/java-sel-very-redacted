package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class PulmonaryHypertensionsMedicine extends BasePage {
    public By heartHeader = By.className("HypertensionQuestionScreen");

    public PulmonaryHypertensionsMedicine(WebDriver driver) {
        super(driver);
        titleText="HYPERTENSION MEDICINE";
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(heartHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), "Hypertension Medicine", report);
    }

}
