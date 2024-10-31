package galen.tenant.dexter.MoreInfo;

import galen.base.BaseTest;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_110_111_Clinically_Diagnosed_More_Info extends BaseTest {


    static String OBJECTIVE = "DEX_FRD_110: To verify the Clinical Depression Screen shall provide a link that navigates to the Clinically Diagnosed More Info Modal. \n" +
            "\n" +
            "DEX_FRD_111: To verify the Clinical Depression Modal shall display additional scrollable information regarding being clinically diagnosed with depression. ";
    static String NOTES = "This protocol contains the following verification scenario(s): \n" +
            "\n" +
            "More info link is displayed on the Clinical Depression Screen \n" +
            "\n" +
            "Clicking More info link on Clinical Depression Screen navigates to the More Info Modal \n" +
            "\n" +
            "Clinical Depression More Info Modal displays additional information regarding clinical diagnosis of depression ";

    static String REQUIREMENTS = "DEX_FRD_110_111";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_110_111_Clinically_Diagnosed_More_Info";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_110_111_Clinically_Diagnosed_More_Info() {
        VERSIONHISTORY.add("1.0;11NOV2022;Initial Test Script ;Suresh Sunderraj");
        VERSIONHISTORY.add("2.0;20JUN2024;Per CADENCE-478: Updated Test Steps for FDA changes \n"+
                "Per CADENCE-529: Removed N/A from Actual Result column for Happy flow execution related steps \n" +
                "Per CADENCE-591: Update Test Steps for modified assessment and navigation ;James Reale");
    }

    @Test
    public void VTP_DEX_FRD_110_111_Clinically_Diagnosed_More_Info_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_110_111_Clinically_Diagnosed_More_Info";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.depression, report);

        pageObj.depression.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression, report);
        report.addScreenshotStep("Step2_Clinical_Depression_Screen");
        common.clickMoreInfo(report);
        common.verifyMoreInfoDisplayed(report);
        report.addScreenshotStep("Step3_MoreInfoScreen");

    }
}
