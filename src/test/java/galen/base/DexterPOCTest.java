package galen.base;

import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterNavigations;
import galen.helpers.tenant.dexter.DexterUser;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
        VERSIONHISTORY.add(" ; ; ; ");

        report = new GalenReport(getDriver(), reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "POC";
    }

    @Test
    public void DexterPOCTest_Test() throws IOException, InterruptedException {
        commonPageFeatures = new CommonPageFeatures(driver);
        bh = new BasicHelpers(driver);
        DexterPageObj pageObj = new DexterPageObj(driver);
        DexterUser user = new DexterUser();

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterNavigations(driver).partialNavigationIA(user, pageObj.antifungal, report);
    }
}
