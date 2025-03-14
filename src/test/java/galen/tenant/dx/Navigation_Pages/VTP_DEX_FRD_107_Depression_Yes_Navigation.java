package galen.tenant.dx.Navigation_Pages;

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

public class VTP_DEX_FRD_107_Depression_Yes_Navigation extends BaseTest {

    static String OBJECTIVE = "Verify on the Depression screen, if the user answers yes indicating that they do " +
            "currently or previously had depression, the application shall navigate to the Clinical Depression screen. ";
    static String NOTES = "This protocol contains the following verification scenario(s): \n" +
            "Submitting Yes to Depression while initially performing health survey navigates to Clinical Depression Screen \n" +
            "Submitting Yes to Depression when editing by navigation of the Editable Summary navigates to Clinical " +
            "Depression Screen ";
    static String REQUIREMENTS = "DEX_FRD_107";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_107_Depression_Yes_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_107_Depression_Yes_Navigation() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_107_Depression_Yes_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_107_Depression_Yes_Navigation";
        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);

        new PritUnlPage(driver).authenticateUserIfRequired(URLType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.depression, report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("Yes", pageObj.diagnosedDepression, report);
        report.addScreenshotStep("Step2_DiagnosedDepression_Screen");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.review, report);
        pageObj.review.clickDepressionEdit(report);
        pageObj.depression.verifyAtPage(report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("Yes", pageObj.diagnosedDepression, report);
        report.addScreenshotStep("Step5_DiagnosedDepression_Screen");
    }
}
