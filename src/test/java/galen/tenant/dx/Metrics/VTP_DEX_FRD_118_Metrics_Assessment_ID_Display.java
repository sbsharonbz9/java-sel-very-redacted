package galen.tenant.dx.Metrics;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.*;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_FRD_118_Metrics_Assessment_ID_Display extends BaseTest {
    static String OBJECTIVE = "To verify When a new user starts to take an initial health survey, the application shall " +
            "assign an assessment ID to the health survey answers and this ID shall be displayed on the welcome screen" +
            " and on the final notification screens";
    static String NOTES = "This protocol contains the following scenario(s):\n" +
            "-\tAssessment ID is displayed on the Welcome Screen\n" +
            "-\tAssessment ID is displayed on Safe To Use Screen\n" +
            "-\tAssessment ID is displayed on ADBU Screen\n" +
            "-\tAssessment ID is displayed on ADBU/BP Screen\n" +
            "-\tAssessment ID is displayed on Confirm Customer DNU Screen\n" +
            "-\tAssessment ID is displayed on Menstruation DNU Screen\n" +
            "-\tAssessment ID is displayed on Prevent Pregnancy DNU Screen\n" +
            "-\tAssessment ID is displayed on Smoker Over 35 DNU Screen\n" +
            "-\tAssessment ID is displayed on Cancers DNU Screen\n" +
            "-\tAssessment ID is displayed on BP Meds DNU Screen\n" +
            "-\tAssessment ID is displayed on Heart Conditions DNU Screen\n" +
            "-\tAssessment ID is displayed on Blood Clots DNU Screen\n" +
            "-\tAssessment ID is displayed on Irregular Heartbeat DNU Screen\n" +
            "-\tAssessment ID is displayed on Liver Disease or Liver Cancer DNU Screen\n" +
            "-\tAssessment ID is displayed on Vaginal Bleeding DNU Screen\n" +
            "-\tAssessment ID is displayed on Diabetes DNU Screen\n" +
            "-\tAssessment ID is displayed on Pregnant DNU Screen\n" +
            "-\tAssessment ID is displayed on Breastfeeding DNU Screen\n" +
            "-\tAssessment ID is displayed on Pregnancy Loss DNU Screen\n" +
            "-\tAssessment ID is displayed on Migraines with Aura DNU Screen\n" +
            "-\tAssessment ID is displayed on BP DNU Screen\n" +
            "-\tAssessment ID is displayed on BP Immediate DNU Screen";
    static String REQUIREMENTS = "FRD_118";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx \n" +
            "HappyFlow_IA_Initial_Assessment_ADBU_wBP.docx\n" +
            "HappyFlow_IA_Initial_Assessment_ADBU_NoBP.docx";
    DxPageObj pageObj;
    DxUser user;
    String reportName = "VTP_DEX_FRD_118_Metrics_Assessment_ID_Display";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_FRD_118_Metrics_Assessment_ID_Display()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_118_Metrics_Assessment_ID_Display_Test() throws IOException, InterruptedException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_118 – Metrics Assessment ID Display";
        bh = new BasicHelpers(driver);
        pageObj = new DxPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(URLType.DX);
        commonPageFeatures = new CommonPageFeatures(driver);
        user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();

        pageObj.pritUnl.load(URLType.DX);
        pageObj.welcomePage.verifyAtPage(report);
        commonPageFeatures.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step1");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.purchaseOptions, report);
        pageObj.purchaseOptions.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step3");

        new DxHFWrappers(driver).runDxHFADBUwBP(user, pageObj.adbu, report);
        pageObj.adbu.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step5");

        new DxHFWrappers(driver).runDxHFADBUNoBP(user, pageObj.adbubpScreen, report);
        pageObj.adbubpScreen.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step7");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.orderForSelf, report);
        pageObj.usedProduct.clickCloseButton(report);
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step10");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.menstrual, report);
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.birthControlB, report);
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step13");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.pregnancy, report);
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step15");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.smoking, report);
        pageObj.smoking.selectRadioResponseAndProgress(SmokeType.SMOKE_REGULARLY.label, pageObj.birthYear, report);
        user.dobYear="1984";
        pageObj.birthYear.fillOutBirthdayAndProgress(user,pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step18");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.everHadCancer, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.cancerList, report);
        pageObj.cancerList.selectCheckboxAndProgress(CancerType.Breast_Cancer.label, pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step20");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.bloodPressureMeds, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step22");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.cardiacRisk, report);
        pageObj.cardiacRisk.selectCheckboxAndProgress(HeartConditionType.HEART_ATTACK.label, pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step24");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.bloodClot, report);
        pageObj.bloodClot.selectCheckboxAndProgress("Blood clot", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step26");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.irregularHeartBeat, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step28");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.liverCancer, report);
        pageObj.liverCancer.selectCheckboxAndProgress(LiverCancerType.LIVER_CANCER.label, pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step30");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.vaginalBleeding, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step32");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.diabetes, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step34");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.pregnant, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step36");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.breastFeeding, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step38");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.pregnancyLoss, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step40");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.migraines, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step42");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        user.systolic="121";
        user.diastolic="81";
        pageObj.enterBP.enterBP(user, report);
        commonPageFeatures.clickNextToPage(pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step44");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        user.systolic="190";
        user.diastolic="130";
        pageObj.enterBP.enterBP(user, report);
        commonPageFeatures.clickNextToPage(pageObj.kickoutPage, report);
        pageObj.kickoutPage.verifyAssessmentIDDisplayed(report);
        report.addScreenshotStep("Step46");


    }

}
