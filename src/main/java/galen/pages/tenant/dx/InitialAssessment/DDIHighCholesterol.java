package galen.pages.tenant.dx.InitialAssessment;

import galen.enums.tenant.dx.DDIHighCholType;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class DDIHighCholesterol extends CheckboxPage {
    public  By title = By.className("CholesterolMedicationScreen");
    public ArrayList<String> highCOptions = new ArrayList<>(Arrays.asList(DDIHighCholType.ATORVASTATIN.label,
            DDIHighCholType.COLESEVELAM.label, DDIHighCholType.ROSUVASTATIN.label, "None of these"));

    public DDIHighCholesterol(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Are you currently taking any of these cholesterol medications?";
        reportText="DDI Conditions - High Cholesterol Screen";
        options=highCOptions;
    }

}
