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

public class VTP_DEX_FRD_106_Gallbladder_No_Navigation extends BaseTest {
    static String OBJECTIVE = "Verify on the Gallbladder screen, if the user answers no indicating they do not currently" +
            " or have not previously had gallbladder disease, the application shall navigate to the Depression screen. ";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_106";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_106_Gallbladder_No_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_106_Gallbladder_No_Navigation() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_106_Gallbladder_No_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_106_Gallbladder_No_Navigation";

        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);

        new PritUnlPage(driver).authenticateUserIfRequired(URLType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.gallbladder, report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("No", pageObj.depression, report);
        report.addScreenshotStep("Step2_Depression_Screen");
    }
}