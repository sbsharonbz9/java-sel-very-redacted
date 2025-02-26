package galen.tenant.dexter.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_066_Blood_Clots_No_Irregular_Heartbeat_Problems extends BaseTest {
    static String OBJECTIVE = "Verify If the user provides a denial response on the Blood Clot Screen, the application " +
            "shall navigate to the irregular heart-beat/heart valve problems Screen.";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_066";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_066_Blood_Clots_No_Irregular_Heartbeat_Problems";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_066_Blood_Clots_No_Irregular_Heartbeat_Problems()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_066_Blood_Clots_No_Irregular_Heartbeat_Problems_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_066 – Blood Clots No Navigates to Irregular Heartbeat or Heart valve Problems " +
                "Screen";

        DexterUser user = new DexterUser();
        DexterPageObj pageObj = new DexterPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.bloodClot, report);
        pageObj.bloodClot.selectCheckboxesAndProgress(user.bloodClot, pageObj.irregularHeartBeat, report);
        report.addScreenshotStep("Step2_Irregular Heartbeat");
    }
}
