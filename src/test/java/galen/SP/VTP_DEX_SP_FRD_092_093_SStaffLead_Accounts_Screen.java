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

public class VTP_DEX_SP_FRD_092_093_SStaffLead_Accounts_Screen extends BaseTest {

    public static final String OBJECTIVE = "To verify the Study Staff Lead Accounts screen shall provide a list of accounts with the following columns \n" +
            "•\tFirst name (sortable) \n" +
            "•\tLast name (sortable) \n" +
            "•\tEmail address (sortable) \n" +
            "•\tRole (sortable) \n" +
            "•\tAction (edit)\n" +
            "To verify the Study Staff Lead Accounts screen shall provide a link to add an account\n";

    public static final String NOTES =
            "This protocol contains the following scenarios:\n" +
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
                    "o\tRole \n";

    static String REQUIREMENTS = "DEX_SP_FRD_092, DEX_SP_FRD_093";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_092_093_SStaffLead_Accounts_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_092_093_SStaffLead_Accounts_Screen() {
        VERSIONHISTORY.add("1.0;28FEB2023;Per 244: Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_092_093_SStaffLead_Accounts_Screen_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_092_093 – Study Staff Lead Accounts Screen";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        Accounts accounts = pageObj.accounts;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.STUDY_STAFF_LEAD.email, report);
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
