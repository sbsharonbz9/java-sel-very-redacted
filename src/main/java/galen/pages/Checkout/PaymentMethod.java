package galen.pages.Checkout;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentMethod extends BasePage {
    public By title = By.xpath("//h1[@class='secondary'][contains(text(),'Payment Method')]");
    public static By rdCardPaymentMethod = By.xpath("//input[@id='card']");
    

    public PaymentMethod(WebDriver driver) {
        super(driver);
        headingTitle=title;
        reportText="Payment Method";
        titleText="Payment Method";
    }

    public WebElement getCardPaymentMethod() {
        return basicHelpers.getWebElement(rdCardPaymentMethod);
    }
}
