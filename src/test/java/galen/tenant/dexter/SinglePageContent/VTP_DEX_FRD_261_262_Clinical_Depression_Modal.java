package galen.tenant.dexter.SinglePageContent;

import galen.base.BaseTest;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_FRD_261_262_Clinical_Depression_Modal extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_261: To verify Clinical Depression Modal shall provide a control that allow users " +
            "to input a confirmation response or return to the previous screen.\n" +
            "DEX_FRD_262: To verify if the user provides a confirmation response on the Clinical Depression Modal, " +
            "the application shall navigate to the Know BP Numbers Screen.\n";
    static String NOTES = "This protocol contains the following verification scenario(s):\n" +
            "•\tClinical Depression Confirmation Modal displays control for confirmation and control for return to the Clinical Depression Screen\n" +
            "•\tSelection of the return control closes the modal and displays the Clinical Depression Screen\n" +
            "•\tConfirmation on the Clinical Depression Confirmation Modal navigates to the Know BP Numbers Screen\n";
    static String REQUIREMENTS = "DEX_FRD_261, DEX_FRD_262";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_261_262_Clinical_Depression_Modal";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_261_262_Clinical_Depression_Modal() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Name Redacted");
    }

    @Test
    public void VTP_DEX_FRD_261_262_Clinical_Depression_Modal_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_261_262 – Clinical Depression Confirmation Modal";

        DexterUser user = new DexterUser();
        DexterPageObj pageObj = new DexterPageObj(driver);

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.depression, report);
        pageObj.depression.clickYesOrNo("Yes", report);
        pageObj.depression.clickNextToPage(pageObj.diagnosedDepression, report);
        pageObj.diagnosedDepression.clickYesOrNo("Yes", report);
        pageObj.diagnosedDepression.clickNext(report);
        pageObj.diagnosedDepression.verifyConfirmModalOpen(report);
        pageObj.diagnosedDepression.verifyConfirmButton(report);
        pageObj.diagnosedDepression.verifyBackButton(report);
        report.addScreenshotStep("Step3_Clinical_Depression_Modal");

        pageObj.diagnosedDepression.clickBackButton(report);
        pageObj.diagnosedDepression.verifyAtPage(report);
        report.addScreenshotStep("Step4_Clinical_Depression_Screen");
        pageObj.diagnosedDepression.clickNext(report);
        pageObj.diagnosedDepression.verifyConfirmModalOpen(report);
        pageObj.diagnosedDepression.clickConfirm(report);
        pageObj.knowBPNumber.verifyAtPage(report);
        report.addScreenshotStep("Step6_Clinical_Depression_Screen");
    }
}