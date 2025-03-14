package galen.tenant.dx.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.ReviewAnswersLinks;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_079_HormonalBC_Yes_RiskModal extends BaseTest {

    static String OBJECTIVE = "To verify the Hormonal Birth Control A Screen shall navigate to the Hormonal Birth " +
            "Control Risk modal, when the user submits a confirmation response. ";
    static String NOTES = "This protocol contains the following verification scenario(s):\n" +
            "-\tSubmitting Yes on Hormonal BC (A) Screen triggers Risk modal\n" +
            "-\tSubmitting Yes on Hormonal BC (A) Screen when navigated by Editable Summary triggers Risk modal";

    static String REQUIREMENTS = "DEX_FRD_079";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_079_HormonalBC_Yes_RiskModal";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_079_HormonalBC_Yes_RiskModal() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_079_HormonalBC_Yes_RiskModal_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_079 – Hormonal BC Yes triggers Risk Modal";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.birthControl, report);
        pageObj.birthControl.clickYesNoNextToModal("Yes", "Birth Control", report);
        report.addScreenshotStep("Step2_BC_A_Yes");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.review, report);
        pageObj.review.clickEditToPage(ReviewAnswersLinks.BIRTH_CONTROL, pageObj.birthControl, report);
        pageObj.birthControl.clickYesNoNextToModal("Yes", "Birth Control", report);
        report.addScreenshotStep("Step5_BC_Edit_Yes");
    }
}
