package galen.tenant.dexter.SinglePageContent;

import galen.base.BaseTest;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterNavigations;
import galen.helpers.tenant.dexter.DexterUser;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_023_Privacy_Notice extends BaseTest {
    static String OBJECTIVE = "On the Privacy screen, the application shall present the privacy notice and a " +
            "\"I Accept\" button.";
    static String NOTES = "This protocol contains the following verification scenarios:\n" +
            "- I Accept button displays on Privacy Notice Screen\n" +
            "- Full Privacy Notice can be expanded\n" +
            "- Full Privacy Notice can be collapsed";
    static String REQUIREMENTS = "DEX_FRD_023";
    static String REFERENCES = "N/A";
    String reportName = "VTP_DEX_FRD_023_Privacy_Notice";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_023_Privacy_Notice()  {
        VERSIONHISTORY.add("1.0;02NOV2022;Initial Test Script;Name Redacted;" +
                "2.0;18JUN2024;Per CADENCE-591: Update Test Steps for modified assessment and navigation;Name Redacted");
    }

    @Test
    public void VTP_DEX_FRD_023_Privacy_Notice_Test() throws Exception {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_023 - Privacy_Notice";
        BasicHelpers bh = new BasicHelpers(driver);
        DexterUser user = new DexterUser();
        DexterPageObj pageObj = new DexterPageObj(driver);
        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterNavigations(driver).partialNavigationIA(user, pageObj.privacyPage, report);

        pageObj.privacyPage.verifyAllPageElementsPresent(report);
        report.addScreenshotStep("Step2_PrivacyPage");

        bh.clickFlex(bh.getWebElement(pageObj.privacyPage.privacyDropdownButton), "Dropdown to Expand Full" +
                " Privacy Notice", report);
        pageObj.privacyPage.isNoticeExpanded(report);
        report.addScreenshotStep("Step3_OpenPrivacyNotice");

        bh.clickFlex(bh.getWebElement(pageObj.privacyPage.privacyDropdownButton), "Dropdown to collapse Full Privacy " +
                        "Notice", report);
        bh.verifyNotDisplayedFlex(pageObj.privacyPage.fullPrivacyNotice, "Full Privacy Notice", report);
        report.addScreenshotStep("Step4_ClosePrivacyNotice");
    }

}
