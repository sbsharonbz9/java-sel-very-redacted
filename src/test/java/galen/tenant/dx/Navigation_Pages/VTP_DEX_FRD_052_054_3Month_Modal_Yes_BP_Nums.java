package galen.tenant.dx.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_052_054_3Month_Modal_Yes_BP_Nums extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_052: To verify if the user selects that they do know their BP numbers on the Know" +
            " BP Numbers screen, the application shall dis-play the BP Within 3 Months Modal\n" +
            "DEX_FRD_054: To verify if the user selects “Yes” on the BP Within 3 Months Modal, the application shall" +
            " navigate to the Enter BP Numbers Screen";
    static String NOTES = "The following scenario(s) are verified in this protocol:\n" +
            "-\t3Month Modal is displayed if user submits Yes, I know my blood pressure numbers\n" +
            "-\tEnter BP Numbers Screen is displayed if user clicks Yes on 3Month Modal";
    static String REQUIREMENTS = "DEX_FRD_052, VTP_DEX_FRD_054";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_052_054_3Month_Modal_Yes_BP_Nums";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_052_054_3Month_Modal_Yes_BP_Nums()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_052_054_3Month_Modal_Yes_BP_Nums_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_052_054 – 3Month Modal Navigates to Enter BP Numbers ";

        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.knowBPNumber, report);

        pageObj.knowBPNumber.clickYesAndOpenModal(report);
        report.addScreenshotStep("Step2_ThreeMonthsModal");

        pageObj.knowBPNumber.clickYesNoModalToPage("Yes", pageObj.enterBP, report);
        report.addScreenshotStep("Step3_EnterBP");
    }
}
