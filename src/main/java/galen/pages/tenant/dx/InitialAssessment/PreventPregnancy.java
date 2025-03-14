package galen.pages.tenant.dx.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PreventPregnancy extends BasePage {

    public By title = By.className("PreventPregnancyScreen");

    public PreventPregnancy(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Are you buying Zena to prevent pregnancy?";
        reportText="Prevent Pregnancy Screen";
    }
}
