package galen.pages.tenant.dx.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DNUPreventPregnancyScreen extends BasePage {
    public By title = By.className("DNUPreventPregnancyScreen");

    public DNUPreventPregnancyScreen(WebDriver driver) {
        super(driver);
        titleText="DNU Prevent Pregnancy Screen";
        headingTitle=title;
        reportText= "DNU Prevent Pregnancy Screen";
    }
}
