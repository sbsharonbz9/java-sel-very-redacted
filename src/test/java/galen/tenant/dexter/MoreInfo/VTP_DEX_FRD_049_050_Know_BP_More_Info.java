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

public class VTP_DEX_FRD_049_050_Know_BP_More_Info extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_049: To verify On the Enter BP Numbers Screen, the application shall provide a " +
            "link to the blood pressure more in-formation modal.\n" +
            "DEX_FRD_050: To verify On the Enter BP Numbers Screen, if the user selects the more information link" +
            " the application shall navigate to the blood pressure more information modal.\n";
    static String NOTES = "The following verification scenario(s) are contained in this protocol:\n" +
            "-\tMore Info link is displayed on the Enter BP Screen\n" +
            "-\tClicking More Info link on the Enter BP Screen navigates to the More Info Modal";
    static String REQUIREMENTS = "DEX_FRD_049, DEX_FRD_050";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_049_050_Know_BP_More_Info ";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_049_050_Know_BP_More_Info()  {
        VERSIONHISTORY.add("1.0;13OCT2022;Initial Test Script;James Reale;" +
                "2.0;18JUN2024;Per CADENCE-567: Remove N/A from Expected Results column when using HappyFlow \n" +
                "Per CADENCE-591: Update Test Steps for modified assessment and navigation;Tural Aslanov");
    }

    @Test
    public void VTP_DEX_FRD_049_050_Know_BP_More_Info_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_049_050 – Know BP Numbers More Info";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.enterBP, report);
        common.verifyMoreInfoLinkDisplayed(report);
        report.addScreenshotStep("Step2_MoreInfoLink");
        common.clickMoreInfo(report);
        common.verifyMoreInfoDisplayed(report);
        report.addScreenshotStep("Step3_MoreInfoScreen");
    }
}
