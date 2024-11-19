package galen.pages.tenant.dexter.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterUser;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class BirthYear extends BasePage {

    public By title = By.className("BirthdayScreen");
    public By birthYearField = By.xpath("//input[@id='birthYear']");
    public String yearValidError = "Please enter valid year";
    public By yearValidErrorField = By.cssSelector("div.assessment-numeric-input.has-error > ul");

    public BirthYear(WebDriver driver) {
        super(driver);
        titleText="What is your year of birth?";
        headingTitle=title;
        reportText="Smoking or Vape – Year of Birth Screen";
    }

    public WebElement getBirthYearField() {
        return basicHelpers.getWebElement(birthYearField);
    }

    public void fillOutBirthday(DexterUser user,  @Nullable GalenReport report) {
        enterYear(user.dobYear, report);
        clickYesOrNo(user.hadBirthday, report);
    }

    public void enterYear(String year,  @Nullable GalenReport report) {
        basicHelpers.sendTextFlex(getBirthYearField(), year, "Year of birth", report);
    }

    public boolean verifyYearErrorDisplayed(@Nullable GalenReport report) {
        return basicHelpers.verifyDisplayedFlex(yearValidErrorField,"Year of Birth error", report);
    }

    public boolean verifyYearFieldDisplayed(@Nullable GalenReport report) {
        return basicHelpers.verifyDisplayedFlex(birthYearField,"Year of Birth Field", report);
    }

    public boolean verifyAllQuestionsAnswered(DexterUser user, @Nullable GalenReport report) {
        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        WebElement yobField = getBirthYearField();
        if (getBirthYearField() != null) {
            result.put("Year of birth", yobField.getAttribute("value").equals(user.dobYear));
        } else {
            result.put("Year of birth", false);
        }
        boolean birthdayYetCorrect=basicHelpers.verifyRadioButtonSelected(user.hadBirthday, null);
        result.put("Have you had your birthday yet this year?", birthdayYetCorrect);

        return (report != null) ? report.addMultipleVerificationStep("the following questions are " +
                "displayed and answered:", result, false) : !result.containsValue(false);
    }

    public boolean fillOutBirthdayAndProgress(DexterUser user, BasePage nextPage, @Nullable GalenReport report) {
        String nextPageTitle = (!nextPage.reportText.equals("Next page"))? nextPage.reportText:nextPage.titleText+ " page";
        String failureText="User is not at "+ nextPageTitle;
        boolean result;
        try {
            fillOutBirthday(user, null);
            result= clickNextToPage(nextPage, null);
        } catch (Exception e) {
            result=false;
            failureText="Failed with exception: "+ e.getMessage();
        }
        if (report!=null){
            report.addStep("Enter year: "+ user.dobYear+"\nSelect "+ user.hadBirthday+"\nClick 'Next' button\n" +
                    "Verify at "+ nextPageTitle,
                    "At "+nextPageTitle, result?"As expected":failureText, result, true );
        }
        return result;
    }


}
