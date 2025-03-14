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

    public ArrayList<String> bpOptions = new ArrayList<>(Arrays.asList(BloodPressureType.Yes_Know_BP.label,
            BloodPressureType.No_Know_BP.label, BloodPressureType.No_DO_NOT_Know_BP.label));

    public KnowBPNumber(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Do you know your blood pressure numbers?";
        reportText="Know BP Numbers Screen";
        options=bpOptions;
        modal=modalTitle;
    }

    public void verifyModalThreeMonthsOpen(@Nullable GalenReport report) {
        basicHelpers.verifyDisplayedFlex(modalTitle, "3 Month Confirmation Modal", report);
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
