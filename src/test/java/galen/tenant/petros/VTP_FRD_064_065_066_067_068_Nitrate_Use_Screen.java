package galen.tenant.petros;

import com.itextpdf.io.IOException;
import galen.base.BaseTest;
import galen.driver.DriverManager;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosNavigations;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_064_065_066_067_068_Nitrate_Use_Screen extends BaseTest {
    static String OBJECTIVE = "FRD_064: To verify Nitrate Use Screen shall provide controls that allow the user to input a confirmation, denial, or uncertain response.\n" +
            "FRD_065: To verify Nitrate Use Screen shall provide a control that allows the user to submit their response selection.\n" +
            "FRD_066: To verify Nitrate Use Screen shall navigate to the Clarification on Nitrate Use Screen when the user inputs a confirmation response.\n" +
            "FRD_067: To verify Nitrate Use Screen shall navigate to the Clarification on Nitrate Use Screen when the user inputs an uncertain response.\n" +
            "FRD_068: To verify Nitrate Use Screen shall navigate to the Clarification on Nitrate Use Screen when the user inputs a denial response.\n";
    static String NOTES = "This protocol contains the following scenario(s):\n" +
            "-\tThe Nitrate Use Screen has controls that allow the user to input a confirmation, denial, or uncertain response\n" +
            "-\tNext button is displayed to submit the user response\n" +
            "-\tSubmitting a confirmation response will navigate the user to the Clarification on Nitrate Use Screen\n" +
            "-\tSubmitting a denial response will navigate the user to the Clarification on Nitrate Use Screen\n" +
            "-\tSubmitting an uncertain response will navigate the user to the Clarification on Nitrate Use Screen\n" +
            "- Submitting an acknowledgement response will navigate the user to the Nitrate Use Screen";
    static String REQUIREMENTS = "FRD_064, FRD_065, FRD_066, FRD_067, FRD_068";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_w_Guest_User.docx";
    PetrosUser user;
    String reportName = "VTP_FRD_064_065_066_067_068_Nitrate_Use_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_FRD_064_065_066_067_068_Nitrate_Use_Screen() throws IOException {
        this.user = new PetrosUser();
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_FRD_064_065_066_067_068_Nitrate_Use_Screen_Test() throws IOException, InterruptedException, java.io.IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_064_065_066_067_068_Nitrate_Use_Screen";
        PetrosPageObj pageObj = new PetrosPageObj(driver);
        PetrosUser user = new PetrosUser();
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);
        PetrosNavigations petrosNav = new PetrosNavigations(driver);

        petrosNav.partialNavigationIA(user, pageObj.nitrateUse, report);
        pageObj.nitrateUse.verifyYesNoUnsurePresent(report);
        report.addScreenshotStep("Nitrate Use Screen Options");

        pageObj.nitrateUse.clickYesNoNextToPage("Yes",pageObj.clarification, report);
        report.addScreenshotStep("Clarification on Nitrate Use Screen ");

        pageObj.pritUnl.load(UrlType.PETROS);
        petrosNav.partialNavigationIA(user, pageObj.nitrateUse, report);
        pageObj.nitrateUse.clickYesNoUnsure("Unsure", report);
        pageObj.nitrateUse.clickNextToPage(pageObj.clarification, report);
        report.addScreenshotStep("Clarification Is seen Screen Options");

        pageObj.pritUnl.load(UrlType.PETROS);
        petrosNav.partialNavigationIA(user, pageObj.nitrateUse, report);
        pageObj.nitrateUse.clickYesNoNextToPage("No",pageObj.clarification, report);
        report.addScreenshotStep("Clarification Is seen Screen Options 3");
    }
}
