package galen.pages.sp;

import galen.helpers.common.GalenReport;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public class DownloadRecords extends SPBasePage {

    public By header = By.xpath("//h1[text()='Download Records']");
    public By participants = By.id("participants");
    public By clinician = By.id("clinicians");
    public By both = By.id("participants-clinicians");
    public By downloadButton = By.xpath("//button[text()='Download CSV']");

    public DownloadRecords(WebDriver driver) {
        super(driver);
        headingTitle=header;
        reportText="Download Records Screen";
    }

    public boolean verifyRecordDownload(String csvName, @Nullable GalenReport report) throws IOException {
        File reportSummaryFile=(report!=null)?new File("reports/" + report.reportName+"/"+csvName):
                new File(csvName);
        File records=new File(System.getProperty("user.home")+"/Downloads/records.csv");
        while (!records.exists()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        FileUtils.moveFile(records, reportSummaryFile);
        return basicHelpers.verifyCondition(reportSummaryFile::exists, "download successful", true, report);
    }

    public void downloadParticipantRecords(String csvName, GalenReport report) throws IOException {
         basicHelpers.clickFlex(participants, "Participant Records option", report);
         basicHelpers.clickFlex(downloadButton, "Download CSV", report);
        verifyRecordDownload(csvName, report);
    }

    public void downloadClinicianRecords(String csvName, GalenReport report) throws IOException {
        basicHelpers.clickFlex(clinician, "Clinician Records option", report);
        basicHelpers.clickFlex(downloadButton, "Download CSV", report);
        verifyRecordDownload(csvName, report);
    }

    public void downloadAllRecords(String csvName, GalenReport report) throws IOException {
        basicHelpers.clickFlex(both, "Participant and Clinician Records option", report);
        basicHelpers.clickFlex(downloadButton, "Download CSV", report);
        verifyRecordDownload(csvName, report);
    }

    public void verifyAllElementsDisplayed(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();
        results.put("Download Participant", participants);
        results.put("Download Clinician", clinician);
        results.put("Download Participant and Clinician", both);
        results.put("Download Button", downloadButton);
        basicHelpers.verifyElementsDisplayed(results, report);
    }
}