package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.AccountTabs;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.GalenReport;
import galen.pages.sp.Accounts;
import galen.pages.sp.Participants;
import galen.pages.sp.StudyAdminPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_021_Add_Accounts extends BaseTest {

    public static final String OBJECTIVE = "To verify the portal shall provide the capability for an authorized user to " +
            "create study staff accounts and assign roles to those accounts.";
    public static final String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tAdmin Role ability to add the following accounts:\n" +
            "\uF0A7\tCentral Assessor\n" +
            "\uF0A7\tStudy Staff Lead\n" +
            "\uF0A7\tStudy Staff\n" +
            "\uF0A7\tClinician Lead\n" +
            "\uF0A7\tClinician\n" +
            "o\tStudy Staff Lead Role ability to add the following accounts:\n" +
            "\uF0A7\tStudy Staff Lead\n" +
            "\uF0A7\tStudy Staff\n" +
            "o\tClinician Lead Role ability to add the following accounts:\n" +
            "\uF0A7\tClinician Lead\n" +
            "\uF0A7\tClinician";

    static String REQUIREMENTS = "DEX_SP_FRD_021";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_021_Add_Accounts";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;

    VTP_DEX_SP_FRD_021_Add_Accounts() {
        VERSIONHISTORY.add("1.0;28FEB2023;Per 193: Initial Test Script;Tester");
        VERSIONHISTORY.add("2.0;17MAY2023;Update Test Steps for correct language;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_021_Add_Accounts_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_021 – Add Study Portal Accounts";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        Accounts accounts = pageObj.accounts;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.ADMIN.email, report);
        par.selectTabToPage(AccountTabs.ACCOUNTS, pageObj.accounts,report);
        accounts.clickAddAccount(report);
        accounts.verifyAddAccountElementsDisplayed(report);
        report.addScreenshotStep("Step4_Add_Accounts_Screen");

        String email = bh.getUniqueEmail();
        accounts.clickAddAccount(report);
        accounts.enterNewAccountData("Central Assessor", "QA_021_CA", "TesterCA_021", email, report);
        accounts.clickSave(report);
        accounts.findAccountInTable(email, report);
        report.addScreenshotStep("Step5_CA_In_Table");

        email = bh.getUniqueEmail();
        accounts.clickAddAccount(report);
        accounts.enterNewAccountData("Study Staff Lead", "QA_021_SSL", "TesterSSL_021", email, report);
        accounts.clickSave(report);
        accounts.findAccountInTable(email, report);
        report.addScreenshotStep("Step6_SSL_In_Table");

        email = bh.getUniqueEmail();
        accounts.clickAddAccount(report);
        accounts.enterNewAccountData("Study Staff", "QA_021_SS", "TesterSS_021", email, report);
        accounts.clickSave(report);
        accounts.findAccountInTable(email, report);
        report.addScreenshotStep("Step7_SS_In_Table");

        email = bh.getUniqueEmail();
        accounts.clickAddAccount(report);
        accounts.enterNewAccountData("Clinician Lead", "QA_021_CL", "TesterCL_021", email, report);
        accounts.clickSave(report);
        accounts.findAccountInTable(email, report);
        report.addScreenshotStep("Step8_ClinicianLead_In_Table");

        email = bh.getUniqueEmail();
        accounts.clickAddAccount(report);
        accounts.enterNewAccountData("Clinician", "QA_021_C", "TesterC_021", email, report);
        accounts.clickSave(report);
        accounts.findAccountInTable(email, report);
        report.addScreenshotStep("Step9_C_In_Table");

        accounts.logout(report);

        pageObj.login.logIn(RoleType.STUDY_STAFF_LEAD.email, report);
        par.selectTabToPage(AccountTabs.ACCOUNTS, pageObj.accounts,report);
        accounts.clickAddAccount(report);

        bh.clickFlex(accounts.roleDropdown, "Role Dropdown", report);
        bh.verifyDropdownContains(accounts.roleDropdown, "Role Dropdown","Study Staff Lead", report);
        bh.verifyDropdownContains(accounts.roleDropdown, "Role Dropdown","Study Staff", report);
        report.addScreenshotStep("Step13_Role_Dropdown_Screen");

        email = bh.getUniqueEmail();
        accounts.clickAddAccount(report);
        accounts.enterNewAccountData("Study Staff Lead", "QA_021_SSL", "TesterSSL_021", email, report);
        accounts.clickSave(report);
        accounts.findAccountInTable(email, report);
        report.addScreenshotStep("Step14_SSL_In_Table");

        email = bh.getUniqueEmail();
        accounts.clickAddAccount(report);
        accounts.enterNewAccountData("Study Staff", "QA_021_SS", "TesterSS_021", email, report);
        accounts.clickSave(report);
        accounts.findAccountInTable(email, report);
        report.addScreenshotStep("Step16_In_Table");

        accounts.logout(report);

        pageObj.login.logIn(RoleType.CLINICIAN_LEAD.email, report);
        par.selectTabToPage(AccountTabs.ACCOUNTS, pageObj.accounts,report);
        accounts.clickAddAccount(report);

        bh.clickFlex(accounts.roleDropdown, "Role Dropdown", report);
        bh.verifyDropdownContains(accounts.roleDropdown, "Role Dropdown","Clinician Lead", report);
        bh.verifyDropdownContains(accounts.roleDropdown,"Role Dropdown", "Clinician", report);
        report.addScreenshotStep("Step20_Role_Dropdown_Screen");

        email = bh.getUniqueEmail();
        accounts.clickAddAccount(report);
        accounts.enterNewAccountData("Clinician Lead", "QA_021_CL", "TesterCL_021", email, report);
        accounts.clickSave(report);
        accounts.findAccountInTable(email, report);
        report.addScreenshotStep("Step21_ClinicianLead_In_Table");

        email = bh.getUniqueEmail();
        accounts.clickAddAccount(report);
        accounts.enterNewAccountData("Clinician", "QA_021_C", "TesterC_021", email, report);
        accounts.clickSave(report);
        accounts.findAccountInTable(email, report);
        report.addScreenshotStep("Step23_C_In_Table");
    }
}
