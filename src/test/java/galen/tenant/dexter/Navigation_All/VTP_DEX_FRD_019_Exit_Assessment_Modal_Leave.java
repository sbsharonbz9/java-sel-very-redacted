package galen.tenant.dexter.Navigation_All;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.DDIConditionType;
import galen.enums.tenant.dexter.SmokeType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.BasePage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;


public class VTP_DEX_FRD_019_Exit_Assessment_Modal_Leave extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    DexterPageObj pageObj;
    DexterUser user;
    String reportName = "VTP_DEX_FRD_019_Exit_Assessment_Modal_Leave";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;
    DexterHFWrappers hf;

    VTP_DEX_FRD_019_Exit_Assessment_Modal_Leave()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    void verifyMoreInfoExit(int step, BasePage page) {
        verifyExit(step, page);
        if (page==pageObj.diagnosedDepression) {
            hf.runDexterHFNonsmokingwBP(user, pageObj.depression, report);
            pageObj.depression.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression, report);
        } else if (page == pageObj.orderForSelf) {
            hf.runDexterHFNonsmokingwBP(user, page, report);
            pageObj.orderForSelf.clickCloseToDismiss(report);
        } else {
            hf.runDexterHFNonsmokingwBP(user, page, report);
       }
        commonPageFeatures.clickMoreInfoToModal(report);
        page.clickBrowserBackToModal(report);
        page.clickExitLeaveToModalDismissed(report);
        pageObj.welcomePage.verifyAtPage(report);
        int step2 = (page==pageObj.diagnosedDepression)?step+5:step+4;
        report.addScreenshotStep("Step" + step2 + "_MoreInfo", driver);
    }

    void DDIMoreInfo(int step, BasePage page, String option) {
        hf.runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        pageObj.ddiCondition.selectCheckboxAndProgress(option, page,report);
        page.clickBrowserBackToModal(report);
        page.clickExitLeaveToModalDismissed(report);
        pageObj.welcomePage.verifyAtPage(report);
        report.addScreenshotStep("Step" + step + "_" + page.reportText);

        hf.runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        pageObj.ddiCondition.selectCheckboxAndProgress(option, page,report);
        commonPageFeatures.clickMoreInfoToModal(report);
        page.clickBrowserBackToModal(report);
        page.clickExitLeaveToModalDismissed(report);
        pageObj.welcomePage.verifyAtPage(report);
        report.addScreenshotStep("Step"+step+5 +"_MoreInfo", driver);
    }

    void verifyExit(int step, BasePage page)  {
        hf.runDexterHFNonsmokingwBP(user, page, report);
        page.clickBrowserBackToModal(report);
        page.clickExitLeaveToModalDismissed(report);
        pageObj.welcomePage.verifyAtPage(report);
        report.addScreenshotStep("Step"+step +"_"+page.reportText, driver);
    }

    @Test
    public void VTP_DEX_FRD_019_Exit_Assessment_Modal_Leave_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_019 – Exit Health Survey Modal (Leave)";
        bh = new BasicHelpers(driver);
        pageObj = new DexterPageObj(driver);

        commonPageFeatures = new CommonPageFeatures(driver);
        hf = new DexterHFWrappers(driver);
        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        verifyExit(3, pageObj.privacyPage);
        verifyExit(6, pageObj.numbers);
        verifyExit(9, pageObj.oAuth);
        verifyExit(12, pageObj.usedProduct);
        verifyMoreInfoExit(15, pageObj.orderForSelf);
        verifyMoreInfoExit(22, pageObj.pregnancy);
        verifyExit(29, pageObj.menstrual);
        verifyMoreInfoExit(32, pageObj.birthControl);
        verifyMoreInfoExit(39, pageObj.smoking);

        user.smokingType=SmokeType.SMOKE_REGULARLY;
        verifyExit(46, pageObj.birthYear);

        int currentYear = Year.now().getValue();
        user.dobYear=String.valueOf((currentYear-20));
        verifyExit(52, pageObj.smokingDisclaimer);

        user.smokingType=SmokeType.DO_NOT_SMOKE;
        verifyExit(55, pageObj.everHadCancer);

        user.everHadCancer="Yes";
        verifyMoreInfoExit(54, pageObj.cancerList);
        user.everHadCancer="No";

        verifyExit(62, pageObj.bloodPressureMeds);
        verifyMoreInfoExit(65, pageObj.cardiacRisk);
        verifyMoreInfoExit(72, pageObj.bloodClot);
        verifyMoreInfoExit(79, pageObj.irregularHeartBeat);
        verifyMoreInfoExit(86, pageObj.liverCancer);
        verifyMoreInfoExit(93, pageObj.vaginalBleeding);
        verifyMoreInfoExit(100, pageObj.diabetes);
        verifyExit(107, pageObj.pregnant);
        verifyExit(110, pageObj.breastFeeding);
        verifyExit(113, pageObj.pregnancyLoss);
        verifyMoreInfoExit(116, pageObj.migraines);
        verifyExit(123, pageObj.obesity);
        verifyMoreInfoExit(126, pageObj.ddiCondition);

        DDIMoreInfo(139, pageObj.ddiHepC, DDIConditionType.HEPATITIS_C.label);
        DDIMoreInfo(148, pageObj.ddiThyroid, DDIConditionType.THYROID_DISEASE.label);
        DDIMoreInfo(157, pageObj.ddiEpBipolar, DDIConditionType.EPILEPSY.label);
        DDIMoreInfo(166, pageObj.ddihiv, DDIConditionType.HIV.label);
        DDIMoreInfo(175, pageObj.ddiHighCholesterol, DDIConditionType.HIGH_CHOLESTEROL.label);

        verifyExit(183, pageObj.antifungal);
        user.isAntifungal="Yes";
        verifyMoreInfoExit(187, pageObj.antifungalMeds);
        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        verifyMoreInfoExit(195, pageObj.otherMedication);
        verifyMoreInfoExit(202, pageObj.gallbladder);
        verifyMoreInfoExit(209, pageObj.depression);

        user.depression="Yes";
        verifyMoreInfoExit(217, pageObj.diagnosedDepression);
        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        verifyExit(225, pageObj.knowBPNumber);
        verifyMoreInfoExit(228, pageObj.enterBP);
        verifyExit(235, pageObj.review);

        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        verifyExit(238, pageObj.purchaseOptions);

        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker();
        verifyExit(241, pageObj.adbubpNormal);

        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker();
        verifyExit(244, pageObj.enterBPEnd);

        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        verifyExit(247, pageObj.adbu);
    }
}
