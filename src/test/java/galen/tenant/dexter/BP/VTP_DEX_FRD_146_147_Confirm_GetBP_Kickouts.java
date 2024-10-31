package galen.tenant.dexter.BP;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.BloodPressureType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import galen.pages.tenant.dexter.InitialAssessment.EnterBPEnd;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_146_147_Confirm_GetBP_Kickouts extends BaseTest {

    static String OBJECTIVE = "DEX_FRD_146: To verify on the Final Enter BP Numbers screen, if the user enters BP > 120/80, the application shall end the health survey and display a BP too high DNU message. \n" +
            "DEX_FRD_147: To verify on the Final Enter BP Numbers screen, if the user enters BP ≥ 180/120, the application shall end the health survey and display a BP dangerously high DNU message. ";
    static String NOTES = "This protocol contains the following verification scenarios: \n" +
            "Inputting 121 for Systolic (DNU) and 75 for Diastolic results in BP DNU Screen \n" +
            "Inputting 115 for Systolic and 81 for Diastolic (DNU) results in BP DNU Screen \n" +
            "Inputting 121 for Systolic (DNU) and 81 for Diastolic (DNU) results in BP DNU Screen \n" +
            "Inputting 120 for Systolic and 80 for Diastolic results in Safe To Use Screen \n" +
            "Inputting 180 for Systolic (Immediate - DNU) and 75 for Diastolic results in BP Immediate DNU Screen \n" +
            "Inputting 120 for Systolic and 120 for Diastolic (Immediate - DNU) results in BP Immediate DNU Screen \n" +
            "Inputting 180 for Systolic (Immediate - DNU) and 120 for Diastolic (Immediate - DNU) results in BP Immediate DNU Screen \n" +
            "Inputting 179 for Systolic (DNU) and 119 for Diastolic (DNU) results in BP DNU Screen ";
    static String REQUIREMENTS = "DEX_FRD_146_147";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_146_147_Confirm_GetBP_Kickouts";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_146_147_Confirm_GetBP_Kickouts() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script;James Reale");
        VERSIONHISTORY.add("2.0;17MAY2023;Update Test Steps for correct language ;James Reale");
        VERSIONHISTORY.add("3.0;18JUN2024;Per CADENCE-508: Updated Test Steps based on ADBU flows \n" +
                "Per CADENCE-567: Remove N/A from Expected Results column when using HappyFlow \n" +
                "Per CADENCE-569: Update Test Steps to remove any reference to OAuth, Returning User, or Checkout ;" +
                "Gulzira Nurseilova");
        VERSIONHISTORY.add("4.0;25OCT2024;Per CADENCE-616: Update Test Steps to click the Next button to trigger 3 Month" +
                " Modal and update Diastolic DNU Scenario to use value less than Systolic based on rules;James Reale");
    }

    @Test
    public void VTP_DEX_FRD_146_147_Confirm_GetBP_Kickouts_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_146_147_Confirm_GetBP_Kickouts";
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        EnterBPEnd enterBPEnd = pageObj.enterBPEnd;
        
        new PritUnlPage(driver).authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "121";
        user.diastolic = "75";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step7_BP_DNU");

        new DexterHFWrappers(driver).runDexterHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "115";
        user.diastolic = "81";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step14_BP_DNU");

        new DexterHFWrappers(driver).runDexterHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "121";
        user.diastolic = "81";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step21_BP_DNU");

        new DexterHFWrappers(driver).runDexterHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "120";
        user.diastolic = "80";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.purchaseOptions,report);
        report.addScreenshotStep("Step28_Purchase_Option");

        new DexterHFWrappers(driver).runDexterHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "180";
        user.diastolic = "75";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step35_BP_DNU");

        new DexterHFWrappers(driver).runDexterHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "121";
        user.diastolic = "120";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step42_BP_DNU");

        new DexterHFWrappers(driver).runDexterHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "180";
        user.diastolic = "120";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step49_BP_DNU");

        new DexterHFWrappers(driver).runDexterHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "179";
        user.diastolic = "119";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step56_BP_DNU");
    }
}

