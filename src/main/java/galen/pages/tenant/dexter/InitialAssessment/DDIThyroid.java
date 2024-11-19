package galen.pages.tenant.dexter.InitialAssessment;

import galen.enums.tenant.dexter.DDIThyroidType;
import galen.helpers.common.GalenReport;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class DDIThyroid extends CheckboxPage {
    public  By title = By.className("ThyroidTherayScreen");
    public ArrayList<String> thyroidOptions = new ArrayList<>(Arrays.asList(DDIThyroidType.LEVOTHYROXINE.label,
            DDIThyroidType.DIFFERENT_THYROID_MEDS.label,DDIThyroidType.NO_THYROID_MEDS.label));
    public ArrayList<String> allButNoneThyroidADBU= new ArrayList<>(Arrays.asList(DDIThyroidType.LEVOTHYROXINE.adbuText,
            DDIThyroidType.DIFFERENT_THYROID_MEDS.adbuText));

    public DDIThyroid(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Are you currently taking thyroid replacement medication?";
        reportText="Thyroid Screen";
        options=thyroidOptions;
    }

    @Override
    public boolean verifyAllOptionsInADBU(@Nullable GalenReport report) {
        this.options = allButNoneThyroidADBU;
        return super.verifyAllOptionsInADBU(report);
    }

}
