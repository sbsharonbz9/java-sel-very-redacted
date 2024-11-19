package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.GalenReport;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.sp.ViewRecords;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_049_CAssesor_Individual_Participant_Metrics extends BaseTest {

    public static final String OBJECTIVE = "To verify on the Central Assessor Participant Records, if the user selects " +
            "1 or more records in the “select” column and selects “Download CSV”, the application shall generate and " +
            "download a CSV file of the selected health survey records";
    public static final String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tCentral Assessor Role:\n" +
            "\uF0A7\tAbility to generate Individual Participant CSV Record\n" +
            "\uF0A7\tAbility to generate multiple Individual Participant CSV Records";

    static String REQUIREMENTS = "DEX_SP_FRD_049";
    static String REFERENCES = "N/A";
    String reportName = "VTP_DEX_SP_FRD_049_CAssesor_Individual_Participant_Metrics";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;

    VTP_DEX_SP_FRD_049_CAssesor_Individual_Participant_Metrics() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 222;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_049_CAssesor_Individual_Participant_Metrics_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_049 – Generate Individual Participant Metrics";
        bh = new BasicHelpers(driver);
        StudyAdminPageObj pageObj;
        pageObj = new StudyAdminPageObj(driver);
        ViewRecords view = pageObj.viewRecords;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.CENTRAL_ASSESSOR.email, report);
        pageObj.participants.viewRecordWithMultipleAssessments(report);

        view.downloadIndividualRecords("SStaff_View_Records_1", 0, report);
        report.addScreenshotStep("Step4_DownloadFirstRecord");

        view.getAllCheckboxes().get(0).click();

        view.downloadIndividualRecords("SStaff_View_Records_2", 1, report);
        report.addScreenshotStep("Step5_DownloadSecondRecord");
    }
}
