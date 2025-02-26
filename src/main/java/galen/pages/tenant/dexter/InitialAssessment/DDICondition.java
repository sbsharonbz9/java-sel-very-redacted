package galen.pages.tenant.dexter.InitialAssessment;

import galen.enums.tenant.dexter.DDIConditionType;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class DDICondition extends CheckboxPage {
    public By title = By.className("ConditionsScreen");
    public ArrayList<String> conditionOptions = new ArrayList<>(Arrays.asList(DDIConditionType.HEPATITIS_C.label,
            DDIConditionType.THYROID_DISEASE.label, DDIConditionType.EPILEPSY.label, DDIConditionType.BIPOLAR_DISORDER.label,
            DDIConditionType.HIV.label, DDIConditionType.HIGH_CHOLESTEROL.label, DDIConditionType.NONE_OF_THESE.label));

    public DDICondition(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Have you ever had any of these conditions?";
        options=conditionOptions;
        reportText="Conditions or DDI Screen";
    }

}
