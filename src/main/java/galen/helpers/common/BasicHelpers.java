package galen.helpers.common;

import com.google.common.collect.ImmutableMap;
import galen.constants.FrameworkConstants;
import galen.enums.SP.AccountTabs;
import galen.enums.SP.RoleType;
import galen.pages.common.BasePage;
import galen.pages.sp.StudyAdminPageObj;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.support.ui.*;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public class BasicHelpers {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverWait shortWait;

    public BasicHelpers(WebDriver driver) {
        this.wait = new WebDriverWait(driver, FrameworkConstants.getExplicitWait());
        this.shortWait = new WebDriverWait(driver, FrameworkConstants.getExplicitShortWait());
        this.driver = driver;
    }

    public boolean verifyActiveElement(Boolean expected, By by, String byText, @Nullable GalenReport report) {
        WebElement actualElement = (WebElement)((JavascriptExecutor) driver).executeScript("return document.activeElement;");
        WebElement expectedElement = getWebElement(by);
        boolean result = actualElement.equals(expectedElement)==expected;
        return verifyCondition(()->result,"Cursor in element "+byText+" is " + expected, false, report);
    }

    public void setOfflineMode(Boolean offline, @Nullable GalenReport report) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("offline", offline);
        map.put("latency", 0);
        map.put("download_throughput", 0);
        map.put("upload_throughput", 0);

        CommandExecutor executor = ((ChromeDriver)driver).getCommandExecutor();
        executor.execute(new Command(((ChromeDriver)driver).getSessionId(),
                "setNetworkConditions", ImmutableMap.of("network_conditions",ImmutableMap.copyOf(map))));
        if (report!= null) {
            String reportOffline = (offline)?"Set to offline mode":"Take out of offline mode";
            report.addStep(reportOffline, reportOffline,reportOffline, true );
        }
    }

    public boolean verifyAtPage(ExpectedCondition<Boolean> condition, String reportingText, @Nullable GalenReport report) {
        boolean result;
        try {
            result = shortWait.until(condition);
        } catch (Exception e) {
            result = false;
        }
        if (report != null) {
            report.reportPageTransition(reportingText, result);
        }
        return result;
    }

    public boolean verifyAtPage(Boolean condition, String reportingText, @Nullable GalenReport report) {
        boolean result = condition;
        if (report != null) {
            report.reportPageTransition(reportingText, result);
        }
        return result;
    }

    public void reportHappyFlow(String HFType, String pageReportText, boolean result, @Nullable GalenReport report) {
        String positiveResult = "See reference document "+ HFType;
        String negativeResult = "User not at page " + pageReportText;
        if (report != null) {
            report.addStep("Execute "+ HFType+ " through to "+ pageReportText, pageReportText +
                            " is displayed",
                    result?positiveResult:negativeResult, result, true);
        }
    }

    public void reportContinueHappyFlow(String HFType, String pageText, String endPageText, boolean result, @Nullable GalenReport report) {
        String positiveResult = "See reference document "+ HFType;
        String negativeResult = "User not at page " + pageText;
        if (report != null) {
            report.addStep("Continue to execute "+ HFType+ " from " +pageText + " to "+ endPageText,
                    endPageText + " is displayed",
                    result?positiveResult:negativeResult, result, true);
        }
    }

    public int getRandomValue(int max) {
        return (int) Math.floor(Math.random()*max);
    }

    public void clickFlex(WebElement element, String navigatorText, @Nullable GalenReport report) {
        try {
            wait.withTimeout(Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(element));
            if (report != null) {
                report.clickAndReport(element, navigatorText);
            } else {
                element.click();
            }
        } catch(Exception e) {
            if (report != null) {
                report.addStep("Click " + navigatorText, navigatorText + " is clicked",
                        "Element not found", false);
            }
        }
    }

    public void clickFlex(By by, String navigatorText, @Nullable GalenReport report) {
        clickFlex(getWebElement(by, wait), navigatorText,report);
    }

    public String getUniqueEmail() {
        long time = new Date().getTime();
        String date = Long.toString(time);

        // Extract the last 10 characters of the timestamp
        String shortDate = date.substring(date.length() - 10);

        // Generate a random number, multiply by 10000, and round to the nearest whole number
        int randomNumber = (int) Math.round(Math.random() * 10000);

        // Construct the unique email string
        return "auto.tester+" + shortDate + randomNumber + "@domain.com";
    }

    public String getRandomDropdownOption(By name) {
        List<String> options = getAllDropdownOptions(name);
        if (options.size()==0 ) {
            return null;
        }
        int index = getRandomValue(options.size());
        return options.get(index);
    }

    public void scrollToElement(By scrollToBy, @Nullable GalenReport report) {
        WebElement element=getWebElement(scrollToBy);
        if (element!=null) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } else {
            if (report != null) {
                report.addStep("Scroll to element", "Scrolled to element", "Element not " +
                        "present", false, false);
            }
        }
    }

    public Boolean verifyCondition(Callable<Boolean> callable, String closureText, boolean hardFail, @Nullable GalenReport report) {
        try {
            return (report != null) ?
                    report.verifyCondition(callable, closureText, hardFail) :
                    callable.call();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement getWebElement(By byStatement) {
        return getWebElement(byStatement, wait);
    }

    public WebElement getWebElement(By byStatement, WebDriverWait localWait) {
        try {
            return localWait.until(ExpectedConditions.visibilityOfElementLocated(byStatement));
        } catch (TimeoutException e) {
            return null;
        }
    }

    public List<WebElement> getAllWebElements(By byStatement) {
        try {
            return driver.findElements(byStatement);
        } catch (TimeoutException e) {
            return null;
        }
    }

    public Boolean verifyRadioButtonSelected(String expected, @Nullable GalenReport report) {
        WebElement element = getWebElement(By.id("assessment-radio-" + expected.toLowerCase()));
        boolean isSelected = element != null && element.isSelected();
        if (report != null) {
            report.addStep("Verify '" + expected + "' is selected", "'" + expected + "' is " +
                    "selected", isSelected ? "As expected" : "Fail", isSelected, false);
        }
        return isSelected;
    }

    public Boolean verifyRadioButtonNotSelected(String expected, @Nullable GalenReport report) {
        WebElement element = getWebElement(By.id("assessment-radio-" + expected.toLowerCase()));
        boolean isNotSelected = element != null && !element.isSelected();
        if (report != null) {
            report.addStep("Verify '" + expected + "' is not selected", "'" + expected + "' is " +
                    "not selected", isNotSelected ? "As expected" : "Fail", isNotSelected, false);
        }
        return isNotSelected;
    }

    public Boolean verifyNotDisplayedFlex(By byStatement, String navigatorText, @Nullable GalenReport report) {
        return (report == null) ? getWebElement(byStatement, shortWait) == null : report.verifyNotDisplayed(
                getWebElement(byStatement, shortWait), navigatorText, false);
    }

    public Boolean verifyDisplayedFlex(By byStatement, String navigatorText, @Nullable GalenReport report) {
        return (report == null) ? getWebElement(byStatement) != null : report.verifyActionToNavDisplayed(
                "Verify " + navigatorText + " displayed", getWebElement(byStatement), navigatorText, false);
    }

    public void sendTextFlex(By by, String inputText, String identifier, @Nullable GalenReport report) {
        WebElement element = getWebElement(by);
        clearTextField(by, null);
        if (report != null) {
            report.sendTextAndReport(element, inputText, identifier, false);
        } else {
            element.sendKeys(inputText);
        }
    }

    public Boolean verifyClickToNavDisplayed(By toClick, String navText, By toVerify, String navText2,
                                             @Nullable GalenReport report) {
        try {
            clickFlex(toClick, navText, null);
            sleep(1000);
            return verifyActionToNavDisplayed("Click " + navText, toVerify, navText2, report);
        } catch (Exception e) {
            return verifyActionToNavDisplayed("Click " + navText, null, navText2, report);
        }
    }

    public Boolean verifyClickToNavNotDisplayed(By toClick, String navText, By toVerify, String navText2,
                                             @Nullable GalenReport report) {
        clickFlex(toClick, navText, null);
        return verifyActionToNavNotDisplayed("Click "+ navText, toVerify, navText2, report);
    }

    public Boolean verifyClickToPageTransition(BasePage expectedPage, By by, String navText, @Nullable GalenReport report) {
        WebElement element = getWebElement(by);
        clickFlex(element, navText, null);
        return verifyActionToPageDisplayed("Click '" + navText + "'", expectedPage,
                expectedPage.reportText, report);
    }

    public Boolean verifyActionToNavDisplayed(String action, By by, String navText, @Nullable GalenReport report) {
        WebElement nav = getWebElement(by);
        if (report != null) {
            try {
                return report.verifyActionToNavDisplayed(action, nav, navText, true);
            } catch (Exception e) {
                report.addStep(action + "\nVerify " + navText + " is displayed", navText + " is displayed",
                        e.getMessage() , false);
                return false;
            }
        }
        return nav != null && nav.isDisplayed();
    }

    public Boolean verifyActionToNavNotDisplayed(String action, By by, String navText,
                                                 @Nullable GalenReport report) {
        WebElement nav = getWebElement(by, shortWait);
        String result;
        if (report != null) {
            result = nav==null? navText + " is not displayed":navText+ " is displayed";
            try {
                report.addStep(action + "\nVerify " + navText + " is not displayed",
                        navText + " is not displayed", result, nav==null);
            } catch (Exception e) {
                report.addStep(action + "\nVerify " + navText + " is not displayed", navText + " is displayed",
                        e.getMessage() , false);
                return false;
            }
        }
        return nav == null || !nav.isDisplayed();
    }

    public Boolean verifyActionToPageDisplayed(String action, BasePage toPage, String pageText,
                                               @Nullable GalenReport report) {
        if (report != null) {
            try {
                report.verifyActionToPageDisplayed(action, toPage, pageText, true);
            } catch (Exception e) {
                report.addStep(action + "\nVerify " + pageText + " is displayed", pageText + " is displayed",
                        e.getMessage() , false);
                return false;
            }
        }
        return toPage.verifyAtPage();
    }

    public Boolean verifyButtonEnabled(By by, Boolean isEnabled, @Nullable GalenReport report) {
        WebElement element = getWebElement(by);
        boolean actualEnabled = element.isEnabled();
        Boolean result = actualEnabled == isEnabled;
        String condition = element.getText() + " button is " + (isEnabled ? "" : " not ") + " enabled ";
        if (report != null) {
            return report.verifyCondition(() -> result, condition, false);
        }
        return result;
    }

    public void clearTextField(By by, @Nullable GalenReport report) {
        WebElement element = getWebElement(by);
        char[] c = element.getAttribute("value").toCharArray();
        for (char ch : c) {
            element.sendKeys(Keys.BACK_SPACE);
        }
        if (report != null) {
            report.addStep("Clear text field ", "Clear text field", "As expected", true);
        }
    }

    public String getStringList(ArrayList<String> input) {
        String response;
        if (input.size() <=1) {
            return input.get(0);
        }
        response=input.get(0);
        input.remove(0);
        for (String a : input) {
            response =  (", " +a);
        }
        return response;
    }

    public Boolean verifyText(By by, String navText, String textToVerify, @Nullable GalenReport report) {
        WebElement element = getWebElement(by);
        if (report != null) {
            return report.addTextVerificationStep(element, navText, textToVerify, false);
        }
        return element.getText().toLowerCase().contains(textToVerify.toLowerCase());
    }

    public Boolean verifyElementsDisplayed(LinkedHashMap<String, By> elements, @Nullable GalenReport report) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        WebElement element;
        for (Map.Entry<String, By> entry : elements.entrySet()) {
            element = getWebElement(entry.getValue());
            if (element != null) {
                result.put(entry.getKey(), element.isDisplayed());
            } else {
                result.put(entry.getKey(), false);
            }
        }
        return addMultipleVerificationStep("the following elements are displayed:", result, report);
    }

    public Boolean verifyElementsNotDisplayed(LinkedHashMap<String, By> elements, @Nullable GalenReport report) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        WebElement element;
        for (Map.Entry<String, By> entry : elements.entrySet()) {
            element = getWebElement(entry.getValue(), shortWait);
                result.put(entry.getKey(), element==null);
        }
        return addMultipleVerificationStep("the following elements are not displayed:", result, report);
    }


    public boolean addMultipleVerificationStep(String condition, LinkedHashMap<String, Object> result, @Nullable GalenReport report) {
        return (report != null) ? report.addMultipleVerificationStep(condition+":", result, false) :
                !result.containsValue(false);
    }

    public void compareCSVValueByAssessmentID(File fileName, String assessmentID, String column, String value,
                                              @Nullable GalenReport report) {
        String condition = "CSV value of "+ column + " equals " + value;
        String actualResult;
        try {
            actualResult = new CSVHelpers().getCSVValueByAssessmentID(fileName, assessmentID, column);
        } catch(Exception e) {
            actualResult="";
        }
        if (report!=null) {
            report.addStep("Verify " + condition, condition, actualResult, value.equals(actualResult), false);
        }
    }

    public boolean verifyCSVValuePresent(File fileName, String assessmentID, String column,
                                              @Nullable GalenReport report) {
        String condition = "CSV value of "+ column + " is not blank";
        boolean result;
        String actualResult=column + " is blank";
        CSVHelpers csv = new CSVHelpers();
        try {
            actualResult = column + " contains "+ csv.getCSVValueByAssessmentID(fileName, assessmentID, column);
            result=csv.verifyCSVValuePresent(fileName, assessmentID, column);
        } catch(Exception e) {
            result=false;
        }
        if (report != null)
            report.addStep("Verify "+ condition, StringUtils.capitalize(condition), actualResult,result ,false);

        return result;
    }

    public List<String> getAllDropdownOptions(By dropdown) {
        Select select = new Select(getWebElement(dropdown));
        List<WebElement> options =  select.getOptions();
        ArrayList<String> optionsText= new ArrayList<>();
        for (WebElement option : options) {
            if (!option.getText().equals("")) {
                optionsText.add(option.getText());
            }
        }
        return optionsText;
    }

    public String getCurrentDropdownOption(By dropdown) {
        Select select = new Select(getWebElement(dropdown));
        return select.getFirstSelectedOption().getText();
    }

    public void selectDropDownByText(By by, String value, String identifier, @Nullable GalenReport report) {
        WebElement element = getWebElement(by);
        if (report != null)
            report.selectDropDownByTextAndReport(element, value, identifier);
        else {
            Select select = new Select(element);
            select.selectByVisibleText(value);
        }
    }

    public void verifyCurrentDropdownValue(By by, String identifier, String value, @Nullable GalenReport report) {
        WebElement element = getWebElement(by);
        verifyCondition((()-> new Select(element).getFirstSelectedOption().getText().equals(value)),
                    "Selected value of '" + identifier + " equals '" + value + "'", false, report);
    }

    public void verifyDropdownContains(By by, String identifier, String value, @Nullable GalenReport report) {
        WebElement element = getWebElement(by);
        verifyCondition((()-> new Select(element).getOptions().contains(element)),
                "Dropdown '" + identifier + " contains '" + value + "'", false, report);
    }

    public void verifyDropdownNotContains(By by, String identifier, String value, @Nullable GalenReport report) {
        WebElement element = getWebElement(by);
        verifyCondition((()-> !(new Select(element).getOptions().contains(element))),
                "Dropdown '" + identifier + " does not contain '" + value + "'", false, report);
    }

    public void refreshPage(String navText, @Nullable GalenReport report) {
        driver.navigate().refresh();
        if (report != null)
            report.addStep("Click the browser refresh button", navText, "As Expected", true);
    }

    public void clickBrowserBack(@Nullable GalenReport report) {
        driver.navigate().back();
        if (report != null)
            report.addStep("Click the browser back button", "Browser back", "As Expected", true);
    }

    public boolean downloadCSVAndVerify(String csvName, StudyAdminPageObj pageObj, @Nullable GalenReport report) {
        boolean result=true;
        try {
            pageObj.login.logIn(RoleType.CENTRAL_ASSESSOR.email, null);
            pageObj.participants.verifyAtPage();
            pageObj.participants.selectTab(AccountTabs.RECORDS, null);
            pageObj.downloadRecords.downloadParticipantRecords(csvName, report);
        } catch( Exception e) {
            result = false;
            if (report != null) {
                report.addStep("Generate Metrics CSV", "Generated  Metrics CSV\n" +
                        "CSV metrics record captured\n", e.getMessage(), false);
            }
        }
        return  result;
    }

    public boolean downloadIndividualCSVAndVerify(String csvName, String assessmentID,StudyAdminPageObj pageObj,
                                                  String email,GalenReport report) {
        boolean result=true;
        String actual;
        try {
            sleep(1000);
            pageObj.login.logIn(RoleType.CENTRAL_ASSESSOR.email, null);
            pageObj.participants.verifyAtPage();
            pageObj.participants.clickViewRecords(email, null);
            pageObj.viewRecords.verifyAtPage();
            pageObj.viewRecords.downloadIndividualRecords(csvName, assessmentID, report);
        } catch( Exception e) {
            actual = e.getMessage();
            if (report != null) {
                report.addStep("Download Individual CSV", "Download Individual CSV\n" +
                        "CSV metrics record captured\n", actual, result=false);
            }
        }
        return  result;
    }
}
