package galen.pages.tenant.dexter.InitialAssessment;

import galen.constants.FrameworkConstants;
import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;

import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;

public class BloodClot extends CheckboxPage {
    public By title = By.className("BloodClotScreen");
    public ArrayList<String> clotOptions = new ArrayList<>(Arrays.asList("Blood clots", "Blood clotting disorder",
            "Family history of a blood clotting disorder", "None of these"));

    public BloodClot(WebDriver driver) {
        super(driver);
        headingTitle=title;
        options=clotOptions;
        titleText="Do you currently have or have you ever had blood clots or a blood clotting disorder?";
        reportText="Blood Clot Screen";
   }

}
