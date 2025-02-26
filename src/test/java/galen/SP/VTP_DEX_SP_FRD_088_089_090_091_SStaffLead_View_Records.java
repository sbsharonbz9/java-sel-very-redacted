package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.GalenReport;
import galen.pages.sp.Participants;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.sp.ViewRecords;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_088_089_090_091_SStaffLead_View_Records extends BaseTest {

    public static final String OBJECTIVE = "DEX_SP_FRD_088: To verify if a  Study Staff Lead user selects the " +
            "“View Records” link in the Action column of the Participants screen for a participant, the portal shall " +
            "navigate to a screen displaying health survey records for that participant.\n" +
            "DEX_SP_FRD_089: To verify the Study Staff Lead Participant Records, view displays a list of all health " +
            "survey records for the selected participant and with the following columns: \n" +
            "•\tSelect (allows user to select which records to export to CSV) \n" +
            "•\tAssessment type (sortable) \n" +
            "•\tCompleted by (name or participant ID for the person who completed the health survey) \n" +
            "•\tCompleted date\n" +
            "DEX_SP_FRD_090: To verify on the Study Staff Lead Participant Records view, if the user selects 1 or more " +
            "records in the “select” column and selects “Download CSV”, the application shall generate and download a " +
            "CSV file of the selected health survey records \n" +
            "DEX_SP_FRD_091: To verify on the Study Staff Lead Participant Records screen, if the user selects the " +
            "“close records’ button, the application shall return to the Central Assessor dashboard screen.\n";

    public static final String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tView Records button navigates to View Records Screen with Health Records displayed\n" +
            "o\tThe Health Records table contains the following columns:\n" +
            "\uF0A7\tSelect \n" +
            "\uF0A7\tAssessment Type\n" +
            "\uF0A7\tCompleted By\n" +
            "\uF0A7\tCompleted Date\n" +
            "o\tAbility to generate Individual Participant CSV Record\n" +
            "o\tAbility to generate multiple Individual Participant CSV Records\n" +
            "o\tClose Records button navigates user back to Participants Screen";

    static String REQUIREMENTS = "DEX_SP_FRD_088, DEX_SP_FRD_089, DEX_SP_FRD_090, DEX_SP_FRD_091";
    static String REFERENCES = "N/A";
    String reportName = "VTP_DEX_SP_FRD_088_089_090_091_SStaffLead_View_Records";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;


    VTP_DEX_SP_FRD_088_089_090_091_SStaffLead_View_Records() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 260;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_088_089_090_091_SStaffLead_View_Records_Test() throws java.io.IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_088_089_090_091 – Study Staff Lead View Records";
        bh = new BasicHelpers(driver);
        StudyAdminPageObj pageObj;
        DexterPageObj dexPageObj;
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        ViewRecords view = pageObj.viewRecords;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.STUDY_STAFF_LEAD.email, report);
        par.viewRecordWithMultipleAssessments(report);
        view.verifyAllColumnsPresent(report);
        report.addScreenshotStep("Step3_ViewRecords_Columns");

        view.downloadIndividualRecords("SStaff_View_Records_1", 0, report);
        report.addScreenshotStep("Step4_DownloadFirstRecord");

        view.downloadIndividualRecords("SStaff_View_Records_2", 1, report);
        report.addScreenshotStep("Step5_DownloadSecondRecord");

        view.clickCloseRecordButton(report);
        par.verifyAtPage(report);
        report.addScreenshotStep("Step6_CloseRecord");
    }
}
