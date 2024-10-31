package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class ClarificationOnPoppers extends BasePage {

    public By clarificationHeader = By.xpath("//*[contains(@class, 'unsurePopperScreen')]");

    public ClarificationOnPoppers(WebDriver driver) {
        super(driver);
        titleText="Clarification on Nitrate Poppers";
    }

    public WebElement getHeader() {
        return basicHelpers.getWebElement(clarificationHeader);
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader()!=null, titleText, report);
    }

}
