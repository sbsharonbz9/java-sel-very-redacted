package galen.pages.tenant.dx.InitialAssessment;

import galen.helpers.common.GalenReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;

public class ADBUBP extends ADBU{

    public By adbubpHeader = By.className("ADBUBPScreen");
    public By assessmentNumber = By.className("assessment-base__assessment-number");
    public By adbubpTitle = By.tagName("h2");
    public By adbubpSubtitle = By.tagName("h3");

    public ADBUBP(WebDriver driver) {
        super(driver);
        headingTitle=adbubpHeader;
        assessmentID=assessmentNumber;
        titleText="You need your recent blood pressure numbers.";
        reportText="Get Blood Pressure Measured Screen";
    }

    public Boolean verifyTitle(String toFind, @Nullable GalenReport report) {
        return basicHelpers.verifyText(adbubpTitle, "ADBU BP Screen Title", toFind,report);
    }

    public Boolean verifySubtitle(String toFind, @Nullable GalenReport report) {
        return basicHelpers.verifyText(adbubpSubtitle, "ADBU BP Screen Subtitle", toFind,report);
    }
}