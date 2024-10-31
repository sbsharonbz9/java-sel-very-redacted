package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.GalenReport;
import galen.pages.sp.StudyAdminPageObj;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_SP_FRD_019_Session_Timeout extends BaseTest {
    static String OBJECTIVE = "To verify the portal shall log a user out and provide a notification message if the user " +
            "session exceeded the one hour session time limit.";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tAdmin role user is logged out when session expires after one hour\n" +
            "o\tDisplay of notification message for expired session ";
    static String REQUIREMENTS = "FRD_019";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_019_Session_Timeout";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_SP_FRD_019_Session_Timeout()  {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per CADENCE-191;Name Redacted");
    }

    @Test
    public void VTP_DEX_SP_FRD_019_Session_Timeout_Test() throws IOException, InterruptedException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_019 – Session Timeout (Token Expiration)";
        pageObj = new StudyAdminPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.ADMIN.email, report);
        report.addStep("Wait 61 minutes", "Wait 61 minutes", "61 minutes have passed", true);
        sleep(3600000);
        new BasicHelpers(driver).verifyDisplayedFlex(By.tagName("a"), "Session Timeout", report);
        pageObj.login.verifyAtPage(report);
        report.addScreenshotStep("Timeout");
    }
}
