package galen.tenant.dx.SinglePageContent;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
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
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_261_262_Clinical_Depression_Modal_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_261_262 – Clinical Depression Confirmation Modal";

        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        user.depression = "Yes";
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.diagnosedDepression, report);
        pageObj.diagnosedDepression.clickYesNoNextToModal("Yes", "Clinical Depression Modal", report);
        common.basicHelpers.verifyDisplayedFlex(common.btnBack, "Back", report);
        report.addScreenshotStep("Step3_Clinical_Depression_Modal");

        common.clickBackToPage(pageObj.diagnosedDepression, report);
        report.addScreenshotStep("Step4_Clinical_Depression_Screen");

        pageObj.diagnosedDepression.clickYesAndAddressModalToPage(pageObj.knowBPNumber, report);
        report.addScreenshotStep("Step6_Know BP Number_Screen");
    }
}