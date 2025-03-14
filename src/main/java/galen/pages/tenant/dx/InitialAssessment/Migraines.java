package galen.pages.tenant.dx.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Migraines extends BasePage {
    public By title = By.className("MigraneScreen"); // Misspelled in app css as well

    public Migraines(WebDriver driver) {
        super(driver);
        headingTitle=title;
        reportText="Migraines with Aura  Screen";
    }

}

