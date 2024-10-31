package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BreastFeeding extends BasePage {
    public By title = By.className("BreastFeedingScreen");

    public BreastFeeding(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Are you breastfeeding your baby?";
        reportText="Breastfeeding Screen";
    }

}
