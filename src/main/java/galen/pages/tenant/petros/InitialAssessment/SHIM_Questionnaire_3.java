package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.SingleResponseRadioButtonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class SHIM_Questionnaire_3 extends SingleResponseRadioButtonPage {

    public SHIM_Questionnaire_3(WebDriver driver) {
        super(driver);
        titleText = "ERECTION MAINTENANCE";
        reportText="Questionnaire Screen 3";
        headingTitle=By.className("shimScreen3");
        options = new ArrayList<>(Arrays.asList("Almost never or never", "A few times (much less than half the time)",
                "Sometimes (about half the time)", "Most times (much more than half the time)",
                "Almost always or always"));
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeadingTitle()!=null, reportText, report);
    }

}
