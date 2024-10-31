package galen.tenant.dexter.OAuth;

import galen.base.BaseTest;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_Get_BP_Guest  extends BaseTest {
    static String OBJECTIVE = "To verify on the Login Selection screen, if the user selects to continue as guest the application shall navigate to:\n" +
            "-\tADBU Screen if they triggered an ADBU and entered BP\n" +
            "-\tADBU/BP screen if they triggered and ADBU and did not enter BP\n" +
            "-\tBP Screen if they have no ADBU and did not enter BP\n";

    static String NOTES="This protocol verifies the following scenario(s):\n" +
            "-\tGuest user did not enter BP with no ADBU navigated from Log In Selection (Post Assessment) Screen to Get BP Screen\n";
    static String REQUIREMENTS = "DEX_FRD_237";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_noBP_NonSmoker.docx;";
    String reportName = "VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_Get_BP_Guest";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_Get_BP_Guest()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Name Redacted");
    }

    @Test
    public void VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_Get_BP_Guest_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_237 – Log In Selection 2 Navigates to Get BP (If Triggered) Guest";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.clickConfirm(report);
        pageObj.review.verifyConfirmModalOpen(report);
        pageObj.review.clickFinishToOauth(report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.adbubpNormal.verifyAtPage(report);
        report.addScreenshotStep("Step2_Get_BP_Screen");
    }
}
