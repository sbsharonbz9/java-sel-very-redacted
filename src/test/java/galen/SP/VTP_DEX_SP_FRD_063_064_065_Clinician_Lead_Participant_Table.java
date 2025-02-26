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

public class VTP_DEX_SP_FRD_063_064_065_Clinician_Lead_Participant_Table extends BaseTest {
    public static final String OBJECTIVE =
            "Test Objective\n\n" +
                    "DEX_SP_FRD_063: To verify the Clinician Lead portal dashboard screen shall provide a list of study participants with the following columns:\n" +
                    "- Participant ID\n" +
                    "- Email address\n" +
                    "- Action (contains View Records and Edit options)\n\n" +
                    "DEX_SP_FRD_064: To verify the Clinician Lead portal Participant screen shall be sortable by:\n" +
                    "- Participant ID\n" +
                    "- Email address\n\n" +
                    "DEX_SP_FRD_065: To verify on the Clinician Lead Participants screen, if the Clinician Lead User selects the “edit” link in the Action column for a Participant, the portal shall display an Edit Info Popup screen.";

    public static final String NOTES =
            "This protocol contains the following scenarios:\n\n" +
                    "- Clinician Lead user has the following columns displayed in the Participant Table\n" +
                    "  - Participant ID\n" +
                    "  - Email Address\n" +
                    "  - Action\n" +
                    "- Clicking the Edit link opens the Edit Modal\n" +
                    "- Clinician Lead user has ability to sort Participant ID column (Ascending (by default) and Descending)\n" +
                    "- Clinician Lead user has ability to sort Email Address column (Ascending and Descending)";

    static String REQUIREMENTS = "DEX_SP_FRD_063_064_065";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_063_064_065_Clinician_Lead_Participant_Table";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_063_064_065_Clinician_Lead_Participant_Table() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 173;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_063_064_065_Clinician_Lead_Participant_Table_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_063_064_065_Clinician_Lead_Participant_Table";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        commonPageFeatures = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.CLINICIAN_LEAD.email, report);
        par.verifyHeadersDisplayed(report);
        report.addScreenshotStep("View Records");

        par.clickEditPart(report);
        bh.verifyDisplayedFlex(par.saveChangesButton, "Edit Participant Modal", report);
        report.addScreenshotStep("View Records2");

        par.clickCloseButton(report);
        par.verifyAtPage(report);
        bh.verifyDisplayedFlex(par.participantIdHeader, "Participant ID Column Ascending", report);
        par.clickAndVerifyAscending("Participant ID",report);
        report.addScreenshotStep("Ascending Participant ID", driver);

        bh.verifyDisplayedFlex(par.participantIdHeader, "Participant ID Column Descending", report);
        par.clickAndVerifyDescending("Participant ID",report);
        report.addScreenshotStep("Descending Participant", driver);

        bh.verifyDisplayedFlex(par.participantEmailHeader, "Email Address Column Ascending", report);
        par.clickAndVerifyAscending("Email Address",report);
        report.addScreenshotStep("Ascending Email", driver);

        bh.verifyDisplayedFlex(par.participantEmailHeader, "Email Address Column Descending", report);
        par.clickAndVerifyDescending("Email Address",report);
        report.addScreenshotStep("Descending Email", driver);
    }
}
