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

public class VTP_DEX_SP_FRD_079_SStaff_Participant_Table extends BaseTest {
    public static final String OBJECTIVE =
            "To verify the Study Staff portal dashboard screen shall provide a list of study participants with the following columns:\n" +
                    "- Participant ID (sortable)\n" +
                    "- Email address (sortable)\n" +
                    "- Initial outcome (sortable)\n" +
                    "- Health survey date (sortable)\n" +
                    "- Action (contains View Records and Edit options)";

    public static final String NOTES =
            "This protocol contains the following scenarios:\n" +
                    "- Study Staff user has the following columns displayed in the Participant Table:\n" +
                    "  * Participant ID (sortable)\n" +
                    "  * Email address (sortable)\n" +
                    "  * Initial outcome (sortable)\n" +
                    "  * Health survey date (sortable)\n" +
                    "  * Action (contains View Records and Edit options)\n" +
                    "- Study Staff user has ability to sort Participant ID column (Ascending (by default) and Descending)\n" +
                    "- Study Staff user has ability to sort Email Address column (Ascending and Descending)\n" +
                    "- Study Staff user has ability to sort Initial Outcome column (Ascending and Descending)\n" +
                    "- Study Staff user has ability to sort Health Survey";


    static String REQUIREMENTS = "VTP_DEX_SP_FRD_079_SStaff_Participant_Table";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_079_SStaff_Participant_Table";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_079_SStaff_Participant_Table() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 173;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_079_SStaff_Participant_Table_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_079_SStaff_Participant_Table";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        commonPageFeatures = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.STUDY_STAFF.email, report);
        par.verifySS_CA_HeadersDisplayed(report);
        report.addScreenshotStep("Participant Table");

        par.clickEditPart(report);
        bh.verifyDisplayedFlex(par.saveChangesButton, "Edit Participant Modal", report);
        report.addScreenshotStep("Edit Participant Modal");

        par.clickCloseButton(report);
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
