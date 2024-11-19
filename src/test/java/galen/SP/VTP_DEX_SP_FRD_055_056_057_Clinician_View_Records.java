package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.pages.sp.Participants;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.sp.ViewRecords;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_055_056_057_Clinician_View_Records extends BaseTest {

    public static final String OBJECTIVE =
            "DEX_SP_FRD_055: To verify on the Clinician portal dashboard, if a Clinician user selects the “View Records”" +
                    " link in the Action column of the Participants screen for a participant, the portal shall navigate " +
                    "to a screen displaying clinician submitted health survey records for that participant. \n" +
                    "DEX_SP_FRD_056: To verify the participant records screen displayed for a Clinician user shall " +
                    "display: \n" +
                    "Participant ID \n" +
                    "Participant Email Address \n" +
                    "A Close Records button at the top of the screen \n" +
                    "An Add Record button \n" +
                    "DEX_SP_FRD_057: To verify the records section of the participant records screen shall contain the " +
                    "following columns: \n" +
                    "Assessment Type (sortable) \n" +
                    "Completed by (sortable) \n" +
                    "Completed date (sortable)";

    public static final String NOTES =
            "This protocol contains the following scenarios: \n" +
                    "View Records button navigates to View Records Screen with Health Records displayed \n" +
                    "View Records Screen displays the following: \n" +
                    "Participant ID \n" +
                    "Participant Email Address \n" +
                    "A Close Records button at the top of the screen \n" +
                    "An Add Record button \n" +
                    "The Health Records table contains the following columns: \n" +
                    "Assessment Type \n" +
                    "Completed By \n" +
                    "Completed Date";
    static String REQUIREMENTS = "DEX_SP_FRD_055, DEX_SP_FRD_056, DEX_SP_FRD_057 ";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_055_056_057_Clinician_View_Records";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_055_056_057_Clinician_View_Records() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 173;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_055_056_057_Clinician_View_Records_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_055_056_057_Clinician_View_Records";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        ViewRecords view = pageObj.viewRecords;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.CLINICIAN.email, report);
        par.clickViewRecords(report);
        view.verifyAllColumnsPresent(report);
        view.verifyAllElementsDisplayed(report);
        report.addScreenshotStep("Step3_All_Columns");

        bh.verifyDisplayedFlex(view.assessmentTypeHeader, "Assessment Type  Column Ascending", report);
        view.clickAndVerifyAscending("Assessment Type", report);
        report.addScreenshotStep("Step4_Ascending Assessment Type");

        bh.verifyDisplayedFlex(view.assessmentTypeHeader, "Assessment Type  Column Descending", report);
        view.clickAndVerifyDescending("Assessment Type", report);
        report.addScreenshotStep("Step5_Descending Assessment Type");

        bh.verifyDisplayedFlex(view.completedByHeader, "Completed By  Column Ascending", report);
        view.clickAndVerifyAscending("Completed By", report);
        report.addScreenshotStep("Step6_Ascending Completed By");

        bh.verifyDisplayedFlex(view.completedByHeader, "Completed By  Column Descending", report);
        view.clickAndVerifyDescending("Completed By", report);
        report.addScreenshotStep("Step7_Descending Completed By");

        bh.verifyDisplayedFlex(view.completedDateHeader, "Completed Date Column Ascending", report);
        view.clickAndVerifyAscending("Completed Date", report);
        report.addScreenshotStep("Step8_Ascending Completed Date");

        bh.verifyDisplayedFlex(view.completedDateHeader, "Completed Date Column Descending", report);
        view.clickAndVerifyDescending("Completed Date", report);
        report.addScreenshotStep("Step9_Descending Completed Date");

        view.clickCloseRecordButton(report);
        par.verifyAtPage(report);
        report.addScreenshotStep("Step10_Participant_Screen");
    }
}
