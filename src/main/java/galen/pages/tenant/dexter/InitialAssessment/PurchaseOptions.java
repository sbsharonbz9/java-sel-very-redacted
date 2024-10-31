package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PurchaseOptions extends BasePage {
    public By title = By.className("outcome");
    public By assessmentNumber = By.className("assessment-base__assessment-number");

    public PurchaseOptions(WebDriver driver) {
        super(driver);
        headingTitle=title;
        reportText = "Purchase Options Screen";
        assessmentID=assessmentNumber;
    }

  }
