package galen.pages.tenant.dexter.InitialAssessment;

import galen.enums.tenant.dexter.DDIConditionType;
import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class DDICondition extends CheckboxPage {
    public  By title = By.className("ConditionsScreen");
    public ArrayList<String> conditionOptions = new ArrayList<>(Arrays.asList("Hepatitis C", "Thyroid disease",
            "Epilepsy", "Bipolar disorder", "HIV","High cholesterol", "None of these"));
    public ArrayList<String> allButNone = new ArrayList<>(Arrays.asList("Hepatitis C", "Thyroid disease",
            "Epilepsy", "HIV","High cholesterol"));

    public DDICondition(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Have you ever had any of these conditions?";
        options=conditionOptions;
        reportText="DDICondition Screen";
    }

}
