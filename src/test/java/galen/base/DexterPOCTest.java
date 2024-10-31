package galen.base;

import galen.driver.DriverManager;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterNavigations;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.petros.PetrosNavigations;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class DexterPOCTest extends BaseTest {

    static String OBJECTIVE = "test";
    static String NOTES = "test";
    static String REQUIREMENTS = "POC";
    static String REFERENCES = "POC";

    String reportName = "test";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    CommonPageFeatures commonPageFeatures;
    BasicHelpers bh;

    DexterPOCTest() throws IOException {
        VERSIONHISTORY.add("1.0 ;29SEP2023 ;Initial Test Script ;Name Redacted");

        report = new GalenReport(getDriver(), reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "POC";
    }

    @Test
    public void DexterPOCTest_Test() throws IOException, InterruptedException {
        WebDriver driver = DriverManager.getDriver();
        this.commonPageFeatures = new CommonPageFeatures(driver);
        this.bh = new BasicHelpers(driver);
        DexterPageObj pageObj = new DexterPageObj(driver);
        DexterUser user = new DexterUser();
        //driver.get(ConfigLoader.getInstance().getBaseUrl());
        sleep(1000);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        sleep(1000);
        new DexterNavigations(driver).partialNavigationIA(user, pageObj.antifungal, report);
    }
}
