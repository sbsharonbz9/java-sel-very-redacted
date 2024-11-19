package galen.helpers.common;

import galen.pages.common.BasePage;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.HashMap;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;

import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import com.itextpdf.kernel.geom.PageSize;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

public class GalenReport {
    public Date runTime;
    public String reportTitle;
    Table prerequisitesTable;
    static Table stepTable;
    Table postExecutionTable;
    Table environmentTable;
    Table versionHistoryTable;
    Table preExecutionTable;
    Table stickyTable;
    FileOutputStream fos;
    PdfWriter pdfw;
    PdfDocument pdfd;
    Document document;
    PdfFont font;
    PdfFont bold;
    float[] colWidths = {3, 25, 25, 25, 10, 10};
    float[] colPrereqWidths = {20, 80};
    float[] colExecWidths = {20, 20, 20, 40};
    float[] colVersionWidths = {25, 25, 25, 25};
    float[] colHeaderWidths = {40, 30, 30};
    Boolean overallResult = true;
    ArrayList<Image> failureImage = new ArrayList<>();
    ArrayList<String> reportVersionUpdates;
    HashMap<String, String[]> preExecApprovals;
    WebDriver driver;
    String screenshotDir;
    String requirement;
    public String reportName;
    String thisVersion;
    String references;
    String objective;

    GalenReport(WebDriver driver, String reportName, String objective, String requirements, String references, String
            notes)
    {
        this.reportName = reportName;
        this.requirement = requirements;
        this.references = references.replace(";", "");
        this.objective = objective.replace(";", "");
        try {
            File reportDir = new File("reports/" + reportName);
            if (reportDir.exists()) {
                FileUtils.cleanDirectory(reportDir);
            }
            this.screenshotDir = "reports/" + reportName + "/screenshots";
            if (new File(screenshotDir).mkdirs()) {
                startReport();
            }
            this.driver = driver;
            createPrereqTable(this.objective, this.requirement, this.references, notes.replace(";", ""));
        } catch (IOException e) {
            System.out.println("Could not create report");
            e.printStackTrace();
        }
    }

    public GalenReport(WebDriver driver, String reportName, String objective, String requirements, String references,
                        String notes, ArrayList<String> versionEntries) throws IOException {
        this(driver, reportName, objective, requirements, references, notes);
        this.reportVersionUpdates = versionEntries;
        createVersionHistoryTable();
    }

    public GalenReport(WebDriver driver, String reportName, String objective, String requirements, String references,
                        String notes, ArrayList<String> versionEntries, HashMap<String, String[]> preExecApprovalList) throws IOException {
        this(driver, reportName, objective, requirements, references, notes, versionEntries);
        this.preExecApprovals = preExecApprovalList;
    }

    public void startReport() throws IOException {
        runTime = new Date();
        fos = new FileOutputStream("reports/" + reportName + "/" + reportName + ".pdf");
        pdfw = new PdfWriter(fos);
        pdfd = new PdfDocument(pdfw);
        document = new Document(pdfd, PageSize.A4.rotate());
        font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        initializeTables();
        writeStepHeader();
        reportVersionUpdates = new ArrayList<>();
        preExecApprovals = new HashMap<>();
    }

    void initializeTables() {
        stickyTable = new Table(UnitValue.createPercentArray(colHeaderWidths)).setWidthPercent(100).setFontSize(8);
        stepTable = new Table(UnitValue.createPercentArray(colWidths)).setWidthPercent(100).setFontSize(8);
        environmentTable = new Table(UnitValue.createPercentArray(colPrereqWidths)).setWidthPercent(100).setFontSize(8);
        prerequisitesTable = new Table(UnitValue.createPercentArray(colPrereqWidths)).setWidthPercent(100).setFontSize(8);
        postExecutionTable = new Table(UnitValue.createPercentArray(colExecWidths)).setWidthPercent(100).setFontSize(8);
        preExecutionTable = new Table(UnitValue.createPercentArray(colExecWidths)).setWidthPercent(100).setFontSize(8);
        versionHistoryTable = new Table(UnitValue.createPercentArray(colVersionWidths)).setWidthPercent(100).setFontSize(8);
    }

    public void addScreenshotStep(String ssName, WebDriver driver) {
        Image screenShot = null;
        try {
            screenShot = takeScreenshot((ssName.length() > 35) ? ssName.substring(0, 34) : ssName, driver);
        } catch (Exception ignored) {

        }
        stepTable.addCell(new Cell().add(new Paragraph(String.valueOf(stepTable.getNumberOfRows() + 1)).setFont(font)));
        stepTable.addCell(new Cell().add(new Paragraph("Capture screenshot").setFont(font)));
        stepTable.addCell(new Cell().add(new Paragraph("Screenshot captured").setFont(font)));
        stepTable.addCell(new Cell(1, 3).add(screenShot));
    }

    public Image takeScreenshot(String ssName, WebDriver driver) throws IOException  {
        int bodyHeight = driver.findElement(By.tagName("body")).getSize().getHeight();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String zoom = "document.body.style.zoom = '60.0%'";
        js.executeScript(zoom);
        Dimension originalSize = driver.manage().window().getSize();
        Dimension size = new Dimension(originalSize.getWidth(), bodyHeight);
        driver.manage().window().setSize(size);

        File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenShot = new File(screenshotDir + "/" + ssName + ".png");
        FileUtils.copyFile(ss, screenShot);
        ImageData imageData = ImageDataFactory.create(screenShot.getPath());
        zoom = "document.body.style.zoom = '100.0%'";
        js.executeScript(zoom);
        driver.manage().window().setSize(originalSize);
        return new Image(imageData).scaleToFit(300, 600);
    }

    public void addScreenshotStep(String ssName)  {
        addScreenshotStep(ssName, driver);
    }


    public boolean verifyCondition(Callable<Boolean> callable, String closureText, boolean hardFail) {
        Boolean result;
        String expected = StringUtils.capitalize(closureText);
        String actualFail = closureText+ " condition is not met";
        try {
            result=callable.call();
        } catch (Exception e) {
            result=false;
        }
        addStep("Verify " + closureText, expected, result ? expected : actualFail, result, hardFail);
        return result;
    }

    public boolean addTextVerificationStep(WebElement element, String navText, String textToVerify, boolean hardFail) {
        String actual = "";
        String actualRep;
        String expected = ((textToVerify.length() > 90) && !textToVerify.contains(" "))? navText + " is correct" : StringUtils.capitalize(navText) + " contains '" + textToVerify + "'";
        if (element==null) {
            addStep("Verify " + expected, expected, "Navigator not displayed", false, hardFail);
            return false;
        }
        if (!element.getText().isEmpty()) {
            actual = element.getText();
        } else if (!element.getAttribute("value").isEmpty()) {
            actual = element.getAttribute("value");
        }
        boolean result = actual.toLowerCase().contains(textToVerify.toLowerCase());
        if ((actual.length() > 60) && !textToVerify.contains(" ")) {
            actualRep = result ? navText + " is correct" : navText + " is incorrect";
        } else {
            actualRep = result ? "Field contains '"+actual+"'" : StringUtils.capitalize(navText) + " does not contain '" + textToVerify + "'";
        }
        addStep("Verify " + expected, expected, actualRep, result, hardFail);
        return result;
    }

    public boolean addMultipleVerificationStep(String condition, HashMap<String, Object> resultMap, Boolean hardFail) {
        String step = "Verify " + condition +"\n";
        String expectedResults = StringUtils.capitalize(condition) + "\n";
        String actualResults = StringUtils.capitalize(condition) + "\n";
        for (HashMap.Entry<String, Object> entry : resultMap.entrySet()) {
            step=step.concat("   " + entry.getKey()+"\n");
            expectedResults = expectedResults.concat(entry.getKey() + " : true\n");
            actualResults = actualResults.concat(entry.getKey() + " : " + entry.getValue().toString() + "\n");
        }
        addStep(step, expectedResults, actualResults, !resultMap.containsValue(false), hardFail);
        return !resultMap.containsValue(false);
    }

    public void addMetricsVerificationStep( LinkedHashMap<String, Object[]> resultMap,  Boolean hardFail) {
        String step = "Verify user expected metrics match CSV\n";
        String expectedResults = "User expected metrics match CSV\n";
        String actualResults = "Actual metrics\n";
        List<Boolean> metricComparisons = new ArrayList<>();
        for ( Map.Entry<String, Object[]> entry : resultMap.entrySet()) {
            String resultLine = entry.getKey() + " : " + entry.getValue()[1];
            boolean compResult;
            if (entry.getKey().contains("Timestamp")) {
                compResult=entry.getValue()[1]!=null;
            } else if (entry.getValue()[0]==null || entry.getValue()[1]==null) {
                compResult = entry.getValue()[0] == null && entry.getValue()[1] == null;
            } else {
                compResult=entry.getValue()[0].equals(entry.getValue()[1]);
            }
            step=step.concat("   " + entry.getKey()+"\n");
            expectedResults = expectedResults.concat(entry.getKey() + " : " + entry.getValue()[0]+"\n");
            actualResults = (!compResult)?
               actualResults.concat("*NO MATCH: " + resultLine.toUpperCase() + " should be " + entry.getValue()[0]+"\n"):
               actualResults.concat("CORRECT: "+resultLine+"\n");
            metricComparisons.add(compResult);
        }
        addStep(step, expectedResults, actualResults, !metricComparisons.contains(false), hardFail);
    }

    public void addStep(String step, String expectedResult, String actualResult, boolean passOrFail, boolean hardFail) {
        int stepNumber = stepTable.getNumberOfRows() + 1;
        if (passOrFail) {
            writeTableLine(stepTable, stepNumber + ";" + step + ";" + expectedResult + ";" + actualResult +
                    ";PASS;N/A", font, Color.BLACK, false, false);
        } else {
            writeTableLine(stepTable, stepNumber + ";" + step + ";" + expectedResult + ";" + actualResult + ";" +
                    "FAIL;To Be Entered - See screenshot below", font, Color.RED, false, false);
            overallResult = false;
            try {
                if (!step.contains("CSV value equals")) {
                    failureImage.add(takeScreenshot("failure_" + expectedResult, driver));
                }
            } catch (Exception ignored) {
            }
            if (hardFail) {
                stepTable.complete();
            }
        }
    }

    public void addStep(String step, String expectedResult, String actualResult, boolean passOrFail) {
        addStep(step, expectedResult, actualResult, passOrFail, false);
    }

    void writeTableLine(Table table, String line, PdfFont font, Color fontColor, boolean isHeader,
                        Boolean isLeftColumnHeader) {
        ArrayList<String> tokenizer = new ArrayList<>(Arrays.asList(line.split(";")));
        for (int i = 0; i < tokenizer.size(); i++) {
            String entry = tokenizer.get(i);
            if (isHeader) {
                table.addHeaderCell(
                        new Cell().add(new Paragraph(entry).setFont(bold)).setBackgroundColor(Color.LIGHT_GRAY).setHorizontalAlignment(HorizontalAlignment.CENTER));
            } else if (i == 0 && isLeftColumnHeader) {
                table.addCell(
                        new Cell().add(new Paragraph(entry).setFont(bold)).setBackgroundColor(Color.LIGHT_GRAY).setHorizontalAlignment(HorizontalAlignment.CENTER));
            } else {
                table.addCell(
                        new Cell().add(new Paragraph(entry).setFont(font)).setFontColor(fontColor).setBackgroundColor(Color.WHITE));
            }
        }
    }

    public void printFinalReport()  {
        createStickyHeaderTable();
        createEnvironmentTable();
        createPostExecutionTable();
        createPreExecutionTable();

        document.add(stickyTable);
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Pre-Execution Approvals:").setFont(bold));
        document.add(new Paragraph(" "));
        document.add(preExecutionTable);
        document.add(new AreaBreak());
        if (reportVersionUpdates.size() != 0) {
            document.add(new Paragraph("Version History:").setFont(bold));
            document.add(new Paragraph(" "));
            document.add(versionHistoryTable);
        }
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Test Prerequisites:").setFont(bold));
        document.add(new Paragraph(" "));
        document.add(prerequisitesTable);
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Test Steps:").setFont(bold));
        document.add(new Paragraph(""));
        document.add(environmentTable);
        document.add(new Paragraph(" "));
        document.add(stepTable);
        document.add(new AreaBreak());
        document.add(new Paragraph("Post-Execution Approvals").setFont(bold));
        document.add(new Paragraph(" "));

        if (!failureImage.isEmpty()) {
            document.add(failureImage.get(failureImage.size() - 1));
        }

        document.add(new Paragraph(" "));
        document.add(postExecutionTable);
    }

    public void closeDocument() {
        document.close();
    }

    void clickAndReport(WebElement element, String navIdentifier) {
        if (navIdentifier.isEmpty() && element.getText() != null) {
            navIdentifier = element.getText();
        }
        try {
            element.click();
            addStep("Click '" + navIdentifier + "'", "'" + navIdentifier + "' is clicked", "As expected", true, false);
        } catch (Exception e) {
            addStep("Click " + navIdentifier + "'", "'" + navIdentifier + "' is clicked", "Error occurred " + e.getClass().getSimpleName(), false, false);
        }
    }

    public void reportPageTransition(String pageText, Boolean result) {
        String step= "Verify " + pageText + " is displayed";
        String expected = pageText + " is displayed";
        String actualFail = pageText + " is NOT displayed";
        addStep(step, expected, result?expected:actualFail, result, true);
    }

    public void doubleClickAndReport(WebElement nav, String navIdentifier) {
        if (navIdentifier.isEmpty() && nav.getText() != null) {
            navIdentifier = nav.getText();
        }
        try {
            new Actions(this.driver).doubleClick(nav).perform();
            addStep("Double-click '" + navIdentifier + "'", "'" + navIdentifier + "' is double-clicked", "As expected", true);
        } catch (Exception e) {
            addStep("Double-click " + navIdentifier + "'", "'" + navIdentifier + "' is double-clicked", "Error occurred " + e.getClass().getSimpleName(), false);
        }
    }

    public boolean verifyActionToNavDisplayed(String actionText, WebElement element, String navText, boolean hardFail) {
        String expected = "'" + navText + "' is displayed";
        String actualPass = "'" + navText + "' is displayed";
        String actualFail = "'" + navText + "' is NOT displayed";

        boolean result = element!=null;
        addStep(actionText, expected, result ? actualPass : actualFail, result, hardFail);
        return result;
    }

    public Boolean verifyActionToPageDisplayed(String actionText, BasePage thisPage,
                                               String pageText, Boolean hardFail) {
        String expected =  pageText + " is displayed";
        String actualPass = pageText + " is displayed";
        String actualFail = pageText + " is NOT displayed";
        boolean result = thisPage.verifyAtPage();
        addStep(actionText, expected, result ? actualPass : actualFail, result, hardFail);
        return result;
    }

    public Boolean verifyNotDisplayed(WebElement element, String navText, boolean hardFail) {
        boolean result;
        String expected = "'" + navText + "' is NOT displayed";
        String actualPass = "'" + navText + "' is NOT displayed";
        String actualFail = "'" + navText + "' is displayed";

        result= element == null || !element.isDisplayed();
        addStep("Verify " + expected, expected, result ? actualPass : actualFail, result, hardFail);
        return result;
    }

    public void sendTextAndReport(WebElement element, String text, String navIdentifier, boolean hardFail) {
        String expectedResult = text.equals("") ? navIdentifier + " is blank " : "'" + text + "' is typed into '" + navIdentifier + "'.";
        String step = text.equals("") ? navIdentifier + " is blank " : "Type '" + text + "' into '" + navIdentifier + "' field.";
        try {
            element.sendKeys(text);
            addStep(step, expectedResult, "As expected", true, hardFail);
        } catch (Exception e) {
            addStep(step, expectedResult, "Error occurred " + e.getClass().getSimpleName(), false, hardFail);
        }
    }

    void writeStepHeader() {
        String header = "Step;Test Steps;Expected Results;Actual Results;Pass/Fail;Defect Number";
        writeTableLine(stepTable, header, bold, Color.BLACK, true, false);
    }

    void createPrereqTable(String objectives, String requirements, String references, String notes) {
        String[] rows = {"Test Objectives;" + objectives,
                "Test Requirements;" + requirements,
                "Test References;" + references,
                "Test Notes;" + notes};
        for (String row : rows) {
            writeTableLine(prerequisitesTable, row, font, Color.BLACK, false, true);
        }
    }


    void createPostExecutionTable() {
        String result = overallResult ? "PASS" : "FAIL";
        postExecutionTable.addCell(
                new Cell(1, 1).add(new Paragraph("Overall Test Protocol Disposition(Pass/Fail)")
                                .setFont(bold).setFontColor(Color.BLACK))
                        .setBackgroundColor(Color.LIGHT_GRAY));
        postExecutionTable.addCell(new Cell(1, 3).add(result));
        postExecutionTable.addCell(
                new Cell(1, 1).add(new Paragraph("Comments/Deviations/Defects Found")
                                .setFont(bold).setFontColor(Color.BLACK))
                        .setBackgroundColor(Color.LIGHT_GRAY));
        postExecutionTable.addCell(new Cell(1, 3).add("Note: N/A is used as a default value when step is informational only"));
        postExecutionTable.addCell(
                new Cell(3, 1).setBackgroundColor(Color.LIGHT_GRAY).setFont(bold).add(new Paragraph("Post-Execution Approval"))
                        .setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER));
        postExecutionTable.addCell(
                new Cell(1, 1).setBackgroundColor(Color.LIGHT_GRAY).setFont(bold).add(new Paragraph("Executed By:")));
        postExecutionTable.addCell(new Cell(1, 1).add("\n\n\n\n"));
        postExecutionTable.addCell(new Cell(1, 1).add("\n\n\n\n"));
        postExecutionTable.addCell(
                new Cell(2, 1).setBackgroundColor(Color.LIGHT_GRAY).setFont(bold)
                        .add(new Paragraph("Reviewed By:")));
        postExecutionTable.addCell(new Cell(1, 1).add("\n\n\n\n"));
        postExecutionTable.addCell(new Cell(1, 1).add("\n\n\n\n"));
        postExecutionTable.addCell(new Cell(1, 1).setBackgroundColor(Color.LIGHT_GRAY).setFont(bold).add("Printed name and role"));
        postExecutionTable.addCell(new Cell(1, 1).setBackgroundColor(Color.LIGHT_GRAY).setFont(bold).add("Signature/Date"));
    }

    void createPreExecutionTable() {
        String[] author = preExecApprovals.getOrDefault("author", new String[]{"", ""});
        String[] pm = preExecApprovals.getOrDefault("pm", new String[]{"", ""});
        String[] dev = preExecApprovals.getOrDefault("dev", new String[]{"", ""});
        String[] sqa = preExecApprovals.getOrDefault("sqa", new String[]{"", ""});
        String[] qa = preExecApprovals.getOrDefault("qa", new String[]{"", ""});
        String[] columns = {
                "Author;" + author[0] + "\n" + author[1] + "\n\n\n;  ",
                "Product Manager;" + pm[0] + "\n" + pm[1] + "\n\n\n;  ",
                "Software Development;" + dev[0] + "\n" + dev[1] + "\n\n\n;  ",
                "Software Quality Assurance;" + sqa[0] + "\n" + sqa[1] + "\n\n\n;  "
        };
        preExecutionTable.addCell(new Cell(6, 1).setBackgroundColor(Color.LIGHT_GRAY).setFont(bold).add("Pre-Execution Approval"));
        for (String column : columns) {
            writeTableLine(preExecutionTable, column, font, Color.BLACK, false, true);
        }

        preExecutionTable.addCell(new Cell(2, 1).
                add(new Paragraph("Quality Assurance Representative\n\n\n\n").setFont(bold).setFontColor(Color.BLACK))
                .setBackgroundColor(Color.LIGHT_GRAY)
                .setVerticalAlignment(VerticalAlignment.MIDDLE));

        preExecutionTable.addCell(new Cell(1, 1).add(qa[0] + "\n" + qa[1] + "\n\n\n"));
        preExecutionTable.addCell(new Cell(1, 1).add(""));
        preExecutionTable.addCell(new Cell(1, 1).setBackgroundColor(Color.LIGHT_GRAY).add("Printed name and role").setFont(bold));
        preExecutionTable.addCell(new Cell(1, 1).setBackgroundColor(Color.LIGHT_GRAY).add("Signature/Date").setFont(bold));
    }

    void createVersionHistoryTable() {
        if (reportVersionUpdates != null) {
            thisVersion = reportVersionUpdates.get(reportVersionUpdates.size() - 1).split(";")[0];
            writeTableLine(versionHistoryTable, "Version;Date;Description of Changes;Author",
                    font, Color.BLACK, true, false);
            for (String update : reportVersionUpdates) {
                writeTableLine(versionHistoryTable, update, font, Color.BLACK, false, false);
            }
        }
    }

    void createEnvironmentTable() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String environment = driver.getCurrentUrl().split("/")[2] + "\n " +
                "Browser: " + cap.getBrowserName() + " Version: "+ cap.getBrowserVersion()+"\n" +
                "OS: " + System.getProperty("os.name")+ " Version: "+ System.getProperty("os.version");
        Date runDate = new Date();
        LocalDate localDate = runDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String day = (localDate.getDayOfMonth()<10)?"0"+localDate.getDayOfMonth(): String.valueOf(localDate.getDayOfMonth());
        String runDateString = day +localDate.getMonth().name().substring(0, 3) + localDate.getYear();
        String[] rows = {"Tester Name; "+ System.getProperty("user.name"),
                "Software Version; v0.29.1", // Hardcode
                "Environment;" + environment,
                "Run Date;" + runDateString, "Run Number;2"};
        for (String row : rows) {
            writeTableLine(environmentTable, row, font, Color.BLACK, false, true);
        }
    }

    void createStickyHeaderTable() {
        try {
            ImageData imageData = ImageDataFactory.create("IELogo.png");
            Image IELogo = new Image(imageData).scaleToFit(100, 300);
            stickyTable.addCell(new Cell(2, 1).add(IELogo));
        } catch (Exception e) {
            stickyTable.addCell(new Cell(2, 1).add("Logo not found"));
        }
        stickyTable.addCell(new Cell(1, 2).add("Idea Evolver, LLC").
                setTextAlignment(TextAlignment.CENTER).setFont(bold));
        stickyTable.addCell(new Cell(1, 2).add("Test Script").
                setTextAlignment(TextAlignment.CENTER).setFont(bold));
        stickyTable.addCell(new Cell(1, 1).add("Document Number:\n").add(new Paragraph(reportName).
                setFont(bold)));
        stickyTable.addCell(new Cell(1, 2).add("Title:\n").add(new Paragraph((reportTitle == null) ? reportName : reportTitle).
                setFont(bold)));
        stickyTable.addCell(new Cell(1, 1).add("Version:\n").
                add(new Paragraph((reportVersionUpdates.size() != 0) ? thisVersion : "Unknown").
                        setFont(bold)));
        stickyTable.addCell(new Cell(1, 2).add("Effective Date:\n").
                add(new Paragraph("See Last Approval Date").
                        setFont(bold)));
    }

    void selectDropDownByTextAndReport(WebElement element, String value, String navIdentifier) {
        String expectedResult = value.equals("") ? navIdentifier + " is blank " : "'" + value + "' is selected from '" +
                navIdentifier + "'.";
        String step = value.equals("") ? navIdentifier + " is blank " : "Select '" + value + "' from '" + navIdentifier + "' field.";
        try {
            Select select = new Select(element);
            select.selectByVisibleText(value);
            addStep(step, expectedResult, "As expected", true, true);
        } catch (Exception e) {
            addStep(step, expectedResult, "Error occurred " + e.getMessage(), false, false);
        }
    }

}
