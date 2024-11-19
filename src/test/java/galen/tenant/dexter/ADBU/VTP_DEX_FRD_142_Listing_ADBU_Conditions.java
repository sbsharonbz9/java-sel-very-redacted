package galen.tenant.dexter.ADBU;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterNavigations;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.tenant.dexter.InitialAssessment.ADBUBP;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;


public class VTP_DEX_FRD_142_Listing_ADBU_Conditions extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_142_Listing_ADBU_Conditions";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    CommonPageFeatures common;
    BasicHelpers bh;

    VTP_DEX_FRD_142_Listing_ADBU_Conditions() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_142_Listing_ADBU_Conditions_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_142 – Listing ADBU Conditions on ADBU/BP Screen";

        common = new CommonPageFeatures(driver);
        bh = new BasicHelpers(driver);
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_noBP();
        DexterPageObj pageObj = new DexterPageObj(driver);
        ADBUBP adbuBP = pageObj.adbubpScreen;

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
        new DexterNavigations(driver).partialNavigationIA(user, adbuBP, report);

        adbuBP.verifyTitle(adbuBP.titleText, report);
        adbuBP.verifySubtitle(adbuBP.askADoctor, report);
        adbuBP.verifyConditionIsListed(adbuBP.obesityCondition, report);
        adbuBP.verifyConditionIsListed(adbuBP.gallBladderCondition, report);
        adbuBP.verifyConditionIsListed(adbuBP.depressionCondition, report);

        pageObj.ddiHepC.verifyAllOptionsInADBU(report);
        pageObj.ddiThyroid.verifyAllOptionsInADBU(report);
        pageObj.ddiEpBipolar.verifyAllOptionsInADBU(report);
        pageObj.ddihiv.verifyAllOptionsInADBU(report);
        pageObj.ddiHighCholesterol.verifyAllOptionsInADBU(report);
        pageObj.otherMedication.verifyAllOptionsInADBU(report);
        pageObj.antifungalMeds.verifyAllOptionsInADBU(report);
        adbuBP.verifyDocDecide(report);
        adbuBP.verifyZenaSafe(report);

        bh.verifyDisplayedFlex(common.btnYesModal, "Yes", report);
        bh.verifyDisplayedFlex(common.btnNoModal, "No", report);
        report.addScreenshotStep("Step32_ADBUBP");
    }
}