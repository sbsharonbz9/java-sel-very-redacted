package galen.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Kickout extends BasePage {
    public By dnuHeader = By.className("kickout");
    public By notRight = By.className("highlight");
    public String notRightText="Avanafil OTC is not right for you.";
    public By assessmentNumber = By.className("assessment-base__assessment-number");

    public Kickout(WebDriver driver) {
        super(driver);
        titleText="Kickout";
        headingTitle=dnuHeader;
        reportText="DNU Screen";
        assessmentID=assessmentNumber;
    }

}
