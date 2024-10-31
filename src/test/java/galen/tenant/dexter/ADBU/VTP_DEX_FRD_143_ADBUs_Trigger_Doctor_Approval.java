package galen.tenant.dexter.ADBU;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.BloodPressureType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterNavigations;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
        VERSIONHISTORY.add("1.0;10NOV2022;Initial Test Script;Name Redacted");
        VERSIONHISTORY.add("2.0;19SEP2023;Per CADENCE-359/CADENCE-360: Updated Test Steps navigation for restructured cancer flow;Name Redacted");
        VERSIONHISTORY.add("3.0;20JUN2024;Per CADENCE-476/CADENCE-503: Updated Test Steps for FDA changes   \n" +
                "Per CADENCE-529: Removed N/A from Actual Result column for Happy flow execution related steps \n" +
                "Per CADENCE-591: Update Test Steps for modified assessment and navigation ;Name Redacted");
        VERSIONHISTORY.add("4.0;28FEB2024;Per CADENCE-632: Update Test Steps to align with language requested by FDA ;Name Redacted");
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
        user.conditionType=pageObj.ddiCondition.allButNone;
        user.hepCMeds=pageObj.ddiHepC.allButNoneHepC;
        user.thyroidMeds=pageObj.ddiThyroid.allButNoneThyroid;
        user.epBipolarMeds=pageObj.ddiEpBipolar.allButNoneBipolar;
        user.hivMeds=pageObj.ddihiv.allButNoneHIV;
        user.highCholMeds=pageObj.ddiHighCholesterol.allButNoneHC;
        user.otherMedicationType=pageObj.otherMedication.allButNone;
        user.antiFungalMeds= pageObj.antifungalMeds.allButNone;
        user.gallbladder="Yes";
        user.depression="Yes";
        user.diagnosedDepression="Yes";
        user.isAntifungal="Yes";

        new PritUnlPage(driver).authenticateUserIfRequired(UrlType.DEXTER);
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
