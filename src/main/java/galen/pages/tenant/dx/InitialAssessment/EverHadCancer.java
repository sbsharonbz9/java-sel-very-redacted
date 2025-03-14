package galen.pages.tenant.dx.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EverHadCancer extends BasePage {

    public By title = By.className("Cancer1Screen");
    
    public EverHadCancer(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Have you ever had cancer?";
        reportText="Ever Had Cancer Screen";
    }

}
