package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class DDIHighCholesterol extends CheckboxPage {
    public  By title = By.className("CholesterolMedicationScreen");
    public ArrayList<String> highCOptions = new ArrayList<>(Arrays.asList("Atorvastatin", "Colesevelam",
            "Rosuvastatin", "None of these"));
    public ArrayList<String> allButNoneHC = new ArrayList<>(Arrays.asList("Atorvastatin", "Colesevelam",
            "Rosuvastatin"));

    public DDIHighCholesterol(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Are you currently taking any of these cholesterol medications?";
        reportText="High Cholesterol Screen";
        options=highCOptions;
    }

}
