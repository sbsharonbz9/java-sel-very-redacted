package galen.tenant.petros;

import galen.base.BaseTest;
import galen.driver.DriverManager;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosNavigations;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_029_030_031_Purchase_for_Self_Screen extends BaseTest {
    static String OBJECTIVE = "FRD_029: To verify the Purchase for Self Screen shall provide controls that allow the user to submit a confirmation or denial response.\n" +
            "FRD_030: To verify the Purchase for Self Screen shall navigate to the Date of Birth / Sex at Birth Screen when the user submits a confirmation response.\n" +
            "FRD_031: To verify the Purchase for Self Screen shall navigate to the DNU – Not Purchasing For Self Screen when the user submits a denial response.\n";
    static String NOTES = "This protocol contains the following scenario(s):\n" +
            "-\tThe Purchase for Self Screen has controls that allow the user to select whether they confirm or deny a question\n" +
            "-\tSubmitting a confirmation response navigates the user to the Date of Birth / Sex at Birth Screen \n" +
            "-\tSubmitting a denial response navigates the user to the DNU Screen\n" +
            "-\tSubmitting a denial response after Edit Link navigation from the Review Answers Screen navigates the user to the DNU Screen\n";
    static String REQUIREMENTS = "FRD_029, FRD_030, FRD_031";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_w_Guest_User.docx";
    PetrosPageObj pageObj;
    PetrosUser user;
    String reportName = "VTP_FRD_029_030_031_Purchase_for_Self_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;

    VTP_FRD_029_030_031_Purchase_for_Self_Screen() throws IOException {
        this.user = new PetrosUser();
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_FRD_029_030_031_Purchase_for_Self_Screen() throws IOException, InterruptedException {
        WebDriver driver = DriverManager.getDriver();
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_029_030_031_Purchase_for_Self_Screen";
        bh = new BasicHelpers(driver);
        pageObj = new PetrosPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);;
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.purchaseSelf, report);
        pageObj.purchaseSelf.verifyYesNoPresent(report);
        pageObj.purchaseSelf.verifyNextPresent(report);
        report.addScreenshotStep("Purchase Self Screen Step 2", driver);
        pageObj.purchaseSelf.clickYesOrNo("Yes", report);
        bh.verifyButtonEnabled(pageObj.purchaseSelf.getNextButton(), true, report);
        report.addScreenshotStep("Purchase Self Screen next button Enabled Step 3", driver);
        pageObj.purchaseSelf.clickYesOrNo("Yes", report);
        bh.verifyButtonEnabled(pageObj.purchaseSelf.getNextButton(), true, report);
        report.addScreenshotStep("Purchase Self Screen next button Enabled Step 4", driver);
        pageObj.purchaseSelf.clickYesOrNo("No", report);
        bh.verifyButtonEnabled(pageObj.purchaseSelf.getNextButton(), true, report);
        pageObj.purchaseSelf.clickYesOrNo("Yes", report);
        pageObj.purchaseSelf.clickNextToPage(pageObj.purchaseSelf, report);
        report.addScreenshotStep("Date Of Birth Screen Step 5 ", driver);

        pageObj.pritUnl.getWelcomePage(report);
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.purchaseSelf, report);
        pageObj.purchaseSelf.verifyAtPage(report);
        pageObj.purchaseSelf.clickYesOrNo("No", report);
        pageObj.purchaseSelf.clickNextToPage(pageObj.purchaseSelf, report);
        report.addScreenshotStep("Not Purchasing Step 6", driver);
        pageObj.notPurchasingSelfScreen.verifyAtPage(report);
        report.addScreenshotStep("Not Purchasing Step 8", driver);

        pageObj.pritUnl.getWelcomePage(report);
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.review, report);
        pageObj.review.verifyAtPage(report);
        pageObj.review.clickSelfPurchaseEdit(report);
        pageObj.purchaseSelf.verifyAtPage(report);
        pageObj.purchaseSelf.clickYesOrNo("No", report);
        pageObj.purchaseSelf.clickNextToPage(pageObj.purchaseSelf, report);
        report.addScreenshotStep("Not Purchasing Last Time Screen Step 11", driver);
    }

}
