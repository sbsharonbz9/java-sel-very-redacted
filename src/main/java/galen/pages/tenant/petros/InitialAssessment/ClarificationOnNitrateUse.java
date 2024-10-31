package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosReport;
import galen.pages.common.BasePage;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class ClarificationOnNitrateUse extends CheckboxPage {

    public By clarificationHeader = By.xpath("//*[contains(@class, 'nitrateClarificationScreen')]");

    public ClarificationOnNitrateUse(WebDriver driver) {
        super(driver);
        titleText="Clarification on Nitrate Use";
        options=new ArrayList<>(Arrays.asList("Use nitrates regularly", "Only use in emergencies",
                "No longer using them", "Prescribed but never filled", "Prescribed, filled, but never used",
                "Doctor approved use of nitrates with erectile dysfunction medicine", "None of these"));
    }

    public WebElement getHeader() {
        return basicHelpers.getWebElement(clarificationHeader);
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader()!=null, titleText, report);
    }

}
