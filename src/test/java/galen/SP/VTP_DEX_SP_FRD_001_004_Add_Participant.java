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
import java.util.Arrays;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_001_004_Add_Participant extends BaseTest {
    static String OBJECTIVE = "DEX_SP_FRD_001: To verify the portal shall provide the capability for authorized users to" +
            " add study participants.\n" +
            "DEX_SP_FRD_004: To verify the portal shall require the following information to be input for each study " +
            "participant:  \n" +
            "-\tUser email address\n";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tAdmin Role\n" +
            "\uF0A7\tAdd Participant button is displayed\n" +
            "\uF0A7\tAdd Participant Modal is displayed\n" +
            "\uF0A7\tCorrect fields are displayed in Add Participant Modal\n" +
            "\uF0A7\tEmail address is required field\n" +
            "\uF0A7\tParticipant is added to the Participant table upon submission\n" +
            "o\tStudy Staff Lead\n" +
            "\uF0A7\tAdd Participant button is displayed\n" +
            "\uF0A7\tAdd Participant Modal is displayed\n" +
            "\uF0A7\tCorrect fields are displayed in Add Participant Modal\n" +
            "\uF0A7\tEmail address is required field\n" +
            "\uF0A7\tParticipant is added to the Participant table upon submission\n" +
            "o\tStudy Staff\n" +
            "\uF0A7\tAdd Participant button is displayed\n" +
            "\uF0A7\tAdd Participant Modal is displayed\n" +
            "\uF0A7\tCorrect fields are displayed in Add Participant Modal\n" +
            "\uF0A7\tEmail address is required field\n" +
            "\uF0A7\tParticipant is added to the Participant table upon submission";
    static String REQUIREMENTS = "FRD_001, FRD_004";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_001_004_Add_Participant";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_001_004_Add_Participant()  {
        VERSIONHISTORY.add("1.0;27FEB2023;Initial Test Script – Per 173;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_001_004_Add_Participant_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_001_004 – Add Study Participants using Authorized Role";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);

        ArrayList<RoleType> roles = new ArrayList<>(Arrays.asList(RoleType.ADMIN, RoleType.STUDY_STAFF_LEAD,
                RoleType.STUDY_STAFF));

        for (RoleType r : roles) {
            pageObj.login.logIn(r.email, report);
            report.addScreenshotStep(r.name()+ "_Login");

            bh.verifyDisplayedFlex(par.addParticipantButton, "Add Participants button", report);
            par.openAddParticipant(report);
            report.addScreenshotStep(r.name()+ "_Add_Initial");

            String email = bh.getUniqueEmail();
            String site = par.getEnabledSiteID();
            String study = par.getEnabledStudyID();

            par.verifyAllModalElementsDisplayed(report);
            par.selectSiteID(site,report);
            par.selectStudyID(study, report);
            par.verifyCreateUserEnabledDisabled(false, report);
            report.addScreenshotStep(r.name()+ "_No_Email");

            par.enterEmail(email, report);
            par.verifyAllAddModalFilledIn(email, study, site, report);
            par.verifyCreateUserEnabledDisabled(true, report);
            report.addScreenshotStep(r.name()+ "_Button_Enabled");

            par.clickCreateUser(report);
            par.findEmailInTable(email, report);
            report.addScreenshotStep(r.name()+ "_Email_In_Table");

            par.logout(report);
        }
    }
}
