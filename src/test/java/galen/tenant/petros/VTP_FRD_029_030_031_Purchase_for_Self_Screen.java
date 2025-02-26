package galen.tenant.petros;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosHFWrappers;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_029_030_031_Purchase_for_Self_Screen extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    PetrosPageObj pageObj;
    PetrosUser user;
    String reportName = "VTP_FRD_029_030_031_Purchase_for_Self_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;

    VTP_FRD_029_030_031_Purchase_for_Self_Screen()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_FRD_029_030_031_Purchase_for_Self_Screen_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_029_030_031_Purchase_for_Self_Screen";
        bh = new BasicHelpers(driver);
        pageObj = new PetrosPageObj(driver);
        this.user = new PetrosUser();
        PetrosHFWrappers hf = new PetrosHFWrappers(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);
        hf.executeHappyFlow(user, pageObj.purchaseSelf, "IA Checkout w Guest User", report);
        common.verifyYesNoPresent(report);
        common.verifyNextPresent(report);
        report.addScreenshotStep("Purchase Self Screen Step 2", driver);

        common.clickYesOrNo("Yes", report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Purchase Self Screen next button Enabled Step 3", driver);

        common.clickYesOrNo("Yes", report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Purchase Self Screen next button Enabled Step 4", driver);

        common.clickYesOrNo("No", report);
        common.verifyNextButtonEnabled(report);
        common.clickYesNoNextToPage("Yes", pageObj.sexAndBirthYear, report);
        report.addScreenshotStep("Date Of Birth Screen Step 5 ", driver);

        hf.executeHappyFlow(user, pageObj.purchaseSelf, "IA Checkout w Guest User", report);
        pageObj.purchaseSelf.verifyAtPage(report);
        common.clickYesNoNextToPage("No",pageObj.notPurchasingSelfScreen, report);
        report.addScreenshotStep("Not Purchasing Step 6", driver);

        pageObj.notPurchasingSelfScreen.verifyAtPage(report);
        report.addScreenshotStep("Not Purchasing Step 8", driver);

        hf.executeHappyFlow(user, pageObj.review, "IA Checkout w Guest User", report);
        pageObj.review.clickSelfPurchaseEdit(report);
        pageObj.purchaseSelf.verifyAtPage(report);
        common.clickYesNoNextToPage("No", pageObj.notPurchasingSelfScreen, report);
        report.addScreenshotStep("Not Purchasing Last Time Screen Step 11", driver);
    }
}
