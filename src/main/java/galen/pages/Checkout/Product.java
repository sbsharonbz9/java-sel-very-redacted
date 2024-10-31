package galen.pages.Checkout;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class Product extends BasePage {
    public By title = By.xpath("//h1[@class='secondary'][contains(text(),'Select Product')]");
    public static By rd2Month = By.xpath("//input[@id='2month']");
    

    public Product(WebDriver driver) {
        super(driver);
        reportText="Select Product";
        headingTitle=title;
    }

    public WebElement getRD2Month() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(rd2Month));
    }
}
