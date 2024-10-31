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
    static String OBJECTIVE = "If the user selects the “Get Started” link, the application shall navigate to" +
            "the Privacy screen if the user provided a token.";
    static String NOTES = "This protocol contains the following verification scenarios: \n" +
            "Clicking Get Started with Deactivated user account is navigated to the Deactivated Account Screen \n" +
            "Clicking Get Started with Active User is navigated to the Privacy Notice Screen ";
    static String REQUIREMENTS = "DEX_FRD_022";
    static String REFERENCES = "N/A";
    String reportName = "VTP_DEX_FRD_022_Participant_Token_Admittance";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public CommonPageFeatures commonPageFeatures;
    public BasicHelpers bh;

    VTP_DEX_FRD_022_Participant_Token_Admittance()  {
        VERSIONHISTORY.add("1.0;07MAY2023;Initial Test Script;Name Redacted");
        VERSIONHISTORY.add("2.0;02NOV2022;Per CADENCE-603: Update test Steps to reflect the deactivated user experience ;Name Redacted");
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
        driver.navigate().to("https://cadence.qa.codescripts.com/?invite=7GZ6F66T_fKn1bhuLq5Ei_LT8NQ");
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
