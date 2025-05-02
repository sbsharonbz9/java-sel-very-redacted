package galen.tenant.dx.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.DDIConditionType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
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
    public void VTP_DEX_FRD_093_High_Cholesterol_ADBU_Navigation_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_093_High_Cholesterol_ADBU_Navigation";
        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        user.conditionType= pageObj.ddiCondition.getCondition(DDIConditionType.HIGH_CHOLESTEROL.label);
        pageObj.ddiCondition.selectCheckboxesAndProgress(user.conditionType, pageObj.ddiHighCholesterol,report);
        report.addScreenshotStep("Step2_HighCholesterolMedsScreen");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.adbu, report);
        pageObj.adbu.verifyConditionIsListed(pageObj.adbu.highCholesterolCondition, report);
        report.addScreenshotStep("Step5_ADBUScreen");
    }
}
