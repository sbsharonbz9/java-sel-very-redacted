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

public class VTP_DEX_FRD_068_Irregular_Heartbeat_No_Navigation extends BaseTest {
    static String OBJECTIVE = "To verify on the irregular heartbeat/heart valve problems screen, if the user answers no " +
            "indicating they do not currently or have not previously had irregular heartbeat/heart valve problems, the " +
            "application shall navigate to the Liver Disease incl. Liver Cancer screen.";
    static String NOTES = "The following verification scenario(s) are contained in this protocol:\n" +
            "-Answering No on the Irregular Heartbeat Screen navigates to the Liver Disease Screen\n";
    static String REQUIREMENTS = "DEX_FRD_068";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_068_Irregular_Heartbeat_No_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_068_Irregular_Heartbeat_No_Navigation()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_068_Irregular_Heartbeat_No_Navigation_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_068 – Irregular Heartbeat No Navigation";

        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.irregularHeartBeat, report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("No", pageObj.liverCancer, report);
        report.addScreenshotStep("Step2_Liver Cancer");
    }
}
