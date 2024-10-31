package galen.tenant.dexter.OAuth;

import galen.base.BaseTest;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_225_226_Guest_Confirmation_Modal extends BaseTest {

    static String OBJECTIVE = "To verify on the summary screen, if a user confirms the answers, the application shall present a confirmation modal with options to “finish” or “go back";
    static String NOTES = "This protocol contains the following scenarios: \n" +
            "Confirmation Modal is displayed with the following options: \n" +
            "Go Back button \n" +
            "Finish button \n" +
            "Clicking Go Back navigates to Editable Summary ";

    static String REQUIREMENTS = "DEX_FRD_225_226";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_225_226_Guest_Confirmation_Modal";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_225_226_Guest_Confirmation_Modal() {
        VERSIONHISTORY.add("1.0;09MAR2023;Initial Test Script ;Name Redacted");
        VERSIONHISTORY.add("2.0;24JUN2024;Per CADENCE-478: Updated Test Steps for FDA changes \n" +
                "Per CADENCE-529: Removed N/A from Actual Result column for Happy flow execution related steps \n" +
                "Per CADENCE-591: Update Test Steps for modified assessment and navigation ;Name Redacted");
    }

    @Test
    public void VTP_DEX_FRD_225_226_Guest_Confirmation_Modal_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_225_226_Guest_Confirmation_Modal";
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.oAuth, report);
        pageObj.oAuth.clickGuestButton(report);
        pageObj.oAuth.verifyConfirmDisplayed(report);
        pageObj.oAuth.clickXButton(report);
        pageObj.oAuth.verifyAtPage(report);
        report.addScreenshotStep("Step_3_Login_Selection");
        pageObj.oAuth.clickGuestButton(report);
        pageObj.oAuth.verifyConfirmDisplayed(report);
        pageObj.oAuth.clickConfirm(report);
        pageObj.usedProduct.verifyAtPage(report);
        report.addScreenshotStep("Step_5_Prior_Use");



    }
}