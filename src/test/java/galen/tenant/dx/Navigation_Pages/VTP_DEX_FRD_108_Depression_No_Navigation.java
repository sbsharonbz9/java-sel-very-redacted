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

public class VTP_DEX_FRD_108_Depression_No_Navigation extends BaseTest {
    static String OBJECTIVE = "To verify on the Depression screen, if the user answers no indicating that they do not " +
            "currently or have not previously had depression, the application shall navigate to BP Numbers screen.";
    static String NOTES = "This protocol contains the following verification scenario(s): \n" +
            "Submitting No to Depression while initially performing health survey navigates to the Know BP Screen";
    static String REQUIREMENTS = "DEX_FRD_108";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_108_Depression_No_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_108_Depression_No_Navigation() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_108_Depression_No_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_108_Depression_No_Navigation";

        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);

        new PritUnlPage(driver).authenticateUserIfRequired(URLType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.depression, report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("No", pageObj.knowBPNumber, report);
        report.addScreenshotStep("Step2_No_BP_Screen");
    }
}
