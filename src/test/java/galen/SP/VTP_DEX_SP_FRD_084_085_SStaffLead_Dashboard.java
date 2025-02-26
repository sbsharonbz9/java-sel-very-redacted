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

public class VTP_DEX_SP_FRD_084_085_SStaffLead_Dashboard extends BaseTest {
    public static final String OBJECTIVE = "DEX_SP_FRD_084: To verify the Study Staff Lead portal dashboard screen shall a menu to allow the authorized user to: \n" +
            "•\tView a list of participants \n" +
            "•\tView a list of accounts \n" +
            "•\tView metrics records \n" +
            "•\tView list of Study IDs \n" +
            "•\tView list of Site IDs \n" +
            "•\tReset password\n" +
            "DEX_SP_FRD_085: To verify the Study Staff Lead dashboard shall provide a list of study participants with the following columns: \n" +
            "-\tParticipant ID (sortable)\n" +
            "-\tEmail address (sortable)\n" +
            "-\tInitial outcome (sortable)\n" +
            "-\tHeath survey date (sortable)\n" +
            "-\tAction\n";

    public static final String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tVerify user can see all tabs\n" +
            "o\tVerify User can see list of participants (Participants Screen)\n" +
            "o\tVerify user can see list of all accounts (Accounts Screen)\n" +
            "o\tVerify user is able to see metrics records (Records Screen)\n" +
            "o\tVerify user is able to see list of Study IDs (Study ID Screen)\n" +
            "o\tVerify user is able to see list of Site IDs (Sites Screen)\n" +
            "o\tVerify user is able to see reset password fields (My Password Screen)\n" +
            "Network tab needs to be open during the running of the protocol.\n";

    static String REQUIREMENTS = "DEX_SP_FRD_084, DEX_SP_FRD_085";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_084_085_SStaffLead_Dashboard";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_084_085_SStaffLead_Dashboard() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 201;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_084_085_SStaffLead_Dashboard_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_084_085 – Study Staff Lead Dashboard View";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        commonPageFeatures = new CommonPageFeatures(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.STUDY_STAFF_LEAD.email, report);

        par.verifyTabsDisplayed( RoleType.STUDY_STAFF_LEAD.tabs, report);
        par.verifySS_CA_HeadersDisplayed(report);
        report.addScreenshotStep("Screen2_Participant_List_Screen");

        bh.verifyDisplayedFlex(par.participantIdHeader, "Participant ID Column Ascending", report);
        pageObj.participants.clickAndVerifyAscending("Participant ID", report);
        report.addScreenshotStep("Step3_Participant_ID_Ascending");

        bh.verifyDisplayedFlex(par.participantIdHeader, "Participant ID Column Descending", report);
        pageObj.participants.clickAndVerifyDescending("Participant ID", report);
        report.addScreenshotStep("Step4_Participant_ID_Descending");

        bh.verifyDisplayedFlex(par.participantEmailHeader, "Email Address Column Ascending", report);
        pageObj.participants.clickAndVerifyAscending("Email Address", report);
        report.addScreenshotStep("Step5_Participant_Email_Ascending");

        bh.verifyDisplayedFlex(par.participantEmailHeader, "Email Address Column Descending", report);
        pageObj.participants.clickAndVerifyDescending("Email Address", report);
        report.addScreenshotStep("Step6_Participant_Email_Descending");

        bh.verifyDisplayedFlex(par.participantInitialOutcomeHeader, "Initial Outcome Column Ascending", report);
        pageObj.participants.clickAndVerifyAscending("Initial Outcome", report);
        report.addScreenshotStep("Step7_Initial_Outcome_Ascending");

        bh.verifyDisplayedFlex(par.participantInitialOutcomeHeader, "Initial Outcome Column Descending", report);
        pageObj.participants.clickAndVerifyDescending("Initial Outcome", report);
        report.addScreenshotStep("Step8_Initial_Outcome_Descending");

        bh.verifyDisplayedFlex(par.participantSurveyDateHeader, "Health survey date Column Ascending", report);
        pageObj.participants.clickAndVerifyAscending("Health survey date", report);
        report.addScreenshotStep("Step9_Health_Survey_Date_Ascending");

        bh.verifyDisplayedFlex(par.participantSurveyDateHeader, "Health survey date Column Descending", report);
        pageObj.participants.clickAndVerifyDescending("Health survey date", report);
        report.addScreenshotStep("Step10_Health_Survey_Date_Descending");

        pageObj.participants.selectTabToPage(AccountTabs.PARTICIPANTS, pageObj.participants, report);
        report.addScreenshotStep("Step11_Participants_Screen");

        pageObj.participants.selectTabToPage(AccountTabs.RECORDS, pageObj.downloadRecords, report);
        report.addScreenshotStep("Step12_Records_Screen");

        pageObj.downloadRecords.selectTabToPage(AccountTabs.STUDY, pageObj.studies,report);
        report.addScreenshotStep("Step13_Study_Screen");

        pageObj.studies.selectTabToPage(AccountTabs.SITE, pageObj.sites,report);
        report.addScreenshotStep("Step14_Sites_Screen");

        par.clickCloseRecords(report);
        pageObj.myPassword.selectTabToPage(AccountTabs.MY_PASSWORD, pageObj.myPassword,report);
        report.addScreenshotStep("Step15_My_Password");
    }
}