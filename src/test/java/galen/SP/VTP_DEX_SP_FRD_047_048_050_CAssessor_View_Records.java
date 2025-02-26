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

public class VTP_DEX_SP_FRD_047_048_050_CAssessor_View_Records  extends BaseTest {
    static String OBJECTIVE = "DEX_SP_FRD_047: To verify if a Central Assessor user selects the “View Records” link in the "+
            "Action column of the Participants screen for a participant, the portal shall navigate to a screen displaying health "+
            "survey records for that participant.\n" +
            "DEX_SP_FRD_048: To verify the Central Assessor Participant Records, view displays a list of all health survey records "+
        "for the selected participant and with the following columns: \n" +
            "•\tSelect (allows user to select which records to export to CSV) \n" +
            "•\tAssessment type\n" +
            "•\tCompleted by (name or participant ID for the person who completed the health survey) \n" +
            "•\tCompleted date\n" +
            "DEX_SP_FRD_050: To verify on the Central Assessor Participant Records screen, if the user selects the “close records’ button, "+
            "the application shall return to the Central Assessor dashboard screen.\n";

    static String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tView Records button navigates to View Records Screen with Health Records displayed\n" +
            "o\tThe Health Records table contains the following columns:\n" +
            "\uF0A7\tSelect \n" +
            "\uF0A7\tAssessment Type\n" +
            "\uF0A7\tCompleted By\n" +
            "\uF0A7\tCompleted Date\n" +
            "o\tClose Records button navigates user back to Participants Screen\n";

    static String REQUIREMENTS = "DEX_SP_FRD_047, DEX_SP_FRD_048, DEX_SP_FRD_050";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_047_048_050_CAssessor_View_Records";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_047_048_050_CAssessor_View_Records() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 219;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_047_048_050_CAssessor_View_Records_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_047_048_050 – Central Assessor View Records";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.CENTRAL_ASSESSOR.email, report);
        par.clickViewRecords(report);
        pageObj.viewRecords.verifyAllColumnsPresent(report);
        pageObj.viewRecords.verifyAllElementsDisplayed(report);
        report.addScreenshotStep("Step3_Health_Records");

        pageObj.viewRecords.clickCloseRecordButton(report);
        par.verifyAtPage(report);
        report.addScreenshotStep("Step5_Participant_Screen");
    }
}