package galen.pages.Checkout;

import galen.constants.FrameworkConstants;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShippingMethod extends BasePage {
    public By title = By.xpath("//h1[@class='secondary'][contains(text(),'Shipping Method')]");
    public static By rd3to5ShippingMethod = By.xpath("//input[@id='3to5']");
    

    public ShippingMethod(WebDriver driver) {
  super(driver);    }


    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title));
    }

    public WebElement get3To5ShippingMethod() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(rd3to5ShippingMethod));
    }
}
