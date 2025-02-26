package galen.pages.tenant.petros.InitialAssessment;

import galen.enums.common.Gender;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.IOException;

public class SexAndBirthYear extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(SexAndBirthYear.class);
    public By title = By.xpath("//h1[contains(text(),'Sex at birth')]");
    public static By maleRadioBtn = By.xpath("//input[@type='radio' and @name='sexAtBirth' and @value='male']");
    public static By femaleRadioBtn = By.xpath("//label[@for='assessment-radio-female']//span[@class='assessment-radio__custom-radio']");
    public static By dateOfBirthInputLocatorMonth = By.xpath("//input[@placeholder='MM']");
    public static By dateOfBirthInputLocatorDay = By.xpath("//input[@placeholder='DD']");
    public static By dateOfBirthInputLocatorYear = By.xpath("//input[@placeholder='YYYY']");
    public By  dobHeader = By.className("InfoEntryScreen");
    public static By sexAtBirthTxt=By.xpath("//p[normalize-space()='Sex at birth:']");
    public static By dateOfBirthTxt=By.xpath("//p[normalize-space()='Date of birth:']");

    public SexAndBirthYear(WebDriver driver) {
        super(driver);
        titleText="BASIC INFORMATION";
        reportText="Sex and DOB page";
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dobHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), reportText, report);
    }

    public WebElement getMaleRadioButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(maleRadioBtn));
    }

    public WebElement verifySexAtBirthTxt() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sexAtBirthTxt));
    }
    public WebElement verifyDateAtBirthTxt() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dateOfBirthTxt));
    }


    public WebElement getFemaleRadioButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(femaleRadioBtn));
    }

    public  WebElement getDayInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dateOfBirthInputLocatorDay));
    }

    public WebElement getMonthInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dateOfBirthInputLocatorMonth));
    }

    public WebElement getYearInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dateOfBirthInputLocatorYear));
    }

    public Boolean verifyDOBAndSexAtBirthScreen(WebDriver driver, PetrosUser user, GalenReport report) throws IOException {
        Boolean result=true;
        if (user.gender== Gender.Male) {
            result=result && basicHelpers.verifyRadioButtonSelected("Male", report);
        } else {
            result=result && basicHelpers.verifyRadioButtonSelected("Female", report);
        }
        return result && verifyDOB(driver, user, report);
    }

    public Boolean verifyDOB(WebDriver driver, PetrosUser user, @Nullable GalenReport report) throws IOException {
        boolean result=true;
        result=result && basicHelpers.verifyText( getDayInput(), "Day", user.dobDay, report);
        result=result && basicHelpers.verifyText( getMonthInput(), "Month", user.dobMonth, report);
        result=result && basicHelpers.verifyText( getYearInput(), "Year", user.dobYear, report);
        return result;
    }

    public void enterDateOfBirth(WebDriver driver, PetrosUser user, @Nullable GalenReport report) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement monthField = getMonthInput();
        WebElement dayField = getDayInput();
        WebElement yearField = getYearInput();

        basicHelpers.sendTextFlex(dayField, user.dobDay, "Day", null);
        basicHelpers.sendTextFlex(monthField, user.dobMonth, "Month", null);
        basicHelpers.sendTextFlex(yearField, user.dobYear, "Year", null);

        // Trigger any JavaScript events associated with the input fields
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", monthField);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", dayField);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", yearField);

        if (report!=null) {
            report.addStep("Input DOB "+ user.dobMonth+"-"+user.dobDay+"-"+user.dobYear, "DOB entered",
                    "As expected", true);
        }
    }

    public void enterGender(PetrosUser user, @Nullable  GalenReport report) {
        if (user.gender == Gender.Male) {
            basicHelpers.clickFlex(maleRadioBtn, "Male Radio Button", report);
        } else {
            basicHelpers.clickFlex(femaleRadioBtn, "Female Radio Button", report);
        }
    }

    public void fillOutForm(PetrosUser user, @Nullable GalenReport report) {
        enterGender(user, null);
        enterDateOfBirth( driver, user, null);
        basicHelpers.clickFlex(getNextButton(), "Next",null);
        if (report!=null) {
            report.addStep("Input gender: "+ user.gender.name()+"\n" +
                            "Input DOB "+ user.dobMonth+"-"+user.dobDay+"-"+user.dobYear+"\n" +
                            "Click 'Next' button to OAuth screen", "Data is entered\n", "As Expected",true);
      }
    }

}