package galen.tenant.dexter.DNU;

import galen.base.BaseTest;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_030_HormonalBC_No_DNU extends BaseTest {
    static String OBJECTIVE = "If the user submits a denial response on the Hormonal Birth Control B Screen, the " +
            "application shall end the health survey and navigate to the DNU screen for non-menstruating users.";
    static String NOTES = "This protocol contains the following verification scenario(s):\n" +
            "-\tUser entering No on Hormonal BC (B) Screen on the initial run of the health survey triggers DNU Screen\n" +
            "-\tUser entering No on Hormonal BC (B) Screen by navigation of Editable Summary triggers DNU Screen";
    static String REQUIREMENTS = "DEX_FRD_030";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_030_HormonalBC_No_DNU";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_030_HormonalBC_No_DNU()  {
        VERSIONHISTORY.add("1.0;03NOV2022;Initial Test Script;James Reale;" +
                "2.0;18JUN2024;Per CADENCE-567: Remove N/A from Expected Results column when using HappyFlow;James Reale");
    }

    @Test
    public void VTP_DEX_FRD_030_HormonalBC_No_DNU_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_030 – Hormonal BC Usage No Navigates to DNU Screen";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.menstrual, report);
        pageObj.menstrual.clickYesNoNextToPage("No", pageObj.birthControlB, report);
        pageObj.birthControlB.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        report.addScreenshotStep("Step3_DNU");

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.review, report);
        pageObj.review.clickMenstrualEdit(report);
        pageObj.menstrual.clickYesNoNextToPage("No", pageObj.birthControlB, report);
        pageObj.birthControlB.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        report.addScreenshotStep("Step7_DNU");
    }
}
