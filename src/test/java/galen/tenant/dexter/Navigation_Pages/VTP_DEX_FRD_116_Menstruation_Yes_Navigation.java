package galen.tenant.dexter.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_116_Menstruation_Yes_Navigation extends BaseTest {


    static String OBJECTIVE = "Verify on the Menstruation screen, if a user answers yes indicating they do have " +
            "menstrual periods, the application shall navigate to the Hormonal Birth Control A screen. ";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_116";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_116_Menstruation_Yes_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_116_Menstruation_Yes_Navigation() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_116_Menstruation_Yes_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_116_Menstruation_Yes_Navigation";
        DexterUser user = new DexterUser();
        DexterPageObj pageObj = new DexterPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.menstrual, report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("Yes", pageObj.menstrual, report);
        pageObj.birthControl.verifyAtPage(report);
        report.addScreenshotStep("Step2_BC(A)_Screen");



    }
}
