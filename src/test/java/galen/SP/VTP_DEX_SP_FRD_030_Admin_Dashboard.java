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
import java.util.HashMap;

public class VTP_DEX_SP_FRD_030_Admin_Dashboard extends BaseTest {
    public static final String OBJECTIVE =
            "To verify the portal dashboard shall provide the capability for an IE Admin user to: \n" +
                    "-\tView a table of participants \n" +
                    "-\tView table of accounts - these are admin staff roles, not participants \n" +
                    "-\tView metrics records \n" +
                    "-\tView/configure table of study IDs \n" +
                    "-\tView/configure table of sites \n" +
                    "-\tReset password \n";

    public static final String NOTES =
            "This protocol contains the following scenarios:\n" +
                    "o\tVerify user can see all navigation tabs\n" +
                    "o\tVerify User can see table of participants (Participants Screen)\n" +
                    "o\tVerify user can see table of all accounts (Accounts Screen)\n" +
                    "o\tVerify user is able to see metrics records (Records Screen)\n" +
                    "o\tVerify user is able to see table of Studies (Studies Screen)\n" +
                    "o\tVerify user is able to see table of Sites (Sites Screen)\n" +
                    "o\tVerify user is able to see reset password fields (My Password Screen)\n" +
                    "Network tab needs to be open during the running of the protocol.";
    static String REQUIREMENTS = "DEX_SP_FRD_030";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_030_Admin_Dashboard";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_030_Admin_Dashboard() {
        VERSIONHISTORY.add("1.0;28FEB2023;Per 201: Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_030_Admin_Dashboard_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_030 – Admin Dashboard View";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        commonPageFeatures = new CommonPageFeatures(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);

        pageObj.login.logIn(RoleType.ADMIN.email, report);
        par.verifyTabsDisplayed( RoleType.ADMIN.tabs, report);
        bh.verifyDisplayedFlex(par.table, "Admin Participant table",report);
        report.addScreenshotStep("Step2_ParticipantsTab");

        par.selectTabToPage(AccountTabs.ACCOUNTS, pageObj.accounts,report);
        bh.verifyDisplayedFlex(pageObj.accounts.table, "Admin Accounts table",report);
        report.addScreenshotStep("Step3_AccountsTab");

        par.selectTabToPage(AccountTabs.RECORDS, pageObj.downloadRecords,report);
        report.addScreenshotStep("Step4_Records");

        pageObj.downloadRecords.selectTabToPage(AccountTabs.STUDY, pageObj.studies,report);
        bh.verifyDisplayedFlex(pageObj.studies.table, "Admin Studies table",report);
        report.addScreenshotStep("Step5_Studies");

        pageObj.studies.selectTabToPage(AccountTabs.STUDY, pageObj.studies,report);
        bh.verifyDisplayedFlex(pageObj.sites.table, "Admin Sites table",report);
        report.addScreenshotStep("Step6_Site");

        pageObj.sites.selectTabToPage(AccountTabs.MY_PASSWORD, pageObj.myPassword,report);
        report.addScreenshotStep("Step7_MyPassword");
    }
}
