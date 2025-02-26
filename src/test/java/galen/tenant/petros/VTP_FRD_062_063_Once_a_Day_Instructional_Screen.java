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

import static java.lang.Thread.sleep;

public class VTP_FRD_062_063_Once_a_Day_Instructional_Screen extends BaseTest {
    static String OBJECTIVE = "FRD_062: To verify the Once a Day Instructional Screen shall provide a control that " +
            "allows the user to input an acknowledgement response.\n" +
            "FRD_063: To verify the Once a Day Instructional Screen shall navigate to the Nitrate Use Screen when " +
            "the user inputs an acknowledgement response";
    static String NOTES = "This protocol contains the following scenario(s):\n" +
            "- The Once a Day Instructional Screen has controls that allow the user to input an acknowledgement " +
            "response\n" +
            "- Submitting an acknowledgement response will navigate the user to the Nitrate Use Screen";
    static String REQUIREMENTS = "FRD_062_063";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_w_Guest_User.docx";
    PetrosPageObj pageObj;
    PetrosUser user;
    String reportName = "VTP_FRD_062_063_Once_a_Day_Instructional_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_FRD_062_063_Once_a_Day_Instructional_Screen() throws IOException {
        this.user = new PetrosUser();
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_FRD_062_063_Once_a_Day_Instructional_Screen_Test() throws IOException, InterruptedException {
        WebDriver driver = DriverManager.getDriver();
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_062_063 - Once a Day Instructional Screen";
        pageObj = new PetrosPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);;

        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.otherEDMeds, report);
        pageObj.otherEDMeds.clickYesOrNo( "Yes", report);
        pageObj.otherEDMeds.clickNextToPage(pageObj.onceADayInstruction, report);
        pageObj.edAndHeartDiseaseInfo.verifyNextPresent(report);
        report.addScreenshotStep("Step2_InstructionalScreen", driver);

        pageObj.heartProblemsInstruction.clickNextToPage(pageObj.nitrateUse, report);
        report.addScreenshotStep("Step3_NitrateUseScreen", driver);
    }

}
