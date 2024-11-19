package galen.tenant.dexter.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_064_ChestPain_HeartAttack_Stroke_Navigate_Blood_Clot extends BaseTest {
    static String OBJECTIVE = "To verify If the user selects “None of These” on the Heart Conditions Screen, the " +
            "application shall navigate to the Blood Clot Screen.";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_064";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_064_ChestPain_HeartAttack_Stroke_Navigate_Blood_Clot";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_064_ChestPain_HeartAttack_Stroke_Navigate_Blood_Clot()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");

    }

    @Test
    public void VTP_DEX_FRD_064_ChestPain_HeartAttack_Stroke_Navigate_Blood_Clot_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_064 - Select None of These Condition Navigates to Blood Clot screen";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.cardiacRisk, report);
        pageObj.cardiacRisk.selectCheckboxesAndProgress(user.conditionType, pageObj.bloodClot, report);
        report.addScreenshotStep("Step2_Blood Clot Page");
    }

}
