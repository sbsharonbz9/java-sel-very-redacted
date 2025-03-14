package galen.pages.tenant.dx.InitialAssessment;

import galen.enums.tenant.dx.DDIHepCType;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class DDIHepC extends CheckboxPage {
    public  By title = By.className("HepatitisCScreen");
    public ArrayList<String> hepCOptions =
            new ArrayList<>(Arrays.asList(DDIHepCType.OMBITASVIR.label,DDIHepCType.PARITAPREVIR.label,
                    DDIHepCType.RITONAVIR.label,"None of these"));

    public DDIHepC(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Are you currently or have you recently taken (within last 2-3 weeks) any of these Hepatitis C " +
                "medications?";
        reportText = "DDI Conditions - Hepatitis C Meds Screen";
        options=hepCOptions;
    }

}
