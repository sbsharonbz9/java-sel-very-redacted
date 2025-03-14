package galen.pages.tenant.dx.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SmokingDisclaimer extends BasePage {

    public By title = By.className("SmokingDisclaimerScreen");

    public SmokingDisclaimer(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText= "Smoking Disclaimer";
        reportText="Smoking or Vape (Risks) Screen";
    }

}
