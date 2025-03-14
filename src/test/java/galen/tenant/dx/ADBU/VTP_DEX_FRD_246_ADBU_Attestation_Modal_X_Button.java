package galen.tenant.dx.ADBU;

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

public class VTP_DEX_FRD_246_ADBU_Attestation_Modal_X_Button extends BaseTest {
    static String OBJECTIVE = "To verify on the ADBU Doctor Not Approved modal, if the user selects the ‘X’ button on "+
            "the modal, the application shall close the modal.";

    static String NOTES=" This protocol contains verification of the following scenario(s):\n" +
            "-\tClicking X button closes the modal and displays ADBU screen\n" +
            "-\tSelecting checkbox and clicking X button closes the modal and displays ADBU screen\n" +
            "-\tClicking X button closes the modal and displays ADBU/BP screen\n" +
            "-\tSelecting checkbox and clicking X button closes the modal and displays ADBU/BP screen\n";
    static String REQUIREMENTS = "FRD_246";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_ADBU_noBP\n" +
            "HappyFlow_IA_Initial_Assessment_ADBU_wBP.docx\n";
    String reportName = "VTP_DEX_FRD_246_ADBU_Attestation_Modal_X_Button";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_246_ADBU_Attestation_Modal_X_Button()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_246_ADBU_Attestation_Modal_X_Button_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_246– Attestation Modal X Button";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_wBP();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFADBUwBP(user, pageObj.review, report);

        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbu,report);
        pageObj.adbu.clickYesNoToOpenModal("No",report);
        report.addScreenshotStep("Step2_ADBU_Doctor_Not_Approved_Modal");

        pageObj.adbu.clickXButton(report);
        pageObj.adbu.verifyAtPage(report);
        report.addScreenshotStep("Step3_ADBU_Screen");

        pageObj.adbu.clickYesNoToOpenModal("No",report);
        pageObj.adbu.clickConfirmCheckbox(report);
        pageObj.adbu.clickXButton(report);
        pageObj.adbu.verifyAtPage(report);
        report.addScreenshotStep("Step5_ADBU_Screen");

        user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_noBP();

        new DxHFWrappers(driver).runDxHFADBUNoBP(user, pageObj.review, report);

        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpScreen,report);
        pageObj.adbubpScreen.clickYesNoToOpenModal("No",report);
        report.addScreenshotStep("Step7_ADBUBP_Modal");

        pageObj.adbubpScreen.clickXButton(report);
        pageObj.adbubpScreen.verifyAtPage(report);
        report.addScreenshotStep("Step8_ADBUBP_Screen");

        pageObj.adbubpScreen.clickYesNoToOpenModal("No",report);
        pageObj.adbubpScreen.clickConfirmCheckbox(report);
        pageObj.adbubpScreen.clickXButton(report);
        pageObj.adbubpScreen.verifyAtPage(report);
        report.addScreenshotStep("Step10_ADBUBP_Screen");
    }
}