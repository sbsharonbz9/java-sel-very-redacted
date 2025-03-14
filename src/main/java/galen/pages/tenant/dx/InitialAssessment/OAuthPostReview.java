package galen.pages.tenant.dx.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.helpers.common.GalenUser;
import galen.pages.common.BasePage;
import galen.pages.common.OAuth;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;

public class OAuthPostReview extends OAuth {

    public OAuthPostReview(WebDriver driver) {
        super(driver);
    }

    public void chooseAccountTypeAndProgress(GalenUser user, BasePage page, @Nullable GalenReport report) {
        basicHelpers.verifyClickToPageTransition(page, user.accountType.nav, user.accountType.name(), report);
    }

}
