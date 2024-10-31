package galen.pages.tenant.petros.InitialAssessment;

import galen.constants.FrameworkConstants;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DrugFactsLabelPage extends BasePage {

    public static By activeIngredientTabletTxt = By.xpath("Be sure to read the Drugs Facts Label (DFL) below.");
    public static By purposeTxt = By.xpath("//i[normalize-space()='Purpose']");
    public static By warningTxt = By.xpath("//i[normalize-space()='Warnings']");
    public static By askDoctorBeforeUsTxt = By.xpath("//h2[normalize-space()='Ask a doctor before use if you:']");
    public static By askBeforeTakingtxt = By.xpath("//div[@class='dfl-sub-header']");
    public static By whenUsingProductTxt = By.xpath("//h2[normalize-space()='When using this product:']");
    public static By stopuseAndAskDocTxt = By.xpath("//h2[normalize-space()='Stop use and ask a doctor if you:']");
    public static By directuonsTxt = By.xpath("//i[normalize-space()='Directions']");
    public static By otherInformationTxt = By.xpath("//h2[normalize-space()='Other Information']");
    public static By inactiveIngredients = By.xpath("//i[normalize-space()='Inactive ingredients:']");
    public static By questionsTxt = By.xpath("//i[normalize-space()='Questions? Go to']");


    public By title = By.xpath("//h2[normalize-space()='Be sure to read the Drugs Facts Label (DFL) below.']");
    protected WebDriver driver;
    protected WebDriverWait wait;

    public DrugFactsLabelPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, FrameworkConstants.getExplicitWait());
    }

    public boolean verifyDrugFactsLabelPage() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(headingTitle,
                "Be sure to read the Drugs Facts Label (DFL) below."));
    }

    public WebElement getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title));
    }

    private static void verifyElementText(WebDriver driver, By locator, String expectedText, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String actualText = element.getText();
        Assert.assertEquals(actualText, expectedText, "Text verification failed for locator: " + locator.toString());
    }

    public static void verifyAllTexts(WebDriver driver, int timeoutInSeconds) {
        verifyElementText(driver, activeIngredientTabletTxt, "Be sure to read the Drugs Facts Label (DFL) below.", timeoutInSeconds);
        verifyElementText(driver, purposeTxt, "Purpose", timeoutInSeconds);
        verifyElementText(driver, warningTxt, "Warnings", timeoutInSeconds);
        verifyElementText(driver, askDoctorBeforeUsTxt, "Ask a doctor before use if you:", timeoutInSeconds);
        verifyElementText(driver, askBeforeTakingtxt, "Expected Text for askBeforeTakingtxt", timeoutInSeconds); // Replace with actual expected text
        verifyElementText(driver, whenUsingProductTxt, "When using this product:", timeoutInSeconds);
        verifyElementText(driver, stopuseAndAskDocTxt, "Stop use and ask a doctor if you:", timeoutInSeconds);
        verifyElementText(driver, directuonsTxt, "Directions", timeoutInSeconds);
        verifyElementText(driver, otherInformationTxt, "Other Information", timeoutInSeconds);
        verifyElementText(driver, inactiveIngredients, "Inactive ingredients:", timeoutInSeconds);
        verifyElementText(driver, questionsTxt, "Questions? Go to", timeoutInSeconds);
    }



}
