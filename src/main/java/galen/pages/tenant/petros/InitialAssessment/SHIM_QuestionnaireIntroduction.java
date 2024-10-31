package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

public class SHIM_QuestionnaireIntroduction extends BasePage {

    public static By shimQuestTextTwo=By.xpath("//p[contains(text(),'The next five questions have been helpful in deter')]");


    public By shimIntroHeader = By.className("shimIntoScreen");

    public SHIM_QuestionnaireIntroduction(WebDriver driver) {
        super(driver);
        titleText="";
        reportText="SHIM Questionnaire Introduction Screen";
    }

    public WebElement getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shimIntroHeader));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader().isDisplayed(), "SHIM Questionnaire Introduction Screen", report);
    }


    public WebElement getShimQuestionTxtTwo() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shimQuestTextTwo));


}}
