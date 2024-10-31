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

import static java.lang.Thread.sleep;

public class VTP_DEX_SP_FRD_014_Generate_Bulk_Metrics_CSV extends BaseTest {
    static String OBJECTIVE = "To verify the portal shall provide the capability to create the following type of " +
            "CSV records: \n" +
            "-\tParticipant Initial health survey \n" +
            "-\tClinician Initial health survey";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tAdmin Role:\n" +
            "\uF0A7\tAbility to generate Participant CSV Records\n" +
            "\uF0A7\tAbility to generate Clinician CSV Records\n" +
            "o\tCentral Assessor\n" +
            "\uF0A7\tAbility to generate Participant CSV Records\n" +
            "\uF0A7\tAbility to generate Clinician CSV Records\n" +
            "o\tStudy Staff Lead\n" +
            "\uF0A7\tAbility to generate  Participant CSV Records";
    static String REQUIREMENTS = "DEX_SP_FRD_014";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_014_Generate_Bulk_Metrics_CSV";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_014_Generate_Bulk_Metrics_CSV()  {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per CADENCE-185;James Reale");
    }

    @Test
    public void VTP_DEX_SP_FRD_014_Generate_Bulk_Metrics_CSV_Test() throws IOException, InterruptedException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_014 – Generate Bulk Metrics CSV ";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);

        pageObj.login.logIn(RoleType.ADMIN.email, report);
        sleep(1000);
        par.verifyAtPage(report);
        par.selectTab(AccountTabs.RECORDS, report);
        pageObj.downloadRecords.downloadParticipantRecords("admin_participant", report);
        report.addScreenshotStep("Step4");
        pageObj.downloadRecords.downloadClinicianRecords("admin_clinician", report);
        report.addScreenshotStep("Step5");
        pageObj.downloadRecords.downloadAllRecords("admin_all", report);
        report.addScreenshotStep("Step6");
        bh.clickFlex(pageObj.login.getLogout(), "Log Out", report);

        pageObj.login.logIn(RoleType.CENTRAL_ASSESSOR.email, report);
        sleep(1000);
        par.verifyAtPage(report);
        par.selectTab(AccountTabs.RECORDS, report);
        pageObj.downloadRecords.downloadParticipantRecords("ca_participant", report);
        report.addScreenshotStep("Step10");
        pageObj.downloadRecords.downloadClinicianRecords("ca_clinician", report);
        report.addScreenshotStep("Step11");
        pageObj.downloadRecords.downloadAllRecords("ca_all", report);
        report.addScreenshotStep("Step12");
        bh.clickFlex(pageObj.login.getLogout(), "Log Out", report);

        pageObj.login.logIn(RoleType.STUDY_STAFF_LEAD.email, report);
        sleep(1000);
        par.verifyAtPage(report);
        par.selectTab(AccountTabs.RECORDS, report);
        pageObj.downloadRecords.downloadParticipantRecords("ss_lead_participant", report);
        report.addScreenshotStep("Step16");
    }
}
