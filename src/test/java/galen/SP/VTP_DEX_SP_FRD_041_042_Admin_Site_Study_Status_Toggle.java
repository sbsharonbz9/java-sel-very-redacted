package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.AccountTabs;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.pages.sp.Participants;
import galen.pages.sp.Sites;
import galen.pages.sp.Studies;
import galen.pages.sp.StudyAdminPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_041_042_Admin_Site_Study_Status_Toggle extends BaseTest {
    static String OBJECTIVE = "DEX_SP_FRD_041: To verify the IE Admin portal site screen shall provide the capability " +
            "to activate or deactivate site ID locations.\n" +
            "DEX_SP_FRD_042: To verify the IE Admin portal site screen shall provide the capability to activate or " +
            "deactivate study ID numbers";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tAdmin is able to toggle Study Site ID OFF (deactivated)\n" +
            "o\tAdmin is not able to see the deactivated Site ID on the Add Participant Modal \n" +
            "o\tAdmin is not able to see the deactivated Site ID on the Add Account Modal \n" +
            "o\tAdmin is able to toggle Study Site ID ON\n" +
            "o\tAdmin is able to see the activated Site ID on the Add Participant Modal \n" +
            "o\tAdmin is able to see the activated Site ID on the Add Account Modal \n" +
            "o\tAdmin is able to toggle Study ID OFF  (deactivated)\n" +
            "o\tAdmin is not able to see the deactivated Study ID on the Add Participant Modal \n" +
            "o\tAdmin is able to toggle Study ID ON\n" +
            "o\tAdmin is able to see the activated Study ID on the Add Participant Modal";
    static String REQUIREMENTS = "DEX_SP_FRD_041, DEX_SP_FRD_042";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_041_042_Admin_Site_Study_Status_Toggle";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_041_042_Admin_Site_Study_Status_Toggle()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_SP_FRD_041_042_Admin_Site_Study_Status_Toggle_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_041_042 – Admin Toggle Status of Study Site and Site ID";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        Sites sites = pageObj.sites;
        Studies studies = pageObj.studies;
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);

        pageObj.login.logIn(RoleType.ADMIN.email, report);

        par.selectTabToPage(AccountTabs.SITE, sites, report);
        sites.verifyAtPage(report);
        String site = sites.setActivationRandomSite("Off", report);
        report.addScreenshotStep("Step4_SiteToggleOff");

        sites.selectTabToPage(AccountTabs.PARTICIPANTS, par,report);
        par.openAddParticipant(report);
        par.verifyModalDisplayed(null);
        bh.clickFlex(par.siteIDDropdown, "Site ID", report);
        bh.verifyDropdownNotContains(par.siteIDDropdown,site, report);
        report.addScreenshotStep("Step7_SiteNotDisplayed");

        par.clickCloseButton(report);
        par.verifyModalDismissed(null);
        sites.selectTabToPage(AccountTabs.SITE, sites, report);
        sites.clickToggleFromSite(site, report);
        report.addScreenshotStep("Step10_SiteToggleOn");

        sites.selectTabToPage(AccountTabs.PARTICIPANTS, par,report);
        par.openAddParticipant(report);
        bh.clickFlex(par.siteIDDropdown, "Site ID", null);
        bh.verifyDropdownContains(par.siteIDDropdown,site, report);
        report.addScreenshotStep("Step13_SiteDisplayed");
        par.clickCloseButton(report);

        par.selectTabToPage(AccountTabs.STUDY, studies,report);
        String study = studies.setActivationRandomStudy("Off", report);
        report.addScreenshotStep("Step16_StudyIDToggleOff");

        studies.selectTabToPage(AccountTabs.PARTICIPANTS, par,report);
        par.openAddParticipant(report);
        bh.clickFlex(par.studyIDDropdown, "Study ID", null);
        report.addScreenshotStep("Step19_StudyIDNotDisplayed");
        bh.verifyDropdownNotContains(par.studyIDDropdown,study, report);

        par.clickCloseButton(report);
        par.selectTabToPage(AccountTabs.STUDY, studies,report);
        studies.clickToggleFromStudy(study, report);
        report.addScreenshotStep("Step22_StudyIDToggleOn");

        studies.selectTabToPage(AccountTabs.PARTICIPANTS, par,report);
        par.openAddParticipant(report);
        bh.clickFlex(par.studyIDDropdown, "Study ID", null);
        bh.verifyDropdownContains(par.studyIDDropdown,study, report);
        report.addScreenshotStep("Step25_StudyIDDisplayed");
        par.clickCloseButton(report);
    }
}
