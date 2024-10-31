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

public class VTP_DEX_FRD_031_HormonalBC_Yes_HormonalBC_RiskModal extends BaseTest {
    static String OBJECTIVE = "If the user submits a confirmation response on the Hormonal Birth Control B Screen, the " +
            "application shall navigate to the Hormonal Birth Control Risk modal.";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_031";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_031_HormonalBC_Yes_HormonalBC_RiskModal";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_031_HormonalBC_Yes_HormonalBC_RiskModal()  {
        VERSIONHISTORY.add("1.0;03NOV2022;Initial Test Script;James Reale;" +
                "2.0;18JUN2024;Per CADENCE-529: Removed N/A from Actual Result column for Happy flow execution related steps\n" +
                "Per CADENCE-591: Update Test Steps for modified assessment and navigation;James Reale");
    }

    @Test
    public void  VTP_DEX_FRD_031_HormonalBC_Yes_HormonalBC_RiskModal_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_031 – Hormonal BC Yes Navigates to Hormonal BC Risk Modal";

        DexterUser user = new DexterUser();
        DexterPageObj pageObj = new DexterPageObj(driver);

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.menstrual, report);
        pageObj.menstrual.clickYesNoNextToPage("No", pageObj.birthControlB, report);
        pageObj.birthControlB.clickYesNoNext("Yes", report);
        pageObj.birthControlB.verifyConfirmModalOpen(report);
        report.addScreenshotStep("Step3_Hormonal BC Risk Modal");
    }
}
