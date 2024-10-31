package galen.tenant.dexter.Navigation_All;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.BloodPressureType;
import galen.enums.tenant.dexter.CancerType;
import galen.enums.tenant.dexter.SmokeType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterNavigations;
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

public class VTP_DEX_FRD_017_Nav_App_Back_Button extends BaseTest {
    static String OBJECTIVE = "To verify upon selection of the Application back button, the application shall return to " +
            "the previous screen in a health survey.";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "-\tNavigation of the Back button on the following screens:\n" +
            "o\tKnow Numbers Screen\n" +
            "o\tPrior Use Screen\n" +
            "o\tConfirm Customer Screen\n" +
            "o\tConfirm Customer (More Info Modal) Screen\n" +
            "o\tPrevent Pregnancy Screen\n" +
            "o\tPrevent Pregnancy (More Info Modal) Screen\n" +
            "o\tMenstruation Screen\n" +
            "o\tHormonal Birth Control (More Info Modal) Screen\n" +
            "o\tHormonal Birth Control Screen (Both deviations)\n" +
            "o\tSmoking or Vape (More Info Modal) Screen\n" +
            "o\tSmoking or Vape Screen\n" +
            "o\tSmoking or Vape – Year of Birth Screen\n" +
            "o\tSmoking or Vape (Risks) Screen\n" +
            "o\tEver Had Cancer Screen\n" +
            "o\tList of Cancers Screen\n" +
            "o\tList of Cancers (More Info Modal) Screen\n" +
            "o\tBlood Pressure Medication Screen (Both deviations)\n" +
            "o\tHeart Conditions (More Info Modal) Screen\n" +
            "o\tHeart Conditions Screen (Both deviations)\n" +
            "o\tBlood Clot (More Info Modal) Screen\n" +
            "o\tBlood Clot Screen\n" +
            "o\tIrregular Heartbeat or Heart Valve Problems (More Info Modal) Screen\n" +
            "o\tIrregular Heartbeat or Heart Valve Problems Screen\n" +
            "o\tLiver Disease or Liver Cancer (More Info Modal) Screen\n" +
            "o\tLiver Disease or Liver Cancer Screen\n" +
            "o\tUnexplained Vaginal Bleeding (More Info Modal) Screen\n" +
            "o\tUnexplained Vaginal Bleeding Screen\n" +
            "o\tDiabetes (More Info Modal) Screen\n" +
            "o\tDiabetes Screen\n" +
            "o\tPregnant Screen\n" +
            "o\tBreastfeeding Screen\n" +
            "o\tPregnancy Loss Screen\n" +
            "o\tMigraines with Aura (More Info Modal) Screen\n" +
            "o\tMigraines with Aura Screen\n" +
            "o\tObesity/BMI Screen\n" +
            "o\tConditions/DDI Screen\n" +
            "o\tConditions/DDI – Hepatitis C Meds Screen\n" +
            "o\tConditions/DDI – Thyroid Disease Meds Screen\n" +
            "o\tConditions/DDI – Epilepsy or Bipolar Disorder Meds Screen\n" +
            "o\tConditions/DDI – HIV Meds Screen\n" +
            "o\tConditions/DDI – High Cholesterol Meds Screen\n" +
            "o\tAntifungal Products Screen (Both deviations)\n" +
            "o\tAntifungal Meds Screen\n" +
            "o\tOther Medication More Info Modal) Screen\n" +
            "o\tOther Medication Screen (Both deviations)\n" +
            "o\tGallbladder (More Info Modal) Screen\n"+
            "o\tGallbladder Screen\n" +
            "o\tDepression (More Info Modal) Screen\n" +
            "o\tDepression Screen\n" +
            "o\tClinical Depression Screen\n" +
            "o\tClinical Depression (More Info Modal) Screen \n" +
            "o\tKnow BP Numbers Screen\n" +
            "o\tKnow BP Numbers (Get BP Numbers) Screen\n" +
            "o\tEnter BP Numbers (More Info Modal) Screen\n" +
            "o\tEnter BP Numbers Screen\n";
    static String REQUIREMENTS = "FRD_017";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    DexterPageObj pageObj;
    DexterUser user;
    String reportName = "DEX_FRD_017_Nav_App_Back_Button";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_FRD_017_Nav_App_Back_Button()  {
        VERSIONHISTORY.add("1.0;13OCT2022;Initial Test Script;James Reale");
        VERSIONHISTORY.add("2.0;20SEP2023;Per CADENCE-359/CADENCE-360: Updated Test Steps navigation for restructured cancer flow;Suresh Sunderraj");
        VERSIONHISTORY.add("3.0;13JUN2024;Per CADENCE-476: Updated Test Steps for FDA changes\n" +
                "Per CADENCE-529: Removed N/A from Actual Result column for Happyflow execution related steps\n" +
                "Per CADENCE-591: Update Test Steps for modified assessment and navigation;Gulzira Nurseilova");
    }

    void verifyMoreInfoBack(int step, BasePage page) throws InterruptedException {
        commonPageFeatures.clickBackToPage(page, report);
        report.addScreenshotStep("Step"+step, driver);
        commonPageFeatures.clickMoreInfoToModal(report);
        commonPageFeatures.clickMoreInfoBackToModalDismissed(report);
        report.addScreenshotStep("Step"+step+2, driver);
    }

    @Test
    public void VTP_DEX_FRD_017_Nav_App_Back_Button_Test() throws IOException, InterruptedException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_017 – Navigation of Application Back Button";
        bh = new BasicHelpers(driver);
        pageObj = new DexterPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        commonPageFeatures = new CommonPageFeatures(driver);
        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_Smoker();
        user.conditionType=pageObj.ddiCondition.allButNone;
        user.depression="Yes";
        user.isAntifungal="Yes";
        
        new DexterNavigations(driver).partialNavigationIA(user, pageObj.enterBP, report);
        commonPageFeatures.clickBackToPage(pageObj.knowBPNumber, report);
        report.addScreenshotStep("Step42_ClickBackToKnowBP", driver);

        verifyMoreInfoBack(43, pageObj.diagnosedDepression);
        verifyMoreInfoBack(46, pageObj.depression);
        verifyMoreInfoBack(49, pageObj.gallbladder);
        verifyMoreInfoBack(52, pageObj.otherMedication);
        verifyMoreInfoBack(55, pageObj.antifungalMeds);
        verifyMoreInfoBack(58, pageObj.antifungal);
        verifyMoreInfoBack(61, pageObj.ddiHighCholesterol);
        verifyMoreInfoBack(64, pageObj.ddihiv);
        verifyMoreInfoBack(67, pageObj.ddiEpBipolar);
        verifyMoreInfoBack(70, pageObj.ddiThyroid);
        verifyMoreInfoBack(73, pageObj.ddiHepC);
        verifyMoreInfoBack(76, pageObj.ddiCondition);

        commonPageFeatures.clickBackToPage(pageObj.obesity, report);
        report.addScreenshotStep("Step79", driver);

        verifyMoreInfoBack(80, pageObj.migraines);
        commonPageFeatures.clickBackToPage(pageObj.pregnancyLoss, report);
        report.addScreenshotStep("Step83", driver);

        commonPageFeatures.clickBackToPage(pageObj.breastFeeding, report);
        report.addScreenshotStep("Step84", driver);

        commonPageFeatures.clickBackToPage(pageObj.pregnant, report);
        report.addScreenshotStep("Step85", driver);

        verifyMoreInfoBack(86, pageObj.diabetes);
        verifyMoreInfoBack(89, pageObj.vaginalBleeding);
        verifyMoreInfoBack(92, pageObj.liverCancer);
        verifyMoreInfoBack(95, pageObj.irregularHeartBeat);
        verifyMoreInfoBack(98, pageObj.bloodClot);
        verifyMoreInfoBack(101, pageObj.cardiacRisk);
        commonPageFeatures.clickBackToPage(pageObj.bloodPressureMeds, report);
        report.addScreenshotStep("Step105");

        commonPageFeatures.clickBackToPage(pageObj.everHadCancer, report);
        commonPageFeatures.clickYesNoNextToPage("Yes", pageObj.cancerList, report);
        commonPageFeatures.clickMoreInfoToModal(report);
        commonPageFeatures.clickMoreInfoBackToModalDismissed(report);
        report.addScreenshotStep("Step108");

        pageObj.cancerList.selectCheckboxAndProgress(CancerType.Other_Cancer.label,pageObj.bloodPressureMeds, report);
        commonPageFeatures.clickBackToPage(pageObj.cancerList, report);
        report.addScreenshotStep("Step110");

        commonPageFeatures.clickBackToPage(pageObj.everHadCancer, report);
        report.addScreenshotStep("Step111");

        commonPageFeatures.clickBackToPage(pageObj.smokingDisclaimer, report);
        report.addScreenshotStep("Step112");

        commonPageFeatures.clickBackToPage(pageObj.birthYear, report);
        report.addScreenshotStep("Step113");

        verifyMoreInfoBack(114, pageObj.smoking);
        verifyMoreInfoBack(117, pageObj.birthControl);
        commonPageFeatures.clickBackToPage(pageObj.menstrual, report);
        report.addScreenshotStep("Step120");
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.birthControlB, report);
        pageObj.birthControlB.addressConfirmationsAndProgress(pageObj.smoking, report);
        commonPageFeatures.clickBackToPage(pageObj.birthControlB, report);
        report.addScreenshotStep("Step124");

        commonPageFeatures.clickBackToPage(pageObj.menstrual, report);
        report.addScreenshotStep("Step125");

        verifyMoreInfoBack(126, pageObj.pregnancy);
        commonPageFeatures.clickBackToPage(pageObj.orderForSelf, report);
        report.addScreenshotStep("Step129");
        pageObj.usedProduct.clickCloseButton(report);
        commonPageFeatures.clickMoreInfoToModal(report);
        commonPageFeatures.clickMoreInfoBackToModalDismissed(report);
        report.addScreenshotStep("Step131");

        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        new DexterNavigations(driver).partialNavigationIA(user, pageObj.knowBPNumber, report);
        commonPageFeatures.clickBackToPage(pageObj.depression, report);
        report.addScreenshotStep("Step133_Depression");

        commonPageFeatures.clickBackToPage(pageObj.gallbladder, report);
        commonPageFeatures.clickBackToPage(pageObj.otherMedication, report);
        commonPageFeatures.clickBackToPage(pageObj.antifungal, report); //Should be Antifungal on doc?
        commonPageFeatures.clickBackToPage(pageObj.ddiCondition, report);
        report.addScreenshotStep("Step137");

        commonPageFeatures.clickBackToPage(pageObj.obesity, report);
        commonPageFeatures.clickBackToPage(pageObj.migraines, report);
        commonPageFeatures.clickBackToPage(pageObj.pregnancyLoss, report);
        commonPageFeatures.clickBackToPage(pageObj.breastFeeding, report);
        commonPageFeatures.clickBackToPage(pageObj.pregnant, report);
        commonPageFeatures.clickBackToPage(pageObj.diabetes, report);
        commonPageFeatures.clickBackToPage(pageObj.vaginalBleeding, report);
        commonPageFeatures.clickBackToPage(pageObj.liverCancer, report);
        commonPageFeatures.clickBackToPage(pageObj.irregularHeartBeat, report);
        commonPageFeatures.clickBackToPage(pageObj.bloodClot, report);
        commonPageFeatures.clickBackToPage(pageObj.cardiacRisk, report);
        commonPageFeatures.clickBackToPage(pageObj.bloodPressureMeds, report);
        commonPageFeatures.clickBackToPage(pageObj.everHadCancer, report);
        commonPageFeatures.clickBackToPage(pageObj.smoking, report);
        report.addScreenshotStep("Step151");

        commonPageFeatures.clickBackToPage(pageObj.birthControl, report);
        commonPageFeatures.clickBackToPage(pageObj.menstrual, report);
        commonPageFeatures.clickYesNoNextToPage("No", pageObj.birthControlB, report);
        commonPageFeatures.clickBackToPage(pageObj.menstrual, report);
        report.addScreenshotStep("Step155");

        new DexterNavigations(driver).partialNavigationIA(user, pageObj.numbers, report);
        commonPageFeatures.clickMoreInfoToModal(report);
        commonPageFeatures.clickMoreInfoBackToModalDismissed(report);
        report.addScreenshotStep("Step158_Know_Numbers", driver);

        commonPageFeatures.clickBackToPage(pageObj.privacyPage, report);
        report.addScreenshotStep("Step159_know_Numbers", driver);
    }
}
