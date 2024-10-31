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

public class PaymentInfo extends BasePage {
    public By title = By.xpath("//h1[@class='secondary'][contains(text(),'Payment Info')]");
    public static By cardNumber = By.xpath("//input[@name='cardnumber']");
    public static By expiryDate = By.xpath("//input[@name='exp-date']");
    public static By cvc = By.xpath("//input[@name='cvc']");
    public static By btnPreviewOrder = By.xpath("//button[text()='Preview Order']");
    
    BasicHelpers bh;

    public PaymentInfo(WebDriver driver) {
  super(driver);        this.bh = new BasicHelpers(driver);
    }

    public boolean verifyPaymentInfoPage() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(title,
                "Payment Info"));
    }

    public WebElement getCardNumber() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumber));
    }

    public WebElement getExpiryDate() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(expiryDate));
    }

    public WebElement getCVC() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cvc));
    }

    public WebElement getBtnPreviewOrder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(btnPreviewOrder));
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title));
    }

    public void enterPaymentDetail(String cardnumber, String expirydate, String cvc, DexterReport report) {
        WebElement cardNumberFrame = driver.findElement(By.xpath("//iframe[contains(@name,'__privateStripeFrame')][@title='Secure card number input frame']"));
        driver.switchTo().frame(cardNumberFrame);
        bh.sendTextFlex(getCardNumber(), cardnumber, "card number", report);
        driver.switchTo().defaultContent();

        WebElement expiryFrame = driver.findElement(By.xpath("//iframe[contains(@name,'__privateStripeFrame')][@title='Secure expiration date input frame']"));
        driver.switchTo().frame(expiryFrame);
        bh.sendTextFlex(getExpiryDate(), expirydate, "expiry date", report);
        driver.switchTo().defaultContent();

        WebElement cvcFrame = driver.findElement(By.xpath("//iframe[contains(@name,'__privateStripeFrame')][@title='Secure CVC input frame']"));
        driver.switchTo().frame(cvcFrame);
        bh.sendTextFlex(getCVC(), cvc, "cvc", report);
        driver.switchTo().defaultContent();
    }
}
