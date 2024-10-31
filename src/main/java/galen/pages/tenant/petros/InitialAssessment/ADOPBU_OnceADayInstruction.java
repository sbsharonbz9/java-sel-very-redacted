package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class ADOPBU_OnceADayInstruction extends BasePage {

    public By header = By.className("multipleEDMedicationInfoScreen");

    public ADOPBU_OnceADayInstruction(WebDriver driver) {
        super(driver);
        titleText = "OTHER MEDICATIONS";
    }

    public WebElement getHeader() {
        return basicHelpers.getWebElement(header);
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader()!=null, "Once a Day Instruction", report);
    }

}
