package galen.pages.tenant.dx.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;

public class ObesityBMI extends BasePage {
    public  By title = By.className("BMIObesityScreen");
    public  By selectHeight = By.xpath("//select[@name='height']");
    public  By inputWeight = By.xpath("//input[@id='weight']");
    public By weightInputError = By.className("assessment-numeric-input__error");
    public String weightInputErrorText = "Please enter a valid weight over 40 pounds";

    public ObesityBMI(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Enter your current height and weight";
        reportText="Obesity and BMI page";
    }

    public void enterHeightAndWeight(DxUser user, @Nullable GalenReport report) {
        String finalMessage="As expected";
        try {
            basicHelpers.selectDropDownByText(selectHeight, user.height, "Height", null);
            basicHelpers.sendTextFlex(inputWeight, user.weight, "Weight", null);
        } catch(Exception e) {
            finalMessage=e.getMessage();
        }
        if (report!=null) {
            report.addStep("Enter values:\nHeight : "+user.height+"\nWeight : "+ user.weight,
                    "Values entered",finalMessage, finalMessage.equals("As expected"), false );
        }
    }

    public Boolean enterHeightAndWeightAndProgress(DxUser user, BasePage nextPage, @Nullable GalenReport report) {
        enterHeightAndWeight(user, report);
        return clickNextToPage(nextPage, report);
    }

    public void verifyWeightInputError(@Nullable GalenReport report) {
        LinkedHashMap<String, Object> results = new LinkedHashMap<>();
        String condition = "invalid weight error message";
        results.put("Is displayed", basicHelpers.verifyDisplayedFlex(weightInputError, condition , null));
        results.put("Contains correct text",basicHelpers.verifyText(weightInputError,
                "invalid weight error message", weightInputErrorText, null));
        basicHelpers.addMultipleVerificationStep(condition, results, report);
    }
}