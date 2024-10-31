package galen.pages.tenant.petros.InitialAssessment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OAuthModal extends OAuth {

    public By title = By.xpath("//h1[@class='primary']");
    public static By subTitle = By.xpath("//h1[@class='secondary']");
    public static By btnContinueAsGuest =  By.xpath("//button[@testid='profile-sign-in']");
    public By  oauthModal = By.className("summary-auth-modal");

    public OAuthModal(WebDriver driver) {
        super(driver);
        titleText="DON'T LOSE YOUR PROGRESS";
        reportText="OAuth page";
    }

    public WebElement getModal() {
        return basicHelpers.getWebElement(oauthModal);
    }

    public WebElement getContinueAsGuestButton() {
        return basicHelpers.getWebElement(btnContinueAsGuest);
    }

}
