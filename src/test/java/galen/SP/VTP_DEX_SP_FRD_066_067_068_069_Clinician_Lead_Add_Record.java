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

public class VTP_DEX_SP_FRD_066_067_068_069_Clinician_Lead_Add_Record extends BaseTest {

    public static final String OBJECTIVE = "DEX_SP_FRD_066: To verify on the Clinician Lead portal dashboard, if a " +
            "Clinician Lead user selects the Add Record button, " +
            "the portal shall display an Add Record popup.\n" +
            "DEX_SP_FRD_067: To verify on the Add Record Popup, the Participant ID field shall be pre-populated with the" +
            " Participant ID for the selected participant.\n" +
            "DEX_SP_FRD_068: To verify on the Add Record Popup, if the Clinician Lead user selects the Cancel button, " +
            "the portal will return to the Participant screen without adding any records for the selected participant.\n" +
            "DEX_SP_FRD_069: To verify on the Add Record Popup, if the Clinician Lead user selects the Save button, the " +
            "portal shall open a new tab using a clinician specific Web App URL.";

    public static final String NOTES = "This protocol contains the following scenarios:\n" +
            "Clicking Add Record button displays Add Record Modal\n" +
            "Participant ID field is pre-populated with the Participant ID for the selected participant\n" +
            "Cancel link navigates to the Participant View Records Screen\n" +
            "Save button opens a new tab to allow Clinician to take assessment with specific URL";

    static String REQUIREMENTS = "DEX_SP_FRD_066, DEX_SP_FRD_067, DEX_SP_FRD_068, DEX_SP_FRD_069";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_066_067_068_069_Clinician_Lead_Add_Record";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_066_067_068_069_Clinician_Lead_Add_Record() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 173;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_066_067_068_069_Clinician_Lead_Add_Record_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_066_067_068_069_Clinician_Lead_Add_Record";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        ViewRecords view = pageObj.viewRecords;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.CLINICIAN_LEAD.email, report);
        pageObj.participants.clickViewRecords(report);
        view.clickAddRecordButton(report);
        bh.verifyDisplayedFlex(view.addRecordPartID, "Participant Id", report);
        report.addScreenshotStep("Participant ID");

        pageObj.viewRecords.clickCancelToDismiss("Add Record modal", report);
        report.addScreenshotStep("Record Screen");

        pageObj.viewRecords.clickAddRecordButton(report);
        bh.clickFlex(view.saveButton, "Save", report);
        report.addScreenshotStep("Welcome Screen");
    }
}
