package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class NoncardiacAllergicReactions extends BasePage {
    public By allergiesHeader = By.className("AvanafilAllergyScreen");

    public NoncardiacAllergicReactions(WebDriver driver) {
        super(driver);
        titleText="ALLERGIC REACTION";
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(allergiesHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), "Allergies", report);
    }

}
