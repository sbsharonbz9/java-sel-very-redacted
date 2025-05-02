package galen.tenant.dx.SinglePageContent;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
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
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void  VTP_DEX_FRD_031_HormonalBC_Yes_HormonalBC_RiskModal_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_031 – Hormonal BC Yes Navigates to Hormonal BC Risk Modal";

        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.menstrual, report);
        pageObj.menstrual.clickYesNoNextToPage("No", pageObj.birthControlB, report);
        pageObj.birthControlB.clickYesNoNext("Yes", report);
        pageObj.birthControlB.verifyConfirmModalOpen(report);
        report.addScreenshotStep("Step3_Hormonal BC Risk Modal");
    }
}
