package galen.tenant.dexter.ADBU;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_121_123_214_ADBU_Attestation_Navigation extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_121: To verify on the ADBU and ADBU/BP screen if the user answers no indicating a" +
            " doctor has not said it was safe to use Zena, the application shall present the ADBU Doctor Not Approved " +
            "modal. \n" +
            "DEX_FRD_123: To verify on the ADBU Doctor Not Approved modal, if the user who previously entered BP " +
            "readings selects the checkbox and clicks the confirm button, the application shall navigate to the ADBU-End" +
            " screen. \n" +
            "DEX_FRD_214: To verify on the ADBU Doctor Not Approved attestation modal, if the user that did not " +
            "previously enter BP selects the checkbox, clicks the confirm button and the user did not previously flag a " +
            "DDI medication, the application shall navigate to the ADBU/BP-End screen ";
    static String NOTES = "This protocol contains the following scenario(s): \n" +
            "After triggering ADBU flag and clicking No on the ADBU Screen, the ADBU Doctor Not Approved Modal is displayed \n" +
            "Triggering ADBU ONLY, navigation from the ADBU Doctor Not Approved Modal navigates to the ADBU - " +
            "Reconfirmation Screen \n" +
            "After triggering ADBU flag and clicking No on the ADBU/BP Screen, the ADBU Doctor Not Approved Modal is displayed ";
    static String REQUIREMENTS = "DEX_FRD_121_123_214";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_ADBU_wBP.docx;   \n" +
            "HappyFlow_IA_Initial_Assessment_ADBU_noBP.docx ";
    String reportName = "VTP_DEX_FRD_121_123_214_ADBU_Attestation_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_121_123_214_ADBU_Attestation_Navigation() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Name Redacted");
    }

    @Test
    public void VTP_DEX_FRD_121_123_214_ADBU_Attestation_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_121_123_214_ADBU_Attestation_Navigation";
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_wBP();
        DexterPageObj pageObj = new DexterPageObj(driver);

        new PritUnlPage(driver).authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFADBUwBP(user, pageObj.adbu, report);
        pageObj.adbu.clickYesNoToOpenModal("No", report);
        report.addScreenshotStep("Step2_Not_Approved_Modal");
        
        pageObj.adbu.addressOpenModalConfirmations(report);
        pageObj.docAttestationNo.verifyAtPage(report);
        report.addScreenshotStep("Step3_ADBU");

        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_noBP();
        new DexterHFWrappers(driver).runDexterHFADBUNoBP(user, pageObj.adbubpScreen, report);
        pageObj.adbubpScreen.clickYesNoToOpenModal("No", report);
        report.addScreenshotStep("Step5_Not_Approved_Modal");
        
        pageObj.adbubpScreen.addressOpenModalConfirmations(report);
        pageObj.docAttestationNo.verifyAtPage(report);
        report.addScreenshotStep("Step6_ADBU_BP");

    }
}