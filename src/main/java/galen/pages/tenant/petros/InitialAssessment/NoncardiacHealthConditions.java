package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class NoncardiacHealthConditions extends CheckboxPage {
    public By nonCardiacHeader = By.className("NonCardiacHealthConditionsScreen");

    public NoncardiacHealthConditions(WebDriver driver) {
        super(driver);
        titleText="NON-CARDIAC CONDITIONS";
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nonCardiacHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), "Non-Cardiac Health Conditions Screen", report);
    }

}
