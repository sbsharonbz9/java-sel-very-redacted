package galen.tenant.dexter.Metrics;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.CancerType;
import galen.enums.tenant.dexter.HeartConditionType;
import galen.enums.tenant.dexter.LiverCancerType;
import galen.enums.tenant.dexter.SmokeType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_118_Metrics_Assessment_ID_Display extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    DexterPageObj pageObj;
    DexterUser user;
    String reportName = "VTP_DEX_FRD_118_Metrics_Assessment_ID_Display";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_FRD_118_Metrics_Assessment_ID_Display()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_118_Metrics_Assessment_ID_Display_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_118 – Metrics Assessment ID Display";
        bh = new BasicHelpers(driver);
        pageObj = new DexterPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        commonPageFeatures = new CommonPageFeatures(driver);
        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();

        pageObj.pritUnl.load(UrlType.DEXTER);
        pageObj.welcomePage.verifyAtPage(report);
        commonPageFeatures.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step1");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.purchaseOptions, report);
        pageObj.purchaseOptions.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step3");

        new DexterHFWrappers(driver).runDexterHFADBUwBP(user, pageObj.adbu, report);
        pageObj.adbu.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step5");

        new DexterHFWrappers(driver).runDexterHFADBUNoBP(user, pageObj.adbubpScreen, report);
        pageObj.adbubpScreen.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step7");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.orderForSelf, report);
        pageObj.orderForSelf.clickCloseToDismiss(report);
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step10");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.menstrual, report);
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.birthControlB, report);
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step13");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.pregnancy, report);
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step15");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.smoking, report);
        pageObj.smoking.selectRadioResponseAndProgress(SmokeType.SMOKE_REGULARLY.label, pageObj.birthYear, report);
        user.dobYear="1984";
        pageObj.birthYear.fillOutBirthdayAndProgress(user,pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step18");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.everHadCancer, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.cancerList, report);
        pageObj.cancerList.selectCheckboxAndProgress(CancerType.Breast_Cancer.label, pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step20");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.bloodPressureMeds, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step22");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.cardiacRisk, report);
        pageObj.cardiacRisk.selectCheckboxAndProgress(HeartConditionType.HEART_ATTACK.label, pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step24");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.bloodClot, report);
        pageObj.bloodClot.selectCheckboxAndProgress("Blood clot", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step26");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.irregularHeartBeat, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step28");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.liverCancer, report);
        pageObj.liverCancer.selectCheckboxAndProgress(LiverCancerType.LIVER_CANCER.label, pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step30");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.vaginalBleeding, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step32");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.diabetes, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step34");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.pregnant, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step36");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.breastFeeding, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step38");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.pregnancyLoss, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step40");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.migraines, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step42");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.enterBP, report);
        user.systolic="121";
        user.diastolic="81";
        pageObj.enterBP.enterBP(user, report);
        commonPageFeatures.clickNextToPage(pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step44");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.enterBP, report);
        user.systolic="190";
        user.diastolic="130";
        pageObj.enterBP.enterBP(user, report);
        commonPageFeatures.clickNextToPage(pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step46");
    }
}
