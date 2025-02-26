package galen.pages.tenant.dexter.InitialAssessment;

import galen.enums.tenant.dexter.DDIEpBipolarType;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class DDIEpBipolar extends CheckboxPage {
    public  By title = By.className("EpilepsyMedScreen");
    public ArrayList<String> epBipolarOptions = new ArrayList<>(Arrays.asList(DDIEpBipolarType.BARBITUATES.label,
            DDIEpBipolarType.FELBAMATE.label,DDIEpBipolarType.LAMOTRAGINE.label, DDIEpBipolarType.PHENYTOIN.label,
            DDIEpBipolarType.PRIMADONE.label, DDIEpBipolarType.RUFINAMIDE.label,"None of these"));

    public DDIEpBipolar(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Are you currently taking any of these epilepsy or bipolar disorder medications?";
        reportText = "DDI Conditions - Epilepsy or Bipolar Disorder Meds Screen";
        options=epBipolarOptions;
    }

}
