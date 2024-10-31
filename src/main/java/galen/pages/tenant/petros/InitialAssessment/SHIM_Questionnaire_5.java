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

public class SHIM_Questionnaire_5 extends SingleResponseRadioButtonPage {
    public By  shimHeader = By.className("shimScreen5");

    public SHIM_Questionnaire_5(WebDriver driver) {
        super(driver);
        titleText="SATISFACTION";
        options=new ArrayList<>(Arrays.asList("Almost never or never", "A few times (much less than half the time)",
                "Sometimes (about half the time)", "Most times (much more than half the time)",
                "Almost always or always"));
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shimHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), "SHIM Questionnaire Screen 5", report);
    }
}
