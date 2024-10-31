package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class Antifungals extends BasePage {
    public By antifungalHeader = By.className("AntifungalScreen");

    public Antifungals(WebDriver driver) {
        super(driver);
        titleText="ANTIFUNGAL MEDICINE";
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(antifungalHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), "Antifungal", report);
    }

}
