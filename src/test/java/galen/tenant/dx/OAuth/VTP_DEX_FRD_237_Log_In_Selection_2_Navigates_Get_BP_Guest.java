package galen.tenant.dx.OAuth;

import galen.base.BaseTest;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
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
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_Get_BP_Guest_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_237 – Log In Selection 2 Navigates to Get BP (If Triggered) Guest";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnlauthenticateUserIfRequired();
        new DxHFWrappers(driver).runDxHFnoBPNonsmoker(user, pageObj.adbubpNormal, report);
        report.addScreenshotStep("Step2_Get_BP_Screen");
    }
}
