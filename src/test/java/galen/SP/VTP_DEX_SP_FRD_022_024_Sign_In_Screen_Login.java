package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.pages.sp.Login;
import galen.pages.sp.StudyAdminPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_022_024_Sign_In_Screen_Login extends BaseTest {
    static String OBJECTIVE = "DEX_SP_FRD_022: To verify the portal shall include a sign in page.\n" +
            "DEX_SP_FRD_024: To verify the portal shall require the user to log in and authenticate with a user ID and " +
            "Password.";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "o;Study Portal Sign In Screen is displayed\n" +
            "o;Valid Email (User ID) and Password are required";
    static String REQUIREMENTS = "DEX_SP_FRD_022, DEX_SP_FRD_024";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_022_024_Sign_In_Screen_Login";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_022_024_Sign_In_Screen_Login()  {
        VERSIONHISTORY.add("1.0;28FEB2023;Per 193: Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_022_024_Sign_In_Screen_Login_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_022_024_Sign_In_Screen_Login";
        bh = new BasicHelpers(driver);
        commonPageFeatures = new CommonPageFeatures(driver);
        pageObj = new StudyAdminPageObj(driver);
        Login log = pageObj.login;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        log.verifyAtPage(report);
        bh.verifyButtonEnabled(log.submitButton, false, report);
        report.addScreenshotStep("Step1_LoginInitial");

        bh.sendTextFlex(log.emailTextField, RoleType.ADMIN.email, "Email text field", report);
        bh.verifyButtonEnabled(log.submitButton, false, report);
        report.addScreenshotStep("Step2_PasswordOnly");

        bh.clearTextField(log.emailTextField, report);
        log.enterPassword(report);
        bh.verifyButtonEnabled(log.submitButton, false, report);
        report.addScreenshotStep("Step3_PasswordOnly");

        bh.sendTextFlex(log.emailTextField, RoleType.ADMIN.email, "Email text field", report);
        bh.verifyButtonEnabled(log.submitButton, true, report);
        report.addScreenshotStep("Step4_SubmitEnabled");

        bh.verifyClickToPageTransition(pageObj.participants, log.submitButton,"Submit button", report);
        report.addScreenshotStep("Step5_AfterLogin");
    }
}
