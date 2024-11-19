package galen.tenant.dexter.Misc;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.WelcomePage;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_FRD_022_Participant_Token_Admittance extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_022_Participant_Token_Admittance";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public CommonPageFeatures commonPageFeatures;
    public BasicHelpers bh;

    VTP_DEX_FRD_022_Participant_Token_Admittance()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
     }

    @Test
    public void VTP_DEX_FRD_022_Participant_Token_Admittance_Test() throws IOException, InterruptedException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_022_Participant_Token_Admittance";
        WelcomePage welcomePage = new WelcomePage(driver);
        bh = new BasicHelpers(driver);
        new PritUnlPage(driver).authenticateUserIfRequired();
        commonPageFeatures = new CommonPageFeatures(driver);

        sleep(1000);
        driver.navigate().to("https://url/?invite=<expired token>");
        sleep(1000);
        report.addStep("Open browser with a known expired token", "Browser opens to Welcome page",
                "At Welcome page", welcomePage.verifyAtPage());

        bh.clickFlex(welcomePage.btnBeginDex, "Begin", report);
        welcomePage.verifyAtPage(report);
        report.addScreenshotStep("Step1_Welcome Page");

        welcomePage.load(UrlType.DEXTER);
        report.addStep("Open browser with valid token", "Browser opens to Welcome page",
                "At Welcome page", welcomePage.verifyAtPage());
        welcomePage.clickBegin(report);
        report.addScreenshotStep("Step2_Privacy Screen");
    }
}
