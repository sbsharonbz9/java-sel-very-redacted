package galen.helpers.common;

import galen.helpers.tenant.petros.PetrosReport;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.common.BasePage;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Navigations {

    protected WebDriver driver;
    public CommonPageFeatures commonPageFeatures;
    public BasicHelpers basicHelpers;

    public Navigations(WebDriver driver) {
        this.driver = driver;
        this.commonPageFeatures = new CommonPageFeatures(driver);
        this.basicHelpers = new BasicHelpers(driver);
    }

    public Class<? extends BasePage> partialNavigationCheckout(CheckoutInfo checkoutInfo, Class<? extends BasePage> endPage, PetrosReport report) throws IOException {


        return endPage;
    }
}

