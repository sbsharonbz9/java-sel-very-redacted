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

public class VTP_DEX_SP_FRD_070_071_072_Clinician_Lead_Accounts_Screen extends BaseTest {
    public static final String OBJECTIVE = "DEX_SP_FRD_070: To verify the Clinician Lead Accounts screen shall provide a link to add an account" +
            " DEX_SP_FRD_071: To verify the Clinician Lead portal Accounts screen shall provide a list of accounts with the following columns" +
            " First name" +
            " Last name" +
            " Email address" +
            " Role" +
            " Action" +
            " DEX_SP_FRD_072: To verify the Clinician Lead portal Accounts screen list shall be sortable by:" +
            " First name" +
            " Last name" +
            " Email address" +
            " Role";

    public static final String NOTES = "This protocol contains the following scenarios:" +
            " Add Account link is displayed on Account Screen" +
            " The following columns are displayed in the Accounts Table:" +
            " First name" +
            " Last name" +
            " Email address" +
            " Role" +
            " Action" +
            " The following columns are sortable:" +
            " First name" +
            " Last name" +
            " Email address" +
            " Role";


    static String REQUIREMENTS = "DEX_SP_FRD_070, DEX_SP_FRD_071, DEX_SP_FRD_072 ";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_070_071_072_Clinician_Lead_Accounts_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_070_071_072_Clinician_Lead_Accounts_Screen() {
        VERSIONHISTORY.add("1.0;28FEB2023;Per 208: Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_070_071_072_Clinician_Lead_Accounts_Screen_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_070_071_072_Clinician_Lead_Accounts_Screen";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        Accounts accounts = pageObj.accounts;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.CLINICIAN_LEAD.email, report);
        par.selectTabToPage(AccountTabs.ACCOUNTS, pageObj.accounts,report);

        accounts.verifyAllColumnHeadersDisplayed(report);
        bh.verifyDisplayedFlex(accounts.addAccountButton, "Add Account Button", report);
        report.addScreenshotStep("Step3_Accounts_Screen");

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
    }
}
