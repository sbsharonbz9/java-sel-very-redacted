package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import galen.pages.common.BasePage;

import javax.annotation.Nullable;

public class PurchaseSelf extends BasePage {

    public static String selfCheckText="This self-check should only be used by the person who will take Avanafil OTC.";
    public static By question = By.xpath("//p[@class='assessment-paragraph']");
    public By  purchaseSelfHeader = By.className("SelfOrderScreen");

    public PurchaseSelf(WebDriver driver) {
        super(driver);
        titleText="BASIC INFORMATION";
        reportText="Purchase for Self";
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(purchaseSelfHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), reportText, report);
    }
}
