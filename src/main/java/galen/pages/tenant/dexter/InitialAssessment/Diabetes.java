package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Diabetes extends BasePage {
    public By title = By.className("DiabetesScreen");

    public Diabetes(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Do you have diabetes?";
        reportText="Diabetes Screen";
    }

}