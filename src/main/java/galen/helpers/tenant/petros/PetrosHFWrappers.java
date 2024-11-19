package galen.helpers.tenant.petros;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;

public class PetrosHFWrappers extends PetrosNavigations {

    public PetrosHFWrappers(WebDriver driver) {
        super(driver);
    }

    public void executeHappyFlow(PetrosUser user, BasePage endPage, String HFType, @Nullable GalenReport report) {
        boolean result;
        try {
            result = endPage.getClass() == new PetrosNavigations(driver).partialNavigationIA(user, endPage,
                    null).getClass();
        } catch (Exception e ) {
            result=false;
        }
        basicHelpers.reportHappyFlow(HFType, endPage.reportText, result, report);
    }
}
