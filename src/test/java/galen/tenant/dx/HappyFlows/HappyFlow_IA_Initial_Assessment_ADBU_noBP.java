package galen.tenant.dx.HappyFlows;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HappyFlow_IA_Initial_Assessment_ADBU_noBP extends BaseTest {
    static String OBJECTIVE = "This Happy Path flow covers the use-case scenario where the user takes the Initial Assessment " +
            "and triggers ADBU/BP. This is in- tended to provide a reference during testing for a user.";
    static String NOTES = "None";
    static String REQUIREMENTS = "None";
    static String REFERENCES = "None";
    DxPageObj pageObj;
    DxUser user;
    String reportName = "HappyFlow_IA_Initial_Assessment_ADBU_noBP";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;

    HappyFlow_IA_Initial_Assessment_ADBU_noBP() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void HappyFlow_IA_Initial_Assessment_ADBU_noBP_Test() throws IOException, InterruptedException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "HappyFlow_IA_Initial_Assessment_ADBU_noBP";
        bh = new BasicHelpers(driver);
        user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_noBP();
        pageObj = new DxPageObj(driver);
        CommonPageFeatures commonPageFeatures = new CommonPageFeatures(driver);
        pageObj.pritUnl.load(URLType.DX);

        pageObj.welcomePage.verifyAtPage(report);
        report.addScreenshotStep("Welcome");

        pageObj.welcomePage.clickBegin(report);
        report.addScreenshotStep("Privacy");

        pageObj.privacyPage.clickIAcceptBtnToPage(pageObj.numbers, report);
        report.addScreenshotStep("Numbers");

        pageObj.numbers.clickNextToPage(pageObj.oAuth, report);
        report.addScreenshotStep("Sign In");

        pageObj.oAuth.chooseAccountTypeAndProgress(user, pageObj.usedProduct,report);
        report.addScreenshotStep("HaveUsedProduct");

        pageObj.orderForSelf.clickYesNoNextToModal(user.productUsed, "Tooltip",report);
        pageObj.orderForSelf.clickConfirmModalToPage(pageObj.orderForSelf, report);
        report.addScreenshotStep("ConfirmCustomer");

        commonPageFeatures.clickYesNoNextToPage(user.orderForSelf, pageObj.pregnancy, report);
        report.addScreenshotStep("Prevent Pregnancy");

        commonPageFeatures.clickYesNoNextToPage(user.preventPregnancy, pageObj.menstrual, report);
        report.addScreenshotStep("Menstruation");

        commonPageFeatures.clickYesNoNextToPage(user.menstrualPeriod, pageObj.birthControl, report);
        report.addScreenshotStep("Birth Control");

        commonPageFeatures.clickYesNoNextToPage(user.birthControl, pageObj.smoking, report);
        report.addScreenshotStep("Smoking");

        pageObj.smoking.selectRadioResponseAndProgress(user.smokingType.label, pageObj.everHadCancer, report);
        report.addScreenshotStep("Ever Had Cancer");

        commonPageFeatures.clickYesNoNextToPage(user.everHadCancer, pageObj.cancerList, report);
        report.addScreenshotStep("Cancer List");

        pageObj.cancerList.selectCheckboxesAndProgress(user.cancerList, pageObj.bloodPressureMeds, report);
        report.addScreenshotStep("Blood Pressure Medicine");

        pageObj.bloodPressureMeds.clickYesNoNextToPage(user.bloodPressureMeds, pageObj.cardiacRisk, report);
        report.addScreenshotStep("Heart Conditions");

        pageObj.cardiacRisk.selectCheckboxesAndProgress(user.chestPainType, pageObj.bloodClot, report);
        report.addScreenshotStep("Blood Clot");

        pageObj.bloodClot.selectCheckboxesAndProgress(user.bloodClot, pageObj.irregularHeartBeat, report);
        report.addScreenshotStep("Irregular Heartbeat");

        commonPageFeatures.clickYesNoNextToPage(user.irregularHeartBeat, pageObj.liverCancer, report);
        report.addScreenshotStep("Liver Cancer");

        pageObj.liverCancer.selectCheckboxesAndProgress(user.liverCancer, pageObj.vaginalBleeding, report);
        report.addScreenshotStep("Vaginal Bleeding");

        pageObj.vaginalBleeding.clickYesNoNextToPage(user.vaginalBleeding, pageObj.diabetes, report);
        report.addScreenshotStep("Diabetes");

        commonPageFeatures.clickYesNoNextToPage(user.diabetes, pageObj.pregnant, report);
        report.addScreenshotStep("Pregnant");

        commonPageFeatures.clickYesNoNextToPage(user.pregnant, pageObj.breastFeeding, report);
        report.addScreenshotStep("Breastfeeding");

        commonPageFeatures.clickYesNoNextToPage(user.breastfeeding, pageObj.pregnancyLoss, report);
        report.addScreenshotStep("Pregnancy Loss");

        commonPageFeatures.clickYesNoNextToPage(user.pregnancyLoss, pageObj.migraines, report);
        report.addScreenshotStep("Migraines");

        commonPageFeatures.clickYesNoNextToPage(user.migraines, pageObj.obesity, report);
        report.addScreenshotStep("Obesity");

        pageObj.obesity.enterHeightAndWeightAndProgress(user, pageObj.ddiCondition, report);
        report.addScreenshotStep("DDI Conditions");

        pageObj.ddiCondition.selectCheckboxesAndProgress(user.conditionType, pageObj.ddiHepC, report);
        report.addScreenshotStep("Hepatitis C Meds");

        pageObj.ddiHepC.selectCheckboxesAndProgress(user.hepCMeds, pageObj.ddiThyroid, report);
        report.addScreenshotStep("Thyroid Disease Meds");

        pageObj.ddiThyroid.selectCheckboxesAndProgress(user.thyroidMeds, pageObj.ddiEpBipolar, report);
        report.addScreenshotStep("Epilepsy or Bipolar Disorder Meds");

        pageObj.ddiEpBipolar.selectCheckboxesAndProgress(user.epBipolarMeds, pageObj.ddihiv, report);
        report.addScreenshotStep("HIV Meds");

        pageObj.ddihiv.selectCheckboxesAndProgress(user.hivMeds, pageObj.ddiHighCholesterol, report);
        report.addScreenshotStep("High Cholesterol");

        pageObj.ddiHighCholesterol.selectCheckboxesAndProgress(user.highCholMeds, pageObj.antifungal, report);
        report.addScreenshotStep("Antifungal");

        commonPageFeatures.clickYesNoNextToPage(user.isAntifungal, pageObj.otherMedication, report);
        report.addScreenshotStep("Other Medications");

        pageObj.otherMedication.selectCheckboxesAndProgress(user.otherMedicationType, pageObj.gallbladder, report);
        report.addScreenshotStep("Gall Bladder");

        commonPageFeatures.clickYesNoNextToPage(user.gallbladder, pageObj.depression, report);
        report.addScreenshotStep("Depression");

        commonPageFeatures.clickYesNoNextToPage(user.depression, pageObj.knowBPNumber, report);
        report.addScreenshotStep("KnowBP");

        pageObj.knowBPNumber.selectRadioResponseAndProgress(user.knowBPType.label,pageObj.review, report);
        report.addScreenshotStep("Editable Summary");

        pageObj.review.clickConfirmToOpenModal(report);
        report.addScreenshotStep("Confirm Modal");

        pageObj.review.clickFinishToOauth(report);
        report.addScreenshotStep("Sign In");

        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpScreen, report);
        report.addScreenshotStep("ADBU BP Screen");

        pageObj.adbubpScreen.addressConfirmationsAndProgress("Yes", pageObj.adbubpNormal, report);
        report.addScreenshotStep("Get BP Screen");
    }
}