package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.AccountTabs;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.pages.sp.Participants;
import galen.pages.sp.StudyAdminPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_051_CAssessor_Generate_Bulk_Metrics_CSV extends BaseTest {

    static String OBJECTIVE = "To verify on the Central Assessor dashboard, if the user selects the 'Records', "
            + "the portal shall provide the capability to generate and download initial health CSV files for: \n"
            + "Participant records \n"
            + "Clinician records \n"
            + "Both Participant and Clinician records";

    static String NOTES = "This protocol contains the following scenarios: \n"
            + "Central Assessor is able to generate Initial Health CSV - Participant Records \n"
            + "Central Assessor is able to generate Initial Health CSV – Clinician Records \n"
            + "Central Assessor is able to generate Initial Health CSV - Participant and Clinician Records";

    static String REQUIREMENTS = "DEX_SP_FRD_051";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_051_CAssessor_Generate_Bulk_Metrics_CSV";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_051_CAssessor_Generate_Bulk_Metrics_CSV()  {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 212;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_051_CAssessor_Generate_Bulk_Metrics_CSV_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_051_CAssessor_Generate_Bulk_Metrics_CSV";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);

        pageObj.login.logIn(RoleType.CENTRAL_ASSESSOR.email, report);
        par.selectTabToPage(AccountTabs.RECORDS, pageObj.downloadRecords,report);
        pageObj.downloadRecords.downloadParticipantRecords("Central_participant", report);
        report.addScreenshotStep("Step4");

        pageObj.downloadRecords.downloadClinicianRecords("Central_clinician", report);
        report.addScreenshotStep("Step5");

        pageObj.downloadRecords.downloadAllRecords("Central_all", report);
        report.addScreenshotStep("Step6");
    }
}
