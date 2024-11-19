package galen.tenant.dexter.HappyFlows;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HappyFlow_IA_Initial_Assessment_noBP_NonSmoker extends BaseTest {
    static String OBJECTIVE = "This Happy Path flow covers the use-case scenario where the user takes the Initial " +
            "Assessment and triggers Get BP. This is intended to provide a reference during testing for a user.";
    static String NOTES = "None";
    static String REQUIREMENTS = "None";
    static String REFERENCES = "None";
    DexterPageObj pageObj;
    DexterUser user;
    String reportName = "HappyFlow_IA_Initial_Assessment_noBP_NonSmoker";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;

    HappyFlow_IA_Initial_Assessment_noBP_NonSmoker() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void HappyFlow_IA_Initial_Assessment_noBP_NonSmoker_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "HappyFlow_IA_Initial_Assessment_noBP_NonSmoker";
        bh = new BasicHelpers(driver);
        pageObj = new DexterPageObj(driver);
        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker();
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        CommonPageFeatures commonPageFeatures = new CommonPageFeatures(driver);
        pageObj.pritUnl.load(UrlType.DEXTER);

        pageObj.welcomePage.verifyAtPage(report);
        report.addScreenshotStep("1_Welcome");

        pageObj.welcomePage.clickBegin(report);
        report.addScreenshotStep("2_Privacy");

        pageObj.privacyPage.clickIAcceptBtnToPage(pageObj.numbers, report);
        report.addScreenshotStep("3_Numbers");

        pageObj.numbers.clickNextToPage(pageObj.oAuth, report);
        report.addScreenshotStep("4_Sign In");

        pageObj.oAuth.clickGuestButton(report);
        pageObj.oAuth.verifyConfirmDisplayed(report);
        report.addScreenshotStep("5_Guest Confirmation Modal");

        pageObj.oAuth.clickConfirmModalToPage(pageObj.usedProduct,report);
        report.addScreenshotStep("6_HaveUsedProduct");

        pageObj.orderForSelf.clickYesNoNextToModal(user.productUsed, "Tooltip",report);
        pageObj.orderForSelf.clickConfirmModalToPage(pageObj.orderForSelf, report);
        report.addScreenshotStep("7_ConfirmCustomer");

        commonPageFeatures.clickYesNoNextToPage(user.orderForSelf, pageObj.pregnancy, report);
        report.addScreenshotStep("8_Prevent Pregnancy");

        commonPageFeatures.clickYesNoNextToPage(user.preventPregnancy, pageObj.menstrual, report);
        report.addScreenshotStep("9_Menstruation");

        commonPageFeatures.clickYesNoNextToPage(user.menstrualPeriod, pageObj.birthControl, report);
        report.addScreenshotStep("10_Birth Control");

        commonPageFeatures.clickYesNoNextToPage(user.birthControl, pageObj.smoking, report);
        report.addScreenshotStep("11_Smoking");

        pageObj.smoking.selectRadioResponseAndProgress(user.smokingType.label, pageObj.everHadCancer, report);
        report.addScreenshotStep("12_Ever Had Cancer");

        commonPageFeatures.clickYesNoNextToPage(user.everHadCancer, pageObj.bloodPressureMeds, report);
        report.addScreenshotStep("13_Blood Pressure Meds");

        commonPageFeatures.clickYesNoNextToPage(user.bloodPressureMeds, pageObj.cardiacRisk, report);
        report.addScreenshotStep("14_Heart Conditions");

        pageObj.cardiacRisk.selectCheckboxesAndProgress(user.chestPainType, pageObj.bloodClot, report);
        report.addScreenshotStep("15_Blood Clot");

        pageObj.bloodClot.selectCheckboxesAndProgress(user.bloodClot, pageObj.irregularHeartBeat, report);
        report.addScreenshotStep("16_Irregular Heartbeat");

        commonPageFeatures.clickYesNoNextToPage(user.irregularHeartBeat, pageObj.liverCancer, report);
        report.addScreenshotStep("17_Liver Cancer");

        pageObj.liverCancer.selectCheckboxesAndProgress(user.liverCancer, pageObj.vaginalBleeding, report);
        report.addScreenshotStep("18_Vaginal Bleeding");

        pageObj.vaginalBleeding.clickYesNoNextToPage(user.vaginalBleeding, pageObj.diabetes, report);
        report.addScreenshotStep("19_Diabetes");

        commonPageFeatures.clickYesNoNextToPage(user.diabetes, pageObj.pregnant, report);
        report.addScreenshotStep("20_Pregnant");

        commonPageFeatures.clickYesNoNextToPage(user.pregnant, pageObj.breastFeeding, report);
        report.addScreenshotStep("21_Breastfeeding");

        commonPageFeatures.clickYesNoNextToPage(user.breastfeeding, pageObj.pregnancyLoss, report);
        report.addScreenshotStep("22_Pregnancy Loss");

        commonPageFeatures.clickYesNoNextToPage(user.pregnancyLoss, pageObj.migraines, report);
        report.addScreenshotStep("23_Migraines");

        commonPageFeatures.clickYesNoNextToPage(user.migraines, pageObj.obesity, report);
        report.addScreenshotStep("24_Obesity");

        pageObj.obesity.enterHeightAndWeightAndProgress(user, pageObj.ddiCondition,report);
        report.addScreenshotStep("25_DDI Conditions");

        pageObj.ddiCondition.selectCheckboxesAndProgress(user.conditionType, pageObj.antifungal, report);
        report.addScreenshotStep("26_Antifungal");

        commonPageFeatures.clickYesNoNextToPage(user.isAntifungal, pageObj.otherMedication, report);
        report.addScreenshotStep("27_Other Medications");

        pageObj.otherMedication.selectCheckboxesAndProgress(user.otherMedicationType, pageObj.gallbladder, report);
        report.addScreenshotStep("28_Gall Bladder");

        commonPageFeatures.clickYesNoNextToPage(user.gallbladder, pageObj.depression, report);
        report.addScreenshotStep("29_Depression");

        commonPageFeatures.clickYesNoNextToPage(user.depression, pageObj.knowBPNumber, report);
        report.addScreenshotStep("30_KnowBP");

        pageObj.knowBPNumber.selectRadioResponseAndProgress(user.knowBPType.label,pageObj.review, report);
        report.addScreenshotStep("31_Editable Summary");

        pageObj.review.clickConfirmToOpenModal(report);
        report.addScreenshotStep("32_Confirm Modal");

        pageObj.review.clickFinishToOauth(report);
        report.addScreenshotStep("33_Sign In");

        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal, report);
        report.addScreenshotStep("34_Get BP Screen");
    }
}
