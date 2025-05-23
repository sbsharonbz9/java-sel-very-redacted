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

public class VTP_DEX_FRD_263_Clinical_Depression_Screen  extends BaseTest {
    static String OBJECTIVE = "To verify the Clinical Depression Screen provides controls that allow users to input "+
            "a confirmation or denial response.";
    static String NOTES = "This protocol contains the following scenario(s):\n" +
            "-\tClinical Depression Screen has controls that allow the user to select whether they confirm or deny a question. \n";
    static String REQUIREMENTS = "DEX_FRD_263";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_263_Clinical_Depression_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_263_Clinical_Depression_Screen()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_263_Clinical_Depression_Screen_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_263 – Clinical Depression Screen";

        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.depression, report);
        pageObj.depression.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression, report);
        pageObj.depression.clickNextToPage(pageObj.diagnosedDepression, report);
        pageObj.diagnosedDepression.verifyYesNoPresent(report);
        report.addScreenshotStep("Step2_YesNoPresent");

        pageObj.diagnosedDepression.clickYesNo_NextEnabled("Yes", report);
        report.addScreenshotStep("Step3_Next Button Enabled");

        pageObj.diagnosedDepression.clickYesOrNo("No", report);
        pageObj.diagnosedDepression.clickYesNo_NextEnabled("Yes", report);
        report.addScreenshotStep("Step4_Next Button Enabled");
    }
}
