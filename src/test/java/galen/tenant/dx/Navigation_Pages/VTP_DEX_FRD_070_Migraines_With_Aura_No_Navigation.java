package galen.tenant.dx.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_070_Migraines_With_Aura_No_Navigation extends BaseTest {
    static String OBJECTIVE = "Verify On the Migraines with Aura screen, if the user answers no indicating they do not " +
            "currently or have not previously had migraines with aura, the application shall navigate to the Obesity " +
            "screen.";
    static String NOTES = "The following verification scenario(s) are contained in this protocol:\n" +
            "o\t Submission of No on the Migraines Screen navigates to the Obesity/BMI Screen";
    static String REQUIREMENTS = "DEX_FRD_070";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_070_Migraines_With_Aura_No_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_070_Migraines_With_Aura_No_Navigation()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_070_Migraines_With_Aura_No_Navigation_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_070 – Migraines with Aura No Navigation";

        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.migraines, report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("No", pageObj.obesity, report);
        report.addScreenshotStep("Step2_Obesity_BMI_Screen");
    }
}
