package galen.tenant.dexter.Navigation_All;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.SmokeType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.BasePage;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_FRD_018_020_Display_Browser_Back_Button extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    DexterPageObj pageObj;
    DexterUser user;
    String reportName = "VTP_DEX_FRD_018_020_Display_Browser_Back_Button";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_FRD_018_020_Display_Browser_Back_Button()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    void verifyMoreInfoExit(int step, BasePage page) throws InterruptedException {
        verifyExit(step, page);
        commonPageFeatures.clickMoreInfoToModal(report);
        verifyExit(step+2, page);
        commonPageFeatures.clickMoreInfoBackToModalDismissed(report);
        Thread.sleep(1000);
    }

    void verifyExit(int step, BasePage page)  {
        page.verifyAtPage();
        page.clickBrowserBackToModal(report);
        report.addScreenshotStep("Step"+step +"_ExitAssessment", driver);
        commonPageFeatures.clickExitCloseToModalDismissed(report);
        step=step+1;
        report.addScreenshotStep("Step"+step +"_"+page.reportText, driver);
    }

    @Test
    public void VTP_DEX_FRD_018_020_Display_Browser_Back_ButtonTest() throws IOException, InterruptedException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_018_020 – Display Browser Back Button";
        bh = new BasicHelpers(driver);
        pageObj = new DexterPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        StudyAdminPageObj sp = new StudyAdminPageObj(driver);
        commonPageFeatures = new CommonPageFeatures(driver);

        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();

        pageObj.pritUnl.load(UrlType.DEXTER);
        pageObj.welcomePage.verifyAtPage(report);
        pageObj.welcomePage.clickBegin(report);
        verifyExit(3, pageObj.privacyPage);
        pageObj.privacyPage.clickIAcceptBtnToPage(pageObj.numbers, report);
        verifyExit(6, pageObj.numbers);

        commonPageFeatures.clickNextToPage(pageObj.oAuth, report);
        pageObj.oAuth.chooseAccountTypeAndProgress(user, pageObj.usedProduct,report);
        verifyExit(11, pageObj.usedProduct);

        commonPageFeatures.clickYesNoNext(user.productUsed, report);
        verifyExit(14, pageObj.orderForSelf);
        pageObj.orderForSelf.clickCloseToDismiss(report);
        pageObj.orderForSelf.clickMoreInfoToModal(report);
        verifyExit(17, pageObj.orderForSelf);
        commonPageFeatures.clickMoreInfoBackToModalDismissed(report);

        commonPageFeatures.clickYesNoNextToPage(user.orderForSelf,pageObj.pregnancy, report);
        verifyMoreInfoExit(21, pageObj.pregnancy);

        commonPageFeatures.clickYesNoNextToPage(user.preventPregnancy,pageObj.menstrual, report);
        verifyExit(28, pageObj.menstrual);

        commonPageFeatures.clickYesNoNextToPage("Yes",pageObj.birthControl, report);
        verifyMoreInfoExit(31, pageObj.birthControl);

        commonPageFeatures.clickYesNoNextToPage("No",pageObj.smoking, report);
        verifyMoreInfoExit(38, pageObj.smoking);

        pageObj.smoking.selectRadioResponseAndProgress(SmokeType.SMOKE_REGULARLY.label, pageObj.birthYear, report);
        verifyExit(45, pageObj.birthYear);

        int currentYear = Year.now().getValue();
        user.dobYear=String.valueOf((currentYear-25));
        pageObj.birthYear.fillOutBirthdayAndProgress(user, pageObj.smokingDisclaimer, report);
        verifyExit(48, pageObj.smokingDisclaimer);

        commonPageFeatures.clickNextToPage( pageObj.everHadCancer,report);
        verifyExit(51, pageObj.everHadCancer);

        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.cancerList,report);
        verifyMoreInfoExit(54, pageObj.cancerList);

        commonPageFeatures.clickBackToPage(pageObj.everHadCancer, report);
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.bloodPressureMeds,report);
        verifyExit(62, pageObj.bloodPressureMeds);

        commonPageFeatures.clickYesNoNextToPage(user.bloodPressureMeds,pageObj.cardiacRisk, report);
        verifyMoreInfoExit(65, pageObj.cardiacRisk);

        pageObj.cardiacRisk.selectCheckboxesAndProgress(user.chestPainType, pageObj.bloodClot, report);
        verifyMoreInfoExit(72, pageObj.bloodClot);

        pageObj.bloodClot.selectCheckboxesAndProgress(user.bloodClot, pageObj.irregularHeartBeat, report);
        verifyMoreInfoExit(79, pageObj.irregularHeartBeat);

        commonPageFeatures.clickYesNoNextToPage(user.irregularHeartBeat, pageObj.liverCancer, report);
        verifyMoreInfoExit(86, pageObj.liverCancer);

        pageObj.liverCancer.selectCheckboxesAndProgress(user.liverCancer, pageObj.vaginalBleeding, report);
        verifyMoreInfoExit(93, pageObj.vaginalBleeding);

        pageObj.vaginalBleeding.clickYesNoNextToPage(user.vaginalBleeding,pageObj.diabetes, report);
        verifyMoreInfoExit(100, pageObj.diabetes);

        commonPageFeatures.clickYesNoNextToPage(user.diabetes, pageObj.pregnant, report);
        verifyExit(107, pageObj.pregnant);

        commonPageFeatures.clickYesNoNextToPage(user.pregnant,pageObj.breastFeeding, report);
        verifyExit(110, pageObj.breastFeeding);

        commonPageFeatures.clickYesNoNextToPage(user.breastfeeding,pageObj.pregnancyLoss, report);
        verifyExit(113, pageObj.pregnancyLoss);

        commonPageFeatures.clickYesNoNextToPage(user.pregnancyLoss, pageObj.migraines, report);
        verifyMoreInfoExit(116, pageObj.migraines);

        commonPageFeatures.clickYesNoNextToPage(user.migraines, pageObj.obesity,report);
        verifyExit(123, pageObj.obesity);

        pageObj.obesity.enterHeightAndWeightAndProgress(user, pageObj.ddiCondition, report);
        verifyMoreInfoExit(126, pageObj.ddiCondition);

        pageObj.ddiCondition.selectCheckboxesAndProgress(pageObj.ddiCondition.getAllButNone(), pageObj.ddiHepC,report);
        verifyMoreInfoExit(133, pageObj.ddiHepC);

        pageObj.ddiHepC.selectCheckboxesAndProgress(user.hepCMeds, pageObj.ddiThyroid,report);
        verifyMoreInfoExit(140, pageObj.ddiThyroid);

        pageObj.ddiThyroid.selectCheckboxesAndProgress(user.thyroidMeds, pageObj.ddiEpBipolar, report);
        verifyMoreInfoExit(147, pageObj.ddiEpBipolar);

        //  Epilepsy to HIV
        pageObj.ddiEpBipolar.selectCheckboxesAndProgress(user.epBipolarMeds, pageObj.ddihiv, report);
        verifyMoreInfoExit(154, pageObj.ddihiv);

        // HIV to High Cholesterol
        pageObj.ddihiv.selectCheckboxesAndProgress(user.hivMeds, pageObj.ddiHighCholesterol, report);
        verifyMoreInfoExit(161, pageObj.ddiHighCholesterol);

        sleep(500);
        pageObj.ddiHighCholesterol.selectCheckboxesAndProgress(user.highCholMeds, pageObj.antifungal, report);
        verifyMoreInfoExit(168, pageObj.antifungal);

        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.antifungalMeds, report);
        verifyMoreInfoExit(175, pageObj.antifungalMeds);

        pageObj.antifungalMeds.selectCheckboxesAndProgress(user.antiFungalMeds, pageObj.otherMedication, report);
        verifyMoreInfoExit(182, pageObj.otherMedication);

        pageObj.otherMedication.selectCheckboxesAndProgress(user.otherMedicationType, pageObj.gallbladder, report);
        verifyMoreInfoExit(189, pageObj.gallbladder);

        commonPageFeatures.clickYesNoNextToPage(user.gallbladder, pageObj.depression,report);
        verifyMoreInfoExit(196, pageObj.depression);

        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression,report);
        verifyMoreInfoExit(203, pageObj.diagnosedDepression);

        pageObj.diagnosedDepression.clickYesAndOpenModal(report);
        verifyExit(210, pageObj.diagnosedDepression);
        pageObj.diagnosedDepression.clickConfirmModalToPage(pageObj.knowBPNumber,report);
        verifyExit(213, pageObj.knowBPNumber);

        pageObj.knowBPNumber.clickYesAndOpenModal(report);
        verifyExit(216, pageObj.knowBPNumber);
        pageObj.knowBPNumber.clickYesNoModalToPage(user.measuredIn3Months,
                pageObj.enterBP, report);
        verifyMoreInfoExit(219, pageObj.enterBP);

        pageObj.enterBP.enterBPAndProgress(user, pageObj.review, report);
        verifyExit(226, pageObj.review);

        pageObj.review.clickConfirmToOpenModal(report);
        verifyExit(229,pageObj.review);
        pageObj.review.clickFinishToOauth(report);

        pageObj.oAuthPostReview.chooseAccountType(user, report);
        verifyExit(233, pageObj.adbu);

        pageObj.adbu.addressConfirmationsAndProgress("Yes", pageObj.purchaseOptions, report);
        verifyExit(237, pageObj.purchaseOptions);

        commonPageFeatures.load(UrlType.STUDY);
        bh.downloadCSVAndVerify("records_Step239", sp, report);

        commonPageFeatures.load(UrlType.DEXTER);
        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker();
        new DexterHFWrappers(driver).runDexterHFADBUNoBP(user, pageObj.adbubpNormal, report);
        verifyExit(241, pageObj.adbubpNormal);

        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);
        verifyExit(244, pageObj.enterBPEnd);

        pageObj.enterBPEnd.enterAndVerifyToPage(user, pageObj.purchaseOptions,report);
        verifyExit(248, pageObj.purchaseOptions);

        commonPageFeatures.load(UrlType.STUDY);
        bh.downloadCSVAndVerify("records_Step250", sp, report);
   }
}
