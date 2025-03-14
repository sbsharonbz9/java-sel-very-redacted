package galen.pages.tenant.dx.InitialAssessment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ADBUBC extends ADBU {

    public By adbuddiHeader = By.className("ADBUDDIScreen");

    public ADBUBC(WebDriver driver) {
        super(driver);
        headingTitle=adbuddiHeader;
        titleText="Use back up birth control while using: fosamprenavir, darunavir, efavirenz, etravirine, nelfinavir, " +
                "nevirapine and ritonavir";
        reportText="Get ADBU BC Screen";
    }

}
