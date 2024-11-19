package galen.tenant.dexter.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.DDIConditionType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class VTP_DEX_FRD_093_High_Cholesterol_ADBU_Navigation extends BaseTest {

    static String OBJECTIVE = "To verify if the user selects High Cholesterol on the DDI/Conditions Screen, the " +
            "application shall flag for ADBU and continue to the DDI Medications screen.";
    static String NOTES = "This protocol contains the following verification scenario(s):\n" +
            "-\tUser selection of High Cholesterol flags ADBU and navigates to High Cholesterol Meds Screen";
    static String REQUIREMENTS = "DEX_FRD_093";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_093_High_Cholesterol_ADBU_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_093_High_Cholesterol_ADBU_Navigation()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_093_High_Cholesterol_ADBU_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_093_High_Cholesterol_ADBU_Navigation";
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        user.conditionType=new ArrayList<>(Arrays.asList(DDIConditionType.HIGH_CHOLESTEROL.label));
        pageObj.ddiCondition.selectCheckboxesAndProgress(user.conditionType, pageObj.ddiHighCholesterol,report);
        report.addScreenshotStep("Step2_HighCholesterolMedsScreen");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.adbu, report);
        pageObj.adbu.verifyConditionIsListed(pageObj.adbu.highCholesterolCondition, report);
        report.addScreenshotStep("Step5_ADBUScreen");
    }
}
