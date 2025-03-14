package galen.pages.tenant.dx.InitialAssessment;

import galen.enums.tenant.dx.DDIHIVType;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class DDIHIV extends CheckboxPage {
    public  By title = By.className("HivMedicationScreen");
    public ArrayList<String> hivOptions = new ArrayList<>(Arrays.asList(DDIHIVType.FOSAMPRENAVIR.label,
            DDIHIVType.DARUNAVIR.label, DDIHIVType.EFAVIRENZ.label, DDIHIVType.ETRAVIRINE.label,
            DDIHIVType.NEVIRAPINE.label, DDIHIVType.NEVIRAPINE.label, DDIHIVType.RITONAVIR.label, "None of these"));

    public DDIHIV(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Are you currently taking any of these HIV medications?";
        reportText = "DDI Conditions – HIV Meds Screen";
        options=hivOptions;
    }

}
