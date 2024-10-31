package galen.tenant.dexter.Navigation_All;

import galen.base.BaseTest;
import galen.enums.SP.AccountTabs;
import galen.enums.SP.RoleType;
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
import galen.utils.ConfigLoader;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_FRD_018_020_Display_Browser_Back_Button extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_018: To verify during an Assessment, upon selection of the browser back button " +
            "during a health survey, the application shall display an Exit Health Survey modal.\n" +
            "DEX_FRD_020: To verify on the Exit Health Survey modal, if the user chooses to continue with the " +
            "health survey, the applica-tion shall remain on the current screen and user progress is saved.\n";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "-\tDisplay of Exit Health Survey Modal when using the Browser back button and closing of Exit Health Survey Modal on the following screens:\n" +
            "o\tPrivacy Notice Screen\n" +
            "o\tKnow Numbers Screen\n" +
            "o\tPrior Use Screen\n" +
            "o\tConfirm Customer Screen\n" +
            "o\tConfirm Customer (More Info Modal) Screen\n" +
            "o\tPrevent Pregnancy Screen\n" +
            "o\tPrevent Pregnancy (More Info Modal) Screen\n" +
            "o\tMenstruation Screen\n" +
            "o\tHormonal Birth Control Screen \n" +
            "o\tHormonal Birth Control (More Info Modal) Screen\n" +
            "o\tSmoking or Vape Screen\n" +
            "o\tSmoking or Vape (More Info Modal) Screen\n" +
            "o\tSmoking or Vape – Birth Year Screen\n" +
            "o\tSmoking or Vape (Risks) Screen\n" +
            "o\tEver Had Cancer Screen\n" +
            "o\tList of Cancers Screen\n" +
            "o\tList of Cancers (More Info Modal) Screen\n" +
            "o\tBlood Pressure Medication Screen\n" +
            "o\tHeart Conditions Screen \n" +
            "o\tHeart Conditions (More Info Modal) Screen\n" +
            "o\tBlood Clot Screen\n" +
            "o\tBlood Clot (More Info Modal) Screen\n" +
            "o\tIrregular Heartbeat or Heart Valve Problems Screen\n" +
            "o\tIrregular Heartbeat or Heart Valve Problems (More Info Modal) Screen\n" +
            "o\tLiver Disease or Liver Cancer Screen\n" +
            "o\tLiver Disease or Liver Cancer (More Info Modal) Screen\n" +
            "o\tUnexplained Vaginal Bleeding Screen\n" +
            "o\tUnexplained Vaginal Bleeding (More Info Modal) Screen\n" +
            "o\tDiabetes Screen\n" +
            "o\tDiabetes (More Info Modal) Screen\n" +
            "o\tPregnant Screen\n" +
            "o\tBreastfeeding Screen\n" +
            "o\tPregnancy Loss Screen\n" +
            "o\tMigraines with Aura Screen\n" +
            "o\tMigraines with Aura (More Info Modal) Screen\n" +
            "o\tObesity/BMI Screen\n" +
            "o\tConditions/DDI Screen\n" +
            "o\tConditions/DDI – Hepatitis C Meds Screen\n" +
            "o\tConditions/DDI – Thyroid Disease Screen\n" +
            "o\tConditions/DDI – Thyroid Disease Meds Screen\n" +
            "o\tConditions/DDI – Epilepsy Meds Screen\n" +
            "o\tConditions/DDI – HIV Meds Screen\n" +
            "o\tConditions/DDI – High Cholesterol Meds Screen\n" +
            "o\tConditions/DDI – Antifungal Product Screens\n" +
            "o\tConditions/DDI – Antifungal Meds Screen\n" +
            "o\tConditions/DDI (More Info Modal) Screen\n" +
            "o\tConditions/DDI – Other Medication Screen\n" +
            "o\tGallbladder Screen\n" +
            "o\tGallbladder (More Info Modal) Screen\n" +
            "o\tDepression Screen\n" +
            "o\tDepression (Info 1 of 2) Screen\n" +
            "o\tClinical Depression Screen\n" +
            "o\tDepression (More Info Modal) Screen \n" +
            "o\tKnow BP Numbers Screen\n" +
            "o\tKnow BP Numbers (Get BP Numbers) Screen\n" +
            "o\tEnter BP Numbers Screen\n" +
            "o\tEnter BP Numbers (More Info Modal) Screen\n" +
            "o\tEditable Summary Screen (including Confirmation Modal)\n" +
            "o\tADBU/BP Screen \n" +
            "o\tGet BP Measured Screen\n" +
            "o\tEnter BP (Final) Screen";
    static String REQUIREMENTS = "FRD_018, FRD_020";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_ADBU_noBP.docx\n" +
            "HappyFlow_IA_Initial_Assessment_noBP_NonSmoker.docx";
    DexterPageObj pageObj;
    DexterUser user;
    String reportName = "VTP_DEX_FRD_018_020_Display_Browser_Back_Button";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_FRD_018_020_Display_Browser_Back_Button()  {
        VERSIONHISTORY.add("1.0;22FEB2023;Initial Test Script;James Reale");
        VERSIONHISTORY.add("2.0;19SEP2023;Per CADENCE-359/CADENCE-360: Updated Test Steps navigation for restructured " +
                "cancer flow;Suresh Sunderraj");
        VERSIONHISTORY.add( "3.0;15JUN2024;Per CADENCE-476: Updated Test Steps for FDA changes\n" +
                "Per CADENCE-529: Removed N/A from Actual Result column for Happy flow execution related steps\n" +
                "Per CADENCE-591: Update Test Steps for modified assessment and navigation;Gulzira Nurseilova");
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
        page.clickExitCloseToModalDismissed(report);
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

        sleep(1000);
        driver.navigate().to(ConfigLoader.getInstance().getDexterUrl());
        sleep(1000);
        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();

        pageObj.welcomePage.verifyAtPage(report);
        pageObj.welcomePage.clickBegin(report);
        verifyExit(3, pageObj.privacyPage);
        pageObj.privacyPage.clickIAcceptBtnToPage(pageObj.numbers, report);
        verifyExit(6, pageObj.numbers);

        commonPageFeatures.clickNextToPage(pageObj.oAuth, report);
        pageObj.oAuth.chooseAccountType(user, report);
        verifyExit(11, pageObj.usedProduct);


        commonPageFeatures.clickYesNoNext(user.productUsed, report);
        verifyExit(14, pageObj.orderForSelf);
        pageObj.usedProduct.clickCloseButton(report);
        commonPageFeatures.clickMoreInfoToModal(report);
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

        pageObj.obesity.enterHeightAndWeight(user, report);
        pageObj.obesity.clickNextToPage(pageObj.ddiCondition, report);
        verifyMoreInfoExit(126, pageObj.ddiCondition);

        pageObj.ddiCondition.selectCheckboxesAndProgress(pageObj.ddiCondition.allButNone, pageObj.ddiHepC,report);
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
        pageObj.ddiHighCholesterol.selectCheckboxesAndProgress(user.highCholMeds,
                pageObj.antifungal, report);
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

        commonPageFeatures.clickYesNoNext("Yes", report);
        pageObj.diagnosedDepression.verifyConfirmModalOpen(report);
        verifyExit(210, pageObj.diagnosedDepression);
        pageObj.diagnosedDepression.clickConfirm(report);
        commonPageFeatures.verifyModalDismissed(report);
        verifyExit(213, pageObj.knowBPNumber);

        pageObj.knowBPNumber.selectRadioReponse(user.knowBPType.label, report);
        pageObj.knowBPNumber.clickNext(report);
        pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
        verifyExit(216, pageObj.knowBPNumber);
        pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
        commonPageFeatures.verifyModalDismissed(report);
        verifyMoreInfoExit(219, pageObj.enterBP);

        pageObj.enterBP.enterBP(user, report);
        pageObj.enterBP.clickNextToPage(pageObj.review, report);
        verifyExit(226, pageObj.review);

        pageObj.review.clickConfirm(report);
        pageObj.review.verifyConfirmModalOpen(report);
        verifyExit(229,pageObj.review);
        pageObj.review.clickFinishToOauth(report);

        pageObj.oAuthPostReview.chooseAccountType(user, report);
        verifyExit(233, pageObj.adbu);

        pageObj.adbu.clickYesNoToOpenModal("Yes", report);
        pageObj.adbu.clickConfirmCheckbox(report);
        pageObj.adbu.clickConfirm(report);
        pageObj.purchaseOptions.verifyAtPage(report);
        verifyExit(237, pageObj.purchaseOptions);

        commonPageFeatures.load(UrlType.STUDY);
        sp.login.logIn(RoleType.CENTRAL_ASSESSOR.email, null);
        sp.participants.verifyAtPage();
        sp.participants.selectTab(AccountTabs.RECORDS, report);
        sp.downloadRecords.verifyAtPage();
        sp.downloadRecords.downloadParticipantRecords("records_Step239",report);

// Get Metrics Record Step 239

        commonPageFeatures.load(UrlType.DEXTER);
        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker();
        new DexterHFWrappers(driver).runDexterHFADBUNoBP(user, pageObj.adbubpNormal, report);
        verifyExit(241, pageObj.adbubpNormal);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);
        verifyExit(244, pageObj.enterBPEnd);

        pageObj.enterBPEnd.enterBP(user, report);
        commonPageFeatures.clickNext(report);
        commonPageFeatures.verifyModalDisplayed(null);
        pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
        commonPageFeatures.verifyModalDismissed(report);
        verifyExit(248, pageObj.purchaseOptions);

        commonPageFeatures.load(UrlType.STUDY);
        sp.login.logIn(RoleType.CENTRAL_ASSESSOR.email, null);
        sp.participants.verifyAtPage();
        sp.participants.selectTab(AccountTabs.RECORDS, report);
        sp.downloadRecords.verifyAtPage();
        sp.downloadRecords.downloadParticipantRecords("records_Step250",report);
    }
}
