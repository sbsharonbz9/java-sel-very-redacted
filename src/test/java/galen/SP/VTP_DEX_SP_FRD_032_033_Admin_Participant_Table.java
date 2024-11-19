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

public class VTP_DEX_SP_FRD_032_033_Admin_Participant_Table extends BaseTest {
    public static final String OBJECTIVE =
            "DEX_SP_FRD_032: To verify the IE Admin portal Participants screen shall provide a list of study participants with the following columns:\n" +
                    "- Participant ID\n" +
                    "- Email address\n" +
                    "- Action\n" +
                    "DEX_SP_FRD_033: To verify the IE Admin portal Participant screen shall be sortable by:\n" +
                    "- Participant ID\n" +
                    "- Email address";

    public static final String NOTES =
            "This protocol contains the following scenarios:\n" +
                    "- Admin user has the following columns displayed in the Participant Table:\n" +
                    "  * Participant ID\n" +
                    "  * Email Address\n" +
                    "  * Action\n" +
                    "- Admin user has ability to sort Participant ID column (Ascending (by default) and Descending)\n" +
                    "- Admin user has ability to sort Email Address column (Ascending and Descending)";


    static String REQUIREMENTS = "DEX_SP_FRD_032_033";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_032_033_Admin_Participant_Table";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_032_033_Admin_Participant_Table() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 173;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_032_033_Admin_Participant_Table_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_032_033_Admin_Participant_Table";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        commonPageFeatures = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);

        pageObj.login.logIn(RoleType.ADMIN.email, report);
        par.verifyHeadersDisplayed(report);
        report.addScreenshotStep("Participant Records");

        bh.verifyDisplayedFlex(par.participantIdHeader, "Participant ID Column Ascending", report);
        pageObj.participants.clickAndVerifyAscending("Participant ID", report);
        report.addScreenshotStep("Participant_ID_Ascending", driver);

        bh.verifyDisplayedFlex(par.participantIdHeader, "Participant ID Column Descending", report);
        pageObj.participants.clickAndVerifyDescending("Participant ID", report);
        report.addScreenshotStep("Participant_ID_Descending", driver);

        bh.verifyDisplayedFlex(par.participantEmailHeader, "Email Address Column Ascending", report);
        pageObj.participants.clickAndVerifyAscending("Email Address", report);
        report.addScreenshotStep("Participant_Email_Ascending", driver);

        bh.verifyDisplayedFlex(par.participantEmailHeader, "Email Address Column Descending", report);
        pageObj.participants.clickAndVerifyDescending("Email Address", report);
        report.addScreenshotStep("Participant_Email_Descending", driver);
    }
}
