package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.SingleResponseRadioButtonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class SHIM_Questionnaire_4 extends SingleResponseRadioButtonPage {

    public By shimHeader = By.className("shimScreen4");

    public SHIM_Questionnaire_4(WebDriver driver) {
        super(driver);
        titleText = "ERECTION MAINTENANCE";
        options = new ArrayList<>(Arrays.asList("Extremely difficult", "Very difficult",
                "Difficult", "Slightly difficult", "Not difficult"));
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shimHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), "SHIM Questionnaire Screen 4", report);
    }


}
