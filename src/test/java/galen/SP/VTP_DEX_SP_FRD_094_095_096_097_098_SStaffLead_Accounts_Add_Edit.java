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

public class VTP_DEX_SP_FRD_094_095_096_097_098_SStaffLead_Accounts_Add_Edit extends BaseTest {

    public static final String OBJECTIVE = "DEX_SP_FRD_094: To verify if a Study Staff Lead user selects the “Add Account” link, the portal shall display a popup screen that allows the user to: \n" +
            "•\tSelect a user role as one of the following:  (Study Staff Lead or Study Staff) \n" +
            "•\tEnter user first name and last name \n" +
            "•\tEnter user email address \n" +
            "•\tSave or cancel adding the account\n" +
            "DEX_SP_FRD_095: To verify on the  Study Staff Lead Accounts screen, if the  Study Staff Lead User selects the “edit” link in the Action column for an account, the portal shall display an Edit Info Popup screen.\n" +
            "DEX_SP_FRD_096: To verify the Edit Info Popup Screen shall be prepopulated with the role, first name, last name and email address for the associated account and shall include the capability for the Study Staff Lead user to disable and re-enable the account.\n" +
            "DEX_SP_FRD_097: To verify if the  Study Staff Lead user selects the account status for the account (on or off) and clicks the save button, the portal shall return to the Accounts screen and shall update the account status appropriately.\n" +
            "DEX_SP_FRD_098: To verify if the Study Staff Lead user selects the account status for the account (on or off) and clicks the cancel button, the portal shall return to the Accounts screen and shall not update the status.\n";

    public static final String NOTES =
            "This protocol contains the following scenarios:\n" +
                    "-\tStudy Staff Lead is able to add the following accounts:\n" +
                    "o\tStudy Staff Lead\n" +
                    "o\tStudy Staff\n" +
                    "-\tClicking Edit link displays the Edit Account Info Modal\n-" +
                    "\tEdit Info Modal displays the following fields:\n" +
                    "o\tFirst Name\n" +
                    "o\tLast Name\n" +
                    "o\tEmail Address\n" +
                    "o\tAccount Status Toggle\n" +
                    "-\tToggle Account Status OFF and clicking Cancel does not Save selection\n" +
                    "-\tToggle Account Status OFF and Saving is reflected in the Accounts Table\n" +
                    "-\tToggle Account Status ON and clicking Cancel does not Save selection \n" +
                    "-\tToggle Account Status ON and Saving is reflected in the Accounts Table\n";

    static String REQUIREMENTS = "DEX_SP_FRD_094, DEX_SP_FRD_095, DEX_SP_FRD_096, DEX_SP_FRD_097,  DEX_SP_FRD_098";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_094_095_096_097_098_SStaffLead_Accounts_Add_Edit";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_094_095_096_097_098_SStaffLead_Accounts_Add_Edit() {
        VERSIONHISTORY.add("1.0;28FEB2023;Per 244: Initial Test Script;Tester");
        VERSIONHISTORY.add("2.0;09JUN2023;Per 343: Update Test Step to align Add Modal content with requirement;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_094_095_096_097_098_SStaffLead_Accounts_Add_Edit_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_094_095_096_097_098 – Study Staff Lead Add or Edit Accounts";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        Accounts accounts = pageObj.accounts;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.STUDY_STAFF_LEAD.email, report);
        par.selectTabToPage(AccountTabs.ACCOUNTS, pageObj.accounts,report);
        accounts.clickAddAccount(report);
        accounts.verifyAddAccountElementsDisplayed(report);
        report.addScreenshotStep("Step4_Add_Accounts_Screen");

        bh.clickFlex(accounts.roleDropdown, "Role Dropdown", report);
        bh.verifyDropdownContains(accounts.roleDropdown, "Role dropdown", "Study Staff Lead", report);
        bh.verifyDropdownContains(accounts.roleDropdown, "Role Dropdown", "Study Staff", report);
        report.addScreenshotStep("Step5_Role_Dropdown_Screen");

        String email = bh.getUniqueEmail();
        accounts.enterNewAccountData("Study Staff Lead", "QA", "TesterSSL", email, report);
        report.addScreenshotStep("Step6_Form_Fields_Data_Screen");
        accounts.clickSave(report);
        accounts.findAccountInTable(email, report);
        report.addScreenshotStep("Step7_Email_In_Table");

        accounts.clickAddAccount(report);
        String emailSS = bh.getUniqueEmail();
        accounts.enterNewAccountData("Study Staff", "QA", "TesterSS", emailSS, report);
        report.addScreenshotStep("Step9_Form_Fields_Data_Screen");

        accounts.clickSave(report);
        accounts.findAccountInTable(emailSS, report);
        report.addScreenshotStep("Step10_Email_In_Table");

        accounts.clickEditAccount(emailSS, report);
        accounts.verifyAllModalElementsDisplayed(report);
        report.addScreenshotStep("Step11_EditModal_Screen");

        accounts.clickStatusToggle(report);
        accounts.clickCancelToDismiss("Edit Account modal",report);
        accounts.verifyNotDisplayedRedDotStatus(emailSS, report);
        report.addScreenshotStep("Step12_Account_Status_NoRedDot");

        accounts.clickEditAccount(emailSS, report);
        accounts.verifyAllModalElementsDisplayed(report);
        accounts.clickStatusToggle(report);
        accounts.clickSave(report);
        accounts.verifyDisplayedRedDotStatus(emailSS, report);
        report.addScreenshotStep("Step14_Account_Status_Off");

        accounts.clickEditAccount(emailSS, report);
        accounts.verifyAllModalElementsDisplayed(report);
        accounts.clickStatusToggle(report);
        accounts.clickCancelToDismiss("Edit Account modal",report);
        accounts.verifyDisplayedRedDotStatus(emailSS, report);
        report.addScreenshotStep("Step16_Account_Status_Off");

        accounts.clickEditAccount(emailSS, report);
        accounts.verifyAllModalElementsDisplayed(report);
        accounts.clickStatusToggle(report);
        accounts.clickSave(report);
        accounts.verifyNotDisplayedRedDotStatus(emailSS, report);
        report.addScreenshotStep("Step18_Account_Status_On");
    }
}
