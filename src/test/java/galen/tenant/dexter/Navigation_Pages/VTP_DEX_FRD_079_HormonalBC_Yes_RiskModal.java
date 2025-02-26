package galen.tenant.dexter.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.ReviewAnswersLinks;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
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

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.birthControl, report);
        pageObj.birthControl.clickYesNoNextToModal("Yes", "Birth Control", report);
        report.addScreenshotStep("Step2_BC_A_Yes");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.review, report);
        pageObj.review.clickEditToPage(ReviewAnswersLinks.BIRTH_CONTROL, pageObj.birthControl, report);
        pageObj.birthControl.clickYesNoNextToModal("Yes", "Birth Control", report);
        report.addScreenshotStep("Step5_BC_Edit_Yes");
    }
}
