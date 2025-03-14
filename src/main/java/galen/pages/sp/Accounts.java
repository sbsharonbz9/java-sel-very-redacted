package galen.pages.sp;

import galen.enums.SP.RoleType;
import galen.helpers.common.GalenReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class Accounts extends SPBasePage {

    public By header = By.xpath("//h1[text()='Accounts']");
    public By addAccountButton = By.xpath("//button[text()='Add Account']");

    // Add Account Modal
    public By firstNameTextField = By.xpath("//input[@id='firstname']");
    public By lastNameTextField = By.xpath("//input[@id='lastname']");
    public By emailTextField = By.xpath("//input[@id='email']");
    public By roleDropdown = By.xpath("//select[@name='role']");
    public By statusToggle = By.xpath("//div[contains(@class, 'react-toggle')]");
    public By firstNameHeader = By.xpath("//th[normalize-space()='First Name']");
    public By lastNameHeader = By.xpath("//th[normalize-space()='Last Name']");
    public By emailHeader = By.xpath("//th[normalize-space()='Email Address']");
    public By roleHeader= By.xpath("//th[normalize-space()='Role']");
    public By actionHeader = By.xpath("//th[normalize-space()='Action']");
    public By editLink = By.xpath("//button[contains(text(),'Edit')]");


    public Accounts(WebDriver driver) {
        super(driver);
        headingTitle=header;
        reportText="Accounts Screen";
        modal=By.xpath(("//div[@aria-modal='true']"));
    }

    public void clickAddAccount(@Nullable GalenReport report) {
        basicHelpers.verifyClickToNavDisplayed(addAccountButton, "Add Account button",modal,
                "Add Account modal", report);
    }

    public void clickEditAccount(String email, @Nullable GalenReport report) {
        basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), report);
        By tableEntryBy = By.xpath("//td[text()='" + email + "']/following-sibling::td/button[contains(text(),'Edit')]");
        basicHelpers.clickFlex(tableEntryBy, "Edit Account link", report);
    }

    public void clickEditByRole(String role, @Nullable GalenReport report) {
       By tableEntryBy = By.xpath("//td[text()='" + role + "']/following-sibling::td/button[contains(text(),'Edit')]");
        basicHelpers.clickFlex(tableEntryBy, "Edit Account link", report);
    }

    public void verifyAllModalElementsDisplayed(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();
        results.put("Email", emailTextField);
        results.put("First Name", firstNameTextField);
        results.put("Last Name", lastNameTextField);
        results.put("Account Status Toggle", statusToggle);
        basicHelpers.verifyElementsDisplayed(results, report);
    }

    public void verifyAllColumnHeadersDisplayed(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();
        results.put("First Name", firstNameHeader);
        results.put("Last Name", lastNameHeader);
        results.put("Email", emailHeader);
        results.put("Role", roleHeader);
        results.put("Action", actionHeader);
        results.put("Account Status Toggle", statusToggle);
        basicHelpers.verifyElementsDisplayed(results, report);
    }

    public void findAccountInTable(String email, @Nullable GalenReport report) {
        try {
            basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), report);
            basicHelpers.verifyDisplayedFlex(By.xpath("//td[text()='" + email + "']"),
                    "Email " + email + " in table", report);
        } catch (Exception ignored) {
        }
    }

    public void verifyAccountNotInTable(String email, @Nullable GalenReport report) {
        basicHelpers.verifyNotDisplayedFlex(By.xpath("//td[text()='" + email + "']"),
                "Email " + email + "not in table", report);
    }

    public void enterNewAccountData(String role, String firstName, String lastName, String email,
                                    @Nullable GalenReport report) {
        String steps = "Select role: '" + role+"'\n" +
                "Enter first name: '" + firstName+"'\n" +
                "Enter last name: '" + lastName+"'\n" +
                "Enter email: '" + email;
        try {
            basicHelpers.selectDropDownByText(roleDropdown, role, "Role: "+
                    role, null);
            basicHelpers.sendTextFlex(firstNameTextField, firstName, "First Name", null);
            basicHelpers.sendTextFlex(lastNameTextField, lastName, "Last Name", null);
            basicHelpers.sendTextFlex(emailTextField, email, "Email", null);
            if (report != null) {
                report.addStep(steps, "Account data is entered", "As expected", true, true);
            }
        } catch (Exception e) {
            if (report != null) {
                report.addStep(steps, steps, e.getMessage(), false, true);
            }
        }
    }

    public void verifyAddAccountElementsDisplayed(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();
        results.put("Role", roleDropdown);
        results.put("First Name", firstNameTextField);
        results.put("Last Name", lastNameTextField);
        results.put("Email Address", emailTextField);
        results.put("Save Button", saveButton);
        results.put("Cancel Button", btnCancel);
        basicHelpers.verifyElementsDisplayed(results, report);
    }

    public void verifyAddValuesCorrect(String role, String firstName, String lastName, String email,
                                          @Nullable GalenReport report) {
        basicHelpers.verifyText(roleDropdown,"Role", role, report);
        basicHelpers.verifyText(firstNameTextField,"First Name", firstName, report);
        basicHelpers.verifyText(lastNameTextField,"Last Name", lastName, report);
        basicHelpers.verifyText(emailTextField,"Email", email, report);
    }

    public void verifyAllRoleDropdownOptions(@Nullable GalenReport report) {
        List<String> options = basicHelpers.getAllDropdownOptions(roleDropdown);
        List<String> expectedOptions = getRolesList();
        basicHelpers.verifyCondition(() -> options.equals(expectedOptions), "Role " +
                        "Options are:\n Central Assessor\nStudy Staff Lead\nStudy Staff\nClinician Lead\nClinician",
                false, report);
    }

    public void verifyDisplayedRedDotStatus(String email, @Nullable GalenReport report) {
        basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), report);
        basicHelpers.verifyDisplayedFlex(By.xpath("//td[text()='" + email + "']/preceding-sibling::td[3]/div"), "Red Dot Status for " + email, report);
    }

    public void verifyNotDisplayedRedDotStatus(String email, @Nullable GalenReport report) {
        basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), report);
        basicHelpers.verifyNotDisplayedFlex(By.xpath("//td[text()='" + email + "']/preceding-sibling::td[3]/div"), "Red Dot Status for " + email, report);
    }

    public ArrayList<String> getRolesList() {
        return new ArrayList<>(Arrays.asList(RoleType.CENTRAL_ASSESSOR.ui_name, RoleType.STUDY_STAFF_LEAD.ui_name, RoleType.STUDY_STAFF.ui_name, RoleType.CLINICIAN_LEAD.ui_name,
                RoleType.CLINICIAN.ui_name));
    }
}