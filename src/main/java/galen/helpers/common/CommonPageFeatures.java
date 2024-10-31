package galen.helpers.common;

import org.openqa.selenium.*;
import galen.pages.common.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonPageFeatures extends BasePage {

    public CommonPageFeatures(WebDriver driver) {
        super(driver);
    }

    public WebElement getBackButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(btnBack));
    }

    public WebElement getMainError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mainError));
    }

    public WebElement getError() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".main-form-error > span")));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void hide(GalenReport report) {
        basicHelpers.clickFlex(getHideIcon(), "'Hide' icon", report);
    }

    public void show(GalenReport report) {
        basicHelpers.clickFlex(getShowIcon(), "'Show' icon", report);
    }

    public WebElement getShowIcon() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(showIcon));
    }

    public WebElement getHideIcon() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(hideIcon));
    }

    public WebElement getConfirmModalTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmModalTitle));
    }

}

