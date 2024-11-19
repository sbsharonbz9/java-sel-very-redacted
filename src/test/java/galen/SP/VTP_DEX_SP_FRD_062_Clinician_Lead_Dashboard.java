package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.AccountTabs;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.pages.sp.MyPassword;
import galen.pages.sp.Participants;
import galen.pages.sp.StudyAdminPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_062_Clinician_Lead_Dashboard extends BaseTest {


    static String OBJECTIVE = "DEX_SP_FRD_062: To verify Clinician Lead portal dashboard screen shall a menu to allow an authorized user to:  \n" +
            "View a list of participants  \n" +
            "View list of accounts \n" +
            "Reset password  ";
    static String NOTES = "This protocol contains the following scenarios: \n" +
            "Verify User can see list of participants (Participants Screen) \n" +
            "Verify user can see list of all accounts (Accounts Screen) \n" +
            "Verify user is able to see reset password fields (My Password Screen) \n" +
            "Network tab needs to be open during the running of the protocol. ";
    static String REQUIREMENTS = "DEX_SP_FRD_062";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_062_Clinician_Lead_Dashboard";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_062_Clinician_Lead_Dashboard() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 173;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_062_Clinician_Lead_Dashboard_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_062_Clinician_Lead_Dashboard";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        MyPassword myP = new MyPassword(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.CLINICIAN_LEAD.email, report);
        par.verifyTabsDisplayed( RoleType.CLINICIAN_LEAD.tabs, report);
        bh.verifyDisplayedFlex(par.participantList, "Participant  List is Displayed", report);
        report.addScreenshotStep("Clinician_Participant");

        par.selectTabToPage(AccountTabs.ACCOUNTS, pageObj.accounts,report);
        bh.verifyDisplayedFlex(par.participantList, "Accounts  List is Displayed", report);
        report.addScreenshotStep("Account List");

        par.clickCloseRecords(report);
        myP.selectTabToPage(AccountTabs.MY_PASSWORD, myP,report);
        report.addScreenshotStep("Clinician_My Password");
    }
}
