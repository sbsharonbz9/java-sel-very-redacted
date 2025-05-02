package galen.tenant.dx.ADBU;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_228_ADBU_Attestation_Modal extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_228_ADBU_Attestation_Modal";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_228_ADBU_Attestation_Modal()  {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_228_ADBU_Attestation_Modal_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_228 – ADBU Attestation Modal";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_wBP();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFADBUwBP(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbu, report);
        pageObj.adbu.clickYesNoToOpenModal("No", "ADBU confirm modal", report);
        pageObj.adbu.verifyConfirmButtonDisabled(report);
        report.addScreenshotStep("Step2_ADBU_Confirm_Button_Disabled");

        pageObj.adbu.clickConfirmCheckbox(report);
        pageObj.adbu.verifyConfirmButtonEnabled(report);
        report.addScreenshotStep("Step3_ADBU_Confirm_Button_Enabled");

        pageObj.adbu.clickConfirmCheckbox(report);
        pageObj.adbu.verifyConfirmButtonDisabled(report);
        report.addScreenshotStep("Step4_ADBU_Confirm_Button_Disabled");

        user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_noBP();
        new DxHFWrappers(driver).runDxHFADBUNoBP(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpScreen,report);
        pageObj.adbubpScreen.clickYesNoToOpenModal("No", "ADBU BP modal",report);
        pageObj.adbubpScreen.verifyConfirmButtonDisabled(report);
        report.addScreenshotStep("Step6_ADBU_Confirm_Button_Disabled");

        pageObj.adbubpScreen.clickConfirmCheckbox(report);
        pageObj.adbubpScreen.verifyConfirmButtonEnabled(report);
        report.addScreenshotStep("Step7_ADBU_Confirm_Button_Enabled");

        pageObj.adbubpScreen.clickConfirmCheckbox(report);
        pageObj.adbubpScreen.verifyConfirmButtonDisabled(report);
        report.addScreenshotStep("Step8_ADBU_Confirm_Button_Disabled");
    }
}