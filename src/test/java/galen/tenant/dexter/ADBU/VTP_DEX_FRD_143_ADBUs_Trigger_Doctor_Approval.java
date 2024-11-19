package galen.tenant.dexter.ADBU;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterNavigations;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_143_ADBUs_Trigger_Doctor_Approval extends BaseTest {

    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_143_ADBUs_Trigger_Doctor_Approval";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_143_ADBUs_Trigger_Doctor_Approval() {
        VERSIONHISTORY.add("1.0;10NOV2022;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_143_ADBUs_Trigger_Doctor_Approval_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_143_ADBUs_Trigger_Doctor_Approval";
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
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

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterNavigations(driver).partialNavigationIA(user, pageObj.adbu, report);

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
