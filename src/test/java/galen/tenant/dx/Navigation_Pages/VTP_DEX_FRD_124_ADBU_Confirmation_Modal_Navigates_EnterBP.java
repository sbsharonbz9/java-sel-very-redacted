package galen.tenant.dx.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_124_ADBU_Confirmation_Modal_Navigates_EnterBP extends BaseTest {

    static String OBJECTIVE = "To verify on the ADBU Doctor Approved Modal, if the user was not flagged as DDI and did " +
            "NOT enter BP readings and selects the checkbox and clicks the confirm button, the application shall " +
            "navigate to the Final BP Screen.";
    static String NOTES = "This contains l verifies the following scenario(s): \n" +
            "User after completing health Survey with ADBU (NOT DDI) + Get BP from Confirmation Modal triggers Enter BP" +
            " - Final BP Screen";
    static String REQUIREMENTS = "DEX_FRD_124";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_ADBU_noBP.docx";
    String reportName = "VTP_DEX_FRD_124_ADBU_Confirmation_Modal_Navigates_EnterBP";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_124_ADBU_Confirmation_Modal_Navigates_EnterBP() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_124_ADBU_Confirmation_Modal_Navigates_EnterBP_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_124_ADBU_Confirmation_Modal_Navigates_EnterBP";
        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_noBP();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFADBUNoBP(user, pageObj.adbubpScreen, report);
        pageObj.adbubpScreen.addressConfirmationsAndProgress("Yes", pageObj.adbubpNormal, report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);
        report.addScreenshotStep("Step3_Enter_BP");
    }
}