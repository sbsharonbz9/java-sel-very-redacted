package galen.tenant.dexter.Navigation_All;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.BloodPressureType;
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

import static java.lang.Thread.sleep;

public class VTP_DEX_FRD_016_Display_App_Back_Button extends BaseTest {
    static String OBJECTIVE = "To verify the application shall include an application back button on every screen, " +
            "except for the Welcome, DNU, Safe To Use, ADBU, ADBU/BP, Get BP, ADBU - Reconfirmation, Timeout, Purchase " +
            "Verification screens.";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "-\tDisplay of the Back button on the following screens:\n" +
            "o\tKnow Numbers Screen\n" +
            "o\tKnow Numbers (More Info Modal) Screen\n" +
            "o\tPrior Use Screen\n" +
            "o\tConfirm Customer Screen\n" +
            "o\tConfirm Customer (More Info Modal) Screen\n" +
            "o\tPrevent Pregnancy Screen\n" +
            "o\tPrevent Pregnancy (More Info Modal) Screen \n" +
            "o\tMenstrual Periods Screen\n" +
            "o\tHormonal Birth Control Screen\n" +
            "o\tHormonal Birth Control (More Info Modal) Screen\n" +
            "o\tSmoking or Vape Screen\n" +
            "o\tSmoking or Vape (More Info Modal) Screen\n" +
            "o\tSmoking or Vape – Year of Birth Screen\n" +
            "o\tSmoking or Vape (Risks) Screen\n" +
            "o\tEver Had Cancer Screen\n" +
            "o\tList of Cancers Screen\n" +
            "o\tList of Cancers (More Info Modal) Screen\n" +
            "o\tBlood Pressure Meds Screen\n" +
            "o\tHeart Conditions Screen\n" +
            "o\tHeart Conditions (More Info Modal) Screen\n" +
            "o\tBlood Clot Screen\n" +
            "o\tBlood Clot (More Info Modal) Screen\n" +
            "o\tIrregular Heartbeat or Heart Valve Problems Screen\n" +
            "o\tIrregular Heartbeat or Heart Valve Problems (More Info Modal) Screen\n" +
            "o\tLiver Disease, Liver Cancer Screen\n" +
            "o\tLiver Disease, Liver Cancer (More Info Modal) Screen\n" +
            "o\tUnexplained Vaginal Bleeding Screen\n" +
            "o\tUnexplained Vaginal Bleeding (More Info Modal) Screen\n" +
            "o\tDiabetes Screen\n" +
            "o\tDiabetes (More Info Modal) Screen\n" +
            "o\tPregnant Screen\n" +
            "o\tBreastfeeding Screen\n" +
            "o\tPregnancy Loss Screen\n" +
            "o\tObesity/BMI Screen\n" +
            "o\tMigraines with Aura Screen\n" +
            "o\tMigraines with Aura (More Info Modal) Screen\n" +
            "o\tConditions/DDI Screen\n" +
            "o\tConditions/DDI (More Info Modal) Screen\n" +
            "o\tConditions/DDI - Hepatitis C Meds Screen\n" +
            "o\tConditions/DDI - Hepatitis C Meds (More Info Modal) Screen\n" +
            "o\tConditions/DDI - Thyroid Disease Meds Screen\n" +
            "o\tConditions/DDI - Thyroid Disease (More Info Modal) Screen\n" +
            "o\tConditions/DDI - Epilepsy or Bipolar Disorder Meds Screen\n" +
            "o\tConditions/DDI - Epilepsy or Bipolar Disorder (More Info Modal) Screen\n" +
            "o\tConditions/DDI - HIV Meds Screen\n" +
            "o\tConditions/DDI - HIV Meds (More Info Modal) Screen\n" +
            "o\tConditions/DDI – High Cholesterol Meds Screen\n" +
            "o\tConditions/DDI - High Cholesterol Meds (More Info Modal) Screen\n" +
            "o\tConditions/DDI – Antifungal Products Screen\n" +
            "o\tConditions/DDI – Antifungal Meds Screen\n" +
            "o\tConditions/DDI - Other Medications Screen\n" +
            "o\tGallbladder Screen\n" +
            "o\tGallbladder (More Info Modal) Screen\n" +
            "o\tDepression Screen\n" +
            "o\tDepression (More Info Modal) Screen\n" +
            "o\tClinical Depression Screen\n" +
            "o\tClinical Depression (More Info Modal) Screen \n" +
            "o\tKnow BP Numbers Screen\n" +
            "o\tKnow BP Numbers (Get BP Numbers) Screen\n" +
            "o\t3-Month Confirmation Modal\n" +
            "o\tEnter BP Numbers Screen\n" +
            "-\tBack button is not displayed on the following screens:\n" +
            "o\tEditable Summary Screen\n" +
            "o\tDNU Screen\n" +
            "o\tPurchase Options\n" +
            "o\tADBU/BP Screen\n" +
            "o\tGet BP Measured Screen\n" +
            "o\tGet BP (Final) Screen";
    static String REQUIREMENTS = "FRD_016";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    DexterPageObj pageObj;
    DexterUser user;
    String reportName = "DEX_FRD_016_Display_App_Back_Button";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_FRD_016_Display_App_Back_Button()  {
        VERSIONHISTORY.add("1.0;13OCT2022;Initial Test Script;Name Redacted");
        VERSIONHISTORY.add("2.0;19SEP2023;Per CADENCE-359/CADENCE-360: Updated Test Steps navigation\n" +
                "for restructured cancer flow;Name Redacted");
        VERSIONHISTORY.add("3.0;13JUN2024\n" +
                ";Per CADENCE-476: Updated Test Steps for FDA changes\n" +
                "Per CADENCE-529: Removed N/A from Actual Result column for Happy flow execution related steps\n" +
                "Per CADENCE-549: Removed Checkout related steps\n" +
                "Per CADENCE-455: Updated Test Steps to change the button selection\n" +
                "Per CADENCE-569: Update Test Steps to remove any reference to OAuth, Returning User, or Checkout\n" +
                "Per CADENCE-591: Update Test Steps for modified assessment flow and response options;Name Redacted");
    }

    boolean verifyMoreInfoBackDisplayed(int step, BasePage page) throws InterruptedException {
        commonPageFeatures.clickMoreInfoToModal(report);
        boolean result =  bh.verifyDisplayedFlex(commonPageFeatures.moreInfoBack, "More Info Back button", report);
        report.addScreenshotStep("Step"+step +"_"+page.reportText + "_MoreInfo_BackButton", driver);
        commonPageFeatures.clickMoreInfoBackToModalDismissed(report);
        Thread.sleep(1000);
        return result;
    }

    boolean verifyBackDisplayed(int step, BasePage page) throws InterruptedException {
        boolean result = bh.verifyDisplayedFlex(commonPageFeatures.btnBack, "Back button", report);
        report.addScreenshotStep("Step"+ step + "_"+page.reportText + "_BackButton", driver);
        Thread.sleep(1000);
        return result;
    }

    boolean verifyBackNotDisplayed(int step, BasePage page) throws InterruptedException {
        boolean result = bh.verifyNotDisplayedFlex(commonPageFeatures.btnBack, "Back button", report);
        report.addScreenshotStep("Step"+ step + "_"+page.reportText + "_BackButton", driver);
        Thread.sleep(1000);
        return result;
    }

    @Test
    public void DEX_FRD_016_Display_App_Back_Button_Test() throws IOException, InterruptedException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_016 – Display Application Back Button ";
        bh = new BasicHelpers(driver);
        pageObj = new DexterPageObj(driver);
        commonPageFeatures = new CommonPageFeatures(driver);
        pageObj.pritUnl.load(UrlType.DEXTER);
        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();

        pageObj.welcomePage.verifyAtPage(report);
        pageObj.welcomePage.clickBegin(report);
        pageObj.privacyPage.clickIAcceptBtnToPage(pageObj.numbers, report);
        verifyBackDisplayed(3, pageObj.numbers);
        verifyMoreInfoBackDisplayed(4, pageObj.numbers);

        // OAuth to Product Used
        commonPageFeatures.clickNextToPage(pageObj.oAuth, report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.usedProduct.verifyAtPage(report);
        verifyBackDisplayed(8, pageObj.usedProduct);


        // Product Used to Prevent
        commonPageFeatures.clickYesNoNext(user.productUsed, report);
        pageObj.usedProduct.clickCloseButton(report);
        pageObj.orderForSelf.verifyAtPage(report);
        verifyBackDisplayed(10, pageObj.orderForSelf);
        verifyMoreInfoBackDisplayed(11,pageObj.orderForSelf);

        commonPageFeatures.clickYesNoNextToPage(user.orderForSelf,pageObj.pregnancy, report);
        verifyBackDisplayed(13, pageObj.pregnancy);
        verifyMoreInfoBackDisplayed(14, pageObj.pregnancy);

        commonPageFeatures.clickYesNoNextToPage(user.preventPregnancy,pageObj.menstrual, report);
        verifyBackDisplayed(16, pageObj.menstrual);

        commonPageFeatures.clickYesNoNextToPage("Yes",pageObj.birthControl, report);
        verifyBackDisplayed(17, pageObj.birthControl);
        verifyMoreInfoBackDisplayed(18, pageObj.birthControl);

        commonPageFeatures.clickYesNoNextToPage("No",pageObj.smoking, report);
        verifyBackDisplayed(20,pageObj.smoking);
        verifyMoreInfoBackDisplayed(21, pageObj.smoking);

        pageObj.smoking.selectRadioResponseAndProgress(SmokeType.SMOKE_REGULARLY.label, pageObj.birthYear, report);
        verifyBackDisplayed(23, pageObj.birthYear);

        int currentYear = Year.now().getValue();
        user.dobYear=String.valueOf((currentYear-25));
        pageObj.birthYear.fillOutBirthdayAndProgress(user, pageObj.smokingDisclaimer, report);
        verifyBackDisplayed(24, pageObj.smokingDisclaimer);

        commonPageFeatures.clickNextToPage( pageObj.everHadCancer,report);
        verifyBackDisplayed(25, pageObj.everHadCancer);

        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.cancerList,report);
        verifyBackDisplayed(26, pageObj.cancerList);
        verifyMoreInfoBackDisplayed(27, pageObj.cancerList);

        commonPageFeatures.clickBackToPage(pageObj.everHadCancer, report);
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.bloodPressureMeds,report);
        verifyBackDisplayed(30, pageObj.bloodPressureMeds);

        commonPageFeatures.clickYesNoNextToPage(user.bloodPressureMeds,pageObj.cardiacRisk, report);
        verifyBackDisplayed(31, pageObj.cardiacRisk);
        verifyMoreInfoBackDisplayed(32, pageObj.cardiacRisk);

        pageObj.cardiacRisk.selectCheckboxesAndProgress(user.chestPainType, pageObj.bloodClot, report);
        verifyBackDisplayed(34, pageObj.bloodClot);
        verifyMoreInfoBackDisplayed(35, pageObj.bloodClot);

        pageObj.bloodClot.selectCheckboxesAndProgress(user.bloodClot, pageObj.irregularHeartBeat, report);
        verifyBackDisplayed(37, pageObj.irregularHeartBeat);
        verifyMoreInfoBackDisplayed(38, pageObj.irregularHeartBeat);

        commonPageFeatures.clickYesNoNextToPage(user.irregularHeartBeat, pageObj.liverCancer, report);
        verifyBackDisplayed(40, pageObj.liverCancer);
        verifyMoreInfoBackDisplayed(41, pageObj.liverCancer);

        pageObj.liverCancer.selectCheckboxesAndProgress(user.liverCancer, pageObj.vaginalBleeding, report);
        verifyBackDisplayed(43, pageObj.vaginalBleeding);
        verifyMoreInfoBackDisplayed(44, pageObj.vaginalBleeding);

        pageObj.vaginalBleeding.clickYesNoNextToPage(user.vaginalBleeding,pageObj.diabetes, report);
        verifyBackDisplayed(46, pageObj.diabetes);
        verifyMoreInfoBackDisplayed(47, pageObj.diabetes);

        commonPageFeatures.clickYesNoNextToPage(user.diabetes, pageObj.pregnant, report);
        verifyBackDisplayed(49, pageObj.pregnant);

        commonPageFeatures.clickYesNoNextToPage(user.pregnant,pageObj.breastFeeding, report);
        verifyBackDisplayed(50, pageObj.breastFeeding);

        commonPageFeatures.clickYesNoNextToPage(user.breastfeeding,pageObj.pregnancyLoss, report);
        verifyBackDisplayed(51, pageObj.pregnancyLoss);

        commonPageFeatures.clickYesNoNextToPage(user.pregnancyLoss, pageObj.migraines, report);
        verifyBackDisplayed(52, pageObj.migraines);
        verifyMoreInfoBackDisplayed(53, pageObj.migraines);

        commonPageFeatures.clickYesNoNextToPage(user.migraines, pageObj.obesity,report);
        verifyBackDisplayed(55, pageObj.obesity);

        // Obesity to DDI
        pageObj.obesity.enterHeightAndWeight(user, report);
        pageObj.obesity.clickNextToPage(pageObj.ddiCondition, report);
        verifyBackDisplayed(56, pageObj.ddiCondition);
        verifyMoreInfoBackDisplayed(57, pageObj.ddiCondition);

        // DDI to HepC
        pageObj.ddiCondition.selectCheckboxesAndProgress(pageObj.ddiCondition.allButNone, pageObj.ddiHepC,report);
        verifyBackDisplayed(59, pageObj.ddiHepC);
        verifyMoreInfoBackDisplayed(60, pageObj.ddiHepC);

        // HepC to Thyroid
        pageObj.ddiHepC.selectCheckboxesAndProgress(user.hepCMeds, pageObj.ddiThyroid,report);
        verifyBackDisplayed(62, pageObj.ddiThyroid);
        verifyMoreInfoBackDisplayed(63, pageObj.ddiThyroid);

        // Thyroid to Epilepsy
        pageObj.ddiThyroid.selectCheckboxesAndProgress(user.thyroidMeds, pageObj.ddiEpBipolar, report);
        verifyBackDisplayed(65, pageObj.ddiEpBipolar);
        verifyMoreInfoBackDisplayed(66, pageObj.ddiEpBipolar);

        //  Epilepsy to HIV
        pageObj.ddiEpBipolar.selectCheckboxesAndProgress(user.epBipolarMeds, pageObj.ddihiv, report);
        verifyBackDisplayed(68, pageObj.ddihiv);
        verifyMoreInfoBackDisplayed(69, pageObj.ddihiv);

        // HIV to High Cholesterol
        pageObj.ddihiv.selectCheckboxesAndProgress(user.hivMeds, pageObj.ddiHighCholesterol, report);
        verifyBackDisplayed(71, pageObj.ddiHighCholesterol);
        verifyMoreInfoBackDisplayed(72, pageObj.ddiHighCholesterol);

        // High Cholesterol to Antifungal
        sleep(500);
        pageObj.ddiHighCholesterol.selectCheckboxesAndProgress(user.highCholMeds,
                pageObj.antifungal, report);
        verifyBackDisplayed(74, pageObj.antifungal);
        verifyMoreInfoBackDisplayed(75, pageObj.antifungal);

        // Antifungal to AF Meds
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.antifungalMeds, report);
        verifyBackDisplayed(77, pageObj.antifungalMeds);
        verifyMoreInfoBackDisplayed(78, pageObj.antifungalMeds);

        pageObj.antifungalMeds.selectCheckboxesAndProgress(user.antiFungalMeds, pageObj.otherMedication, report);
        verifyBackDisplayed(80, pageObj.otherMedication);
        verifyMoreInfoBackDisplayed(81, pageObj.otherMedication);

        pageObj.otherMedication.selectCheckboxesAndProgress(user.otherMedicationType, pageObj.gallbladder, report);
        verifyBackDisplayed(83, pageObj.gallbladder);
        verifyMoreInfoBackDisplayed(84, pageObj.gallbladder);

        commonPageFeatures.clickYesNoNextToPage(user.gallbladder, pageObj.depression,report);
        verifyBackDisplayed(86, pageObj.depression);
        verifyMoreInfoBackDisplayed(87, pageObj.depression);

        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression,report);
        verifyBackDisplayed(89, pageObj.diagnosedDepression);
        verifyMoreInfoBackDisplayed(90, pageObj.diagnosedDepression);

        commonPageFeatures.clickBackToPage(pageObj.depression, report);
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.knowBPNumber,report);
        verifyBackDisplayed(92, pageObj.knowBPNumber);

        pageObj.knowBPNumber.selectRadioReponse(user.knowBPType.label, report);
        pageObj.knowBPNumber.clickNext(report);
        pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
        pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
        commonPageFeatures.verifyModalDismissed(report);
        pageObj.enterBP.verifyAtPage(report);
        verifyBackDisplayed(94, pageObj.enterBP);

        commonPageFeatures.clickBackToPage(pageObj.knowBPNumber, report);
        pageObj.knowBPNumber.selectRadioResponseAndProgress(BloodPressureType.No_Know_BP.label, pageObj.review,report);
        sleep(500);
        verifyBackNotDisplayed(96, pageObj.review);

        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountType(user, report);
        pageObj.adbubpScreen.verifyAtPage(report);
        verifyBackNotDisplayed(99, pageObj.adbubpScreen);

        pageObj.adbubpScreen.clickYesNoToOpenModal("Yes", report);
        pageObj.adbubpScreen.clickConfirmCheckbox(report);
        pageObj.adbubpScreen.clickConfirm(report);
        pageObj.adbubpNormal.verifyAtPage(report);
        verifyBackNotDisplayed(101, pageObj.adbubpNormal);

        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);
        verifyBackNotDisplayed(102, pageObj.enterBPEnd);

        user.systolic="115";
        user.diastolic="75";
        pageObj.enterBPEnd.enterBP(user, report);
        commonPageFeatures.clickNext(report);
        pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
        commonPageFeatures.clickYesOrNoModal("Yes", report);
        pageObj.purchaseOptions.verifyAtPage(report);
        verifyBackNotDisplayed(104, pageObj.purchaseOptions);

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.orderForSelf, report);
        pageObj.orderForSelf.clickClose(null);
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        verifyBackNotDisplayed(106, pageObj.kickoutPage);

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.review, report);
        pageObj.review.clickGallBladderEdit(report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.review, report);

        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbu,report);
        pageObj.adbu.addressConfirmationsAndProgress("No", pageObj.docAttestationNo, report);
        verifyBackNotDisplayed(114, pageObj.docAttestationNo);
    }
}
