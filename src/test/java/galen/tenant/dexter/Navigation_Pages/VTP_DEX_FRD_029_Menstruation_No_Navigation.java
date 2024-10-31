package galen.tenant.dexter.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_029_Menstruation_No_Navigation extends BaseTest {
    static String OBJECTIVE = "On the Menstrual Periods screen if a user answers no, indicating they do not have " +
            "menstrual periods, the application shall navigate to the Hormonal Birth Control B screen.";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_029";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_029_Menstruation_No_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_029_Menstruation_No_Navigation()  {
        VERSIONHISTORY.add("1.0;03NOV2022;Initial Test Script;Name Redacted");
        VERSIONHISTORY.add("2.0;18JUN2024;Per CADENCE-567: Remove N/A from Expected Results column when using HappyFlow;" +
                "Name Redacted");
    }

    @Test
    public void VTP_DEX_FRD_029_Menstruation_No_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_029 – Menstruation No Navigates Hormonal BC screen";

        DexterUser user = new DexterUser();
        DexterPageObj pageObj = new DexterPageObj(driver);

        new PritUnlPage(driver).authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.menstrual, report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("No", pageObj.birthControlB, report);
        report.addScreenshotStep("Step2_Hormonal BC(B)");
    }
}
