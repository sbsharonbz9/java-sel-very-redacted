package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import galen.pages.common.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class ErectileDysfunction extends BasePage {
    public static String questionText = "Are you having recurring difficulties getting or keeping an erection hard " +
            "enough for sex (erectile dysfunction)?";
    public static By question = By.xpath("//fieldset/p");
    public By  edHeader = By.className("DifficultiesScreen");

    public ErectileDysfunction(WebDriver driver) {
        super(driver);
        titleText="BASIC INFORMATION";
        reportText="Erectile Dysfunction";
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(edHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), reportText, report);
    }





}
