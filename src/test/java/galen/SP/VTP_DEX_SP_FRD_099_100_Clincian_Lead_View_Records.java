package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.sp.ViewRecords;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_099_100_Clincian_Lead_View_Records extends BaseTest {

    public static final String OBJECTIVE =
            "Test Objective\n"
                    + "DEX_SP_FRD_099: To verify on the Clinician Lead portal dashboard, if a user selects the “View Records” link in the Action column of the Participants screen for a participant, the portal shall navigate to a screen displaying clinician submitted health survey records for that participant.\n"
                    + "DEX_SP_FRD_100: To verify the participant records screen displayed for a Clinician Lead user shall display:\n"
                    + "• Participant ID\n"
                    + "• Participant Email Address\n"
                    + "• A Close Records button at the top of the screen\n"
                    + "• An Add Record button";

    public static final String NOTES =
            "This protocol contains the following scenarios:\n"
                    + "Clicking View Record navigates to the View Record Screen for the selected participant\n"
                    + "View Records screen displays:";
    static String REQUIREMENTS = "DEX_SP_FRD_099_100";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_099_100_Clincian_Lead_View_Records";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_099_100_Clincian_Lead_View_Records() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 173;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_099_100_Clincian_Lead_View_Records_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_099_100_Clincian_Lead_View_Records";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        ViewRecords view = pageObj.viewRecords;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.CLINICIAN_LEAD.email, report);
        pageObj.participants.clickViewRecords(report);

  //      bh.verifyDisplayedFlex(view, "Participant Id", report);
  //      bh.verifyDisplayedFlex(par.participantEmail, "Participant Email", report);
  //      bh.verifyDisplayedFlex(par.closeRecordButton, "Close Record Button", report);
  //      bh.verifyDisplayedFlex(par.addRecordButton, "Add Record Button", report);
        report.addScreenshotStep("View Records");
    }
}
