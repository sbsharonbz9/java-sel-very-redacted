package galen.tenant.petros;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosHFWrappers;
import galen.helpers.tenant.petros.PetrosNavigations;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_132_133_134_Non_Cardiac_Allergic_Reaction_Screen extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    PetrosUser user;
    String reportName = "VTP_FRD_132_133_134_Non_Cardiac_Allergic_Reaction_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    BasicHelpers bh;
    PetrosPageObj pageObj;
    CommonPageFeatures common;

    VTP_FRD_132_133_134_Non_Cardiac_Allergic_Reaction_Screen() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_FRD_132_133_134_Non_Cardiac_Allergic_Reaction_Screen_Test() throws IOException, InterruptedException {
        
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_132_133_134_Non_Cardiac_Allergic_Reaction_Screen";
        user = new PetrosUser();
        pageObj = new PetrosPageObj(driver);
        bh = new BasicHelpers(driver);
        common = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);
        new PetrosHFWrappers(driver).executeHappyFlow(user, pageObj.noncardiacAllergicReactions, REFERENCES, report);
        common.verifyYesNoPresent(report);
        common.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Non Cardiac Allergic Reactions Screen Options", driver);

        common.clickYesNo_NextEnabled("No", report);

        //reselect no button
        common.clickYesNo_NextEnabled("No", report);
        report.addScreenshotStep("Non Cardiac Allergic Reactions Screen No Options", driver);

        //select yes button
        common.clickYesNo_NextEnabled("Yes", report);
        report.addScreenshotStep("Non Cardiac Allergic Reactions Screen Yes Options", driver);

        common.clickYesNoNextToPage("No", pageObj.erectionLongerThan4Hours, report);
        report.addScreenshotStep("Erection Longer Than4Hours Screen", driver);

        //step 7 as per vtp.
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.noncardiacAllergicReactions, report);
        common.verifyYesNoPresent(report);
        report.addScreenshotStep("Non Cardiac Allergic Reactions Screen Options");

        common.clickYesNo_NextEnabled("Yes", report);
        report.addScreenshotStep("Non Cardiac Allergic Reactions Screen Yes Options");

        common.clickYesNoNextToPage("No", pageObj.erectionLongerThan4Hours, report);
        report.addScreenshotStep("Erection Longer Than4Hours Screen");

        //vtp step 11
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.review, report);
        pageObj.review.verifyAtPage(report);
        getEditButtonAllergicRectionFlow(user, report);
    }

    private void getEditButtonAllergicRectionFlow(PetrosUser user, GalenReport report)  {
        pageObj.review.clickAllergicRectionEditLink(report);
        pageObj.noncardiacAllergicReactions.verifyAtPage(report);
        common.verifyYesNoPresent(report);
        user.allergies = "Yes";
        common.clickYesNoNextToPage(user.allergies, pageObj.hivMedicines, report);
        common.clickNextToPage(pageObj.review, report);
        pageObj.review.addressConfirmationsAndProgress(pageObj.kickoutPage, report);
        report.addScreenshotStep("Universal DNU Screen");
    }
}
