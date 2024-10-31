package galen.pages.sp;

import galen.helpers.common.CSVHelpers;
import galen.helpers.common.GalenReport;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class ViewRecords extends SPBasePage {

    public By closeRecordButton = By.xpath("//button[normalize-space()='Close Record']");
    public By saveButton = By.xpath("//button[normalize-space()='Save']");
    public By selectHeader = By.xpath("//th[normalize-space()='Select']");
    public By assessmentTypeHeader= By.xpath("//th[normalize-space()='Assessment Type']");
    public By completedByHeader= By.xpath("//th[normalize-space()='Completed By']");
    public By completedDateHeader= By.xpath("//th[normalize-space()='Completed Date']");
    public By addRecord = By.xpath("//button[normalize-space()='Add Record']");
    public By checkboxes = By.xpath("//input[@type='checkbox']");
    public By downloadButton = By.xpath("//button[text()='Download CSV']");


    public ViewRecords(WebDriver driver) {
        super(driver);
        headingTitle=closeRecordButton;
        reportText="View Records Screen";
    }

    public WebElement getDownloadButton() {
        return basicHelpers.getWebElement(downloadButton);
    }

    public WebElement getCloseRecordButton() {
        return basicHelpers.getWebElement(closeRecordButton);
    }

    public List<WebElement> getAllCheckboxes() {
        return basicHelpers.getAllWebElements(checkboxes);
    }

    public void clickCloseRecordButton(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getCloseRecordButton(), "Close Record Button", report);
    }

    public WebElement getAddRecordButton() {
        return basicHelpers.getWebElement(addRecord);
    }

    public void clickAddRecordButton(@Nullable GalenReport report) {
        basicHelpers.clickFlex(getAddRecordButton(), "Add Record Button", report);
    }

    public void createNewRecord(@Nullable GalenReport report) {
        clickAddRecordButton(report);
        basicHelpers.clickFlex(saveButton, "Save", report);
        verifyModalDismissed(report);
        Object[] handles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) handles[handles.length-1]);
    }

    public boolean downloadIndividualRecords(String fileName, int index, @Nullable GalenReport report) throws IOException {
        int reportIndex = index+1;
        basicHelpers.clickFlex(getAllCheckboxes().get(index),
                "Checkbox " + reportIndex, report);
        basicHelpers.clickFlex(downloadButton, "CSV Download", report);
        return new DownloadRecords(driver).verifyRecordDownload(fileName, report);
    }

    public boolean verifyAllElementsDisplayed(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();
        results.put("Close Record", closeRecordButton);
        results.put("Download CSV", downloadButton);
        results.put("Assessment table", table);
        return basicHelpers.verifyElementsDisplayed(results, report);
    }

    public boolean verifyAllColumnsPresent(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();
        results.put("Select column", selectHeader);
        results.put("Assessment Type column", assessmentTypeHeader);
        results.put("Completed By column", completedByHeader);
        results.put("Completed Date column", completedDateHeader);
        return basicHelpers.verifyElementsDisplayed(results, report);
    }

    public File downloadIndividualRecords(String fileName,String assessmentNumber,GalenReport report)  {
        List<WebElement> checkboxes = getAllCheckboxes();
        CSVHelpers csv = new CSVHelpers(driver);
        File thisFile;
        File returnFile = null;
        boolean downloadVerified;
        int i=checkboxes.size()-1;
        try {
            while (i >= 0 ) {
                downloadVerified=downloadIndividualRecords("reports/" + report.reportName+"/Individual"+i, i,
                        null);
                if (downloadVerified) {
                    thisFile = new File("reports/" + report.reportName + "/Individual" + i);
                    boolean df = csv.verifyCSVValuePresent(thisFile, assessmentNumber, "AssessmentNumber");
                    if ( df ) {
                        returnFile = new File("reports/" + report.reportName + "/" + fileName);
                        FileUtils.moveFile(thisFile, returnFile);
                        break;
                    }
                    FileUtils.deleteQuietly(thisFile);
                }
                getAllCheckboxes().get(i).click();
                i--;
            }
        }  catch(Exception ignored) {
        }
        return returnFile;
    }
}
