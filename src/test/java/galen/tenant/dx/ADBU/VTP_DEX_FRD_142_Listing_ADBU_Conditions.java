package galen.tenant.dx.ADBU;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxNavigations;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;


public class VTP_DEX_FRD_142_Listing_ADBU_Conditions extends BaseTest {
    static String OBJECTIVE = "To verify on the on the ADBU/BP screen, if the user did not previously enter BP and " +
            "triggered ADBUs, the application shall display a notification that BP is required and a listing of all " +
            "ADBUs that were triggered, ask the user if they have talked to a doctor about whether Zena is safe to use, " +
            "and include controls to input a confirmation or denial response.";
    static String NOTES = "This protocol contains the following verification scenarios:\n" +
            "o\tThe following is displayed on the ADBU/BP Screen:\n" +
            "\uF0A7\tAll ADBUs triggered \n" +
            "\uF0A7\tLanguage notifying user BP is required \n" +
            "\uF0A7\tConfirmation and denial responses";
    static String REQUIREMENTS = "DEX_FRD_142";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_ADBU_noBP.docx";
    String reportName = "VTP_DEX_FRD_142_Listing_ADBU_Conditions";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    CommonPageFeatures common;
    BasicHelpers bh;

    VTP_DEX_FRD_142_Listing_ADBU_Conditions() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_142_Listing_ADBU_Conditions_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_142 – Listing ADBU Conditions on ADBU/BP Screen";

        common = new CommonPageFeatures(driver);
        bh = new BasicHelpers(driver);
        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_noBP();
        DxPageObj pageObj = new DxPageObj(driver);

        user.weight="300";
        user.conditionType=pageObj.ddiCondition.getAllButNone();
        user.hepCMeds=pageObj.ddiHepC.getAllButNone();
        user.thyroidMeds=pageObj.ddiThyroid.getAllButNone();
        user.epBipolarMeds=pageObj.ddiEpBipolar.getAllButNone();
        user.hivMeds=pageObj.ddihiv.getAllButNone();
        user.highCholMeds=pageObj.ddiHighCholesterol.getAllButNone();
        user.otherMedicationType=pageObj.otherMedication.getAllButNone();
        user.antiFungalMeds= pageObj.antifungalMeds.getAllButNone();
        user.gallbladder="Yes";
        user.depression="Yes";
        user.diagnosedDepression="Yes";
        user.isAntifungal="Yes";

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxNavigations(driver).partialNavigationIA(user, pageObj.adbubpScreen, report);

        pageObj.adbubpScreen.verifyTitle(pageObj.adbubpScreen.titleText, report);
        pageObj.adbubpScreen.verifySubtitle(pageObj.adbubpScreen.askADoctor, report);
        pageObj.adbubpScreen.verifyConditionIsListed(pageObj.adbubpScreen.obesityCondition, report);
        pageObj.adbubpScreen.verifyConditionIsListed(pageObj.adbubpScreen.gallBladderCondition, report);
        pageObj.adbubpScreen.verifyConditionIsListed(pageObj.adbubpScreen.depressionCondition, report);

        pageObj.ddiHepC.verifyAllOptionsInADBU(report);
        pageObj.ddiThyroid.verifyAllOptionsInADBU(report);
        pageObj.ddiEpBipolar.verifyAllOptionsInADBU(report);
        pageObj.ddihiv.verifyAllOptionsInADBU(report);
        pageObj.ddiHighCholesterol.verifyAllOptionsInADBU(report);
        pageObj.otherMedication.verifyAllOptionsInADBU(report);
        pageObj.antifungalMeds.verifyAllOptionsInADBU(report);
        pageObj.adbubpScreen.verifyDocDecide(report);
        pageObj.adbubpScreen.verifyZenaSafe(report);

        bh.verifyDisplayedFlex(common.btnYesModal, "Yes", report);
        bh.verifyDisplayedFlex(common.btnNoModal, "No", report);
        report.addScreenshotStep("Step32_ADBUBP");
    }
}