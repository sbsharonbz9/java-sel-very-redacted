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
import java.util.HashMap;

public class VTP_DEX_SP_FRD_086_SStaffLead_Participant_Table extends BaseTest {

    public static final String OBJECTIVE = "To verify if a Study Staff Lead user selects the “Add Participant” link, " +
            "the portal shall display a popup screen with Role set to Participant, that allows the user: \n" +
            "-\tEnter participant email address \n" +
            "-\tSelect Study ID \n" +
            "-\tSelect Study Site \n" +
            "-\tSave or cancel adding the account\n";

    public static final String NOTES ="This protocol contains the following scenarios:\n" +
            "-\tUser can click the Add Participant link\n" +
            "o\tAdmin user can add the following fields:\n" +
            "o\tEnter participant email address \n" +
            "o\tSelect study number \n" +
            "o\tSelect study site \n" +
            "-\tUser can Cancel adding of a participant\n" +
            "-\tUser can Save a new participant\n";

    static String REQUIREMENTS = "DEX_SP_FRD_086";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_086_SStaffLead_Participant_Table";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_086_SStaffLead_Participant_Table() {
        VERSIONHISTORY.add("1.0;28FEB2023;Per 260: Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_086_SStaffLead_Participant_Table_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_086 – Study Staff Lead Participant Table";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.STUDY_STAFF_LEAD.email, report);
        par.openAddParticipant(report);
        par.verifyModalDisplayed(report);
        report.addScreenshotStep("Step3_Add_Participant_Screen");

        ParticipantClass parUser = new ParticipantClass(bh.getUniqueEmail(), par.getEnabledSiteID(),
                par.getEnabledStudyID());
        par.enterNewParticipantData(parUser.email, parUser.studyID, parUser.siteID, report);
        par.verifyAllAddModalFilledIn(parUser.email, parUser.studyID, parUser.siteID, report);
        report.addScreenshotStep("Step4_Participant_Form_Fields");

        par.clickCancelToDismiss("Add Participant modal",report);
        report.addScreenshotStep("Step5_Participant_Screen");

        par.openAddParticipant(report);
        par.enterNewParticipantData(parUser.email, parUser.studyID, parUser.siteID, report);
        par.verifyAllAddModalFilledIn(parUser.email, parUser.studyID, parUser.siteID, report);
        par.clickCreateUser(report);
        par.findEmailInTable(parUser.email, report);
        report.addScreenshotStep("Step7_Participant_Saved");
    }
}