package galen.pages.sp;

import galen.helpers.common.GalenReport;
import galen.helpers.sp.ParticipantClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;

public class Participants extends SPBasePage {

    //Table
    public By parHeader = By.xpath("//h1[text()='Participants']");
    public By participantList = By.xpath("//table[@role='table']");
    public By participants = By.tagName("tr");
    public By participantId = By.xpath("//h1[contains(normalize-space(), 'Participant')]");
    public By participantEmail = By.xpath("//span[contains(normalize-space(), 'Email')]");
    public By participantIdHeader = By.xpath("//th[normalize-space()='Participant ID']"); // Use this
    public By participantIdDropdown = By.xpath("//th[normalize-space()='Participant ID']"); // Redundant but can't
    // delete because it is used in several tests
    public By participantEmailHeader = By.xpath("//th[normalize-space()='Email Address']"); // use this
    public By emailAddressId = By.xpath("//th[normalize-space()='Email Address']"); // Redundant but can't
    // delete because it is used in several tests
    public By participantInitialOutcomeHeader= By.xpath("//th[normalize-space()='Initial Outcome']");
    public By participantSurveyDateHeader= By.xpath("//th[normalize-space()='Heath Survey Date']");
    public By actionHeader = By.xpath("//th[normalize-space()='Action']");
    public By assessmentType=By.xpath("//th[normalize-space()='Assessment Type']");
    public By completedBy=By.xpath("//th[normalize-space()='Completed By']");
    public By completedDate=By.xpath("//th[normalize-space()='Completed Date']");

    // Records
    public By viewRecordsButton = By.xpath("//button[contains(text(), 'View Records')]");
    public By editButton = By.xpath("//button[contains(text(), 'Edit')]");
    public By closeRecordButton = By.xpath("//button[contains(text(), 'Close Record')]");

    // Add Participant Modal
    public By emailField = By.id("participant-email");
    public By studyIDDropdown = By.name("studyid");
    public By siteIDDropdown = By.name("siteid");
    public By createUserButton = By.xpath("//button[text()='Create user']");
    public By addRecordButton = By.xpath("//button[contains(text(), 'Add Record')]");

    // Edit Participant Modal
    public By activeToggle = By.xpath("//div[contains(@class, 'react-toggle')]");
    public By inactiveToggle = By.xpath("//div[contains(@class,'react-toggle') and not (" +
            "contains(@class, 'checked'))]");
    public By editEmailField = By.xpath("//input[@id='email']");
    public By resetEmailLink = By.xpath("//*[text()='Resend Invitation Email Link']");
    public By saveChangesButton = By.xpath("//button[text()='Save Changes']");
    public By addParticipantButton = By.xpath("//button[text()='Add Participant']");
    public By modals = By.xpath("//div[@class='ReactModalPortal']/div[contains(@class, 'after-open')]");

    public Participants(WebDriver driver) {
        super(driver);
        headingTitle = parHeader;
        reportText = "Participant Screen";
        modal = modals;
    }

    public WebElement getAddParticipantButton() {
        return basicHelpers.getWebElement(addParticipantButton);
    }

    public WebElement getEmail() {
        return basicHelpers.getWebElement(emailField);
    }

    public WebElement getEditEmail() {
        return basicHelpers.getWebElement(editEmailField);
    }

    public WebElement getStudyID() {
        return basicHelpers.getWebElement(studyIDDropdown);
    }

    public WebElement getSiteID() {
        return basicHelpers.getWebElement(siteIDDropdown);
    }

    public WebElement getCreateUserButton() {
        return basicHelpers.getWebElement(createUserButton);
    }

    public WebElement getViewRecordsButton() {return basicHelpers.getWebElement(viewRecordsButton);}

    public WebElement getAddRecordButton() {return basicHelpers.getWebElement(addRecordButton);}

    public WebElement getEditButton() {
        return basicHelpers.getWebElement(editButton);
    }

    public WebElement getParticipantIdDropdown() {return basicHelpers.getWebElement(participantIdHeader);}

    public WebElement getAssessmentType() {return basicHelpers.getWebElement(assessmentType);}

    public WebElement getCompletedBy() {return basicHelpers.getWebElement(completedBy);}

    public WebElement getCompletedDate() {return basicHelpers.getWebElement(completedDate);}

    public WebElement getCloseRecords() {
        return basicHelpers.getWebElement(closeRecordButton);
    }

    public WebElement getEmailAddressIdColumn() {
        return basicHelpers.getWebElement(emailAddressId);
    }

    public WebElement getInitialOutcomeColumn() {
        return basicHelpers.getWebElement(participantInitialOutcomeHeader);
    }

    public WebElement getSurveyDatedColumn() {
        return basicHelpers.getWebElement(participantSurveyDateHeader);
    }

    public void addParticipant(String email, String studyID, String siteID, @Nullable GalenReport report) {
        enterNewParticipantData(email, studyID,siteID, report);
        basicHelpers.clickFlex(createUserButton, "Create User", report);
    }

    public void editParticipant(ParticipantClass par, @Nullable GalenReport report) {
        basicHelpers.sendTextFlex(getEditEmail(), par.email, "Email", report);
        basicHelpers.verifyClickToNavNotDisplayed(saveChangesButton, "Save Changes",
                modals, "Edit Participant modal",report);
    }

    public String getEnabledStudyID() {
        return basicHelpers.getRandomDropdownOption(studyIDDropdown);
    }

    public String getEnabledSiteID() {
        return basicHelpers.getRandomDropdownOption(siteIDDropdown);
    }

    public boolean verifyCreateUserEnabledDisabled(boolean isEnabled, @Nullable GalenReport report) {
        return basicHelpers.verifyButtonEnabled(getCreateUserButton(), isEnabled, report);
    }

    public boolean openAddParticipant(@Nullable GalenReport report) {
        return basicHelpers.verifyClickToNavDisplayed(addParticipantButton, "Add Participant", createUserButton,
                "Add Participant modal", report);
    }

    public boolean verifyAllModalElementsDisplayed(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();
        results.put("Email", emailField);
        results.put("Study ID", studyIDDropdown);
        results.put("Site ID", siteIDDropdown);
        results.put("Create User button", createUserButton);
        return basicHelpers.verifyElementsDisplayed(results, report);
    }

    public boolean verifyAllEditModalElementsDisplayed(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();

        results.put("Email", editEmailField);
        results.put("Study ID", studyIDDropdown);
        results.put("Site ID", siteIDDropdown);
        results.put("Save Changes button", saveChangesButton);
        results.put("Cancel button", btnCancel);
        return basicHelpers.verifyElementsDisplayed(results, report);
    }

    public void verifyAllAddModalFilledIn(String email, String studyID, String siteID, @Nullable GalenReport report) {
        basicHelpers.verifyText(getEmail(), "Email", email, report);
        basicHelpers.verifyText(getStudyID(), "Study ID", studyID, report);
        basicHelpers.verifyText(getSiteID(), "Site ID", siteID, report);
    }

    public void selectStudyID(String value, @Nullable GalenReport report) {
        basicHelpers.selectDropDownByText(getStudyID(), value, "Study ID", report);
    }

    public void selectSiteID(String value, @Nullable GalenReport report) {
        basicHelpers.selectDropDownByText(getSiteID(), value, "Site ID", report);
    }

    public void enterEmail(String email, @Nullable GalenReport report) {
        basicHelpers.sendTextFlex(getEmail(), email, "Email", report);
    }

    public void clickCreateUser(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getCreateUserButton(), "Create User", report);
    }

    public void clickViewRecords(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getViewRecordsButton(), "View Records", report);
    }

    public void clickAddRecords(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getAddRecordButton(), "Add Record", report);
    }

    public void clickEditPart(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getEditButton(), "Edit Participant", report);
    }

    public void clickCloseButton(@Nullable GalenReport report) {
        basicHelpers.clickFlex(btnCancel, "Close Modal", report);
    }

    public void clickPartId(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getParticipantIdDropdown(), "Participant Id ", report);
    }

    public void clickAssessmentType(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getAssessmentType(), "Assessment Type", report);
    }

    public void clickCompletedBy(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getCompletedBy(), "Completed By", report);
    }

    public void clickCompletedDate(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getCompletedDate(), "Completed Date", report);
    }

    public void clickEmailAddressId(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getEmailAddressIdColumn(), "Email Address", report);
    }

    public void clickInitialOutcome(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getInitialOutcomeColumn(), "Initial Outcome", report);
    }

    public void clickSurveyDate(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getSurveyDatedColumn(), "Survey Date", report);
    }

    public void clickCloseRecords(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getCloseRecords(), "Close Records", report);
    }

    public void clickSaveChangesButton(@Nullable GalenReport report) {
        basicHelpers.clickFlex(saveChangesButton, "Save Changes", report);
    }

    public boolean verifyDisplayedRedDotStatus(String email, @Nullable GalenReport report) {
        basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), report);
        return basicHelpers.verifyDisplayedFlex(By.xpath("//td[text()='"+email+"']/preceding-sibling::td[2]/div"), "Red Dot Status for " + email, report);
    }

    public boolean verifyNotDisplayedRedDotStatus(String email, @Nullable GalenReport report) {
        basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), report);
        return basicHelpers.verifyNotDisplayedFlex(By.xpath("//td[text()='"+email+"']/preceding-sibling::td[2]/div"), "Red Dot Status for " + email, report);
    }

    public void clickEditParticipant(String email, @Nullable GalenReport report) {
        basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), report);
        By tableEntryBy = By.xpath("//td[text()='" + email + "']/following-sibling::td/div/button[contains(text(),'Edit')]");
        basicHelpers.clickFlex(tableEntryBy, "Edit participant link", report);
    }

    public void clickViewRecords(String email, @Nullable GalenReport report) {
        basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), report);
        By tableEntryBy = By.xpath("//td[text()='" + email + "']/following-sibling::td/div/button[contains(text(),'View Records')]");
        basicHelpers.clickFlex(tableEntryBy, "Click View Records link", report);
    }



    public boolean findEmailInTable(String email, @Nullable GalenReport report) {
        try {
            Thread.sleep(1000);
            basicHelpers.scrollToElement(By.xpath("//td[text()='" + email + "']"), report);
            return basicHelpers.verifyDisplayedFlex(By.xpath("//td[text()='" + email + "']"), "Email " + email, report);
        } catch (Exception e) {
            return false;
        }
    }

    public void enterNewParticipantData(String email, String study, String site, @Nullable GalenReport report) {
        String steps = "Enter email: '" + email+"'\n" +
                "Select Study: '" + study+"'\n" +
                "Select Site: '" + site;
        try {
            basicHelpers.sendTextFlex(getEmail(), email, "Email", report);
            basicHelpers.selectDropDownByText(getStudyID(), study, "Study: "+study, report);
            basicHelpers.selectDropDownByText(getSiteID(), site, "Site: "+site, report);
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

    public boolean verifyEmailNotInTable(String email, @Nullable GalenReport report) {
        return basicHelpers.verifyNotDisplayedFlex(By.xpath("//td[text()='" + email + "']"), "Email " + email, report);
    }

    public Boolean verifyParticipantIDFormat(ParticipantClass par, @Nullable GalenReport report) {

        By tableEntryBy = By.xpath("//td[text()='" + par.email + "']/preceding-sibling::td[contains(text(),'" +
                par.studyID + "')]");
        WebElement tableEntry = basicHelpers.getWebElement(tableEntryBy);
        if (tableEntry != null) {
            par.participantID = tableEntry.getText();
            return basicHelpers.verifyCondition(() ->
                            par.participantID.contains(par.studyID + "-" + par.siteID + "-"),
                    "Participant ID " + par.participantID +
                            " format contains study ID " + par.studyID + " and site id " + par.siteID, false, report);
        } else {
            return basicHelpers.verifyDisplayedFlex(tableEntryBy, "Table entry  for user " + par.email, report);
        }
    }

    public boolean viewRecordWithMultipleAssessments(@Nullable GalenReport report) {
        int i=0;
        ViewRecords view = new ViewRecords(driver);
        int p = basicHelpers.getAllWebElements(participants).size();
        while ( i< p) {
            basicHelpers.clickFlex(basicHelpers.getAllWebElements(viewRecordsButton).get(i), "record",null);
            view.verifyAtPage();
            if (view.getAllCheckboxes().size() >= 2) {
                break;
            }
            view.clickCloseRecordButton(null);
            verifyAtPage();
            i++;
        }
        return basicHelpers.verifyActionToPageDisplayed("Click View Records on a participant with multiple assessments",
                view, "View Records page",report);
    }

}