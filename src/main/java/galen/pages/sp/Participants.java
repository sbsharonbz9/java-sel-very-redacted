package galen.pages.sp;

import galen.helpers.common.GalenReport;
import galen.helpers.sp.ParticipantClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;

import static java.lang.Thread.sleep;

public class Participants extends SPBasePage {

    //Table
    public By parHeader = By.xpath("//h1[text()='Participants']");
    public By participantList = By.xpath("//table[@role='table']");
    public By participants = By.tagName("tr");

    // Table headers
    public By participantIdHeader = By.xpath("//th[normalize-space()='Participant ID']");
    public By participantEmailHeader = By.xpath("//th[normalize-space()='Email Address']");
    public By participantInitialOutcomeHeader= By.xpath("//th[normalize-space()='Initial Outcome']");
    public By participantSurveyDateHeader= By.xpath("//th[normalize-space()='Heath Survey Date']");
    public By actionHeader = By.xpath("//th[normalize-space()='Action']");

    // Records
    public By viewRecordsButton = By.xpath("//button[contains(text(), 'View Records')]");
    public By editButton = By.xpath("//button[contains(text(), 'Edit')]");
    public By closeRecordButton = By.xpath("//button[contains(text(), 'Close Record')]");

    // Add Participant Modal
    public By emailField = By.id("participant-email");
    public By studyIDDropdown = By.name("studyid");
    public By siteIDDropdown = By.name("siteid");
    public By createUserButton = By.xpath("//button[text()='Create user']");

    // Edit Participant Modal
    public By activeToggle = By.xpath("//div[contains(@class, 'react-toggle')]");
    public By inactiveToggle = By.xpath("//div[contains(@class,'react-toggle') and not (" +
            "contains(@class, 'checked'))]");
    public By editEmailField = By.xpath("//input[@id='email']");
    public By resetEmailLink = By.xpath("//*[text()='Resend Invitation Email Link']");
    public By addParticipantButton = By.xpath("//button[text()='Add Participant']");
    public By modals = By.xpath("//div[@class='ReactModalPortal']/div[contains(@class, 'after-open')]");

    public Participants(WebDriver driver) {
        super(driver);
        headingTitle = parHeader;
        reportText = "Participant Screen";
        modal = modals;
    }

    public WebElement getEmail() {
        return basicHelpers.getWebElement(emailField);
    }

    public void addParticipant(String email, String studyID, String siteID, @Nullable GalenReport report) {
        enterNewParticipantData(email, studyID,siteID, report);
        basicHelpers.clickFlex(createUserButton, "Create User", report);
    }

    public void editParticipant(ParticipantClass par, @Nullable GalenReport report) {
        basicHelpers.sendTextFlex(editEmailField, par.email, "Email", report);
        basicHelpers.verifyClickToNavNotDisplayed(saveChangesButton, "Save Changes",
                modals, "Edit Participant modal",report);
    }

    public String getEnabledStudyID() {
        return basicHelpers.getRandomDropdownOption(studyIDDropdown);
    }

    public String getEnabledSiteID() {
        return basicHelpers.getRandomDropdownOption(siteIDDropdown);
    }

    public void verifyCreateUserEnabledDisabled(boolean isEnabled, @Nullable GalenReport report) {
        basicHelpers.verifyButtonEnabled(createUserButton, isEnabled, report);
    }

    public void openAddParticipant(@Nullable GalenReport report) {
        basicHelpers.verifyClickToNavDisplayed(addParticipantButton, "Add Participant", createUserButton,
                "Add Participant modal", report);
    }

    public void verifyAllModalElementsDisplayed(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();
        results.put("Email", emailField);
        results.put("Study ID", studyIDDropdown);
        results.put("Site ID", siteIDDropdown);
        results.put("Create User button", createUserButton);
        basicHelpers.verifyElementsDisplayed(results, report);
    }

    public void verifyAllEditModalElementsDisplayed(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();

        results.put("Email", editEmailField);
        results.put("Study ID", studyIDDropdown);
        results.put("Site ID", siteIDDropdown);
        results.put("Save Changes button", saveChangesButton);
        results.put("Cancel button", btnCancel);
        basicHelpers.verifyElementsDisplayed(results, report);
    }

    public void verifyAllAddModalFilledIn(String email, String studyID, String siteID, @Nullable GalenReport report) {
        basicHelpers.verifyText(emailField, "Email", email, report);
        basicHelpers.verifyCurrentDropdownValue(studyIDDropdown, "Study ID dropdown",
                studyID, report);
        basicHelpers.verifyCurrentDropdownValue(siteIDDropdown, "SiteID dropdown",
                siteID, report);
    }

    public void verifySSElementsDisplayed(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();
        results.put("Add Participant Button", addParticipantButton);
        results.put("Edit Participant Button", editButton);
        results.put("View Records", viewRecordsButton);
        basicHelpers.verifyElementsDisplayed(results, report);
    }

    public void selectStudyID(String value, @Nullable GalenReport report) {
        basicHelpers.selectDropDownByText(studyIDDropdown, value, "Study ID", report);
    }

    public void selectSiteID(String value, @Nullable GalenReport report) {
        basicHelpers.selectDropDownByText(siteIDDropdown, value, "Site ID", report);
    }

    public void enterEmail(String email, @Nullable GalenReport report) {
        basicHelpers.sendTextFlex(emailField, email, "Email", report);
    }

    public void clickCreateUser(@Nullable GalenReport report) {
        basicHelpers.clickFlex(createUserButton, "Create User", report);
    }

    public void clickViewRecords(@Nullable GalenReport report) {
        basicHelpers.verifyClickToPageTransition(new ViewRecords(driver),viewRecordsButton,"View Records", report);
    }

    public void clickEditPart(@Nullable GalenReport report) {
        basicHelpers.clickFlex(editButton, "Edit Participant", report);
    }

    public void clickCloseButton(@Nullable GalenReport report) {
        basicHelpers.clickFlex(btnCancel, "Close Modal", report);
    }

    public void clickCloseRecords(@Nullable GalenReport report) {
        basicHelpers.clickFlex(closeRecordButton, "Close Records", report);
    }

    public void verifyDisplayedRedDotStatus(String email, @Nullable GalenReport report) {

        basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), null);
        basicHelpers.verifyDisplayedFlex(By.xpath("//td[text()='" + email + "']/preceding-sibling::td[2]/div"),
                "Red Dot Status for " + email, report);
    }

    public void verifyNotDisplayedRedDotStatus(String email, @Nullable GalenReport report) {
        basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), null);
        basicHelpers.verifyNotDisplayedFlex(By.xpath("//td[text()='" + email + "']/preceding-sibling::td[2]/div"),
                "Red Dot Status for " + email, report);
    }

    public void clickEditParticipant(String email, @Nullable GalenReport report) {
        basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), report);
        By tableEntryBy = By.xpath("//td[text()='" + email + "']/following-sibling::td/div/button[contains(text(),'Edit')]");
        basicHelpers.clickFlex(tableEntryBy, "Edit participant link", report);
    }

    public void clickViewRecords(String email, @Nullable GalenReport report) {
        basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), report);
        By tableEntryBy = By.xpath("//td[text()='" + email + "']/following-sibling::td/div/button[contains(text(),'View Records')]");
        basicHelpers.verifyClickToPageTransition(new ViewRecords(driver),tableEntryBy,"View Records", report);
    }

    public void findEmailInTable(String email, @Nullable GalenReport report) {
        try {
            sleep(1000);
            basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), report);
            basicHelpers.verifyDisplayedFlex(By.xpath("//td[text()='" + email + "']"), "Email " + email, report);
        } catch (Exception ignored) {
        }
    }

    public void enterNewParticipantData(String email, String study, String site, @Nullable GalenReport report) {
        String steps = "Enter email: '" + email+"'\n" +
                "Select Study: '" + study+"'\n" +
                "Select Site: '" + site;
        try {
            basicHelpers.sendTextFlex(emailField, email, "Email", report);
            basicHelpers.selectDropDownByText(studyIDDropdown, study, "Study: "+study, report);
            basicHelpers.selectDropDownByText(siteIDDropdown, site, "Site: "+site, report);
            if (report != null) {
                report.addStep(steps, "Participant data is filled in", "As expected", true, true);
            }
        } catch (Exception e) {
            if (report != null) {
                report.addStep(steps, steps, e.getMessage(), false, true);
            }
        }
        verifyModalDisplayed(report);
    }

    public void verifyEmailNotInTable(String email, @Nullable GalenReport report) {
        basicHelpers.verifyNotDisplayedFlex(By.xpath("//td[text()='" + email + "']"), "Email " + email, report);
    }

    public void verifyParticipantIDFormat(ParticipantClass par, @Nullable GalenReport report) {

        By tableEntryBy = By.xpath("//td[text()='" + par.email + "']/preceding-sibling::td[contains(text(),'" +
                par.studyID + "')]");
        WebElement tableEntry = basicHelpers.getWebElement(tableEntryBy);
        if (tableEntry != null) {
            par.participantID = tableEntry.getText();
            basicHelpers.verifyCondition(() ->
                            par.participantID.contains(par.studyID + "-" + par.siteID + "-"),
                    "Participant ID " + par.participantID +
                            " format contains study ID " + par.studyID + " and site id " + par.siteID, false, report);
        } else {
            basicHelpers.verifyDisplayedFlex(tableEntryBy, "Table entry  for user " + par.email, report);
        }
    }

    public void viewRecordWithMultipleAssessments(@Nullable GalenReport report) {
        int i=0;
        ViewRecords view = new ViewRecords(driver);
        int p = basicHelpers.getAllWebElements(participants).size();
        while ( i< p) {
            basicHelpers.clickFlex(basicHelpers.getAllWebElements(viewRecordsButton).get(i), "record",null);
            view.verifyAtPage();
            try {
                sleep(500);
            } catch (InterruptedException ignored) {
            }
            if (view.getAllCheckboxes().size() >= 2) {
                break;
            }
            view.clickCloseRecordButton(null);
            verifyAtPage();
            i++;
        }

        basicHelpers.verifyActionToPageDisplayed("Click View Records on a participant with multiple assessments",
                view, "View Records page", report);
    }

    public void verifySS_CA_HeadersDisplayed(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();
        results.put("Participant ID Header", participantIdHeader);
        results.put("Participant Email Header", participantEmailHeader);
        results.put("Initial Outcome Header", participantInitialOutcomeHeader);
        results.put("Survey Date Header", participantSurveyDateHeader);
        results.put("Action Header", actionHeader);
        basicHelpers.verifyElementsDisplayed(results, report);
    }

    public void verifyHeadersDisplayed(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();
        results.put("Participant ID Header", participantIdHeader);
        results.put("Participant Email Header", participantEmailHeader);
        results.put("Action Header", actionHeader);
        basicHelpers.verifyElementsDisplayed(results, report);
    }
}