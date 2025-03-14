package galen.tenant.dx.DDIConditions;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.CancerType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.CancerList;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_241_List_Of_Cancers_Screen_Control extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_241_List_Of_Cancers_Screen_Control";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_241_List_Of_Cancers_Screen_Control() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_241_List_Of_Cancers_Screen_Control_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_241 – List of Cancers Screen Control";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        CancerList cancers = pageObj.cancerList;

        pageObj.pritUnl.authenticateUserIfRequired(URLType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.everHadCancer, report);
        pageObj.everHadCancer.clickYesNoNextToPage("Yes", cancers, report);
        cancers.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step2_Next_Button_Disabled");

        String breastCancer = CancerType.Breast_Cancer.label;
        cancers.selectCheckboxReponse(breastCancer, report);
        cancers.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step3_Next_Button_Enabled");

        cancers.selectCheckboxReponse(breastCancer, null);
        ArrayList<String> allOptions = cancers.cancerOptions;
        cancers.selectCheckboxReponses(allOptions, report);
        cancers.verifyCheckboxesSelected(allOptions, report);
        cancers.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step4_Next_Button_Enabled");

        cancers.selectCheckboxReponses(allOptions, report);
        cancers.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step5_Next_Button_Disabled");
    }
}

