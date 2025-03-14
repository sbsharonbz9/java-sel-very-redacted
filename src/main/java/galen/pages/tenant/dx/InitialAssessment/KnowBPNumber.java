package galen.pages.tenant.dx.InitialAssessment;

import galen.enums.tenant.dx.BloodPressureType;
import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import galen.pages.common.SingleResponseRadioButtonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class KnowBPNumber extends SingleResponseRadioButtonPage {
    public By title = By.className("KnowBPNumbersScreen");
    public static By modalTitle = By.className("yesno-confirm");
    public By knowBPTextElement1 = By.cssSelector("fieldset > p:nth-child(3)");
    public By knowBPTextElement2 = By.cssSelector("fieldset > p:nth-child(4)");
    public By knowBPTextElement3 = By.cssSelector("fieldset > p:nth-child(5)");

    public String knowBPText1 = "Zena may not be safe for people with high blood pressure.";
    public String knowBPText2 = "If you don't know your blood pressure numbers or your blood pressure was taken " +
            "more than 3 months ago, you will need to get your blood pressure measured.";
    public String knowBPText3 =  "We will help find a convenient way to get it measured";
    public ArrayList<String> bpOptions = new ArrayList<>(Arrays.asList("Yes, I know my blood pressure numbers",
            "No, I don't know my blood pressure numbers but I know my blood pressure is normal",
            "No, I don't know my blood pressure"));

    public KnowBPNumber(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Do you know your blood pressure numbers?";
        reportText="Know BP Numbers Screen";
        options=bpOptions;
        modal=modalTitle;
    }

    public boolean verifyKnowBPText(@Nullable GalenReport report)  {
        boolean result = true;
        result = result && basicHelpers.verifyText(basicHelpers.getWebElement(knowBPTextElement1),"paragraph 1",
                knowBPText1, report );
        result = result && basicHelpers.verifyText(basicHelpers.getWebElement(knowBPTextElement2),"paragraph 2",
                knowBPText2, report );
        result = result && basicHelpers.verifyText(basicHelpers.getWebElement(knowBPTextElement3),"paragraph 3",
                knowBPText3, report );
        return result;
    }

    public void verifyModalThreeMonthsOpen(@Nullable GalenReport report) {
        basicHelpers.verifyDisplayedFlex(modalTitle, "3 Month Confirmation Modal", report);
    }

    public boolean verifyNoOptionsSelected(@Nullable GalenReport report) {
        boolean result;
        options = new ArrayList<>(Arrays.asList("yes","no", "maybe"));
        result=super.verifyNoOptionsSelected(report);
        options=bpOptions;
        return result;
    }

    public boolean clickYesAndAddressModalToPage(BasePage page, String modalResponse, @Nullable GalenReport report) {
        clickYesAndOpenModal(report);
        return clickYesNoModalToPage(modalResponse, page, report);
    }

    public void clickYesAndOpenModal( @Nullable GalenReport report) {
        selectRadioReponse(BloodPressureType.Yes_Know_BP.label, report);
        clickNext(report);
        verifyModalThreeMonthsOpen(report);
    }
}
