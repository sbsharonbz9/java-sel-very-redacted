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

public class VTP_DEX_FRD_073_Other_Cancers_Flag_ADBU_Navigation extends BaseTest {
    static String OBJECTIVE = "Verify on the List of Cancers screen, if the user selects only “Other” cancers and " +
            "selects the Next button, the application shall flag for ADBU and navigate to the BP Meds Screen.";
    static String NOTES = "The following scenario(s) are verified in this protocol:\n" +
            "•\tSubmission of Other Cancers (ONLY) navigates to the BP Meds Screen \n" +
            "•\tADBU Screen displays with Other Cancer condition due to triggered flag";
    static String REQUIREMENTS = "DEX_FRD_073";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_073_Other_Cancers_Flag_ADBU_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_073_Other_Cancers_Flag_ADBU_Navigation()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_073_Other_Cancers_Flag_ADBU_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_073 - Select Other Cancers ADBU flag and Navigation";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.everHadCancer, report);
        common.clickYesNoNextToPage(user.everHadCancer="Yes", pageObj.cancerList, report);
        pageObj.cancerList.selectCheckboxesAndProgress(user.cancerList, pageObj.bloodPressureMeds, report);
        report.addScreenshotStep("Step3_BloodPressureMedsScreen");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.oAuthPostReview, report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbu, report);
        pageObj.adbu.verifyConditionIsListed("you have or have had other cancer", report);
        report.addScreenshotStep("Step5_ADBU_WithCondition");
 }
}
