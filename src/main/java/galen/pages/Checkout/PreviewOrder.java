package galen.pages.Checkout;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PreviewOrder extends BasePage {
    public By title = By.xpath("//h1[@class='secondary'][contains(text(),'Preview Order')]");
    public static By btnPurchase = By.xpath("//button[text()='Purchase']");
    

    public PreviewOrder(WebDriver driver) {
        super(driver);
        headingTitle=title;
        reportText="Preview Order";
        titleText="Preview Order";
    }

    public WebElement getBtnPurchase() {
        return basicHelpers.getWebElement(btnPurchase);
    }
}
