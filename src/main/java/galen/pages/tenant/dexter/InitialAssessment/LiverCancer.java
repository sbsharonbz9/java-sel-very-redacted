package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class LiverCancer extends CheckboxPage {
    public By title = By.className("LiverDiseaseScreen");
    public ArrayList<String> cancerOptions = new ArrayList<>(Arrays.asList("Liver disease", "Liver cancer", "Hepatitis C",
             "None of these"));

    public LiverCancer(WebDriver driver) {
        super(driver);
        headingTitle=title;
        reportText="Liver Disease/Liver Cancer Screen";
        titleText="Do you currently have?";
        options=cancerOptions;
    }

}
