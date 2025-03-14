package galen.pages.common;

import galen.enums.tenant.dx.DDIConditionType;
import galen.enums.tenant.dx.DDIThyroidType;
import galen.helpers.common.GalenReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CheckboxPage extends BasePage {
    public ArrayList<String> options = new ArrayList<>();

    public CheckboxPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSelected(By selectedBy) {
        return basicHelpers.getWebElement(selectedBy);
    }

    By getOptionValueBy (String optionValue) {
        if (optionValue.contains("'")) {
            optionValue=optionValue.replace("I'm", "");
            optionValue=optionValue.replace("St. John's ", "");
        }
        return  By.xpath("//input[contains(@aria-label,'" + optionValue + "')]/following-sibling::span[1]");
    }

    By getOptionValueByForSelected (String optionValue) {
        if (optionValue.contains("'")) {
            optionValue=optionValue.replace("I'm", "");
            optionValue=optionValue.replace("St. John's ", "");
        }
        return  By.xpath("//input[contains(@aria-label,'" + optionValue + "')]");
    }

    // Select a single checkbox
    public void selectCheckboxReponse(String optionValue, @Nullable GalenReport report) {
        selectCheckboxReponses(getCondition(optionValue), report);
    }

    public boolean selectCheckboxAndProgress(String optionValue, BasePage nextPage, @Nullable GalenReport report) {
        return selectCheckboxesAndProgress(getCondition(optionValue), nextPage, report);
    }

    public void selectCheckboxReponses(ArrayList<String> optionValues, @Nullable GalenReport report) {
        String step = "Select the following values:";
        for (String optionValue : optionValues) {
            basicHelpers.clickFlex(getOptionValueBy(optionValue), optionValue, null);
            step = step.concat("\n    "+optionValue);
        }
        if (report != null) {
            report.addStep(step, step, "As expected", true);
        }
    }

    public boolean selectCheckboxesAndProgress(ArrayList<String> checkboxes, BasePage nextPage, @Nullable GalenReport report) {
        String step = "Select the following values:";
        for (String optionValue : checkboxes) {
            selectCheckboxReponse(optionValue, null);
            step = step.concat("\n    "+optionValue);
        }
        boolean atPage = clickNextToPage(nextPage, null);
        step = step.concat("\nClick 'Next' to " + nextPage.reportText);
        if (report != null) {
            report.addStep(step, step, atPage?nextPage.reportText + " is displayed":nextPage.reportText + " is not " +
                    "displayed", atPage);
        }
        return atPage;
    }

    public void verifyCheckboxesSelected(ArrayList<String> optionValues, @Nullable GalenReport report) {
        String step = "Verify the following values are selected:\n";
        boolean isFail = false;
        for (String optionValue : optionValues) {
            By selection = getOptionValueByForSelected(optionValue);
            WebElement checkBox =basicHelpers.getAllWebElements(selection).get(0);
            if (!checkBox.isSelected()) {
                isFail = true;
                step = step.concat(optionValue + "\n");
                break;
            }
            step = step.concat(optionValue + "\n");
        }
        if (report != null) {
            report.addStep(step, step, isFail?"Options are not selected":"As expected", !isFail);
        }
    }

    public ArrayList<String> getAllButNone() {
        ArrayList<String> localOptions = options;
        localOptions.remove(DDIConditionType.NONE_OF_THESE.label);
        localOptions.remove(DDIThyroidType.NO_THYROID_MEDS.label);
        return localOptions;
    }

    public ArrayList<String> getCondition(String c) {
        return new ArrayList<>(Arrays.asList(c));
    }

    public void verifyCheckboxSelected(String optionValue, @Nullable GalenReport report){
        By selection = getOptionValueByForSelected(optionValue);
        WebElement element =basicHelpers.getAllWebElements(selection).get(0);
        basicHelpers.verifyCondition( ()->element != null && element.isSelected(),"checkbox is selected",
                false, report);
    }

    public Boolean verifyCheckboxNotSelected(String optionValue, @Nullable GalenReport report) {
        By selection = getOptionValueByForSelected(optionValue);
        WebElement element =basicHelpers.getAllWebElements(selection).get(0);
        boolean isNotSelected = element != null && !element.isSelected();
        if (report != null ) {
            report.addStep("Verify '"+ optionValue + "' is not selected", "'"+ optionValue + "' is " +
                    "not selected", isNotSelected?"As expected": "Fail", isNotSelected ,false);
        }
        return isNotSelected;
    }

    public void verifyAllOptionsVisible(@Nullable GalenReport report) {
        HashMap<String, Object> results = new HashMap<>();
        By selection;

        for (String optionValue : options) {
            selection= getOptionValueBy(optionValue);
            results.put(optionValue, getSelected(selection) != null);
        }
        if (report!=null) {
            report.addMultipleVerificationStep("The following values are displayed:", results,
                    false);
        }
    }

    public void verifyNoOptionsSelected(@Nullable GalenReport report) {
        HashMap<String, Object> results = new HashMap<>();
        for (String optionValue : getAllButNone()) {
            results.put(optionValue, verifyCheckboxNotSelected(optionValue, null));
        }
        if (report!=null) {
            report.addMultipleVerificationStep("The following values are not selected:", results,
                    false);
        }
    }

    public boolean verifyAllOptionsInADBU(@Nullable GalenReport report) {
        LinkedHashMap<String, Object> results = new LinkedHashMap<>();
        WebElement adbuList = basicHelpers.getWebElement(By.className("adbuList"));
        if (adbuList==null && report!=null) {
            report.addStep("Verify all medications on "+this.reportText+" are displayed in ADBU", "All " +
                            "medications are listed", "ADBU list is not present", false, true);
            return false;
        }
        for (String optionValue : getAllButNone()) {
            results.put(optionValue, basicHelpers.verifyText(By.className("adbuList"), optionValue, optionValue.toLowerCase(), null));
        }
        basicHelpers.addMultipleVerificationStep("The following medications on "+ this.reportText+" are " +
                        "displayed in ADBU list:", results,
                    report);
        return results.containsValue(false);
    }
}
