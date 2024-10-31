package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;

public class CardiacRisk extends CheckboxPage {

    public By title = By.className("CardiacRiskScreen");
    public ArrayList<String> cardiacOptions = new ArrayList<>(Arrays.asList("Chest pain", "Heart attack", "Stroke",
            "Mini stroke", "None of these"));

    public CardiacRisk(WebDriver driver) {
        super(driver);
        headingTitle=title;
        options=cardiacOptions;
        titleText= "Do you currently have or have you ever had:";
        reportText="Heart Conditions Screen";
    }

}
