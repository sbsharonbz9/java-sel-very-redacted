package galen.pages.tenant.petros.InitialAssessment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OAuth extends galen.pages.common.OAuth {

    public By title = By.xpath("//h1[@class='primary']");

    public OAuth(WebDriver driver) {
        super(driver);
        titleText = "DON'T LOSE YOUR PROGRESS";
    }
}
