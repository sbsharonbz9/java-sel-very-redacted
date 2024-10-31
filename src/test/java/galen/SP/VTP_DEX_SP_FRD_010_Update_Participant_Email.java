package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.sp.ParticipantClass;
import galen.pages.sp.Participants;
import galen.pages.sp.StudyAdminPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_SP_FRD_010_Update_Participant_Email extends BaseTest {
    static String OBJECTIVE = "To verify the portal shall provide the capability to update a participant’s email address.";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "-\tAdmin Role\n" +
            "o\tEdit existing participant’s email address (whom has not yet accessed invitation link and created an account)\n" +
            "-\tStudy Staff Lead\n" +
            "o\tEdit existing participant’s email address (whom has not yet accessed invitation link and created an account)\n" +
            "-\tStudy Staff: \n" +
            "o\tEdit existing participant’s email address (whom has not yet accessed invitation link and created an account)\n" +
            "Study Portal role users will be able to edit the email address of a participant who has not claimed the " +
            "invitation link and created an ac-count. \n" +
            "Study Portal role users will be unable to edit the email address of a participant who has already created " +
            "an account. ";
    static String REQUIREMENTS = "DEX_SP_FRD_010";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_010_Update_Participant_Email";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_010_Update_Participant_Email()  {
        VERSIONHISTORY.add("1.0;13OCT2022;Per CADENCE-173: Initial Test Script;James Reale");
    }

    @Test
    public void VTP_DEX_SP_FRD_010_Update_Participant_Email_Test() throws IOException, InterruptedException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_010 – Update Participant Email";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);

        ArrayList<RoleType> roles = new ArrayList<>(Arrays.asList(RoleType.ADMIN, RoleType.STUDY_STAFF_LEAD,
                RoleType.STUDY_STAFF));

        for (RoleType r : roles) {
            pageObj.login.logIn(r.email, report);
            sleep(1000);
            par.verifyAtPage(report);
            par.openAddParticipant(report);
            ParticipantClass parUser = new ParticipantClass(bh.getUniqueEmail(), par.getEnabledSiteID(),
                    par.getEnabledStudyID());
            par.addParticipant(parUser.email, parUser.studyID, parUser.siteID, report);
            par.clickEditParticipant(parUser.email, report);
            parUser.email = bh.getUniqueEmail();
            par.editParticipant(parUser, report);
            par.findEmailInTable(parUser.email, report);
            report.addScreenshotStep("New email " + parUser.email + " in table");

            bh.clickFlex(pageObj.login.getLogout(), "Log Out", report);
        }

    }
}
