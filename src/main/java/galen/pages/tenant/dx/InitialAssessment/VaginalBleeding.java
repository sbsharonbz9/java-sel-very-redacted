package galen.pages.tenant.dx.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VaginalBleeding extends BasePage {
    public By title = By.className("VaginalBleedingScreen");

    public VaginalBleeding(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="'Do you currently have unexplained  vaginal bleeding?'";
        reportText="Unexpected Bleeding Screen";
    }

}