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

public class VTP_DEX_FRD_235_Log_In_Selection_2_Navigation_Safe_To_Use_Guest extends BaseTest {
    static String OBJECTIVE = "To verify on the Log In Selection screen if the user selects to continue as guest they "+
            "will be navigated to the “Safe To Use” screen if they have no ADBUs and did previously enter BP.";

    static String NOTES = "This protocol contains the following verification scenario(s):\n"+
            "-\t	Guest User with OK outcome is navigated to the Safe To Use Screen from Log In Selection";

    static String REQUIREMENTS = "DEX_FRD_235";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx;";
    String reportName = "VTP_DEX_FRD_235_Log_In_Selection_2_Navigation_Safe_To_Use_Guest";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_235_Log_In_Selection_2_Navigation_Safe_To_Use_Guest() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_235_Log_In_Selection_2_Navigation_Safe_To_Use_Guest_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_235 – Log In Selection 2 Navigation Safe to Use Guest";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.review, report);
        pageObj.review.verifyAtPage(report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.verifyAtPage(report);
        pageObj.oAuthPostReview.chooseAccountType(user, report);
        report.addScreenshotStep("Step2_Safe_To_Use_Screen");
    }
}