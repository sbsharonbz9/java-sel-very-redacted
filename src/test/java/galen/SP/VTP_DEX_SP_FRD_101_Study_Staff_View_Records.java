package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.pages.sp.StudyAdminPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_101_Study_Staff_View_Records extends BaseTest {

    public static final String OBJECTIVE =
            "The participant records screen displayed for a Study Staff user shall display:\n"
                    + "• Participant ID\n"
                    + "• Participant Email Address\n"
                    + "• A Close Records button at the top of the screen";

    public static final String NOTES =
            "This protocol contains the following scenarios:\n"
                    + "View Records screen displays:\n"
                    + "• Participant ID\n"
                    + "• Participant Email Address\n"
                    + "• Close Records button";
    static String REQUIREMENTS = "DEX_SP_FRD_101";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_101_Study_Staff_View_Records";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_SP_FRD_101_Study_Staff_View_Records() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 173;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_101_Study_Staff_View_Records_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_101_Study_Staff_View_Records";
        pageObj = new StudyAdminPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.STUDY_STAFF.email, report);
        pageObj.participants.clickViewRecords(report);
        pageObj.viewRecords.verifyAtPage(report);
        pageObj.viewRecords.verifyAllElementsDisplayed(report);
        report.addScreenshotStep("View Records_STEP_3");
    }
}
