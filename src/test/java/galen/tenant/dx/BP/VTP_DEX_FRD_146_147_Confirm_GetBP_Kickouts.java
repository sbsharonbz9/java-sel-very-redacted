package galen.tenant.dx.BP;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import galen.pages.tenant.dx.InitialAssessment.EnterBPEnd;
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
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_146_147_Confirm_GetBP_Kickouts_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_146_147_Confirm_GetBP_Kickouts";
        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        EnterBPEnd enterBPEnd = pageObj.enterBPEnd;
        
        pageObj.pritUnlauthenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "121";
        user.diastolic = "75";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step7_BP_DNU");

        new DxHFWrappers(driver).runDxHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "115";
        user.diastolic = "81";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step14_BP_DNU");

        new DxHFWrappers(driver).runDxHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "121";
        user.diastolic = "81";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step21_BP_DNU");

        new DxHFWrappers(driver).runDxHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "120";
        user.diastolic = "80";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.purchaseOptions,report);
        report.addScreenshotStep("Step28_Purchase_Option");

        new DxHFWrappers(driver).runDxHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "180";
        user.diastolic = "75";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step35_BP_DNU");

        new DxHFWrappers(driver).runDxHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "121";
        user.diastolic = "120";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step42_BP_DNU");

        new DxHFWrappers(driver).runDxHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "180";
        user.diastolic = "120";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step49_BP_DNU");

        new DxHFWrappers(driver).runDxHFnoBPNonsmoker(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "179";
        user.diastolic = "119";
        enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step56_BP_DNU");
    }
}

