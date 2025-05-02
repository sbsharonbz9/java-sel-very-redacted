package galen.tenant.dx.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_026_Know_Numbers_Navigation extends BaseTest {
    static String OBJECTIVE = "The Know Numbers Screen shall provide a control that allows the user to navigate to the " +
            "Log In Selection Screen, if the user is not a clinician";
    static String NOTES = "This protocol contains the following verification scenario(s):\n" +
            "-\tApp User from the Know Numbers screen is navigated to the Log In Selection Screen";
    static String REQUIREMENTS = "DEX_FRD_026";
    static String REFERENCES = "N/A";
    String reportName = "VTP_DEX_FRD_026_Know_Numbers_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_026_Know_Numbers_Navigation()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_026_Know_Numbers_Navigation_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_026 – Know Numbers Next Navigation";

        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        pageObj.welcomePage.clickBegin(report);
        pageObj.privacyPage.clickIAcceptBtnToPage(pageObj.numbers, report);
        new CommonPageFeatures(driver).clickNextToPage(pageObj.oAuth, report);
        report.addScreenshotStep("Step4_LoginSelectionScreen");
    }
}
