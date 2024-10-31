package galen.pages.tenant.dexter.InitialAssessment;

import galen.enums.tenant.dexter.SmokeType;

import galen.helpers.common.GalenReport;
import galen.pages.common.SingleResponseRadioButtonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Smoking extends SingleResponseRadioButtonPage {

    public By title = By.className("SmokingScreen");
    public static By doNotSmokeRdBtn = By.xpath("//input[@id='no']");
    public static By smokeRegularlyRdBtn = By.xpath("//input[@id='regular']");
    public static By occasionallyRdBtn = By.xpath("//input[@id='occasional']");
    public static By rarelyRdBtn = By.xpath("//input[@id='rare']");
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
