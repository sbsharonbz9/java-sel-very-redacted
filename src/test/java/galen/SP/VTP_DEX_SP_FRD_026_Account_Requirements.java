package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.AccountTabs;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.pages.sp.Accounts;
import galen.pages.sp.StudyAdminPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_026_Account_Requirements extends BaseTest {
    static String OBJECTIVE = "To verify Portal user Accounts require the following information to create an account \n" +
            "-\tFirst Name \n" +
            "-\tLast Name \n" +
            "-\tUser email address \n" +
            "-\tRole Designation\n";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tVerify First Name input field is required\n" +
            "o\tVerify Last Name input field is required\n" +
            "o\tVerify Email Address input field is required\n" +
            "o\tVerify Role Designation selection is required";
    static String REQUIREMENTS = "DEX_SP_FRD_026";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_026_Account_Requirements";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_026_Account_Requirements()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_SP_FRD_026_Account_Requirements_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_026 – Account Requirements";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Accounts acc  = pageObj.accounts;
        String role="Central Assessor";
        String email = bh.getUniqueEmail();
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);

        pageObj.login.logIn(RoleType.ADMIN.email, report);
        acc.selectTabToPage(AccountTabs.ACCOUNTS, pageObj.accounts,report);
        acc.clickAddAccount(report);

        bh.verifyCondition(()->bh.getCurrentDropdownOption(acc.roleDropdown).equals(""), "role " +
                "dropdown is unselected", true, report);
        report.addScreenshotStep("Step4_RoleBlank");

        acc.enterNewAccountData(role, "Test", "User", "", report);
        bh.verifyButtonEnabled(acc.saveButton, false, report);
        report.addScreenshotStep("Step5_NoEmail_SaveDisabled");

        acc.enterNewAccountData(role, "", "User", email, report);
        bh.verifyButtonEnabled(acc.saveButton, false, report);
        report.addScreenshotStep("Step6_NoFirstName");

        acc.enterNewAccountData(role, "Test", "", email, report);
        bh.verifyButtonEnabled(acc.saveButton, false, report);
        report.addScreenshotStep("Step7_NoLastName");

        acc.clickCancelToDismiss("Add Account modal",report);

        acc.clickAddAccount(report);

        // NoRoleSelected, verify Save disabled
        acc.enterNewAccountData("", "Test", "User", email, report);
        bh.verifyButtonEnabled(acc.saveButton, false, report);
        report.addScreenshotStep("Step10_NoRoleSelected");
        
        // Select role, save, verify user created
        acc.enterNewAccountData(role, "Test", "User", email, report);
        acc.clickSave(report);
        acc.findAccountInTable(email, report);
        report.addScreenshotStep("Step11_UserCreated");
    }
}
