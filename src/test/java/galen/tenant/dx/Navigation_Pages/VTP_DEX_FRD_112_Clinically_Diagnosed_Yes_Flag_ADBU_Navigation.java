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

public class VTP_DEX_FRD_112_Clinically_Diagnosed_Yes_Flag_ADBU_Navigation extends BaseTest {

    static String OBJECTIVE = "To verify on the Clinical Depression screen, if the user answers yes indicating that " +
            "their depression was clinically diagnosed, the application shall flag for ADBU and navigate to the Clinical" +
            " Depression Confirmation Modal. ";
    static String NOTES = "The following scenario(s) are verified in this protocol: \n" +
            "Submitting Yes on Clinical Depression navigates to the Know BP Screen \n" +
            "Clinically Diagnosed Depression condition is listed in ADBU screen ";
    static String REQUIREMENTS = "DEX_FRD_112";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_112_Clinically_Diagnosed_Yes_Flag_ADBU_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_112_Clinically_Diagnosed_Yes_Flag_ADBU_Navigation() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_112_Clinically_Diagnosed_Yes_Flag_ADBU_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_112_Clinically_Diagnosed_Yes_Flag_ADBU_Navigation";
        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        pageObj.pritUnlauthenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.depression, report);
        common.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression, report);
        pageObj.diagnosedDepression.clickYesAndAddressModalToPage(pageObj.knowBPNumber, report);
        report.addScreenshotStep("Step4_BP_Screen");

        new DxHFWrappers(driver).runAntifungalToADBU(user,pageObj.knowBPNumber, pageObj.adbu, report);
        pageObj.adbu.verifyConditionIsListed(pageObj.adbu.depressionCondition, report);
        report.addScreenshotStep("Step10_ADBU");
    }
}
