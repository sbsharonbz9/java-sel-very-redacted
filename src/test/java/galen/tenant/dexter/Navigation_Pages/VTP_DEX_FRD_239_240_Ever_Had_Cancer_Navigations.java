package galen.tenant.dexter.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_239_240_Ever_Had_Cancer_Navigations extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_239: To verify on the Ever Had Cancer screen, if a user answers yes indicating " +
            "they have ever had cancer, the application shall navigate to the List of Cancers selection screen\n" +
            "DEX_FRD_240: To verify on the Ever Had Cancer screen, if a user answers no, indicating they have " +
            "never had cancer, the application shall navigate to the BP Meds Screen";

    static String NOTES = "The following scenario(s) are verified in this protocol:\n" +
            "•\tSubmission of YES on the Ever Had Cancer screen navigates to the List of Cancers Screen\n" +
            "•\tSubmission of NO on the Ever Had Cancer screen navigates to the BP Meds Screen\n";
    static String REQUIREMENTS = "DEX_FRD_239, DEX_FRD_240";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_239_240_Ever_Had_Cancer_Navigations";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_239_240_Ever_Had_Cancer_Navigations() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_239_240_Ever_Had_Cancer_Navigations_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_239_240 – Ever Had Cancer Navigations";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.everHadCancer, report);
        common.clickYesNoNextToPage("Yes", pageObj.cancerList, report);
        report.addScreenshotStep("Step2_CancerList_Screen");

        pageObj.cancerList.clickBackToPage(pageObj.everHadCancer, report);
        common.clickYesNoNextToPage("No", pageObj.bloodPressureMeds, report);
        report.addScreenshotStep("Step4_BP_Meds_Screen");
    }
}
