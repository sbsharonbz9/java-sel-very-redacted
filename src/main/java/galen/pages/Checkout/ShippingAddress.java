package galen.pages.Checkout;

import galen.constants.FrameworkConstants;
import galen.helpers.common.BasicHelpers;
import galen.helpers.tenant.dexter.DexterReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShippingAddress extends BasePage {
    public By title = By.xpath("//h1[@class='secondary'][contains(text(),'Shipping Address')]");
    public static By firstName = By.xpath("//input[@id='firstname']");
    public static By lastName = By.xpath("//input[@id='lastname']");
    public static By address1 = By.xpath("//input[@id='line1']");
    public static By address2 = By.xpath("//input[@id='line2']");
    public static By city = By.xpath("//input[@id='city']");
    public static By state = By.xpath("//select[@name='state']");
    public static By zip = By.xpath("//input[@id='zip']");
    
    BasicHelpers bh;

    public ShippingAddress(WebDriver driver) {
  super(driver);        this.bh = new BasicHelpers(driver);
    }

    public boolean verifyShippingAddressPage() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(title,
                "Shipping Address"));
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title));
    }

    public WebElement getFirstName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
    }

    public WebElement getLastName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
    }

    public WebElement getAddress1() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(address1));
    }

    public WebElement getAddress2() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(address2));
    }

    public WebElement getCity() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(city));
    }

    public WebElement getState() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(state));
    }

    public WebElement getZip() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(zip));
    }

    public void enterShippingDetail(String firstname, String lastname, String address1, String address2, String city, String state, String zip,
                                    DexterReport report) {
        bh.sendTextFlex(getFirstName(), firstname, "firstname", report);
        bh.sendTextFlex(getLastName(), lastname, "lastname", report);
        bh.sendTextFlex(getAddress1(), address1, "address1", report);
        bh.sendTextFlex(getAddress2(), address2, "address2", report);
        bh.sendTextFlex(getCity(), city, "city", report);
        bh.sendTextFlex(getZip(), zip, "zip", report);
        //bh.selectDropDownByValue(getState(), state, "state", report);
    }
}
