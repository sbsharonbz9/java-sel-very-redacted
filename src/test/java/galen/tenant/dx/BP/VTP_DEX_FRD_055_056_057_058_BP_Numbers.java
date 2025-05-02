package galen.tenant.dx.BP;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import galen.pages.tenant.dx.InitialAssessment.EnterBP;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_055_056_057_058_BP_Numbers extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_055: To verify If the user’s diastolic BP > systolic BP, the application shall " +
            "provide an error message and provide an option to re-enter.\n" +
            "DEX_FRD_056: To verify If the user’s BP is > 120/80 the application shall end the health survey and " +
            "display the High Blood Pressure DNU Screen.\n" +
            "DEX_FRD_057: To verify If the user’s BP is ≥ 180/120 the application shall end the health survey and " +
            "display the Dangerously High Blood Pressure DNU Screen.\n" +
            "DEX_FRD_058: If the user’s BP is ≤ 120/80, the application shall navigate to the Health Survey Summary " +
            "screen.\n";
    static String NOTES = "The following scenario(s) are verified in this protocol:\n" +
            "-\tError message is provided when Diastolic is greater than Systolic\n" +
            "-\tError message is provided when entering invalid characters\n" +
            "-\tDNU message displays when Systolic is greater than 120\n" +
            "-\tDNU message displays when Diastolic is greater than 80\n" +
            "-\tDNU message displays when Systolic/Diastolic are both greater than 120/80\n" +
            "-\tDNU Dangerously high BP message displays when Systolic is greater than 180\n" +
            "-\tDNU Dangerously high BP message displays when Systolic is equal to 180\n" +
            "-\tDNU Dangerously high BP message displays when Diastolic is greater than 120\n" +
            "-\tDNU Dangerously high BP message displays when Diastolic is equal to 120\n" +
            "-\tDNU Dangerously high BP message displays when Systolic/Diastolic are both greater than 180/120\n" +
            "-\tDNU Dangerously high BP message displays when Systolic/Diastolic are both equal to 180/120\n" +
            "-\tDNU message displays when Systolic/Diastolic are both equal to 179/119\n" +
            "-\tEligible user is navigated to the Editable Summary when Systolic/Diastolic are both equal to 120/80\n";
    static String REQUIREMENTS = "DEX_FRD_055, DEX_FRD_056, DEX_FRD_057, DEX_FRD_058";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_055_056_057_058_BP_Numbers";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_055_056_057_058_BP_Numbers()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_055_056_057_058_BP_Numbers_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_055_056_057_058 – BP Numbers Screen Validation";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        EnterBP bpPage=pageObj.enterBP;
        CommonPageFeatures common = new CommonPageFeatures(driver);


        // Sys 110, Dias 111 to Modal
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);

        user.systolic="110";
        user.diastolic="111";
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        bpPage.enterBP(user, report);
        common.clickNext(report);
        bpPage.verifyModalDisplayed(report);
        report.addScreenshotStep("Step2_Sys_110_Dias_111_Confirm");

        // Sys 0, Dias 0 to Error
        user.systolic="0";
        user.diastolic="0";
        bpPage.clickChangeButton(report);
        bpPage.enterBP(user, report);
        common.clickNext(report);
        bpPage.verifyHasSystolicError(report);
        bpPage.verifyHasDiastolicError(report);
        report.addScreenshotStep("Step4_Sys_0_Dias_0_FieldError");

        // Sys 121, Dias 70 to DNU
        user.systolic="121";
        user.diastolic="70";
        bpPage.enterBPAndProgress(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step5_Sys_121_Dias_70_DNU");

        // Sys 110, Dias 81 to DNU
        user.systolic="110";
        user.diastolic="81";
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        bpPage.enterBPAndProgress(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step7_Sys_110_Dias_81_DNU");

        // Sys 121, Dias 81 to DNU
        user.systolic="121";
        user.diastolic="81";
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        bpPage.enterBPAndProgress(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step9_Sys_121_Dias_81_DNU");

        // Sys 181, Dias 70 to DNU
        user.systolic="181";
        user.diastolic="70";
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        bpPage.enterBPAndProgress(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step11_Sys_181_Dias_70_DNU");

        // Sys 180, Dias 70 to DNU
        user.systolic="180";
        user.diastolic="70";
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        bpPage.enterBPAndProgress(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step13_Sys_180_Dias_70_DNU");

        // Sys 125, Dias 121 to DNU
        user.systolic="125";
        user.diastolic="121";
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        bpPage.enterBPAndProgress(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step15_Sys_125_Dias_121_DNU");

        // Sys 121, Dias 120 to DNU
        user.systolic="121";
        user.diastolic="120";
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        bpPage.enterBPAndProgress(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step17_Sys_121_Dias_120_DNU");

        // Sys 181, Dias 121 to DNU
        user.systolic="181";
        user.diastolic="121";
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        bpPage.enterBPAndProgress(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step19_Sys_181_Dias_121_DNU");

        // Sys 180, Dias 120 to DNU
        user.systolic="180";
        user.diastolic="120";
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        bpPage.enterBPAndProgress(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step21_Sys_180_Dias_120_DNU");

        // Sys 179, Dias 119 to DNU
        user.systolic="179";
        user.diastolic="119";
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        bpPage.enterBPAndProgress(user, pageObj.kickoutPage,report);
        report.addScreenshotStep("Step23_Sys_179_Dias_119_DNU");

        // Sys 120, Dias 80 to Review
        user.systolic="120";
        user.diastolic="80";
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        bpPage.enterBPAndProgress(user, pageObj.review,report);
        report.addScreenshotStep("Step25_Sys_120_Dias_80_Review");
    }
}
