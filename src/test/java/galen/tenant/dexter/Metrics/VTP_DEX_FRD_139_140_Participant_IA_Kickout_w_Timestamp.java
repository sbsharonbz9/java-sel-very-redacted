package galen.tenant.dexter.Metrics;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.CancerType;
import galen.enums.tenant.dexter.HeartConditionType;
import galen.enums.tenant.dexter.LiverCancerType;
import galen.enums.tenant.dexter.SmokeType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CSVHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.BasePage;
import galen.pages.common.PritUnlPage;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class VTP_DEX_FRD_139_140_Participant_IA_Kickout_w_Timestamp extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_139: To verify the application shall record the reason that users are " +
            "disqualified from taking Zena in the DNUReason field.\n" +
            "DEX_FRD_140: To verify the application shall record the timestamp of when users are kicked out of the " +
            "application qualification process in the DNUTimestamp field.";
    static String NOTES = "This protocol contains the following verification scenarios:\n" +
            "-\t Confirm Customer \n" +
            "-\t Prevent Pregnancy \n" +
            "-\t No Menstruation (w. No Hormonal BC)\n" +
            "-\t Smoking or Vape and 35 or older\n" +
            "-\t Cancer\n" +
            "-\t Blood Pressure Medicine\n" +
            "-\t Chest Pain\n" +
            "-\t Blood Clots\n" +
            "-\t Irregular Heartbeat\n" +
            "-\t Liver Disease/Liver Cancer\n" +
            "-\t Vaginal Bleeding\n" +
            "-\t Diabetes\n" +
            "-\t Pregnant\n" +
            "-\t Breastfeeding\n" +
            "-\t Pregnancy Loss\n" +
            "-\t Migraines\n" +
            "-\t BP over 120/80 (Combined)\n" +
            "-\t BP over 180/120 (Combined Dangerous)\n" +
            "-\tCapture metrics UpdatedInitialOutcome, DNUReason, of DNU and DNUTimestamp for participant disqualified for BP over 120/80 (Combined) – Final BP Screen\n" +
            "-\tCapture metrics UpdatedInitialOutcome, DNUReason,of DNU and DNUTimestamp for participant disqualified for BP over 180/120 (Combined Dangerous) - Final BP " +
            "Screen\n";
    static String REQUIREMENTS = "FRD_139, FRD_140";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx\n" +
            "HappyFlow_IA_Initial_Assessment_noBP_NonSmoker.docx";
    String reportName = "VTP_FRD_139_140_Participant_IA_Kickout_w_Timestamp";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    CommonPageFeatures common;
    BasicHelpers basicHelpers;
    CSVHelpers csv;
    DexterUser user;
    StudyAdminPageObj sp;
    DexterHFWrappers hf;
    DexterPageObj pageObj;

    VTP_DEX_FRD_139_140_Participant_IA_Kickout_w_Timestamp()  {
        VERSIONHISTORY.add("1.0;10MAR2023;Initial Test Script;Name Redacted");
        VERSIONHISTORY.add("2.0;19SEP2023;Per CADENCE-359/CADENCE-360: Updated Test Steps navigation for restructured " +
                "cancer flow;Name Redacted");
        VERSIONHISTORY.add("3.0;07DEC2023;Per CADENCE-468: Updated Step 2;Name Redacted");
        VERSIONHISTORY.add("4.0;18JUN2024;Per CADENCE-567: Remove N/A from Expected Results column when using HappyFlow\n" +
                "Per CADENCE 591: Update Test Steps for modified assessment and navigation;Name Redacted");
        VERSIONHISTORY.add("5.0;17JUL2024;Per CADENCE-598: Update Test Objective and Metrics Verification table to include new column;Name Redacted");
    }

    public void checkMetrics(String outcome,String step, String dnuReason) {
        sp.login.load(UrlType.STUDY);
        String csvFileName="Step"+step+"_kickout";
        basicHelpers.downloadCSVAndVerify(csvFileName, sp, report);
        File csvFile=new File("reports/"+reportName+"/"+csvFileName);
        try {
            basicHelpers.compareCSVValueByAssessmentID(csvFile,user.assessmentID, outcome,
                    "DNU", report);
            System.out.println(csv.getCSVValueByAssessmentID(csvFile, user.assessmentID, "DNUReason"));
            basicHelpers.compareCSVValueByAssessmentID(csvFile,user.assessmentID, "DNUReason",
                dnuReason, report);
            basicHelpers.verifyCSVValuePresent(csvFile, user.assessmentID, "DNUTimestamp", report);
         } catch (Exception ignored) {
    
        }
    }

    public void yesNoKickouts(String step, String dnuReason, BasePage page, String value) throws IOException {
        hf.runDexterHFNonsmokingwBP(user, page, report);
        common.clickYesNoNextToPage(value, pageObj.kickoutPage, report);
        checkMetrics("InitialOutcome",step, dnuReason);
   }

    @Test
    public void VTP_FRD_139_140_Participant_IA_Kickout_w_Timestamp_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP FRD_139_140 – Participant Initial Assessment Kickout Recorded with Timestamp";

        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        pageObj = new DexterPageObj(driver);
        sp= new StudyAdminPageObj(driver);
        csv = new CSVHelpers();
        common = new CommonPageFeatures(driver);
        basicHelpers = new BasicHelpers(driver);
        hf = new DexterHFWrappers(driver);

        pageObj.pritUnl.load(UrlType.DEXTER);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);

        hf.runDexterHFNonsmokingwBP(user, pageObj.orderForSelf, report);
        pageObj.usedProduct.clickCloseButton(report);
        common.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        checkMetrics("InitialOutcome","3", "DNUSelfOrderScreen");

        yesNoKickouts("6", "DNUPreventPregnancyScreen", pageObj.pregnancy, "No");

        hf.runDexterHFNonsmokingwBP(user, pageObj.menstrual, report);
        common.clickYesNoNextToPage("No", pageObj.birthControlB, report);
        common.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        checkMetrics("InitialOutcome","10", "DNUChildbearing");

        hf.runDexterHFNonsmokingwBP(user, pageObj.smoking, report);
        user.dobYear="1984";
        pageObj.smoking.selectRadioResponseAndProgress(SmokeType.SMOKE_REGULARLY.label, pageObj.birthYear, report);
        pageObj.birthYear.fillOutBirthdayAndProgress(user,pageObj.kickoutPage, report);
        checkMetrics("InitialOutcome","14", "DNUSmokingScreen");

        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        hf.runDexterHFNonsmokingwBP(user, pageObj.everHadCancer, report);
        common.clickYesNoNextToPage("Yes", pageObj.cancerList, report);
        pageObj.cancerList.selectCheckboxAndProgress(CancerType.Ovarian_Cancer.label, pageObj.kickoutPage, report);
        checkMetrics("InitialOutcome","18", "DNUCancerScreen");

        yesNoKickouts("21", "DNUBPMedsScreen", pageObj.bloodPressureMeds, "Yes");

        hf.runDexterHFNonsmokingwBP(user, pageObj.cardiacRisk, report);
        pageObj.cancerList.selectCheckboxAndProgress(HeartConditionType.HEART_ATTACK.label, pageObj.kickoutPage, report);
        checkMetrics("InitialOutcome","24", "DNUCardiacRiskScreen");

        hf.runDexterHFNonsmokingwBP(user, pageObj.bloodClot, report);
        pageObj.bloodClot.selectCheckboxAndProgress("Blood clots", pageObj.kickoutPage, report);
        checkMetrics("InitialOutcome","27", "DNUBloodClotScreen");

        yesNoKickouts("30", "HeartValveDNUScreen", pageObj.irregularHeartBeat, "Yes");

        hf.runDexterHFNonsmokingwBP(user, pageObj.liverCancer, report);
        pageObj.liverCancer.selectCheckboxAndProgress(LiverCancerType.LIVER_DISEASE.label, pageObj.kickoutPage, report);
        checkMetrics("InitialOutcome","33", "DNULiverScreen");

        yesNoKickouts("36", "DNUVaginalBleedingScreen", pageObj.vaginalBleeding, "Yes");
        yesNoKickouts("39", "DNUDiabetesScreen", pageObj.diabetes, "Yes");
        yesNoKickouts("42", "DNUPregnancyScreen", pageObj.pregnant, "Yes");
        yesNoKickouts("45", "DNUBreastfeedingScreen", pageObj.breastFeeding, "Yes");
        yesNoKickouts("48", "DNUPregnancyLossScreen", pageObj.pregnancyLoss, "Yes");
        yesNoKickouts("51", "DNUMigraineScreen", pageObj.migraines, "Yes");

        hf.runDexterHFNonsmokingwBP(user, pageObj.enterBP, report);
        user.systolic="121";
        user.diastolic="81";
        pageObj.enterBP.enterBP(user, report);
        common.clickNextToPage(pageObj.kickoutPage, report);
        checkMetrics("InitialOutcome","54","DNUHighBpScreen");

        hf.runDexterHFNonsmokingwBP(user, pageObj.enterBP, report);
        user.systolic="181";
        user.diastolic="121";
        pageObj.enterBP.enterBP(user, report);
        common.clickNextToPage(pageObj.kickoutPage, report);
        checkMetrics("InitialOutcome","57","DNUDangerousBpScreen");

        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker();
        hf.runDexterHFNonsmokingwBP(user, pageObj.adbubpNormal, report);
        user.systolic="121";
        user.diastolic="81";
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);
        pageObj.enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage, report);
        checkMetrics("UpdatedInitialOutcome","62","DNUHighBpScreen");

        hf.runDexterHFNonsmokingwBP(user, pageObj.enterBPEnd, report);
        user.systolic="181";
        user.diastolic="121";
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);
        pageObj.enterBPEnd.enterAndVerifyToPage(user, pageObj.kickoutPage, report);
        checkMetrics("UpdatedInitialOutcome","67","DNUDANGEROUSBPSCREEN");
    }
}
