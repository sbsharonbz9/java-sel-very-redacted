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


public class VTP_FRD_069_074_097_102_More_Information extends BaseTest {

    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    PetrosPageObj pageObj;
    PetrosUser user;
    BasicHelpers bh;
    String reportName = "VTP_FRD_069_074_097_102_More_Information";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();


    VTP_FRD_069_074_097_102_More_Information() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_FRD_069_074_097_102_More_Information_Test() throws IOException, InterruptedException {
        
        report = new GalenReport(getDriver(), reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_069_074_097_102 – More Information Pop Out";
        pageObj = new PetrosPageObj(driver);
        bh = new BasicHelpers(driver);
        user = new PetrosUser();
        CommonPageFeatures common = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.nitrateUse, report);
        common.verifyMoreInfoLinkDisplayed(report);
        report.addScreenshotStep("Step2_NitrateUse", driver);

        common.clickMoreInfo(report);
        common.verifyMoreInfoDisplayed(report);
        report.addScreenshotStep("Step3_NitrateUseMoreinfo", driver);

        common.clickMoreInfoBack(report);
        common.verifyMoreInfoNotDisplayed(report);

        common.clickYesNoUnsure("No, Never", report);
        common.clickNextToPage(pageObj.clarification, report);
        common.verifyMoreInfoLinkDisplayed(report);
        report.addScreenshotStep("Step5_NitrateUseClarification", driver);

        common.clickMoreInfo(report);
        common.verifyMoreInfoDisplayed(report);
        report.addScreenshotStep("Step6_NitrateUseClarificationMoreInfo", driver);

        common.clickMoreInfoBack(report);
        common.verifyMoreInfoNotDisplayed(report);

        pageObj.clarification.selectCheckboxesAndProgress(user.clarificationOptions, pageObj.nitratePoppersUse,
                report);
        common.clickYesNoNextToPage("No, Never", pageObj.heartProblemsInstruction,report);
        common.clickNextToPage(pageObj.heartNotSafeForSex, report);
        common.clickYesNoNextToPage("No", pageObj.cardiacConditions1, report);
        pageObj.cardiacConditions1.selectCheckboxesAndProgress(user.cc1, pageObj.heartFailure, report);

        common.verifyMoreInfoLinkDisplayed(report);
        report.addScreenshotStep("Step_12_HeartFailurePage", driver);

        common.clickMoreInfo(report);
        common.verifyMoreInfoDisplayed(report);
        report.addScreenshotStep("Step_13_HeartFailureMoreinfo", driver);

        common.clickMoreInfoBack(report);
        common.verifyMoreInfoNotDisplayed(report);
        common.clickYesNoNextToPage("No", pageObj.cardiacConditions2, report);
        report.addScreenshotStep("Step_15_CardiacConditions_2", driver);

        common.clickMoreInfo(report);
        common.verifyMoreInfoDisplayed(report);
        report.addScreenshotStep("Step_16_CardiacConditionsMoreinfo", driver);
    }
}
