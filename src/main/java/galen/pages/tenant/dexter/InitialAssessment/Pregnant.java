package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Pregnant extends BasePage {
    public By title = By.className("PregancyScreen"); // Also misspelled on app

    public Pregnant(WebDriver driver) {
        super(driver);
        headingTitle=title;
        reportText="Pregnant Screen";
    }

}
