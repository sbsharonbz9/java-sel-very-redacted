package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.BloodPressureType;
import galen.enums.tenant.dexter.SmokeType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.BasePage;
import galen.pages.sp.Participants;
import galen.pages.sp.SPBasePage;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import galen.utils.ConfigLoader;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_SP_FRD_001_004_Add_Participant extends BaseTest {
    static String OBJECTIVE = "DEX_SP_FRD_001: To verify the portal shall provide the capability for authorized users to add study participants.\n" +
            "DEX_SP_FRD_004: To verify the portal shall require the following information to be input for each study participant:  \n" +
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
        VERSIONHISTORY.add("1.0;27FEB2023;Initial Test Script – Per CADENCE-173;Name Redacted");
    }

    @Test
    public void VTP_DEX_SP_FRD_001_004_Add_Participant_Test() throws IOException, InterruptedException {
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
            sleep(1000);
            par.verifyAtPage(report);
            report.addScreenshotStep(r.name()+ "_Login");

            bh.verifyDisplayedFlex(par.addParticipantButton, "Add Participants button", report);
            par.openAddParticipant(report);
            report.addScreenshotStep(r.name()+ "_Add_Initial");

            String email = bh.getUniqueEmail();
            par.verifyAllModalElementsDisplayed(report);
            par.selectSiteID("001",report);
            par.selectStudyID("01", report);
            par.verifyCreateUserEnabledDisabled(false, report);
            report.addScreenshotStep(r.name()+ "_No_Email");

            par.enterEmail(email, report);
            bh.verifyText(bh.getWebElement(par.emailField), "Email text field", email, report);
            bh.verifyText(bh.getWebElement(par.siteIDDropdown), "Site dropdown", "001", report);
            bh.verifyText(bh.getWebElement(par.studyIDDropdown), "Study dropdown", "01", report);
            par.verifyCreateUserEnabledDisabled(true, report);
            report.addScreenshotStep(r.name()+ "_Button_Enabled");

            par.clickCreateUser(report);
            par.findEmailInTable(email, report);
            report.addScreenshotStep(r.name()+ "_Email_In_Table");

            bh.clickFlex(pageObj.login.getLogout(), "Log Out", report);
        }
    }
}
