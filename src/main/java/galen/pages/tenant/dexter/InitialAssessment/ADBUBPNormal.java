package galen.pages.tenant.dexter.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

public class ADBUBPNormal extends BasePage {

    public By adbubpHeader = By.className("bloodPressureStart");
    public By getBloodPressureNumbersButton = By.className("button");

    public ADBUBPNormal(WebDriver driver) {
        super(driver);
        headingTitle=adbubpHeader;
        titleText="You need your recent blood pressure numbers.";
        reportText="Get Blood Pressure Measured Screen";
    }    public WebElement getEnterNumbersButton() {
        return basicHelpers.getWebElement(getBloodPressureNumbersButton);
    }

    public boolean clickGetBPButtonToEnterBP(@Nullable GalenReport report) {
        return basicHelpers.verifyClickToPageTransition(new EnterBPEnd(driver), getEnterNumbersButton(),
                "Enter numbers", report);
    }
}