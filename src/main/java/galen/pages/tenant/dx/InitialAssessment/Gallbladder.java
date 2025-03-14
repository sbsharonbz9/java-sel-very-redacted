package galen.pages.tenant.dx.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Gallbladder extends BasePage {
    public By title = By.className("GallbladderDiseaseScreen");

    public Gallbladder(WebDriver driver) {
        super(driver);
        headingTitle=title;
        reportText="Gallbladder Screen";
    }

  }
