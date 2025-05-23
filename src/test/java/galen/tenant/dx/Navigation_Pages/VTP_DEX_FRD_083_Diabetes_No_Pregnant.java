package galen.tenant.dx.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_083_Diabetes_No_Pregnant extends BaseTest {
    static String OBJECTIVE = "To verify on the Diabetes screen, if the user answers no indicating they do not currently" +
            " or have not previously had diabetes, the application shall navigate to the Pregnancy screen.";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_083";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_083_Diabetes_No_Pregnant";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_083_Diabetes_No_Pregnant()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_083_Diabetes_No_Pregnant_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_083 – Diabetes No Navigates to Pregnant screen";

        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.diabetes, report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("No", pageObj.pregnant, report);
        report.addScreenshotStep("Step2_Pregnancy_Screen");
    }
}
