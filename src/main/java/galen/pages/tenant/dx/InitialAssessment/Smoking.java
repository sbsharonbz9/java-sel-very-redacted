package galen.pages.tenant.dx.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.SingleResponseRadioButtonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class Smoking extends SingleResponseRadioButtonPage {

    public By title = By.className("SmokingScreen");
    public ArrayList<String> smokeOptions = new ArrayList<>(Arrays.asList("I don't smoke or Vape",
            "I smoke and/or Vape regularly", "I smoke or Vape occasionally", "I rarely smoke or Vape"));

    public Smoking(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText= "Do you smoke cigarettes?";
        reportText="Smoking Screen";
        options=smokeOptions;
    }

    public boolean verifyNoOptionsSelected(@Nullable GalenReport report) {
        boolean result;
        options = new ArrayList<>(Arrays.asList("no","regular", "occasional", "rare"));
        result=super.verifyNoOptionsSelected(report);
        options=smokeOptions;
        return result;
    }
}
