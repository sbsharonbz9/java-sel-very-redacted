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

public class SHIM_Questionnaire_2 extends SingleResponseRadioButtonPage {
    public static By veryHighLevel = By.xpath("//input[@id='assessment-radio-1']");
    public static By highLevel = By.xpath("//input[@id='assessment-radio-2']");
    public static By moderateLevel = By.xpath("//input[@id='assessment-radio-3']");
    public static By lowLevel = By.xpath("//input[@id='assessment-radio-4']");
    public static By veryLowLevel = By.xpath("//input[@id='assessment-radio-5']");
    public By shimHeader = By.className("shimScreen2");

    public SHIM_Questionnaire_2(WebDriver driver) {
        super(driver);
        titleText = "ERECTION OVER TIME";
        options = new ArrayList<>(Arrays.asList("Almost never or never", "A few times (much less than half the time)",
                "Sometimes (about half the time)", "Most times (much more than half the time)",
                "Almost always or always"));
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shimHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), "SHIM Questionnaire Screen 2", report);
    }

    public WebElement getVeryLowLevel() {
        return wait.until(ExpectedConditions.elementToBeClickable(veryLowLevel));
    }

    public WebElement getLowLevel() {
        return wait.until(ExpectedConditions.elementToBeClickable(lowLevel));
    }

    public WebElement getModerateLevel() {
        return wait.until(ExpectedConditions.elementToBeClickable(moderateLevel));
    }

    public WebElement getHighLevel() {
        return wait.until(ExpectedConditions.elementToBeClickable(highLevel));
    }

    public WebElement getVeryHighLevel() {
        return wait.until(ExpectedConditions.elementToBeClickable(veryHighLevel));
    }

}
