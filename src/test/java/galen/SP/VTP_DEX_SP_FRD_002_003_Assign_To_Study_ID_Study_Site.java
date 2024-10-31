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
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_SP_FRD_002_003_Assign_To_Study_ID_Study_Site extends BaseTest {
    static String OBJECTIVE = "DEX_SP_FRD_002: To verify the portal shall provide the capability for authorized users " +
            "to assign study participants to a study ID.\n" +
            "DEX_SP_FRD_003: To verify the portal shall provide the capability for authorized users to assign study " +
            "participants to a study site.\n";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tAdmin Role\n" +
            "\uF0A7\tAbility to assign Study ID and Study Site\n" +
            "o\tStudy Staff Lead\n" +
            "\uF0A7\tAbility to assign Study ID and Study Site \n" +
            "o\tStudy Staff\n" +
            "\uF0A7\tAbility to assign Study ID and Study Site\n";
    static String REQUIREMENTS = "DEX_SP_FRD_002, DEX_SP_FRD_003";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_002_003_Assign_To_Study_ID_Study_Site";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_002_003_Assign_To_Study_ID_Study_Site()  {
        VERSIONHISTORY.add("1.0;27FEB2023;Initial Test Script – Per CADENCE-173;James Reale");
    }

    @Test
    public void VTP_DEX_SP_FRD_002_003_Assign_To_Study_ID_Study_Site_Test() throws IOException, InterruptedException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_002_003 – Assign Participant the Study ID and Study Site";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        ParticipantClass user = new ParticipantClass();
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);

        ArrayList<RoleType> roles = new ArrayList<>(Arrays.asList(RoleType.ADMIN, RoleType.STUDY_STAFF_LEAD,
                RoleType.STUDY_STAFF));

        for (RoleType r : roles) {
            pageObj.login.logIn(r.email, report);
            sleep(1000);
            par.verifyAtPage(report);

            user.email = bh.getUniqueEmail();

            par.openAddParticipant(report);
            par.addParticipant(user.email, user.studyID=par.getEnabledStudyID(), user.siteID=par.getEnabledSiteID(), report);
            par.findEmailInTable(user.email, report);
            par.verifyParticipantIDFormat(user, report);
            report.addScreenshotStep(r.name()+ "_Email_In_Table");

            bh.clickFlex(pageObj.login.getLogout(), "Log Out", report);
        }
    }
}
