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

public class VTP_DEX_SP_FRD_080_SStaff_Add_Participant extends BaseTest {
    public static final String OBJECTIVE = "To verify if Study Staff selects the Add Participant link, the portal shall display a popup screen that allows the user to: \n" +
            "Enter user email address \n" +
            "Select Study ID \n" +
            "Select Study site \n" +
            "Save or cancel adding the account";

    public static final String NOTES = "This protocol contains the following scenarios: \n" +
            "User can click the Add Participant link \n" +
            "Admin user can add the following fields: \n" +
            "Enter participant email address \n" +
            "Select study number \n" +
            "Select study site \n" +
            "User can Cancel adding of a participant \n" +
            "User can Save a new participant";
    static String REQUIREMENTS = "DEX_SP_FRD_080";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_080_SStaff_Add_Participant";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_080_SStaff_Add_Participant() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 173;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_080_SStaff_Add_Participant_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_080_SStaff_Add_Participant";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        commonPageFeatures = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.STUDY_STAFF.email, report);
        bh.verifyDisplayedFlex(par.addParticipantButton, "Add Participant button", report);
        report.addScreenshotStep("Step2_AddParticipantButtonDisplayed");

        par.openAddParticipant(report);
        report.addScreenshotStep("Step3_Add_Participants_Initial");

        String email = bh.getUniqueEmail();
        String studyID=par.getEnabledStudyID();
        String siteID=par.getEnabledSiteID();
        par.enterNewParticipantData(email,studyID,siteID, report);
        par.verifyAllAddModalFilledIn(email, studyID, siteID, report);
        report.addScreenshotStep("Step4_FilledIn");

        par.clickCancelToDismiss("Add Participant modal",report);
        par.verifyEmailNotInTable(email, report);
        report.addScreenshotStep("Step5_EmailNotInTable");

        par.openAddParticipant(report);
        par.addParticipant(email, studyID, siteID, report);
        par.findEmailInTable(email, report);
        report.addScreenshotStep("Step7_Email_In_Table");
    }
}
