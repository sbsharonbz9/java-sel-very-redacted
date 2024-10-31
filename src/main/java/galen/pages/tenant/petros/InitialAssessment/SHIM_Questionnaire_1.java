package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosReport;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.common.BasePage;
import galen.pages.common.SingleResponseRadioButtonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class SHIM_Questionnaire_1 extends SingleResponseRadioButtonPage {
    public static String questionText = "Over the past six months, how do you rate your confidence that you could get " +
            "and keep an erection?";
    public static By veryHighLevel = By.xpath("//input[@id='assessment-radio-1']");
    public static By highLevel = By.xpath("//input[@id='assessment-radio-2']");
    public static By moderateLevel = By.xpath("//input[@id='assessment-radio-3']");
    public static By lowLevel = By.xpath("//input[@id='assessment-radio-4']");
    public static By veryLowLevel = By.xpath("//input[@id='assessment-radio-5']");
    public By shimHeader = By.className("shimScreen1");

    public SHIM_Questionnaire_1(WebDriver driver) {
        super(driver);
        titleText = "SHIM Questionnaire Screen 1";
        options = new ArrayList<>(Arrays.asList("Very low", "Low", "Moderate", "High", "Very High"));
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shimHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), "SHIM Questionnaire Screen 1", report);
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
