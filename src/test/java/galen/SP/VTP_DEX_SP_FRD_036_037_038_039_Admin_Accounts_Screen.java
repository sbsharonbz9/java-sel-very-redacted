package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.AccountTabs;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.pages.sp.Accounts;
import galen.pages.sp.Participants;
import galen.pages.sp.StudyAdminPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_036_037_038_039_Admin_Accounts_Screen extends BaseTest {

    public static final String OBJECTIVE = "DEX_SP_FRD_036: To verify the IE Admin portal Accounts screen shall provide a link to add an account.\n" +
            "DEX_SP_FRD_037: To verify the IE Admin portal Accounts screen shall provide a list of accounts with the following columns \n" +
            "•\tFirst name \n" +
            "•\tLast name \n" +
            "•\tEmail address \n" +
            "•\tRole \n" +
            "•\tAction\n" +
            "DEX_SP_FRD_038: To verify the IE Admin portal Accounts screen list shall be sortable by: \n" +
            "•\tFirst name \n" +
            "•\tLast name \n" +
            "•\tEmail address \n" +
            "•\tRole\n" +
            "DEX_SP_FRD_039: To verify if an IE Admin user selects the “Add Account” link, the portal shall display a popup screen that allows the user to: \n" +
            "•\tSelect a user role as one of the following:  (Study Staff Lead, Study Staff, Clinician Lead, Clinician, Central Assessor) \n" +
            "•\tEnter user first name and last name \n" +
            "•\tEnter user email address \n" +
            "•\tSave or cancel adding the account";
    public static final String NOTES = "This protocol contains the following scenarios:\n" +
            "-\tAdd Account link is displayed on Account Screen\n" +
            "-\tThe following columns are displayed in the Accounts Table:\n" +
            "o\tFirst name \n" +
            "o\tLast name \n" +
            "o\tEmail address \n" +
            "o\tRole \n" +
            "o\tAction\n" +
            "-\tThe following columns are sortable:\n" +
            "o\tFirst name \n" +
            "o\tLast name \n" +
            "o\tEmail address \n" +
            "o\tRole \n" +
            "-\tAdmin is able to add the following accounts:\n" +
            "o\tCentral Assessor\n" +
            "o\tStudy Staff Lead\n" +
            "o\tStudy Staff\n" +
            "o\tClinician Lead\n" +
            "o\tClinician\n" +
            "-\tAdmin is able to cancel the adding of an account";

    static String REQUIREMENTS = "DEX_SP_FRD_036, DEX_SP_FRD_037, DEX_SP_FRD_038, DEX_SP_FRD_039";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_036_037_038_039_Admin_Accounts_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_036_037_038_039_Admin_Accounts_Screen() {
        VERSIONHISTORY.add("1.0;28FEB2023;Per 208: Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_036_037_038_039_Admin_Accounts_Screen_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_036_037_038_039 – Admin Accounts Screen";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        Accounts accounts = pageObj.accounts;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.ADMIN.email, report);
        par.selectTabToPage(AccountTabs.ACCOUNTS, pageObj.accounts,report);

        accounts.verifyAllColumnHeadersDisplayed(report);
        bh.verifyDisplayedFlex(accounts.addAccountButton, "Add Account Button", report);
        report.addScreenshotStep("Accounts_Screen");

        bh.verifyDisplayedFlex(accounts.firstNameHeader, "First Name Column Ascending", report);
        accounts.clickAndVerifyAscending("First Name", report);
        report.addScreenshotStep("FirstName_Ascending");

        bh.verifyDisplayedFlex(accounts.firstNameHeader, "First Name Column Descending", report);
        accounts.clickAndVerifyDescending("First Name", report);
        report.addScreenshotStep("FirstName_Descending");

        bh.verifyDisplayedFlex(accounts.lastNameHeader, "Last Name Column Ascending", report);
        accounts.clickAndVerifyAscending("Last Name", report);
        report.addScreenshotStep("LastName_Ascending");

        bh.verifyDisplayedFlex(accounts.lastNameHeader, "Last Name Column Descending", report);
        accounts.clickAndVerifyDescending("Last Name", report);
        report.addScreenshotStep("LastName_Descending");

        bh.verifyDisplayedFlex(accounts.emailHeader, "Email Address Column Ascending", report);
        accounts.clickAndVerifyAscending("Email Address", report);
        report.addScreenshotStep("Email_Ascending");

        bh.verifyDisplayedFlex(accounts.emailHeader, "Email Address Column Descending", report);
        accounts.clickAndVerifyDescending("Email Address", report);
        report.addScreenshotStep("Email_Descending", driver);

        bh.verifyDisplayedFlex(accounts.roleHeader, "Role Column Ascending", report);
        accounts.clickAndVerifyAscending("Role", report);
        report.addScreenshotStep("Role_Ascending");

        bh.verifyDisplayedFlex(accounts.roleHeader, "Role Column Descending", report);
        accounts.clickAndVerifyDescending("Role", report);
        report.addScreenshotStep("Role_Descending");

        accounts.clickAddAccount(report);
        accounts.verifyAddAccountElementsDisplayed(report);
        report.addScreenshotStep("Step12");

        bh.clickFlex(accounts.roleDropdown, "Role dropdown", report);
        accounts.verifyAllRoleDropdownOptions(report);
        report.addScreenshotStep("Step13");

        String role="Central Assessor";
        bh.selectDropDownByText(accounts.getRoleDropdown(), role, "Role Dropdown",report);
        String email = bh.getUniqueEmail();
        accounts.enterNewAccountData(role, "QA", "TesterCA", email , report);
        accounts.verifyAddValuesCorrect(role, "QA", "TesterCA", email, report);
        report.addScreenshotStep("Step14");

        accounts.clickSave(report);
        accounts.findAccountInTable(email,report);
        report.addScreenshotStep("Step15");

        accounts.clickAddAccount(report);
        role="Study Staff Lead";
        bh.selectDropDownByText(accounts.getRoleDropdown(), role, role,report);
        email = bh.getUniqueEmail();
        accounts.enterNewAccountData(role, "QA", "TesterSSL", email , report);
        accounts.verifyAddValuesCorrect(role, "QA", "TesterSSL", email, report);
        report.addScreenshotStep("Step17");

        accounts.clickSave(report);
        accounts.findAccountInTable(email,report);
        report.addScreenshotStep("Step18");

        accounts.clickAddAccount(report);
        role="Study Staff";
        bh.selectDropDownByText(accounts.getRoleDropdown(), role, role,report);
        email = bh.getUniqueEmail();
        accounts.enterNewAccountData(role, "QA", "TesterSS", email , report);
        accounts.verifyAddValuesCorrect(role, "QA", "TesterSS", email, report);
        report.addScreenshotStep("Step20");

        accounts.clickSave(report);
        accounts.findAccountInTable(email,report);
        report.addScreenshotStep("Step21");

        accounts.clickAddAccount(report);
        role="Clinician Lead";
        bh.selectDropDownByText(accounts.getRoleDropdown(), role, role,report);
        email = bh.getUniqueEmail();
        accounts.enterNewAccountData(role, "QA", "TesterCL", email , report);
        accounts.verifyAddValuesCorrect(role, "QA", "TesterCL", email, report);
        report.addScreenshotStep("Step23");
        accounts.clickSave(report);
        accounts.findAccountInTable(email,report);
        report.addScreenshotStep("Step24");

        accounts.clickAddAccount(report);
        role="Clinician";
        bh.selectDropDownByText(accounts.getRoleDropdown(), role, role,report);
        email = bh.getUniqueEmail();
        accounts.enterNewAccountData(role, "QA", "TesterC", email , report);
        accounts.verifyAddValuesCorrect(role, "QA", "TesterC", email, report);
        report.addScreenshotStep("Step26");
        accounts.clickSave(report);
        accounts.findAccountInTable(email,report);
        report.addScreenshotStep("Step27");

        accounts.clickAddAccount(report);
        role="Central Assessor";
        bh.selectDropDownByText(accounts.getRoleDropdown(), role, role,report);
        email = bh.getUniqueEmail();
        accounts.enterNewAccountData(role, "QA", "TesterCancel", email , report);
        accounts.verifyAddValuesCorrect(role, "QA", "TesterCancel", email, report);
        report.addScreenshotStep("Step29");

        accounts.clickCancelToDismiss("Add Account modal",report);
        accounts.verifyAccountNotInTable(email, report);
        report.addScreenshotStep("Step30");
    }
}
