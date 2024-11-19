package galen.tenant.dexter.Navigation_All;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.BloodPressureType;
import galen.enums.tenant.dexter.DDIConditionType;
import galen.enums.tenant.dexter.ReviewAnswersLinks;
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

public class VTP_DEX_FRD_015_249_Refresh_Button_Current_Screen extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    DexterPageObj pageObj;
    DexterUser user;    
    String reportName =  "VTP_DEX_FRD_015_249_Refresh_Button_Current_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_FRD_015_249_Refresh_Button_Current_Screen()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    void refreshMoreInfo(int step, BasePage page) throws InterruptedException {
        commonPageFeatures.clickMoreInfoToModal(report);
        bh.refreshPage(page.reportText, report);
        Thread.sleep(1000);
        report.addScreenshotStep("Step"+step +"_Refresh", driver);
        commonPageFeatures.clickMoreInfoBackToModalDismissed(report);
        Thread.sleep(1000);
    }

    void refresh(int step, BasePage page) throws InterruptedException {
        bh.refreshPage(page.reportText, report);
        Thread.sleep(1000);
        report.addScreenshotStep("Step"+ step + "_Refresh", driver);
        Thread.sleep(1000);
    }

    @Test
    public void VTP_DEX_FRD_015_249_Refresh_Button_Current_Screen_Test() throws IOException, InterruptedException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_015_249 – Refresh Button Returns to Current Screen";
        bh = new BasicHelpers(driver);
        pageObj = new DexterPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        commonPageFeatures = new CommonPageFeatures(driver);
        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();

        pageObj.welcomePage.verifyAtPage(report);
        pageObj.welcomePage.clickBegin(report);
        refresh(3, pageObj.privacyPage);
        pageObj.privacyPage.clickIAcceptBtnToPage(pageObj.numbers, report);
        refresh(5, pageObj.numbers);
        refreshMoreInfo(7, pageObj.numbers);

        // OAuth to Product Used
        commonPageFeatures.clickNextToPage(pageObj.oAuth, report);
        refresh(9, pageObj.oAuth);
        pageObj.oAuth.chooseAccountType(user, report);
        refresh(11, pageObj.oAuth);
        pageObj.oAuth.chooseAccountTypeAndProgress(user, pageObj.usedProduct,report);
        pageObj.usedProduct.verifyAtPage(report);
        refresh(14, pageObj.usedProduct);

        commonPageFeatures.clickYesNoNext(user.productUsed, report);
        refresh(16, pageObj.orderForSelf);

        pageObj.orderForSelf.verifyModalDisplayed(report);
        bh.refreshPage("Confirm Customer page", report);
        report.addScreenshotStep("Step18_Refresh", driver);

        pageObj.orderForSelf.clickCloseToDismiss(report);
        commonPageFeatures.clickYesNoNextToPage(user.orderForSelf,pageObj.pregnancy, report);
        refresh(20, pageObj.pregnancy);
        refreshMoreInfo(22, pageObj.pregnancy);

        commonPageFeatures.clickYesNoNextToPage(user.preventPregnancy,pageObj.menstrual, report);
        refresh(24, pageObj.menstrual);

        commonPageFeatures.clickYesNoNextToPage("Yes",pageObj.birthControl, report);
        refresh(26, pageObj.birthControl);
        refreshMoreInfo(28, pageObj.birthControl);

        commonPageFeatures.clickYesNoNextToPage("No",pageObj.smoking, report);
        refresh(30,pageObj.smoking);
        refreshMoreInfo(32, pageObj.smoking);

        pageObj.smoking.selectRadioResponseAndProgress(SmokeType.SMOKE_REGULARLY.label, pageObj.birthYear, report);
        refresh(34, pageObj.birthYear);

        int currentYear = Year.now().getValue();
        user.dobYear=String.valueOf((currentYear-25));
        pageObj.birthYear.fillOutBirthdayAndProgress(user, pageObj.smokingDisclaimer, report);
        refresh(36, pageObj.smokingDisclaimer);

        commonPageFeatures.clickNextToPage( pageObj.everHadCancer,report);
        refresh(38, pageObj.everHadCancer);

        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.cancerList,report);
        refresh(40, pageObj.cancerList);
        refreshMoreInfo(42, pageObj.cancerList);

        commonPageFeatures.clickBackToPage(pageObj.everHadCancer, report);
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.bloodPressureMeds,report);
        refresh(45, pageObj.bloodPressureMeds);

        commonPageFeatures.clickYesNoNextToPage(user.bloodPressureMeds,pageObj.cardiacRisk, report);
        refresh(47, pageObj.cardiacRisk);
        refreshMoreInfo(49, pageObj.cardiacRisk);

        pageObj.cardiacRisk.selectCheckboxesAndProgress(user.chestPainType, pageObj.bloodClot, report);
        refresh(51, pageObj.bloodClot);
        refreshMoreInfo(53, pageObj.bloodClot);

        pageObj.bloodClot.selectCheckboxesAndProgress(user.bloodClot, pageObj.irregularHeartBeat, report);
        refresh(55, pageObj.irregularHeartBeat);
        refreshMoreInfo(57, pageObj.irregularHeartBeat);

        commonPageFeatures.clickYesNoNextToPage(user.irregularHeartBeat, pageObj.liverCancer, report);
        refresh(59, pageObj.liverCancer);
        refreshMoreInfo(61, pageObj.liverCancer);

        pageObj.liverCancer.selectCheckboxesAndProgress(user.liverCancer, pageObj.vaginalBleeding, report);
        refresh(63, pageObj.vaginalBleeding);
        refreshMoreInfo(65, pageObj.vaginalBleeding);

        pageObj.vaginalBleeding.clickYesNoNextToPage(user.vaginalBleeding,pageObj.diabetes, report);
        refresh(67, pageObj.diabetes);
        refreshMoreInfo(69, pageObj.diabetes);

        commonPageFeatures.clickYesNoNextToPage(user.diabetes, pageObj.pregnant, report);
        refresh(71, pageObj.pregnant);

        commonPageFeatures.clickYesNoNextToPage(user.pregnant,pageObj.breastFeeding, report);
        refresh(73, pageObj.breastFeeding);

        commonPageFeatures.clickYesNoNextToPage(user.breastfeeding,pageObj.pregnancyLoss, report);
        refresh(75, pageObj.pregnancyLoss);

        commonPageFeatures.clickYesNoNextToPage(user.pregnancyLoss, pageObj.migraines, report);
        refresh(77, pageObj.migraines);
        refreshMoreInfo(79, pageObj.migraines);

        commonPageFeatures.clickYesNoNextToPage(user.migraines, pageObj.obesity,report);
        refresh(81, pageObj.obesity);

        // Obesity to DDI
        pageObj.obesity.enterHeightAndWeightAndProgress(user, pageObj.ddiCondition, report);
        refresh(83, pageObj.ddiCondition);
        refreshMoreInfo(85, pageObj.ddiCondition);

        // DDI to HepC
        pageObj.ddiCondition.selectCheckboxesAndProgress(pageObj.ddiCondition.getAllButNone(), pageObj.ddiHepC,report);
        refresh(87, pageObj.ddiHepC);
        refreshMoreInfo(89, pageObj.ddiHepC);

        // HepC to Thyroid
        pageObj.ddiHepC.selectCheckboxesAndProgress(user.hepCMeds, pageObj.ddiThyroid,report);
        refresh(91, pageObj.ddiThyroid);
        refreshMoreInfo(93, pageObj.ddiThyroid);

        // Thyroid to Epilepsy
        pageObj.ddiThyroid.selectCheckboxesAndProgress(user.thyroidMeds, pageObj.ddiEpBipolar, report);
        refresh(95, pageObj.ddiEpBipolar);
        refreshMoreInfo(97, pageObj.ddiEpBipolar);

        //  Epilepsy to HIV
        pageObj.ddiEpBipolar.selectCheckboxesAndProgress(user.epBipolarMeds, pageObj.ddihiv, report);
        refresh(99, pageObj.ddihiv);
        refreshMoreInfo(101, pageObj.ddihiv);

        // HIV to High Cholesterol
        pageObj.ddihiv.selectCheckboxesAndProgress(user.hivMeds, pageObj.ddiHighCholesterol, report);
        refresh(103, pageObj.ddiHighCholesterol);
        refreshMoreInfo(105, pageObj.ddiHighCholesterol);

        // High Cholesterol to Antifungal
        pageObj.ddiHighCholesterol.selectCheckboxesAndProgress(user.highCholMeds,
                pageObj.antifungal, report);
        refresh(107, pageObj.antifungal);
        refreshMoreInfo(109, pageObj.antifungal);

        // Antifungal to AF Meds
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.antifungalMeds, report);
        refresh(111, pageObj.antifungalMeds);
        refreshMoreInfo(113, pageObj.antifungalMeds);

        pageObj.antifungalMeds.selectCheckboxesAndProgress(user.antiFungalMeds, pageObj.otherMedication, report);
        refresh(115, pageObj.otherMedication);
        refreshMoreInfo(117, pageObj.otherMedication);

        pageObj.otherMedication.selectCheckboxesAndProgress(user.otherMedicationType, pageObj.gallbladder, report);
        refresh(119, pageObj.gallbladder);
        refreshMoreInfo(121, pageObj.gallbladder);

        commonPageFeatures.clickYesNoNextToPage(user.gallbladder, pageObj.depression,report);
        refresh(123, pageObj.depression);
        refreshMoreInfo(125, pageObj.depression);

        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression,report);
        refresh(127, pageObj.diagnosedDepression);
        refreshMoreInfo(129, pageObj.diagnosedDepression);

        commonPageFeatures.clickBackToPage(pageObj.depression, report);
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.knowBPNumber,report);
        refresh(131, pageObj.knowBPNumber);

        pageObj.knowBPNumber.clickYesAndOpenModal(report);
        refresh(133, pageObj.knowBPNumber);

        pageObj.knowBPNumber.clickYesAndAddressModalToPage(pageObj.enterBP, "Yes", report);
        refresh(136, pageObj.enterBP);
        refreshMoreInfo(138, pageObj.enterBP);

        user.systolic="110";
        user.diastolic="70";
        pageObj.enterBP.enterBPAndProgress(user, pageObj.review, report);
        refresh(140, pageObj.review);

        pageObj.review.clickConfirmToOpenModal(report);
        refresh(142, pageObj.review);

        pageObj.review.clickEditToPage(ReviewAnswersLinks.HC, pageObj.ddiCondition, report);
        pageObj.ddiCondition.selectCheckboxAndProgress(DDIConditionType.HIGH_CHOLESTEROL.label, pageObj.review, report);
        refresh(142, pageObj.review);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.review, report);

        pageObj.review.addressConfirmations(report);
        refresh(146, pageObj.oAuthPostReview);

        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.purchaseOptions,report);
        refresh(148, pageObj.purchaseOptions);

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.depression, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression,report);
        pageObj.diagnosedDepression.clickYesAndAddressModalToPage(pageObj.knowBPNumber, report);
        pageObj.knowBPNumber.clickYesAndAddressModalToPage(pageObj.enterBP, "Yes", report);

        user.systolic="110";
        user.diastolic="75";
        pageObj.enterBP.enterBPAndProgress(user, pageObj.review,report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbu, report);
        refresh(159, pageObj.adbu);

        pageObj.adbu.clickYesNoToOpenModal("Yes", report);
        refresh(161, pageObj.adbu);
        pageObj.adbu.clickYesNoToOpenModal("No", report);
        refresh(163, pageObj.adbu);
        pageObj.adbu.clickConfirmModal(null);

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.knowBPNumber, report);
        pageObj.knowBPNumber.selectRadioResponseAndProgress(BloodPressureType.No_Know_BP.label, pageObj.review,
                report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal, report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);
        refresh(171, pageObj.enterBPEnd);
        user.systolic="115";
        user.diastolic="75";
        pageObj.enterBPEnd.enterAndVerifyToModal(user, report);
        refresh(173, pageObj.enterBPEnd);
    }
}
