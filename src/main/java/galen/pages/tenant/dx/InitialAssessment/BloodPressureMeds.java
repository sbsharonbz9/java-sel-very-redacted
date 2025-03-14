package galen.pages.tenant.dx.InitialAssessment;


import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BloodPressureMeds extends BasePage {

    public By title = By.className("BPMedicationScreen");

    public BloodPressureMeds(WebDriver driver) {
        super(driver);
        headingTitle = title;
        titleText="Do you take medication for high blood pressure?";
        reportText="Blood Pressure Medications";
   }
}
