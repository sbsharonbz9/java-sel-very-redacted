package galen.tenant.dexter.ADBU;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_231_ADBU_Confirmation_Modal_Navigates_Ready_To_Purchase extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_231_ADBU_Confirmation_Modal_Navigates_Ready_To_Purchase";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_231_ADBU_Confirmation_Modal_Navigates_Ready_To_Purchase()  {
        VERSIONHISTORY.add("1.0;24JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_231_ADBU_Confirmation_Modal_Navigates_Ready_To_Purchase_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_231_ADBU_Confirmation_Modal_Navigates_Ready_To_Purchase";
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_noBP();
        DexterPageObj pageObj = new DexterPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFADBUNoBP(user, pageObj.knowBPNumber, report);

        pageObj.knowBPNumber.clickYesAndAddressModalToPage(pageObj.enterBP, "Yes",report);
      
        user.systolic = "115";
        user.diastolic = "75";
        pageObj.enterBP.enterBPAndProgress(user, pageObj.review,report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbu,report);
        pageObj.adbu.addressConfirmationsAndProgress("Yes", pageObj.purchaseOptions,report);
        report.addScreenshotStep("Step9_Safe_To_Use");
    }
}
