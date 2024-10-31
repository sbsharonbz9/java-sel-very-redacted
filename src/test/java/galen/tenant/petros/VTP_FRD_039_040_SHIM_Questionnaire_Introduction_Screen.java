package galen.tenant.petros;

import galen.base.BaseTest;
import galen.driver.DriverManager;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosNavigations;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_039_040_SHIM_Questionnaire_Introduction_Screen extends BaseTest {
    static String OBJECTIVE = "FRD_039: To verify SHIM Questionnaire Introduction Screen shall provide a control that allows the user to input an acknowledgement response.\n" +
            "FRD_040: To verify SHIM Questionnaire Introduction Screen shall navigate the user to the SHIM Questionnaire – Question 1 Screen when the user inputs an acknowledgement response.\n";
    static String NOTES = "This protocol contains the following scenario(s):\n" +
            "‒\tSHIM Questionnaire Introduction Screen contains a Next button\n" +
            "‒\tNext button navigates the user to the SHIM Questionnaire - Question 1 Screen\n";
    static String REQUIREMENTS = "FRD_039, FRD_040";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_w_Guest_User.docx";
    PetrosPageObj pageObj;
    PetrosUser user;
    String reportName = "VTP_FRD_039_040_SHIM_Questionnaire_Introduction_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_FRD_039_040_SHIM_Questionnaire_Introduction_Screen() throws IOException {
        this.user = new PetrosUser();
        VERSIONHISTORY.add(" ; ; ; ");
    }



    @Test
    public void VTP_FRD_039_040_SHIM_Questionnaire_Introduction_Screen() throws IOException, InterruptedException {
        WebDriver driver = DriverManager.getDriver();
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_039_040_SHIM_Questionnaire_Introduction_Screen";
        pageObj = new PetrosPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);;
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.questionnaireIntroduction, report);
        pageObj.questionnaireIntroduction.verifyNextPresent(report);
        report.addScreenshotStep("SHIM_Questionnaire_Introduction_Screen",driver);
        pageObj.questionnaire1.clickNextToPage(pageObj.questionnaire1,report);
        report.addScreenshotStep("SHIM Questionnaire - Question 1 ",driver);
    }

}
