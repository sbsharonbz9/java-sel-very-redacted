package galen.pages.Checkout;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class PurchasePage extends BasePage {

    By headingTitle = By.xpath("//h1");
    By buyOnlineButton = By.className("outcome-button");
    By buyStoreButton = By.xpath("//button[@class='button button-primary']");

    public PurchasePage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyReviewPage() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(headingTitle,
                "AVANAFIL OTC is right for you!"));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getBuyOnlineButton().isDisplayed(), "Purchase Options Screen", report);
    }

    public WebElement getBuyOnlineButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(buyOnlineButton));
    }

    public WebElement getBuyStoreButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(buyStoreButton));
    }
}
