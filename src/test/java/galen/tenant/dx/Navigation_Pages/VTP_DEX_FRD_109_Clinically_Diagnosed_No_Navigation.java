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

public class VTP_DEX_FRD_109_Clinically_Diagnosed_No_Navigation extends BaseTest {
    static String OBJECTIVE = "To verify If the user provides a denial response on the Clinical Depression Screen, the " +
            "application shall navigate to the Know BP Numbers Screen.";
    static String NOTES = "This protocol contains the following verification scenario(s): \n" +
            "Submitting No to Clinical Depression while initially performing health survey navigates to the Know BP " +
            "Numbers Screen";
    static String REQUIREMENTS = "DEX_FRD_109";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_109_Clinically_Diagnosed_No_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_109_Clinically_Diagnosed_No_Navigation() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_109_Clinically_Diagnosed_No_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_109_Clinically_Diagnosed_No_Navigation";
        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        pageObj.pritUnlauthenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.depression, report);
        common.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression, report);
        common.clickYesNoNextToPage("No", pageObj.knowBPNumber, report);
        report.addScreenshotStep("Step3_Know_BP");
    }
}
