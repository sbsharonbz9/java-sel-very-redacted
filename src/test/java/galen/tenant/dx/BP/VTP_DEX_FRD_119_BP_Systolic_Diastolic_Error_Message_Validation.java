package galen.tenant.dx.BP;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_119_BP_Systolic_Diastolic_Error_Message_Validation extends BaseTest {
    static String OBJECTIVE = "Verify on the Enter BP Numbers Screen, if user enters an invalid entry, the application " +
            "shall display an error message to input a valid number.";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_119";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_119_BP_Systolic_Diastolic_Error_Message_Validation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_119_BP_Systolic_Diastolic_Error_Message_Validation() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_119_BP_Systolic_Diastolic_Error_Message_Validation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_119_BP_Systolic_Diastolic_Error_Message_Validation";
        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        user.systolic = "0";
        user.diastolic = "70";
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        pageObj.enterBP.enterBP(user, report);
        common.clickNext(report);
        pageObj.enterBP.verifyHasSystolicError(report);
        report.addScreenshotStep("Step2_Error_Message_Displayed");

        user.systolic = "120";
        user.diastolic = "0";
        pageObj.enterBP.enterBP(user, report);
        common.clickNext(report);
        pageObj.enterBP.verifyHasDiastolicError(report);
        report.addScreenshotStep("Step3_Error_Message_Displayed");

        user.systolic = "80";
        user.diastolic = "120";
        pageObj.enterBP.enterBP(user, report);
        common.clickNext(report);
        pageObj.enterBP.verifyModalDisplayed(report);
        report.addScreenshotStep("Step4_Confirmation_Modal_Displayed");
    }
}
