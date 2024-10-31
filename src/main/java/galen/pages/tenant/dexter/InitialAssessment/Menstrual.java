package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Menstrual extends BasePage {

    //Do you have menstrual periods?
    public By title = By.className("ChildbearingScreen");

    public Menstrual(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Do you have menstrual periods?";
        reportText="Menstrual Screen";
    }

}
