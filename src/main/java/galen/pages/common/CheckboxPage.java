package galen.pages.common;

import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;

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
        By selection = getOptionValueBy(optionValue);
        WebElement checkBox = getSelected(selection);
        basicHelpers.clickFlex(checkBox, optionValue, report);
    }

    public boolean selectCheckboxAndProgress(String optionValue,
                                             BasePage nextPage, @Nullable GalenReport report) {
        By selection = getOptionValueBy(optionValue);
        WebElement checkBox = getSelected(selection);
        basicHelpers.clickFlex(checkBox, optionValue, report);
        return clickNextToPage(nextPage, report);
    }

    // Select multiple checkboxes
    public void selectCheckboxReponses(ArrayList<String> optionValues, @Nullable GalenReport report) {
        String step = "Input values:\n";
        for (String optionValue : optionValues) {
            selectCheckboxReponse(optionValue, null);
            step = step.concat(optionValue + "\n");
        }
        if (report != null) {
            report.addStep(step, step, "As expected", true);
        }
    }

    public boolean selectCheckboxesAndProgress(ArrayList<String> checkboxes, BasePage nextPage, @Nullable GalenReport report) {
        selectCheckboxReponses(checkboxes, report);
        return clickNextToPage(nextPage, report);
    }

    public void verifySelectedCheckboxes(ArrayList<String> optionValues, @Nullable GalenReport report) {
        String step = "Verify Input values are selected:\n";
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
            if (isFail)
                report.addStep(step, step, "Options are not selected", false);
            else
                report.addStep(step, step, "As expected", true);
        }
    }

    public Boolean verifyCheckboxSelected(String optionValue, @Nullable GalenReport report){
        By selection = getOptionValueByForSelected(optionValue);
        WebElement element =basicHelpers.getAllWebElements(selection).get(0);
        return basicHelpers.verifyCondition( ()->element != null && element.isSelected(),"checkbox is selected",
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

    public boolean verifyAllOptionsVisible(@Nullable GalenReport report) {
        HashMap<String, Object> results = new HashMap<String, Object>();
        By selection;

        for (String optionValue : options) {
            selection= getOptionValueBy(optionValue);
            results.put(optionValue, getSelected(selection) != null);
        }
        if (report!=null) {
            report.addMultipleVerificationStep("The following values are displayed:", results,
                    false);
        }
        return results.containsValue(false);
    }

    public boolean verifyNoOptionsSelected(@Nullable GalenReport report) {
        HashMap<String, Object> results = new HashMap<String, Object>();
        for (String optionValue : options) {
            results.put(optionValue, verifyCheckboxNotSelected(optionValue, null));
        }
        if (report!=null) {
            report.addMultipleVerificationStep("The following values are not selected:", results,
                    false);
        }
        return results.containsValue(false);
    }

    public boolean verifyAllOptionsInADBU(@Nullable GalenReport report) {
        HashMap<String, Object> results = new HashMap<String, Object>();
        ArrayList<String> localOptions = options;
        WebElement adbuList = basicHelpers.getWebElement(By.className("adbuList"));
        if (adbuList==null && report!=null) {
            report.addStep("Verify all medications on "+this.reportText+" are displayed in ADBU", "All medications are listed",
                    "ADBU list is not present", false, true);
            return false;
        }
        localOptions.remove("None of these");
        localOptions.remove("No thyroid medication");
        for (String optionValue : options) {
            results.put(optionValue, basicHelpers.verifyText(adbuList, optionValue, optionValue.toLowerCase(), null));
        }
        if (report!=null) {
            report.addMultipleVerificationStep("The following medications on "+ this.reportText+" are displayed in ADBU list:", results,
                    false);
        }
        return results.containsValue(false);
    }
}
