package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.AccountTabs;
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
import java.util.Arrays;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_015_016_Study_ID_Study_Site_Config extends BaseTest {
    static String OBJECTIVE = "DEX_SP_FRD_015: To verify the portal shall provide the capability to configure study ID " +
            "with a 2-digit numeric study ID.\n" +
            "DEX_SP_FRD_016: To verify the portal shall provide the capability to configure study “sites” with a " +
            "3-digit numeric site ID.\n";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tAdmin Role\n" +
            "\uF0A7\tAbility to configure 2-digit numeric Study ID \n" +
            "\uF0A7\tAbility to configure 3-digit numeric Site ID \n" +
            "o\tStudy Staff Lead\n" +
            "\uF0A7\tAbility to configure 2-digit numeric Study ID \n" +
            "\uF0A7\tAbility to configure 3-digit numeric Site ID";
    static String REQUIREMENTS = "DEX_SP_FRD_015, DEX_SP_FRD_016";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_015_016_Study_ID_Study_Site_Config";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_015_016_Study_ID_Study_Site_Config()  {
        VERSIONHISTORY.add("1.0;28FEB2023;Per 173: Initial Test Script;Tester");
        VERSIONHISTORY.add("2.0;09JUN2023;Per 343: Update Test Steps to align language with user navigation;" +
                "Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_015_016_Study_ID_Study_Site_Config_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_015_016 – Configure Study ID and Study Site ID";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);

        ArrayList<RoleType> roles = new ArrayList<>(Arrays.asList(RoleType.ADMIN, RoleType.STUDY_STAFF_LEAD));

        for (RoleType r : roles) {
            pageObj.login.logIn(r.email, report);

            par.selectTabToPage(AccountTabs.STUDY,pageObj.studies, report);
            pageObj.studies.verifyStudyIDFormat(report);
            report.addScreenshotStep(r.name()+"Studies");

            par.selectTabToPage(AccountTabs.SITE, pageObj.sites,report);
            pageObj.sites.verifySiteIDFormat(report);
            report.addScreenshotStep(r.name()+"Sites");

            par.logout(report);
        }
    }
}
