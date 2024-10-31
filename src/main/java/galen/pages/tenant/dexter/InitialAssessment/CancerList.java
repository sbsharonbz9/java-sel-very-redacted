package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class CancerList extends CheckboxPage {
    public  By title = By.className("Cancer2Screen");
    public ArrayList<String> cancerOptions = new ArrayList<>(Arrays.asList("Breast cancer","Endometrial (uterine) " +
                    "cancer", "Melanoma", "Ovarian cancer", "Lung/bronchial adenocarcinoma","Meningioma","Liver cancer",
            "Other cancer"));



    public CancerList(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Have you ever had any of these cancers?";
        options=cancerOptions;
        reportText="CancerList Screen";
    }

}
