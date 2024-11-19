package galen.SP;

import galen.base.BaseTest;
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

public class VTP_DEX_SP_FRD_044_045_CAssessor_Participant_Table extends BaseTest {

    public static final String OBJECTIVE =
            "DEX_SP_FRD_044: To verify the Central Assessor portal dashboard screen shall provide a list of study participants with the following columns:\n" +
                    "•\tParticipant ID\n" +
                    "•\tEmail address\n" +
                    "•\tInitial outcome\n" +
                    "•\tHealth survey date\n" +
                    "•\tAction\n" +
                    "DEX_SP_FRD_045: To verify the Central Assessor portal Participant screen shall be sortable by: \n" +
                    "•\tParticipant ID\n" +
                    "•\tEmail address\n" +
                    "•\tInitial outcome\n" +
                    "•\tHealth survey date\n";

    public static final String NOTES =
            "This protocol contains the following scenarios:\n" +
                    "-\tCentral Assessor user has the following columns displayed in the Participant Table\n" +
                    "o\tParticipant ID\n" +
                    "o\tEmail address\n" +
                    "o\tInitial outcome\n" +
                    "o\tHealth survey date\n" +
                    "o\tAction\n" +
                    "-\tCentral Assessor user has ability to sort Participant ID column (Ascending (by default) and Descending)\n" +
                    "-\tCentral Assessor user has ability to sort Email Address column (Ascending and Descending)\n" +
                    "-\tCentral Assessor user has ability to sort Initial outcome column (Ascending (by default) and Descending)\n" +
                    "-\tCentral Assessor user has ability to sort Health survey date column (Ascending and Descending)\n";

    static String REQUIREMENTS = "DEX_SP_FRD_044, DEX_SP_FRD_045";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_044_045_CAssessor_Participant_Table";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_044_045_CAssessor_Participant_Table() {
        VERSIONHISTORY.add("1.0;28FEB2023;Per 215: Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_044_045_CAssessor_Participant_Table_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_044_045 – Central Assessor Participant Table";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.CENTRAL_ASSESSOR.email, report);
        par.verifySS_CA_HeadersDisplayed(report);
        report.addScreenshotStep("Step2_Participants_Screen");

        bh.verifyDisplayedFlex(par.participantIdHeader, "Participant ID Column Ascending", report);
        pageObj.participants.clickAndVerifyAscending("Participant ID", report);
        report.addScreenshotStep("Participant_ID_Ascending");

        bh.verifyDisplayedFlex(par.participantIdHeader, "Participant ID Column Descending", report);
        pageObj.participants.clickAndVerifyDescending("Participant ID", report);
        report.addScreenshotStep("Participant_ID_Descending");

        bh.verifyDisplayedFlex(par.participantEmailHeader, "Email Address Column Ascending", report);
        pageObj.participants.clickAndVerifyAscending("Email Address", report);
        report.addScreenshotStep("Participant_Email_Ascending");

        bh.verifyDisplayedFlex(par.participantEmailHeader, "Email Address Column Descending", report);
        pageObj.participants.clickAndVerifyDescending("Email Address", report);
        report.addScreenshotStep("Participant_Email_Descending");

        bh.verifyDisplayedFlex(par.participantInitialOutcomeHeader, "Initial Outcome Column Ascending", report);
        pageObj.participants.clickAndVerifyAscending("Initial Outcome", report);
        report.addScreenshotStep("Initial_Outcome_Ascending");

        bh.verifyDisplayedFlex(par.participantInitialOutcomeHeader, "Initial Outcome Column Descending", report);
        pageObj.participants.clickAndVerifyDescending("Initial Outcome", report);
        report.addScreenshotStep("Initial_Outcome_Descending");

        bh.verifyDisplayedFlex(par.participantSurveyDateHeader, "Health survey date Column Ascending", report);
        pageObj.participants.clickAndVerifyAscending("Health survey date", report);
        report.addScreenshotStep("Health_Survey_Date_Ascending");

        bh.verifyDisplayedFlex(par.participantSurveyDateHeader, "Health survey date Column Descending", report);
        pageObj.participants.clickAndVerifyDescending("Health survey date", report);
        report.addScreenshotStep("Health_Survey_Date_Descending");
    }
}

