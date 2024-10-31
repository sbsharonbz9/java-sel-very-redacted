package galen.pages.common;

import galen.helpers.common.GalenReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class SingleResponseRadioButtonPage extends BasePage {
    public SingleResponseRadioButtonPage(WebDriver driver) {
        super(driver);
    }
    public ArrayList<String> options = new ArrayList<>(Arrays.asList("Very low", "Low", "Moderate", "High", "Very High"));

    public WebElement getSelected(By selectedBy) {
        return basicHelpers.getWebElement(selectedBy);
    }

    public void selectRadioReponse(String optionValue, @Nullable GalenReport report) {
        By selection=By.xpath("//input[contains(@aria-label,\""+optionValue+"\")]/following-sibling::span");
        WebElement radioButton= getSelected(selection);
        basicHelpers.clickFlex(radioButton, optionValue,  report);
    }

    public boolean selectRadioResponseAndProgress(String value, BasePage nextPage, @Nullable GalenReport report) {
        String failureText="User is not at "+ nextPage.reportText;
        boolean result;
        try {
            selectRadioReponse(value, null);
            result= clickNextToPage(nextPage, null);
        } catch (Exception e) {
            result=false;
            failureText="Failed with exception: "+ e.getMessage();
        }
        if (report!=null){
            report.addStep("Select '"+ value+"'\nClick 'Next' button\n" +
                    "Verify at '"+ nextPage.reportText+ "'", "'" + value+"' is selected\n" +
                    "'Next' button is clicked\n " +
                    "At "+nextPage.reportText, result?"As expected":failureText, result, true );
        }
        return result;
    }

    public boolean verifyNoOptionsSelected(@Nullable GalenReport report) {
        HashMap<String, Object> results = new HashMap<String, Object>();
        for (String optionValue : options) {
            results.put(optionValue, basicHelpers.verifyRadioButtonNotSelected(optionValue, null));
        }
        if (report!=null) {
            report.addMultipleVerificationStep("The following values are not selected:", results,
                    false);
        }
        return results.containsValue(false);
    }
}
