package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

public class NotPurchasingSelfScreen extends BasePage {
    public By noPurchasingSelfScreen = By.className("assessment-screen__title");

    public NotPurchasingSelfScreen(WebDriver driver) {super(driver);titleText = "Results";
    }

    public WebElement getHeader() {
        return basicHelpers.getWebElement(noPurchasingSelfScreen);
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader() != null, "Not Purchasing  for Self Screen", report);
    }

}

