package galen.pages.tenant.dexter.InitialAssessment;

import galen.enums.tenant.dexter.CancerType;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class CancerList extends CheckboxPage {
    public  By title = By.className("Cancer2Screen");
    public ArrayList<String> cancerOptions = new ArrayList<>(Arrays.asList(
            CancerType.Breast_Cancer.label,
            CancerType.Endometrial_Cancer.label,
            CancerType.Meningioma.label,
            CancerType.Melanoma.label,
            CancerType.Ovarian_Cancer.label,
            CancerType.Lung.label,
            CancerType.Liver_Cancer.label,
            CancerType.Other_Cancer.label));

    public CancerList(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Have you ever had any of these cancers?";
        options=cancerOptions;
        reportText="List of Cancers Screen";
    }

}
