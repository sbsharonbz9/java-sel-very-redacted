package galen.tenant.dexter.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_108_Depression_No_Navigation extends BaseTest {
    static String OBJECTIVE = "To verify on the Depression screen, if the user answers no indicating that they do not " +
            "currently or have not previously had depression, the application shall navigate to BP Numbers screen.";
    static String NOTES = "This protocol contains the following verification scenario(s): \n" +
            "Submitting No to Depression while initially performing health survey navigates to the Know BP Screen";
    static String REQUIREMENTS = "DEX_FRD_108";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_108_Depression_No_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_108_Depression_No_Navigation() {
        VERSIONHISTORY.add("1.0;11NOV2022;Initial Test Script;Suresh Sunderraj");
        VERSIONHISTORY.add("2.0;20JUN2024;Per CADENCE-567: Remove N/A from Expected Results column when using HappyFlow;" +
                "James Reale");
    }

    @Test
    public void VTP_DEX_FRD_108_Depression_No_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_108_Depression_No_Navigation";

        DexterUser user = new DexterUser();
        DexterPageObj pageObj = new DexterPageObj(driver);

        new PritUnlPage(driver).authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.depression, report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("No", pageObj.knowBPNumber, report);
        report.addScreenshotStep("Step2_No_BP_Screen");
    }
}
