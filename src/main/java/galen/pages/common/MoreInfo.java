package galen.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MoreInfo extends BasePage {
    public By moreInfoHeader = By.className("kickout");

    public MoreInfo(WebDriver driver) {
        super(driver);
        titleText="More Info";
        headingTitle=moreInfoHeader;
        reportText="More Info";
    }
}
