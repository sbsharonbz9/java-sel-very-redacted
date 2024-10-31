package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class ADOPBU_HighBloodPressureMedicines extends BasePage {
    public By hbpHeader = By.className("HighBloodPressureScreen");

    public ADOPBU_HighBloodPressureMedicines(WebDriver driver) {
        super(driver);
        titleText="BLOOD PRESSURE MEDICINE";
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(hbpHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), "High Blood Pressure Screen", report);
    }

}
