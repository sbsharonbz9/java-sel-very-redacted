package galen.tenant.dexter.BP;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_117_BP_Systolic_Autotab_Diastolic extends BaseTest {
    static String OBJECTIVE = "Verify on the Enter BP Numbers Screen, if the user enters 3 digits in the Systolic BP " +
            "field, the application shall automatically tab to the Diastolic input field.";
    static String NOTES = "This protocol contains the following verification scenario(s):\n" +
            "-\tSubmitting 1-digit integer in Systolic input field auto tabs focus to the Diastolic input field\n" +
            "-\tSubmitting 2-digit integer in Systolic input field auto tabs focus to the Diastolic input field\n" +
            "-\tSubmitting 3-digit integer in Systolic input field auto tabs focus to the Diastolic input field\n";
    static String REQUIREMENTS = "DEX_FRD_117";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_117_BP_Systolic_Autotab_Diastolic";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_117_BP_Systolic_Autotab_Diastolic() {
        VERSIONHISTORY.add("1.0;14NOV2022;Initial Test Script;Name Redacted");
        VERSIONHISTORY.add("2.0;20JUN2024;Per CADENCE-567: Remove N/A from Expected Results column when using HappyFlow\n" +
                "Per CADENCE-591: Update Test Steps for modified assessment and navigation;Name Redacted");
    }

    @Test
    public void VTP_DEX_FRD_117_BP_Systolic_Autotab_Diastolic_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_117 - BP Number Entering 3 Digits in Systolic BP field Auto-tab to Diastolic " +
                "field";
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        BasicHelpers basicHelpers = new BasicHelpers(driver);

        new PritUnlPage(driver).authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.enterBP, report);

        basicHelpers.sendTextFlex(pageObj.enterBP.getInputSystolic(), "1", "Systolic", report);
        basicHelpers.verifyActiveElement(false, pageObj.enterBP.inputDiastolic, "Diastolic", report);
        report.addScreenshotStep("Step2_Cursor not in Diastolic");

        basicHelpers.sendTextFlex(pageObj.enterBP.getInputSystolic(), "12", "Systolic", report);
        basicHelpers.verifyActiveElement(false, pageObj.enterBP.inputDiastolic, "Diastolic", report);
        report.addScreenshotStep("Step3_Cursor not in Diastolic");

        basicHelpers.sendTextFlex(pageObj.enterBP.getInputSystolic(), "120", "Systolic", report);
        basicHelpers.verifyActiveElement(true, pageObj.enterBP.inputDiastolic, "Diastolic", report);
        report.addScreenshotStep("Step4_Cursor is in Diastolic");
    }
}
