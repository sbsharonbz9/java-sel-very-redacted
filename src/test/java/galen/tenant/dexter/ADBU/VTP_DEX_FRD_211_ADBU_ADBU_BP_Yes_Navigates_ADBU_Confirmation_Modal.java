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

public class VTP_DEX_FRD_211_ADBU_ADBU_BP_Yes_Navigates_ADBU_Confirmation_Modal extends BaseTest {

    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_211_ADBU_ADBU_BP_Yes_Navigates_ADBU_Confirmation_Modal";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_211_ADBU_ADBU_BP_Yes_Navigates_ADBU_Confirmation_Modal()  {
        VERSIONHISTORY.add("1.0;30JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_211_ADBU_ADBU_BP_Yes_Navigates_ADBU_Confirmation_Modal_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_211_ADBU_ADBU_BP_Yes_Navigates_ADBU_Confirmation_Modal";
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_noBP();
        DexterPageObj pageObj = new DexterPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFADBUNoBP(user, pageObj.adbubpScreen, report);

        pageObj.adbubpScreen.clickYesNoToOpenModal("Yes", report);
        report.addScreenshotStep("Step2_Approval_Modal");

        new DexterHFWrappers(driver).runDexterHFADBUNoBP(user, pageObj.knowBPNumber, report);
        pageObj.knowBPNumber.clickYesAndAddressModalToPage(pageObj.enterBP, "Yes", report);

        user.systolic = "120";
        user.diastolic = "80";
        pageObj.enterBP.enterBPAndProgress(user, pageObj.review,report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbu,report);
        pageObj.adbu.clickYesNoToOpenModal("Yes", report);
        report.addScreenshotStep("Step10_Approval_Modal");
    }


}
