package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.AccountTabs;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.pages.sp.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_029_Role_Permission_Visibility extends BaseTest {
    static String OBJECTIVE = "To verify the Study Portal shall provide permissions for each role per the matrix :\n " +
            " image presented in the VTP documentation";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "-\tVisibility and potential actions (including limitations) from the following users based on the table above:\n" +
            "o\tAdmin Role user\n" +
            "o\tCentral Assessor user\n" +
            "o\tStudy Staff Lead Role user\n" +
            "o\tStudy Staff Role user\n" +
            "o\tClinician Lead Role user\n" +
            "o\tClinician Role user\n";
    static String REQUIREMENTS = "DEX_SP_FRD_029";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_029_Role_Permission_Visibility";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_029_Role_Permission_Visibility()  {
        VERSIONHISTORY.add("1.0;27FEB2023;Initial Test Script – Per 173;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_029_Role_Permission_Visibility_Test() throws IOException{
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_029 – Study Portal Role Permission Visibility";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        Accounts acc = pageObj.accounts;
        DownloadRecords records = pageObj.downloadRecords;
        ViewRecords view = pageObj.viewRecords;
        Sites sites = pageObj.sites;
        Studies studies = pageObj.studies;
        MyPassword pass = pageObj.myPassword;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.ADMIN.email, report);
        par.verifyTabsDisplayed( RoleType.ADMIN.tabs, report);
        bh.verifyDisplayedFlex(par.addParticipantButton, "Add Participant Button", report);
        bh.verifyDisplayedFlex(par.editButton, "Edit Participant Button", report);
        bh.verifyNotDisplayedFlex(par.viewRecordsButton, "View Records", report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        report.addScreenshotStep("Step2_Admin_Participant");

        par.clickEditPart(report);
        par.verifyAllEditModalElementsDisplayed(report);
        report.addScreenshotStep("Step3_Admin_EditModal");
        par.clickCloseButton(report);

        par.selectTabToPage(AccountTabs.ACCOUNTS, pageObj.accounts,report);
        bh.verifyDisplayedFlex(acc.addAccountButton, "Add Account", report);
        bh.verifyDisplayedFlex(acc.editLink, "Edit Account", report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        report.addScreenshotStep("Step5_Admin_Accounts");

        acc.clickAddAccount(report);
        acc.verifyAddAccountElementsDisplayed(report);
        report.addScreenshotStep("Step6_Admin_AddAccountModal");

        bh.clickFlex(acc.roleDropdown, "Role dropdown", report);
        bh.verifyAllDropdownOptions(acc.roleDropdown, Arrays.asList("Central Assessor", "Study Staff Lead",
                "Study Staff Lead", "Study Staff", "Clinician Lead", "Clinician"), report);
        report.addScreenshotStep("Step7_Admin_Roles");

        bh.clickFlex(acc.roleDropdown, "Role dropdown", report);

        // Step 9-18
        for (RoleType r : Arrays.asList(RoleType.CENTRAL_ASSESSOR, RoleType.STUDY_STAFF_LEAD,
                RoleType.STUDY_STAFF, RoleType.CLINICIAN_LEAD, RoleType.CLINICIAN)) {
            acc.clickCancelToDismiss("Add Account modal",report);
            acc.clickEditByRole(r.ui_name, report);
            acc.verifyAllModalElementsDisplayed(report);
            report.addScreenshotStep("Admin_" + r.name()+"AccEdit");
        }

        acc.clickCancelToDismiss("Add Account modal",report);
        acc.selectTabToPage(AccountTabs.RECORDS, pageObj.downloadRecords,report);
        records.verifyAllElementsDisplayed(report);
        acc.logout(report);
        report.addScreenshotStep("Step19_Admin_Records");

        records.selectTabToPage(AccountTabs.STUDY, studies,report);
        bh.verifyDisplayedFlex(studies.activeToggle, "Toggle in table", report);
        report.addScreenshotStep("Step20_Admin_Study");

        studies.selectTabToPage(AccountTabs.SITE, sites,report);
        bh.verifyDisplayedFlex(studies.activeToggle, "Toggle in table", report);
        report.addScreenshotStep("Step21_Admin_Site");

        sites.selectTabToPage(AccountTabs.MY_PASSWORD, pass,report);
         pass.verifyAllElementsDisplayed(report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        report.addScreenshotStep("Step22_Admin_MyPassword");

        pass.logout(report);

        pageObj.login.logIn(RoleType.CENTRAL_ASSESSOR.email, report);
        par.verifyTabsDisplayed( RoleType.CENTRAL_ASSESSOR.tabs, report);
        par.verifyTabsNotDisplayed( Arrays.asList(AccountTabs.ACCOUNTS, AccountTabs.SITE, AccountTabs.STUDY),
                report);
        bh.verifyDisplayedFlex(par.editButton, "Edit Participant Button", report);
        bh.verifyDisplayedFlex(par.viewRecordsButton, "View Records", report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        bh.verifyNotDisplayedFlex(par.addParticipantButton, "Add Participant Button", report);
        report.addScreenshotStep("Step24_CA_Participant");

        par.clickEditPart(report);
        par.verifyAllEditModalElementsDisplayed(report);
        bh.verifyNotDisplayedFlex(par.resetEmailLink, "Reset email link", report);
        report.addScreenshotStep("Step25_CA_AddParticipant");

        par.clickCloseButton(report);
        par.clickViewRecords(report);
        view.verifyAllElementsDisplayed(report);
        report.addScreenshotStep("Step27_CA_ViewRecords");

        view.clickCloseRecordButton(report);
        acc.selectTabToPage(AccountTabs.RECORDS, pageObj.downloadRecords,report);
        records.verifyAllElementsDisplayed(report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        report.addScreenshotStep("Step29_CA_DownloadRecords");

        par.selectTab(AccountTabs.MY_PASSWORD, report);
        pass.verifyAtPage(report);
        pass.verifyAllElementsDisplayed(report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        report.addScreenshotStep("Step30_CA_MyPassword");

        pass.logout(report);

        ////////////////////STUDY STAFF LEAD

        pageObj.login.logIn(RoleType.STUDY_STAFF_LEAD.email, report);
        par.verifyTabsDisplayed( RoleType.STUDY_STAFF_LEAD.tabs, report);
        par.verifySSElementsDisplayed(report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        report.addScreenshotStep("Step32_SSL_Participant");

        par.clickEditPart(report);
        par.verifyAllEditModalElementsDisplayed(report);
        bh.verifyNotDisplayedFlex(par.activeToggle, "Status toggle",report);
        bh.verifyNotDisplayedFlex(par.resetEmailLink, "Resend Invitation Email Link",report);
        report.addScreenshotStep("Step33_SSL_EditModal");

        par.clickCloseButton(report);
        par.clickViewRecords(report);
        view.verifyAllElementsDisplayed(report);
        report.addScreenshotStep("Step35_SSL_ViewRecords");

        view.clickCloseRecordButton(report);
        par.selectTabToPage(AccountTabs.ACCOUNTS, pageObj.accounts,report);
        bh.verifyDisplayedFlex(acc.addAccountButton, "Add Account", report);
        bh.verifyDisplayedFlex(acc.editLink, "Edit Account", report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        report.addScreenshotStep("Step37_SSL_Accounts");

        acc.clickAddAccount(report);
        acc.verifyAddAccountElementsDisplayed(report);
        report.addScreenshotStep("Step38_SSL_AddAccountModal");

        bh.clickFlex(acc.roleDropdown, "Role dropdown", report);
        bh.verifyAllDropdownOptions(acc.roleDropdown, Arrays.asList("Study Staff Lead", "Study Staff"), report);
        report.addScreenshotStep("Step39_SSL_Roles");

        // Step 41-43
        for (RoleType r : Arrays.asList(RoleType.STUDY_STAFF_LEAD, RoleType.STUDY_STAFF)) {
            par.clickCloseButton(report);
            acc.clickEditByRole(r.ui_name, report);
            acc.verifyAllModalElementsDisplayed(report);
            report.addScreenshotStep("SSL_" + r.name()+"AccEdit");
        }

        par.clickCloseButton(report);
        acc.selectTabToPage(AccountTabs.RECORDS, pageObj.downloadRecords,report);
        records.verifyAllElementsDisplayed(report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        report.addScreenshotStep("Step45_SSL_DownloadRecords");

        records.selectTabToPage(AccountTabs.STUDY, studies,report);
        bh.verifyDisplayedFlex(studies.activeToggle, "Toggle in table", report);
        report.addScreenshotStep("Step46_SSLStudy");

        studies.selectTabToPage(AccountTabs.SITE, sites,report);
        bh.verifyDisplayedFlex(studies.activeToggle, "Toggle in table", report);
        report.addScreenshotStep("Step47_SSLSite");

        sites.selectTabToPage(AccountTabs.MY_PASSWORD, pass,report);
        pass.verifyAllElementsDisplayed(report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        report.addScreenshotStep("Step48_SSL_MyPassword");

        pass.logout(report);

        ////////////////STUDY STAFF

        pageObj.login.logIn(RoleType.STUDY_STAFF.email, report);
        par.verifyTabsDisplayed( RoleType.STUDY_STAFF.tabs, report);
        par.verifyTabsNotDisplayed( Arrays.asList(AccountTabs.ACCOUNTS,  AccountTabs.RECORDS, AccountTabs.STUDY,
                        AccountTabs.SITE), report);
        par.verifySSElementsDisplayed(report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        report.addScreenshotStep("Step50_SS_Participant");

        par.clickEditPart(report);
        par.verifyAllEditModalElementsDisplayed(report);
        bh.verifyNotDisplayedFlex(par.resetEmailLink, "Reset email link", report);
        report.addScreenshotStep("Step51_SS_EditParticipant");

        par.clickCloseButton(report);
        par.clickViewRecords(report);
        bh.verifyDisplayedFlex(view.closeRecordButton, "Close Record Button", report);
        bh.verifyNotDisplayedFlex(view.downloadButton, "Download CSV", report);
        report.addScreenshotStep("Step53_SS_ViewRecords");

        view.clickCloseRecordButton(report);
        par.selectTabToPage(AccountTabs.MY_PASSWORD, pass,report);
        pass.verifyAllElementsDisplayed(report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        report.addScreenshotStep("Step55_SS_MyPassword");

        pass.logout(report);

        ///////////////////CLINICIAN LEAD

        pageObj.login.logIn(RoleType.CLINICIAN_LEAD.email, report);
        par.verifyTabsDisplayed(RoleType.CLINICIAN_LEAD.tabs, report);
        par.verifyTabsNotDisplayed( Arrays.asList( AccountTabs.RECORDS, AccountTabs.STUDY, AccountTabs.SITE), report);
        bh.verifyDisplayedFlex(par.editButton, "Edit Participant Button", report);
        bh.verifyDisplayedFlex(par.viewRecordsButton, "View Records", report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        bh.verifyNotDisplayedFlex(par.addParticipantButton, "Add Participant Button", report);
        report.addScreenshotStep("Step57_CL_Participant");

        par.clickEditPart(report);
        par.verifyAllEditModalElementsDisplayed(report);
        bh.verifyDisplayedFlex(par.activeToggle, "Status toggle",report);
        bh.verifyNotDisplayedFlex(par.resetEmailLink, "Resend Invitation Email Link",report);
        report.addScreenshotStep("Step58_CL_EditModal");

        par.clickCloseButton(report);
        par.clickViewRecords(report);
        bh.verifyDisplayedFlex(view.addRecordButton, "Add Record button",report);
        bh.verifyDisplayedFlex(view.closeRecordButton, "Close Record button",report);
        bh.verifyNotDisplayedFlex(view.downloadButton, "Download CSV Button",report);
        report.addScreenshotStep("Step60_CL_ViewRecords");

        view.clickCloseRecordButton(report);
        par.selectTabToPage(AccountTabs.ACCOUNTS, pageObj.accounts,report);
        bh.verifyDisplayedFlex(acc.addAccountButton, "Add Account", report);
        bh.verifyDisplayedFlex(acc.editLink, "Edit Account", report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        report.addScreenshotStep("Step62_CL_Accounts");

        acc.clickAddAccount(report);
        acc.verifyAddAccountElementsDisplayed(report);
        report.addScreenshotStep("Step63_CL_AddAccountModal");

        bh.clickFlex(acc.roleDropdown, "Role dropdown", report);
        bh.verifyAllDropdownOptions(acc.roleDropdown, Arrays.asList("Clinician Lead", "Clinician"), report);
        report.addScreenshotStep("Step64_CL_Roles");

        // Step 66-69
        for (RoleType r : Arrays.asList(RoleType.CLINICIAN_LEAD, RoleType.CLINICIAN)) {
            par.clickCloseButton(report);
            acc.clickEditByRole(r.ui_name, report);
            acc.verifyAllModalElementsDisplayed(report);
            report.addScreenshotStep("CL_" + r.name()+"AccEdit");
        }

        par.clickCloseButton(report);
        par.selectTabToPage(AccountTabs.MY_PASSWORD, pass,report);
        pass.verifyAllElementsDisplayed(report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        report.addScreenshotStep("Step70_CL_MyPassword");

        pass.logout(report);

        ////////////////CLINICIAN

        pageObj.login.logIn(RoleType.CLINICIAN.email, report);
        par.verifyTabsDisplayed( RoleType.CLINICIAN.tabs, report);
        par.verifyTabsNotDisplayed( Arrays.asList(AccountTabs.ACCOUNTS,  AccountTabs.RECORDS, AccountTabs.STUDY,
                AccountTabs.SITE), report);
        bh.verifyDisplayedFlex(par.viewRecordsButton, "View Records", report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        bh.verifyNotDisplayedFlex(par.addParticipantButton, "Add Participant Button", report);
        bh.verifyNotDisplayedFlex(par.editButton, "Edit Participant Button", report);
        report.addScreenshotStep("Step72_C_Participant");

        par.clickViewRecords(report);
        bh.verifyDisplayedFlex(view.closeRecordButton, "Close Record Button", report);
        bh.verifyNotDisplayedFlex(view.downloadButton, "Download CSV", report);
        report.addScreenshotStep("Step73_C_ViewRecords");

        view.clickCloseRecordButton(report);
        par.selectTabToPage(AccountTabs.MY_PASSWORD, pass,report);
        pass.verifyAllElementsDisplayed(report);
        bh.verifyDisplayedFlex(par.logout, "Logout", report);
        report.addScreenshotStep("Step75_C_MyPassword");
    }
}
