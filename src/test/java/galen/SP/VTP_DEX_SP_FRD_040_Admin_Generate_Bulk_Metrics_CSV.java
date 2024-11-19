package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.AccountTabs;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.pages.sp.StudyAdminPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_040_Admin_Generate_Bulk_Metrics_CSV extends BaseTest {
    static String OBJECTIVE = "To verify the IE Admin portal Records screen shall provide the capability to generate and download initial health CSV files for: \n" +
            "•\tParticipant records \n" +
            "•\tClinician records \n" +
            "•\tBoth participant and clinician records";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tAdmin is able to generate Initial Health CSV - Participant Records\n" +
            "o\tAdmin is able to generate Initial Health CSV – Clinician Records\n" +
            "o\tAdmin is able to generate Initial Health CSV - Participant and Clinician Records";
    static String REQUIREMENTS = "DEX_SP_FRD_040";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_040_Admin_Generate_Bulk_Metrics_CSV";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_040_Admin_Generate_Bulk_Metrics_CSV()  {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 212;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_040_Admin_Generate_Bulk_Metrics_CSV_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_040 – Admin Generate Bulk Metrics CSV ";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);

        pageObj.login.logIn(RoleType.ADMIN.email, report);
        pageObj.participants.selectTabToPage(AccountTabs.RECORDS, pageObj.downloadRecords,report);
        pageObj.downloadRecords.downloadParticipantRecords("admin_participant", report);
        report.addScreenshotStep("Step4");

        pageObj.downloadRecords.downloadClinicianRecords("admin_clinician", report);
        report.addScreenshotStep("Step5");

        pageObj.downloadRecords.downloadAllRecords("admin_all", report);
        report.addScreenshotStep("Step6");
    }
}
