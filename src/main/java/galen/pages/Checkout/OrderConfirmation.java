package galen.pages.Checkout;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmation extends BasePage {
    public By title = By.xpath("//h1[@class='secondary'][contains(text(),'Order Confirmation')]");
    

    public OrderConfirmation(WebDriver driver) {
        super(driver);
        headingTitle=title;
        reportText="Order Confirmation";
        headingTitle=title;
    }

}
