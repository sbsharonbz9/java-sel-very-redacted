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

public class VTP_DEX_SP_FRD_087_SStaffLead_Edit_Modal extends BaseTest {

    public static final String OBJECTIVE =
            "To verify if a Study Staff Lead user selects the Edit link in the Action column of the Participants screen," +
                    " the portal shall display a popup screen that allows the user to:  \n" +
                    "Update email address for the participant (once account has been setup) ";

    public static final String NOTES =
            "This protocol contains the following scenarios: \n" +
                    "Ability to edit/update participant’s email address";

    static String REQUIREMENTS = "DEX_SP_FRD_087";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_087_SStaffLead_Edit_Modal";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_087_SStaffLead_Edit_Modal() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 173;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_087_SStaffLead_Edit_Modal_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_087_SStaffLead_Edit_Modal";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.STUDY_STAFF_LEAD.email, report);
        par.clickEditPart(report);
        par.verifyModalDisplayed(report);
        report.addScreenshotStep("Modal Is Displayed");

        ParticipantClass parUser = new ParticipantClass(bh.getUniqueEmail(), par.getEnabledSiteID(),
                par.getEnabledStudyID());
        parUser.email = bh.getUniqueEmail();
        par.editParticipant(parUser, report);
        par.findEmailInTable(parUser.email, report);
        report.addScreenshotStep("New email " + parUser.email + " in table");

    }
}
