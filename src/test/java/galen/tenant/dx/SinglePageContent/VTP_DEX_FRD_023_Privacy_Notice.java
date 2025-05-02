package galen.tenant.dx.SinglePageContent;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxNavigations;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
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
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_023_Privacy_Notice_Test() throws Exception {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_023 - Privacy_Notice";
        BasicHelpers bh = new BasicHelpers(driver);
        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxNavigations(driver).partialNavigationIA(user, pageObj.privacyPage, report);

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
