package galen.tenant.dexter.Misc;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterNavigations;
import galen.helpers.tenant.dexter.DexterUser;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_250_Connectivity_Loss extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_250_Connectivity_Loss";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_250_Connectivity_Loss() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_250_Connectivity_Loss_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_250 – Connectivity Loss";

        DexterUser user = new DexterUser();
        BasicHelpers bh = new BasicHelpers(driver);
        DexterPageObj pageObj = new DexterPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterNavigations(driver).partialNavigationIA(user, pageObj.privacyPage, report);
        bh.setOfflineMode(true, report);
        new CommonPageFeatures(driver).verifyModalDisplayed(report);
        report.addScreenshotStep("Step4_Offline");

        bh.setOfflineMode(false, report);
        new CommonPageFeatures(driver).verifyModalDismissed(report);
        report.addScreenshotStep("Step5_Not Offline");

        pageObj.privacyPage.clickIAcceptBtnToPage(pageObj.numbers, report);
        report.addScreenshotStep("Step6_Numbers Page");
    }
}