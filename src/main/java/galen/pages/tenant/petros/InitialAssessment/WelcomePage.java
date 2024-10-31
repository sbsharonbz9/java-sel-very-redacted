package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;

public class WelcomePage extends galen.pages.common.WelcomePage {

    private static final Logger logger = LoggerFactory.getLogger(WelcomePage.class);

    public String secureSelfCheckText = "Everything in this self-check is secure and private. It is just for you.";
    public String welcomeParagraphText = "This self-check must be completed to determine if Avanafil OTC is appropriate" +
            " for you to use. It is only for men 18 years of age and older. If the results of this self-check confirm " +
            "your eligibility, you will be granted access to purchase Avanafil OTC.";
    public String titleText = "Welcome to the Avanafil OTC Self-Check";

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getBeginButton().isDisplayed(), "Welcome Page", report);
    }

    public boolean verifyAtPage() {
        return basicHelpers.verifyAtPage(getBeginButton().isDisplayed(), "Welcome Page", null);
    }

}
