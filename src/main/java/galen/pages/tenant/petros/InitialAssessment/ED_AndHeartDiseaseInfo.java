package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class ED_AndHeartDiseaseInfo extends BasePage {
    public By edAndCardiacInfoHeader = By.className("edAndCardiacConditionsScreen");

    public ED_AndHeartDiseaseInfo(WebDriver driver) {
        super(driver);
        titleText="ERECTILE DYSFUNCTION AND HEART DISEASE";
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(edAndCardiacInfoHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), "ED and Heart Disease Information", report);
    }
}
