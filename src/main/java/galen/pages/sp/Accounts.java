package galen.pages.sp;

import galen.enums.SP.RoleType;
import galen.helpers.common.GalenReport;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static jodd.util.ThreadUtil.sleep;

public class Accounts extends SPBasePage {

    public By header = By.xpath("//h1[text()='Accounts']");
    public By addAccountButton = By.xpath("//button[text()='Add Account']");

    // Add Account Modal
    public By saveButton = By.xpath("//button[text()='Save']");
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

    public WebElement getSaveButton() { return basicHelpers.getWebElement(saveButton);}

    public WebElement getAccountButton() { return basicHelpers.getWebElement(addAccountButton);}

    public WebElement getFirstNameField() { return basicHelpers.getWebElement(firstNameTextField);}

    public WebElement getLastNameField() { return basicHelpers.getWebElement(lastNameTextField);}

    public WebElement getEmailField() { return basicHelpers.getWebElement(emailTextField);}

    public WebElement getRoleDropdown() { return basicHelpers.getWebElement(roleDropdown);}

    public WebElement getFirstNameHeader() { return basicHelpers.getWebElement(firstNameHeader);}

    public WebElement getLastNameHeader() { return basicHelpers.getWebElement(lastNameHeader);}

    public WebElement getEmailHeader() { return basicHelpers.getWebElement(emailHeader);}

    public WebElement getRoleHeader() { return basicHelpers.getWebElement(roleHeader);}

    public void clickSave(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getSaveButton(), "Save Button", report);
        sleep(1500);
    }

    public void clickFirstNameHeader(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getFirstNameHeader(), "First Name", report);
    }

    public void clickEmailHeader(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getEmailHeader(), "Email Address", report);
    }

    public void clickLastNameHeader(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getLastNameHeader(), "Last Name", report);
    }

    public void clickRoleHeader(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getRoleHeader(), "Role", report);
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

    public void clickEditByRole(RoleType role, @Nullable GalenReport report) {
       By tableEntryBy = By.xpath("//td[text()='" + role.ui_name + "']/following-sibling::td/button[contains(text(),'Edit')]");
        basicHelpers.clickFlex(tableEntryBy, "Edit Account link", report);
    }

    public void clickEditAnyAccount(@Nullable GalenReport report) {
        basicHelpers.clickFlex(editLink, "Edit Account link", report);
    }

    public boolean verifyAllModalElementsDisplayed(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();
        results.put("Email", emailTextField);
        results.put("First Name", firstNameTextField);
        results.put("Last Name", lastNameTextField);
        results.put("Account Status Toggle", statusToggle);
        return basicHelpers.verifyElementsDisplayed(results, report);
    }

    public boolean findAccountInTable(String email, @Nullable GalenReport report) {
        try {
            basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), report);
            return basicHelpers.verifyDisplayedFlex(By.xpath("//td[text()='" + email + "']"),
                    "Email " + email+ " in table", report);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyAccountNotInTable(String email, @Nullable GalenReport report) {
        return basicHelpers.verifyNotDisplayedFlex(By.xpath("//td[text()='" + email + "']"),
                "Email " + email+ "not in table", report);
    }

    public void enterNewAccountData(String role, String firstName, String lastName, String email,
                                    @Nullable GalenReport report) {
        String steps = "Select role: '" + role+"'\n" +
                "Enter first name: '" + firstName+"'\n" +
                "Enter last name: '" + lastName+"'\n" +
                "Enter email: '" + email;
        try {
            basicHelpers.selectDropDownByText(getRoleDropdown(), role, "Role: "+
                    role, report);
            basicHelpers.sendTextFlex(getFirstNameField(), firstName, "First Name", report);
            basicHelpers.sendTextFlex(getLastNameField(), lastName, "Last Name", report);
            basicHelpers.sendTextFlex(getEmailField(), email, "Email", report);

            if (report != null) {
                report.addStep(steps, "Account data is entered", "As expected", true, true);
            }
        } catch (Exception e) {
            if (report != null) {
                report.addStep(steps, steps, e.getMessage(), false, true);
            }
        }
        verifyModalDisplayed(report);
    }

    public boolean verifyAddAccountElementsDisplayed(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();
        results.put("Role", roleDropdown);
        results.put("First Name", firstNameTextField);
        results.put("Last Name", lastNameTextField);
        results.put("Email Address", emailTextField);
        results.put("Save Button", saveButton);
        results.put("Cancel Button", btnCancel);
        return basicHelpers.verifyElementsDisplayed(results, report);
    }

    public void verifyAddValuesCorrect(String role, String firstName, String lastName, String email,
                                          @Nullable GalenReport report) {
        basicHelpers.verifyText(getRoleDropdown(),"Role", role, report);
        basicHelpers.verifyText(getFirstNameField(),"First Name", firstName, report);
        basicHelpers.verifyText(getLastNameField(),"Last Name", lastName, report);
        basicHelpers.verifyText(getEmailField(),"Email", email, report);
    }

    public boolean verifyAllRoleDropdownOptions(@Nullable GalenReport report) {
        List<String> options = basicHelpers.getAllDropdownOptions(roleDropdown);
        List<String> expectedOptions = new ArrayList<>(Arrays.asList("Central Assessor",
                "Study Staff Lead", "Study Staff", "Clinician Lead", "Clinician"));
        return basicHelpers.verifyCondition(()->options.equals(expectedOptions), "Role " +
                "Options are:\n Central Assessor\nStudy Staff Lead\nStudy Staff\nClinician Lead\nClinician",
                false, report);
    }

    public boolean verifyDisplayedRedDotStatus(String email, @Nullable GalenReport report) {
        basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), report);
        return basicHelpers.verifyDisplayedFlex(By.xpath("//td[text()='"+email+"']/preceding-sibling::td[3]/div"), "Red Dot Status for " + email, report);
    }

    public boolean verifyNotDisplayedRedDotStatus(String email, @Nullable GalenReport report) {
        basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), report);
        return basicHelpers.verifyNotDisplayedFlex(By.xpath("//td[text()='"+email+"']/preceding-sibling::td[3]/div"), "Red Dot Status for " + email, report);
    }
}