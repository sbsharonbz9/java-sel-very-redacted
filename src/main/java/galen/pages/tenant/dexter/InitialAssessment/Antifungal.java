package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Antifungal extends BasePage {

    public By afHeader = By.className("AntiBacterialProductScreen");

    public Antifungal(WebDriver driver) {
        super(driver);
        headingTitle=afHeader;
        titleText="Are you currently using an antifungal product?";
        reportText="Antifungal Product Screen";
    }

}