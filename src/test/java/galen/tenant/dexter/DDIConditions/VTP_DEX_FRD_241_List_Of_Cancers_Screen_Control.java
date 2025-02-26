package galen.tenant.dexter.DDIConditions;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.CancerType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.tenant.dexter.InitialAssessment.CancerList;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
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

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        CancerList cancers = pageObj.cancerList;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.everHadCancer, report);
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

