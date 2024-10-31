package galen.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DocAttestationNo extends BasePage {
    public By docHeader = By.xpath("//*[contains(@class, 'AttestationNo')]");

    public DocAttestationNo(WebDriver driver) {
        super(driver);
        headingTitle=docHeader;
        reportText="ADBU – End Screen";
    }
}
