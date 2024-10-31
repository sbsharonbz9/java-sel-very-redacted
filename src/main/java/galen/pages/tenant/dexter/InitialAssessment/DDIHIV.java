package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class DDIHIV extends CheckboxPage {
    public  By title = By.className("HivMedicationScreen");
    public ArrayList<String> hivOptions = new ArrayList<>(Arrays.asList("Fosamprenavir",
            "Darunavir","Efavirenz", "Etravirine", "Nelfinavir","Nevirapine","Ritonavir", "None of these"));
    public ArrayList<String> allButNoneHIV = new ArrayList<>(Arrays.asList("Fosamprenavir",
            "Darunavir","Efavirenz", "Etravirine", "Nelfinavir","Nevirapine","Ritonavir"));


    public DDIHIV(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Are you currently taking any of these HIV medications?";
        reportText = "HIV Medication Screen";
        options=hivOptions;
    }

}
