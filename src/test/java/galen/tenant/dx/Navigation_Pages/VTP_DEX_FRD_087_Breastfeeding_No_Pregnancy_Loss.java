package galen.tenant.dx.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_087_Breastfeeding_No_Pregnancy_Loss extends BaseTest {
    static String OBJECTIVE = "Verify on the Breastfeeding screen, if the user answers no indicating they are not " +
            "currently breastfeeding, the application shall navigate to the Pregnancy loss screen.";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_087";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_087_Breastfeeding_No_Pregnancy_Loss";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_087_Breastfeeding_No_Pregnancy_Loss()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_087_Breastfeeding_No_Pregnancy_Loss_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_087 – Breastfeeding No Navigates to Pregnancy Loss screen";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.breastFeeding, report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("No", pageObj.pregnancyLoss, report);
        report.addScreenshotStep("Step2_PregnancyLoss_Screen");
    }
}
