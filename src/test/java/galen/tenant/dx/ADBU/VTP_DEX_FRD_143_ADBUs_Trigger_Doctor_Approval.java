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

public class VTP_DEX_FRD_143_ADBUs_Trigger_Doctor_Approval extends BaseTest {

    static String OBJECTIVE = "To verify on the ADBU/BP screen, if the user previously entered " +
            "a valid BP and triggered ADBUs, the application shall display a listing of all ADBUs that " +
            "were triggered, ask the user if they have talked to a doctor about whether Zena is safe to " +
            "use, and include controls to input a confirmation or denial response.";
    static String NOTES = "This protocol contains the following verification scenarios: \n" +
            "The following is displayed on the ADBU/BP Screen: \n" +
            "All ADBUs triggered  \n" +
            "Confirmation and denial responses ";
    static String REQUIREMENTS = "DEX_FRD_143";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_143_ADBUs_Trigger_Doctor_Approval";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_143_ADBUs_Trigger_Doctor_Approval() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_143_ADBUs_Trigger_Doctor_Approval_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_143_ADBUs_Trigger_Doctor_Approval";
        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        user.systolic = "110";
        user.diastolic = "70";
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
        new DxNavigations(driver).partialNavigationIA(user, pageObj.adbu, report);

        pageObj.adbubpScreen.verifyTitle(pageObj.adbu.titleText, report);
        pageObj.adbubpScreen.verifyConditionIsListed(pageObj.adbu.obesityCondition, report);
        pageObj.adbubpScreen.verifyConditionIsListed(pageObj.adbu.gallBladderCondition, report);
        pageObj.adbubpScreen.verifyConditionIsListed(pageObj.adbu.depressionCondition, report);
        pageObj.ddiHepC.verifyAllOptionsInADBU(report);
        pageObj.ddiThyroid.verifyAllOptionsInADBU(report);
        pageObj.ddiEpBipolar.verifyAllOptionsInADBU(report);
        pageObj.ddihiv.verifyAllOptionsInADBU(report);
        pageObj.ddiHighCholesterol.verifyAllOptionsInADBU(report);
        pageObj.otherMedication.verifyAllOptionsInADBU(report);
        pageObj.antifungalMeds.verifyAllOptionsInADBU(report);
        pageObj.adbubpScreen.verifyDocDecide(report);
        pageObj.adbubpScreen.verifyZenaSafe(report);
        new BasicHelpers(driver).verifyDisplayedFlex(common.btnYesModal, "Yes", report);
        new BasicHelpers(driver).verifyDisplayedFlex(common.btnNoModal, "No", report);
        report.addScreenshotStep("Step36_ADBU");

    }
}
