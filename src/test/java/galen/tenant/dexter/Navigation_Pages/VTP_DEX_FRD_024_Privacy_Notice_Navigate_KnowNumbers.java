package galen.tenant.dexter.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_024_Privacy_Notice_Navigate_KnowNumbers extends BaseTest {
    static String OBJECTIVE = "To verify on the Privacy Screen, if the user selects the “I Accept” button, the " +
            "application shall navigate to the Know Numbers screen.";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_024";
    static String REFERENCES = "N/A";
    String reportName = "VTP_DEX_FRD_024_Privacy_Notice_Navigate_KnowNumbers";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_024_Privacy_Notice_Navigate_KnowNumbers()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_024_Privacy_Notice_Navigate_KnowNumbers_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_024 - Privacy Notice Next Navigates to Know Numbers screen";

        DexterPageObj pageObj = new DexterPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        pageObj.welcomePage.clickBegin(report);
        pageObj.privacyPage.clickIAcceptBtnToPage(pageObj.numbers, report);
        report.addScreenshotStep("Step3_Know Numbers Page");
    }

}
