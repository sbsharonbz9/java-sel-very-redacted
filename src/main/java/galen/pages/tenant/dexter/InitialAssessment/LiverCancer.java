package galen.pages.tenant.dexter.InitialAssessment;

import galen.enums.tenant.dexter.LiverCancerType;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class LiverCancer extends CheckboxPage {
    public By title = By.className("LiverDiseaseScreen");
    public ArrayList<String> cancerOptions = new ArrayList<>(Arrays.asList(LiverCancerType.LIVER_DISEASE.label,
            LiverCancerType.LIVER_CANCER.label, LiverCancerType.HEPATITIS_C.label, LiverCancerType.NONE_OF_THESE.label));

    public LiverCancer(WebDriver driver) {
        super(driver);
        headingTitle=title;
        reportText="Liver Disease or Liver Cancer Screen";
        titleText="Do you currently have?";
        options=cancerOptions;
    }

}
