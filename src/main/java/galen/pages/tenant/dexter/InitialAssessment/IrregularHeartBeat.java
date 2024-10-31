package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IrregularHeartBeat extends BasePage {

    public By title = By.className("HeartValveProblemsScreen");

    public IrregularHeartBeat(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Do you currently have irregular heartbeat or heart valve problems?";
        reportText = "Irregular Heartbeat or Heart Valve Problems Screen";
    }

}
