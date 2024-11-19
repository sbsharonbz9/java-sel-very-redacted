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

public class VTP_DEX_FRD_078_Unexplained_Vaginal_Bleeding_No_Diabetes extends BaseTest {
    static String OBJECTIVE = "To verify on the Unexplained Vaginal Bleeding screen, if the user answers " +
            "no indicating they do not currently or have not previously had unexplained vaginal bleeding," +
            " the application shall navigate to the Diabetes screen";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_078";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_078_Unexplained_Vaginal_Bleeding_No_Diabetes";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_078_Unexplained_Vaginal_Bleeding_No_Diabetes()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_078_Unexplained_Vaginal_Bleeding_No_Diabetes_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_078 – Unexplained Vaginal Bleeding No Navigates to Diabe-tes screen";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.vaginalBleeding, report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("No", pageObj.diabetes, report);
        report.addScreenshotStep("Step2_Diabetes_Screen");
    }
}
