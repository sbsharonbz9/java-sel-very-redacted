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

public class VTP_DEX_FRD_080_HormonalBC_RiskModal_Acknowledge_Smoking extends BaseTest {
    static String OBJECTIVE = "To verify the Hormonal BC risk modal shall navigate to the Smoking or Vape screen, if " +
            "the user provides a confirmation response.";
    static String NOTES = "This protocol contains the following verification scenario(s):\n" +
            "-\tClicking the checkbox of the Hormonal BC risk modal from the Hormonal BC (A) Screen navigates to Smoking" +
            " or Vape Screen\n" +
            "-\tClicking the checkbox of the Hormonal BC risk modal from the Hormonal BC (B) Screen navigates" +
            " to Smoking or Vape Screen\n";
    static String REQUIREMENTS = "DEX_FRD_080";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_080_HormonalBC_RiskModal_Acknowledge_Smoking";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_080_HormonalBC_RiskModal_Acknowledge_Smoking()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_080_HormonalBC_RiskModal_Acknowledge_Smoking_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_080 – Hormonal BC Risk Modal Navigates to Smoking Screen";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.birthControl, report);
        common.clickYesNoNextToPage("No", pageObj.smoking, report);
        report.addScreenshotStep("Step3_Smoking Page");

        common.clickBackToPage(pageObj.birthControl, report);
        common.clickBackToPage(pageObj.menstrual, report);
        common.clickYesNoNextToPage("No", pageObj.birthControlB, report);
        pageObj.birthControlB.addressConfirmationsAndProgress(pageObj.smoking,report);
        report.addScreenshotStep("Step8_Smoking Page");
    }
}
