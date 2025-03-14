package galen.pages.tenant.dx.InitialAssessment;

import galen.enums.tenant.dx.HeartConditionType;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class CardiacRisk extends CheckboxPage {

    public By title = By.className("CardiacRiskScreen");
    public ArrayList<String> cardiacOptions = new ArrayList<>(Arrays.asList(HeartConditionType.CHEST_PAIN.label,
            HeartConditionType.HEART_ATTACK.label, HeartConditionType.STROKE.label,
            HeartConditionType.MINI_STROKE.label, HeartConditionType.NONE_OF_THESE.label));

    public CardiacRisk(WebDriver driver) {
        super(driver);
        headingTitle=title;
        options=cardiacOptions;
        titleText= "Do you currently have or have you ever had:";
        reportText="Heart Conditions Screen";
    }

}
