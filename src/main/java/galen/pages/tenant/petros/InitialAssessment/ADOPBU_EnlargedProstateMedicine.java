package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class ADOPBU_EnlargedProstateMedicine extends BasePage {
    public By prostateHeader = By.className("enlargedProstateScreen");

    public ADOPBU_EnlargedProstateMedicine(WebDriver driver) {
        super(driver);
        titleText="ENLARGED PROSTATE";
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(prostateHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), "Enlarged Prostate Screen", report);
    }
}
