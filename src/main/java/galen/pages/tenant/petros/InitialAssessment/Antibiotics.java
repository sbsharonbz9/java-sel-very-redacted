package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class Antibiotics extends BasePage {
    public By antibioticHeader = By.className("AntibioticScreen");

    public Antibiotics(WebDriver driver) {
        super(driver);
        titleText = "ANTIBIOTICS";
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(antibioticHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), "Antibiotics", report);
    }

}
