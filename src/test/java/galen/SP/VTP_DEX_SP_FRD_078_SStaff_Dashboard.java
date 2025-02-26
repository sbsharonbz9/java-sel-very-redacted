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

public class VTP_DEX_SP_FRD_078_SStaff_Dashboard extends BaseTest {
    static String OBJECTIVE = "To verify the Study Staff portal dashboard screen shall a menu to allow an authorized user to: \n" +
            "View a list of participants\n" +
            "View metrics records\n" +
            "Reset password";

    static String NOTES = "This protocol contains the following scenarios: \n" +
            "Verify User can see list of participants (Participants Screen)\n" +
            "Verify user can see View Records Screen with list of health surveys\n" +
            "Verify user is able to see reset password fields (My Password Screen)";

    static String REQUIREMENTS = "DEX_SP_FRD_078";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_078_SStaff_Dashboard";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_078_SStaff_Dashboard() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 173;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_078_SStaff_Dashboard_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_078_SStaff_Dashboard";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        MyPassword myP = pageObj.myPassword;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.STUDY_STAFF.email, report);
        par.verifyTabsDisplayed( RoleType.STUDY_STAFF.tabs, report);
        bh.verifyDisplayedFlex(par.participantList, "Participant  List is Displayed", report);
        report.addScreenshotStep("Clinician_Participant");

        par.clickViewRecords(report);
        report.addScreenshotStep("Records Screen");

        par.clickCloseRecords(report);
        par.selectTabToPage(AccountTabs.MY_PASSWORD, myP,report);
        report.addScreenshotStep("My Password");
    }

}
