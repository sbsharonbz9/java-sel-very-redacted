package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class UsedProduct extends BasePage {

    public By title = By.className("PriorUseScreen");
    public By moreInfoModalPopup = By.className("more-info-tooltip-overlay");
    public static By btnClose = By.xpath("//button[text()='Close']");

    public UsedProduct(WebDriver driver) {
        super(driver);
        reportText="Prior Use Screen";
        titleText="Have you used this product before?";
        headingTitle=title;
    }

}
