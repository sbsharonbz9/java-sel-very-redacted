package galen.tenant.dexter.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.ReviewAnswersLinks;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
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
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_107_Depression_Yes_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_107_Depression_Yes_Navigation";
        DexterUser user = new DexterUser();
        DexterPageObj pageObj = new DexterPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.depression, report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("Yes", pageObj.diagnosedDepression, report);
        report.addScreenshotStep("Step2_DiagnosedDepression_Screen");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.review, report);
        pageObj.review.clickEditToPage(ReviewAnswersLinks.DEPRESSION, pageObj.depression, report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("Yes", pageObj.diagnosedDepression, report);
        report.addScreenshotStep("Step5_DiagnosedDepression_Screen");
    }
}
