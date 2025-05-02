package galen.tenant.petros;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosNavigations;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_120_121_122_Antibiotics_Screen extends BaseTest {

    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";

    String reportName = "VTP_FRD_120_121_122_Antibiotics_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    PetrosUser user;
    BasicHelpers bh;
    PetrosPageObj pageObj;
    CommonPageFeatures common;

    VTP_FRD_120_121_122_Antibiotics_Screen() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_FRD_120_121_122_Antibiotics_Screen_Test() throws IOException, InterruptedException {
        
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_120_121_122_Antibiotics_Screen";
        pageObj = new PetrosPageObj(driver);
        bh = new BasicHelpers(driver);
        common = new CommonPageFeatures(driver);
        user = new PetrosUser();

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.antibiotics, report);

        common.verifyYesNoPresent(report);
        common.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Antibiotics_screen", driver);

        common.clickYesNo_NextEnabled("No", report);
        report.addScreenshotStep("No_NextEnabled", driver);

        common.clickYesOrNo("No", report);
        bh.verifyRadioButtonSelected("No", report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("No_NextEnabled_2", driver);

        common.clickYesOrNo("Yes", report);
        bh.verifyRadioButtonSelected("Yes", report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Yes_NextEnabled", driver);

        common.clickYesNoNextToPage(user.antibiotics="No", pageObj.antidepressants,report);
        report.addScreenshotStep("antidepressants screen", driver);
        
        new PetrosNavigations(driver).petrosHappyFlowTo(user, pageObj.antibiotics, report);
        common.clickYesNoNextToPage(user.antibiotics="Yes", pageObj.antidepressants,report);
        report.addScreenshotStep("antidepressants screen 2", driver);
        
        new PetrosNavigations(driver).petrosHappyFlowTo(user, pageObj.review, report);
        getAntiBioticsEditLinkFlow(user, report);
    }

    public void getAntiBioticsEditLinkFlow(PetrosUser user, GalenReport report) {
        pageObj.review.clickAntiBioticsEditLink(report);
        pageObj.antibiotics.verifyAtPage(report);
        user.antibiotics = "Yes";
        common.clickYesNoNextToPage(user.antibiotics, pageObj.review, report);
        pageObj.review.addressConfirmationsAndProgress(pageObj.kickoutPage, report);
        report.addScreenshotStep("Step15_DNU Page");
    }
}
