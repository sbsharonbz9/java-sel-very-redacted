package galen.tenant.dx.Navigation_All;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.BloodPressureType;
import galen.enums.tenant.dx.DDIConditionType;
import galen.enums.tenant.dx.ReviewAnswersLinks;
import galen.enums.tenant.dx.SmokeType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.common.BasePage;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_015_249_Refresh_Button_Current_Screen extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_015: To verify if the user selects the browser refresh button, the application " +
            "shall remain on the current screen.\n" +
            "DEX_FRD_249: To verify when a user refreshes the browser while a modal is being displayed, the application " +
            "shall remain on the current screen.\n";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "-\tUsage of Refresh button on the following screens returns to itself:\n" +
            "o\tPrivacy Notice Screen\n" +
            "o\tKnow Numbers Screen More Info Modal\n" +
            "o\tPrior Use Screen\n" +
            "o\tConfirm Customer Screen\n" +
            "o\tConfirm Customer More Info Modal\n" +
            "o\tPrevent Pregnancy Screen\n" +
            "o\tPrevent Pregnancy More Info Modal\n" +
            "o\tMenstruation Screen\n" +
            "o\tHormonal Birth Control Screen \n" +
            "o\tHormonal Birth Control More Info Modal\n" +
            "o\tSmoking or Vape Screen\n" +
            "o\tSmoking or Vape More Info Modal\n" +
            "o\tSmoking or Vape – Date of Birth Screen\n" +
            "o\tSmoking or Vape (Risks) Screen\n" +
            "o\tEver Had Cancer Screen\n" +
            "o\tEver Had Cancer More Info Modal\n" +
            "o\tList of Cancers Screen\n" +
            "o\tList of Cancers More Info Modal\n" +
            "o\tBlood Pressure Medication Screen\n" +
            "o\tHeart Condition Screen \n" +
            "o\tHeart Condition More Info Modal\n" +
            "o\tBlood Clot Screen\n" +
            "o\tBlood Clot More Info Modal\n" +
            "o\tIrregular Heartbeat or Heart Valve Problems Screen\n" +
            "o\tIrregular Heartbeat or Heart Valve Problems More Info Modal\n" +
            "o\tLiver Disease or Liver Cancer Screen\n" +
            "o\tLiver Disease or Liver Cancer More Info Modal\n" +
            "o\tUnexplained Vaginal Bleeding Screen\n" +
            "o\tUnexplained Vaginal Bleeding More Info Modal\n" +
            "o\tDiabetes Screen\n" +
            "o\tDiabetes More Info Modal\n" +
            "o\tPregnant Screen\n" +
            "o\tBreastfeeding Screen\n" +
            "o\tPregnancy Loss Screen\n" +
            "o\tObesity/BMI Screen\n" +
            "o\tMigraines with Aura Screen\n" +
            "o\tMigraines with Aura More Info Modal\n" +
            "o\tDDI/Conditions Screen\n" +
            "o\tDDI/Conditions Screen More Info Modal\n" +
            "o\tDDI/Conditions – Hepatitis C Meds Screen\n" +
            "o\tDDI/Conditions – Hepatitis C Meds More Info Modal\n" +
            "o\tDDI/Conditions – Thyroid Disease Meds Screen\n" +
            "o\tDDI/Conditions – Thyroid Disease Meds More Info Modal\n" +
            "o\tDDI/Conditions – Epilepsy or Bipolar Disorder Meds Screen\n" +
            "o\tDDI/Conditions – Epilepsy or Bipolar Disorder Meds More Info Modal\n" +
            "o\tDDI/Conditions – HIV Meds Screen\n" +
            "o\tDDI/Conditions – HIV Meds More Info Modal\n" +
            "o\tDDI/Conditions – High Cholesterol Meds Screen\n" +
            "o\tDDI/Conditions – High Cholesterol Meds More Info Modal\n" +
            "o\tDDI/Conditions – Antifungal Product Screen\n" +
            "o\tDDI/Conditions – Antifungal Product More Info Modal\n" +
            "o\tDDI/Conditions – Antifungal Meds Screen\n" +
            "o\tDDI/Conditions – Antifungal Product More Info Modal\n" +
            "o\tDDI/Conditions – Other Medication Screen\n" +
            "o\tDDI/Conditions – Other Medication More Info Modal\n" +
            "o\tGallbladder Screen\n" +
            "o\tGallbladder More Info Modal\n" +
            "o\tDepression Screen\n" +
            "o\tDepression More Info Modal \n" +
            "o\tClinical Depression Screen\n" +
            "o\tClinical Depression More Info Modal\n" +
            "o\tKnow BP Numbers Screen\n" +
            "o\tKnow BP Numbers More Info Modal \n" +
            "o\tEnter BP Numbers Screen\n" +
            "o\tEnter BP Numbers More Info Modal\n" +
            "o\tEditable Summary\n" +
            "o\tSafe to Use Screen\n" +
            "o\tAttestation Screen\n" +
            "o\tADBU ONLY Screen (Including Attestation Modal)\n" +
            "o\tADBU – Reconfirmation Screen\n" +
            "o\tADBU + Get BP Numbers Screen\n" +
            "o\tGet BP Measured Screen\n" +
            "o\tEnter BP (Final) Screen (Including 3 Month Modal)";
    static String REQUIREMENTS = "DEX_FRD_016, DEX_FRD_249";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx\n" +
            "HappyFlow_IA_Initial_Assessment_noBP_NonSmoker.docx";
    DxPageObj pageObj;
    DxUser user;
    String reportName =  "VTP_DEX_FRD_015_249_Refresh_Button_Current_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_FRD_015_249_Refresh_Button_Current_Screen()  {
        VERSIONHISTORY.add(" ; ; ; ");
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
        pageObj = new DxPageObj(driver);
        commonPageFeatures = new CommonPageFeatures(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();

        pageObj.welcomePage.verifyAtPage(report);
        pageObj.welcomePage.clickBegin(report);
        refresh(3, pageObj.privacyPage);
        pageObj.privacyPage.clickIAcceptBtnToPage(pageObj.numbers, report);
        refresh(5, pageObj.numbers);
        refreshMoreInfo(7, pageObj.numbers);

        // OAuth to Product Used
        commonPageFeatures.clickNextToPage(pageObj.oAuth, report);
        refresh(9, pageObj.usedProduct);

        pageObj.oAuth.clickGuestButton(report);
        refresh(11, pageObj.usedProduct);

        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.usedProduct.verifyAtPage(report);
        refresh(14, pageObj.usedProduct);

        commonPageFeatures.clickYesNoNext(user.productUsed, report);
        refresh(16, pageObj.orderForSelf);

        pageObj.orderForSelf.clickCloseToDismiss(report);
        commonPageFeatures.clickMoreInfoToModal(report);
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
        pageObj.obesity.enterHeightAndWeight(user, report);
        pageObj.obesity.clickNextToPage(pageObj.ddiCondition, report);
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

        pageObj.knowBPNumber.selectRadioReponse(BloodPressureType.Yes_Know_BP.label, report);
        pageObj.knowBPNumber.clickNext(report);
        pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
        refresh(133, pageObj.enterBP);

        pageObj.knowBPNumber.selectRadioReponse(BloodPressureType.Yes_Know_BP.label, report);
        pageObj.knowBPNumber.clickNext(report);
        pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
        pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
        commonPageFeatures.verifyModalDismissed(report);
        pageObj.enterBP.verifyAtPage(report);
        refresh(136, pageObj.enterBP);
        refreshMoreInfo(138, pageObj.enterBP);

        user.systolic="115";
        user.diastolic="75";
        pageObj.enterBP.enterBP(user, report);
        commonPageFeatures.clickNextToPage(pageObj.review,report);
        refresh(140, pageObj.review);

        pageObj.review.clickConfirmToOpenModal(report);
        refresh(142, pageObj.review);

        pageObj.review.clickEditToPage(ReviewAnswersLinks.DDI,pageObj.ddiCondition, report);
        pageObj.ddiCondition.selectCheckboxAndProgress(DDIConditionType.HIGH_CHOLESTEROL.label, pageObj.review, report);
        refresh(142, pageObj.review);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.review, report);

        pageObj.review.addressConfirmations(report);
        refresh(146, pageObj.oAuthPostReview);

        pageObj.oAuthPostReview.chooseAccountType(user, report);
        refresh(148, pageObj.purchaseOptions);

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.depression, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression,report);
        pageObj.diagnosedDepression.clickYesAndAddressModalToPage(pageObj.knowBPNumber, report);
        pageObj.knowBPNumber.selectRadioReponse(BloodPressureType.Yes_Know_BP.label, report);
        pageObj.knowBPNumber.clickNext(report);
        pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
        pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
        commonPageFeatures.verifyModalDismissed(report);
        pageObj.enterBP.verifyAtPage(report);

        user.systolic="110";
        user.diastolic="75";
        pageObj.enterBP.enterBP(user, report);
        commonPageFeatures.clickNextToPage(pageObj.review,report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.verifyAtPage(report);
        pageObj.oAuthPostReview.chooseAccountType(user, report);
        pageObj.adbu.verifyAtPage(report);
        refresh(159, pageObj.adbu);

        pageObj.adbu.clickYesNoToOpenModal("Yes", report);
        refresh(161, pageObj.adbu);
        pageObj.adbu.clickYesNoToOpenModal("No", report);
        refresh(163, pageObj.adbu);
        pageObj.adbu.addressConfirmations(user.askedDoctor, report);

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.knowBPNumber, report);
        pageObj.knowBPNumber.selectRadioResponseAndProgress(BloodPressureType.No_Know_BP.label, pageObj.review,
                report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountType(user, report);
        pageObj.adbubpNormal.verifyAtPage(report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);
        refresh(171, pageObj.enterBPEnd);
        user.systolic="115";
        user.diastolic="75";
        pageObj.enterBPEnd.enterBP(user, report);
        commonPageFeatures.clickNext(report);
        pageObj.enterBPEnd.verifyModalDisplayed(report);
        refresh(173, pageObj.enterBPEnd);
    }
}
