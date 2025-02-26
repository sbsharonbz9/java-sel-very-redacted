package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class ADBU_CardiacConditions_2 extends CheckboxPage {
    public By heartHeader = By.className("adbuCardiacConditionsScreen2");

    public ADBU_CardiacConditions_2(WebDriver driver) {
        super(driver);
        titleText="PRE-EXISTING SYMPTOMS";
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(heartHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), "Cardiac Conditions Screen 2", report);
    }
}
