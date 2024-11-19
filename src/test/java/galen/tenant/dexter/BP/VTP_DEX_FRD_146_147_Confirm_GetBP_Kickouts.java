package galen.tenant.dexter.BP;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import galen.pages.tenant.dexter.InitialAssessment.EnterBPEnd;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_146_147_Confirm_GetBP_Kickouts extends BaseTest {

    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_146_147_Confirm_GetBP_Kickouts";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_146_147_Confirm_GetBP_Kickouts() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_146_147_Confirm_GetBP_Kickouts_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_146_147_Confirm_GetBP_Kickouts";
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        EnterBPEnd enterBPEnd = pageObj.enterBPEnd;
        
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
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

