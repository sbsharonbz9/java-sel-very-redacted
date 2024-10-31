package galen.tenant.dexter.OAuth;

import galen.base.BaseTest;
import galen.enums.tenant.dexter.BloodPressureType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class VTP_DEX_FRD_235_Log_In_Selection_2_Navigation_Safe_To_Use_Guest extends BaseTest {
    static String OBJECTIVE = "TTo verify on the Log In Selection screen if the user selects to continue as guest they "+
            "will be navigated to the “Safe To Use” screen if they have no ADBUs and did previously enter BP.";

    static String NOTES = "This protocol contains the following verification scenario(s):\n"+
            "-\t	Guest User with OK outcome is navigated to the Safe To Use Screen from Log In Selection";

    static String REQUIREMENTS = "DEX_FRD_235";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx;";
    String reportName = "VTP_DEX_FRD_235_Log_In_Selection_2_Navigation_Safe_To_Use_Guest";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_235_Log_In_Selection_2_Navigation_Safe_To_Use_Guest() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Gulzira Nurseilova");
    }

    @Test
    public void VTP_DEX_FRD_235_Log_In_Selection_2_Navigation_Safe_To_Use_Guest_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_235 – Log In Selection 2 Navigation Safe to Use Guest";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.review, report);
        pageObj.review.verifyAtPage(report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.purchase.verifyAtPage(report);
        report.addScreenshotStep("Step2_Safe_To_Use_Screen");
    }
}