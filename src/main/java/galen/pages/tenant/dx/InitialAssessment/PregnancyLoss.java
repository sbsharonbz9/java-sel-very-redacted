package galen.pages.tenant.dx.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PregnancyLoss extends BasePage {
    public By title = By.className("PregnancyLossScreen");

    public PregnancyLoss(WebDriver driver) {
        super(driver);
        headingTitle=title;
        reportText="Pregnancy Loss Screen";
    }

}
