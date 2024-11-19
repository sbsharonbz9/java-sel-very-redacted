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

public class VTP_DEX_FRD_028_Confirm_Customer_Yes_Navigation extends BaseTest {
    static String OBJECTIVE = "On the Confirm Customer screen, if a user indicates they are ordering for themselves, " +
            "the application shall navigate to the Prevent Pregnancy screen";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_028";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_028_Confirm_Customer_Yes_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_028_Confirm_Customer_Yes_Navigation()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_028_Confirm_Customer_Yes_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_028 – Confirm Customer Yes Navigation";

        DexterUser user = new DexterUser();
        DexterPageObj pageObj = new DexterPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.orderForSelf, report);
        pageObj.orderForSelf.clickCloseToDismiss(report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("Yes", pageObj.pregnancy, report);
        report.addScreenshotStep("Step2_PreventPregnancy");
    }
}
