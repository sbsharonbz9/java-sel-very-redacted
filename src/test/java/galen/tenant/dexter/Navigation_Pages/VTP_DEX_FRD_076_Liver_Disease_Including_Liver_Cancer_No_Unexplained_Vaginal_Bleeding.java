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

public class VTP_DEX_FRD_076_Liver_Disease_Including_Liver_Cancer_No_Unexplained_Vaginal_Bleeding extends BaseTest {
    static String OBJECTIVE = "To verify if the user selects ‘None of these’ on the Liver Disease Including Liver Cancer" +
            " Screen, the application shall navigate to the Unexplained Vaginal Bleeding Screen.";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_076";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_076_Liver_Disease_Including_Liver_Cancer_No_Unexplained_Vaginal_Bleeding";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_076_Liver_Disease_Including_Liver_Cancer_No_Unexplained_Vaginal_Bleeding()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_076_Liver_Disease_Including_Liver_Cancer_No_Unexplained_Vaginal_Bleeding_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_076 – Liver Disease Including Liver Cancer No Navigates to Unexplained Vaginal Bleeding";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.liverCancer, report);
        pageObj.liverCancer.selectCheckboxesAndProgress(user.liverCancer, pageObj.vaginalBleeding, report);
        report.addScreenshotStep("Step2_VaginalBleeding");
    }
}
