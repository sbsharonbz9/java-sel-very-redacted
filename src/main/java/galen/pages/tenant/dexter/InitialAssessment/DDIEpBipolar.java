package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class DDIEpBipolar extends CheckboxPage {
    public  By title = By.className("EpilepsyMedScreen");
    public ArrayList<String> epBipolarOptions = new ArrayList<>(Arrays.asList("Barbiturates","Felbamate",
            "Lamotrigine","Phenytoin","Primidone","Rufinamide","None of these"));
    public ArrayList<String> allButNoneBipolar = new ArrayList<>(Arrays.asList("Barbiturates","Felbamate",
            "Lamotrigine","Phenytoin","Primidone","Rufinamide"));

    public DDIEpBipolar(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Are you currently taking any of these epilepsy or bipolar disorder medications?";
        reportText = "Epilepsy/Bipolar Meds Screen";
        options=epBipolarOptions;
    }

}
