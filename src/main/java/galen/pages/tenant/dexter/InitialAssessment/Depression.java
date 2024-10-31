package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Depression extends BasePage {
    public By title = By.className("DepressionScreen");

    public Depression(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Do you have or have you ever had depression?";
        reportText="Depression Screen";
    }

   }
