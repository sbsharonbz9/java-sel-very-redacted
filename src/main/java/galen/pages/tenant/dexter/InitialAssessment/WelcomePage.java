package galen.pages.tenant.dexter.InitialAssessment;

import galen.helpers.common.GalenReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;

public class WelcomePage extends galen.pages.common.WelcomePage {

    public By btnBeginDex = By.className("cta-btn");
    public By header = By.className("landing-page");
    public By version = By.className("version");

    public WelcomePage(WebDriver driver) {
        super(driver);
        btnBegin = btnBeginDex;
        headingTitle = header;
        reportText = "Welcome Screen";
    }

    public void verifyVersionNumberIsDisplayed(@Nullable GalenReport report) {
        basicHelpers.verifyDisplayedFlex(version, "Version Number", report);
    }

}
