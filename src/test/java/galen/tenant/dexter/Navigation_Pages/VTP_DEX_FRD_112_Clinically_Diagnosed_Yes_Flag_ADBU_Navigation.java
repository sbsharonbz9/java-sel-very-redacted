package galen.tenant.dexter.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
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
        VERSIONHISTORY.add("1.0;11NOV2022;Initial Test Script;James Reale");
        VERSIONHISTORY.add("2.0;20JUN2024;Per CADENCE-567: Remove N/A from Expected Results column when using HappyFlow \n" +
                "Per CADENCE-591: Update Test Steps for modified assessment and navigation;James Reale");
    }

    @Test
    public void VTP_DEX_FRD_112_Clinically_Diagnosed_Yes_Flag_ADBU_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_112_Clinically_Diagnosed_Yes_Flag_ADBU_Navigation";
        DexterUser user = new DexterUser();
        DexterPageObj pageObj = new DexterPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        new PritUnlPage(driver).authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.depression, report);
        common.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression, report);
        pageObj.diagnosedDepression.clickYesAndAddressModalToPage(pageObj.knowBPNumber, report);
        report.addScreenshotStep("Step4_BP_Screen");

        new DexterHFWrappers(driver).runAntifungalToADBU(user,pageObj.knowBPNumber, pageObj.adbu, report);
        pageObj.adbu.verifyConditionIsListed(pageObj.adbu.depressionCondition, report);
        report.addScreenshotStep("Step10_ADBU");
    }
}
