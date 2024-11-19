package galen.tenant.dexter.Navigation_All;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.*;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.BasePage;
import galen.pages.common.CheckboxPage;
import galen.pages.tenant.dexter.InitialAssessment.DDICondition;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;


public class VTP_DEX_FRD_267_Submit_Button_State extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    DexterPageObj pageObj;
    DexterUser user;
    String reportName = "VTP_DEX_FRD_267_Submit_Button_State";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_FRD_267_Submit_Button_State()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    void verifyCheckboxSteps(int stepNo, String option, CheckboxPage thisPage, BasePage nextPage) {
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step"+ stepNo+"_Next disabled");

        thisPage.selectCheckboxReponse(option, report);
        commonPageFeatures.verifyNextButtonEnabled(report);
        stepNo++;
        report.addScreenshotStep("Step"+stepNo+"_Next enabled");

        thisPage.selectCheckboxReponse(option, report);
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step"+ stepNo+"_Next disabled2");
        thisPage.selectCheckboxAndProgress(option, nextPage, report);
    }

    void verifyYesNoSteps(int stepNo, String yesNo, BasePage nextPage) {
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step"+ stepNo+"_Next disabled");
        commonPageFeatures.clickYesOrNo(yesNo, report);
        commonPageFeatures.verifyNextButtonEnabled(report);
        stepNo++;
        report.addScreenshotStep("Step"+stepNo+"_Next enabled");
        if (stepNo==85) {
            commonPageFeatures.clickNext(report);
            commonPageFeatures.clickConfirmModalToPage(nextPage,report);
        } else {
            commonPageFeatures.clickNextToPage(nextPage, report);
        }
    }

    void verifyConfirmCheckbox(int step, BasePage page) {
        page.verifyModalDisplayed(report);
        page.verifyConfirmButtonDisabled(report);
        report.addScreenshotStep("Step"+step);
        step++;
        page.clickConfirmCheckbox(report);
        page.verifyConfirmButtonEnabled(report);
        report.addScreenshotStep("Step"+step);
        step++;
        page.clickConfirmCheckbox(report);
        page.verifyConfirmButtonDisabled(report);
        report.addScreenshotStep("Step"+step);
        page.addressOpenModalConfirmations(report);
    }

    @Test
    public void VTP_DEX_FRD_267_Submit_Button_State_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_267 - Submit Button State";
        bh = new BasicHelpers(driver);
        pageObj = new DexterPageObj(driver);
        commonPageFeatures = new CommonPageFeatures(driver);
        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_Smoker();

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.usedProduct, report);
        verifyYesNoSteps(2, user.productUsed, pageObj.orderForSelf);

        pageObj.orderForSelf.clickCloseToDismiss(report);
        verifyYesNoSteps(5, user.orderForSelf, pageObj.pregnancy);
        verifyYesNoSteps(7, user.preventPregnancy, pageObj.menstrual);
        verifyYesNoSteps(9, user.menstrualPeriod, pageObj.birthControl);
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step11");

        commonPageFeatures.clickYesOrNo("Yes", report);
        commonPageFeatures.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step12");

        bh.verifyClickToNavDisplayed(commonPageFeatures.nextBTN, "Next", pageObj.birthControl.modal,
                "Birth Control modal", report);
        verifyConfirmCheckbox(13, pageObj.birthControl);

        pageObj.smoking.verifyAtPage(report);
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step16_Disabled");

        pageObj.smoking.selectRadioResponseAndProgress(SmokeType.RARELY_SMOKE.label, pageObj.birthYear, report);
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step17_Disabled");

        int currentYear = Year.now().getValue();
        user.dobYear = String.valueOf((currentYear - 20));
        pageObj.birthYear.fillOutBirthday(user, report);
        commonPageFeatures.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step18");

        user.dobYear = "";
        pageObj.birthYear.fillOutBirthday(user, report);
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step19");

        user.dobYear = String.valueOf((currentYear - 20));
        pageObj.birthYear.fillOutBirthdayAndProgress(user, pageObj.smokingDisclaimer, report);
        commonPageFeatures.clickNextToPage(pageObj.everHadCancer, report);
        report.addScreenshotStep("Step20_Next disabled");

        verifyYesNoSteps(21, "Yes", pageObj.cancerList);
        verifyCheckboxSteps(23, CancerType.Other_Cancer.label, pageObj.cancerList,
                pageObj.bloodPressureMeds);
        verifyYesNoSteps(26, user.bloodPressureMeds, pageObj.cardiacRisk);
        verifyCheckboxSteps(28, HeartConditionType.NONE_OF_THESE.label, pageObj.cardiacRisk,
                pageObj.bloodClot);
        verifyCheckboxSteps(31, HeartConditionType.NONE_OF_THESE.label, pageObj.bloodClot,
                pageObj.irregularHeartBeat);
        verifyYesNoSteps(34, "No", pageObj.liverCancer);
        verifyCheckboxSteps(36, LiverCancerType.NONE_OF_THESE.label, pageObj.liverCancer,
                pageObj.vaginalBleeding);
        verifyYesNoSteps(39, "No", pageObj.diabetes);
        verifyYesNoSteps(41, "No", pageObj.pregnant);
        verifyYesNoSteps(43, "No", pageObj.breastFeeding);
        verifyYesNoSteps(45, "No", pageObj.pregnancyLoss);
        verifyYesNoSteps(47, "No", pageObj.migraines);
        verifyYesNoSteps(49, "No", pageObj.obesity);

        // Obesity to DDI
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step51_Next disabled");

        pageObj.obesity.enterHeightAndWeight(user, report);
        commonPageFeatures.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step52_Next enabled");

        bh.clearTextField( bh.getWebElement(pageObj.obesity.inputWeight),report);
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step53_Next disabled");

        pageObj.obesity.enterHeightAndWeightAndProgress(user, pageObj.ddiCondition,report);
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step54_Next disabled");

        DDICondition ddi = pageObj.ddiCondition;
        ddi.selectCheckboxReponses(ddi.getAllButNone(), report);
        commonPageFeatures.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step55_Next enabled");

        ddi.selectCheckboxReponses(ddi.getAllButNone(), report);
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step56_Next disabled2");

        pageObj.ddiCondition.selectCheckboxesAndProgress(pageObj.ddiCondition.getAllButNone(), pageObj.ddiHepC,
                report);

        verifyCheckboxSteps(57, user.defaultNoneText, pageObj.ddiHepC, pageObj.ddiThyroid);
        verifyCheckboxSteps(60, DDIThyroidType.NO_THYROID_MEDS.label, pageObj.ddiThyroid, pageObj.ddiEpBipolar);
        verifyCheckboxSteps(63, user.defaultNoneText, pageObj.ddiEpBipolar, pageObj.ddihiv);
        verifyCheckboxSteps(66, user.defaultNoneText, pageObj.ddihiv, pageObj.ddiHighCholesterol);
        verifyCheckboxSteps(69, user.defaultNoneText, pageObj.ddiHighCholesterol, pageObj.antifungal);
        verifyYesNoSteps(72, "Yes", pageObj.antifungalMeds);
        verifyCheckboxSteps(74, user.defaultNoneText, pageObj.antifungalMeds, pageObj.otherMedication);
        verifyCheckboxSteps(77, user.defaultNoneText, pageObj.otherMedication, pageObj.gallbladder);
        verifyYesNoSteps(80, user.gallbladder, pageObj.depression);
        verifyYesNoSteps(82, "Yes", pageObj.diagnosedDepression);
        verifyYesNoSteps(84, "Yes", pageObj.knowBPNumber);
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step87");

        pageObj.knowBPNumber.selectRadioReponse(BloodPressureType.Yes_Know_BP.label, report);
        report.addScreenshotStep("Step88");

        pageObj.knowBPNumber.clickYesAndAddressModalToPage(pageObj.enterBP, "Yes", report);

        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step90");
        user.systolic = "110";
        user.diastolic = "70";
        pageObj.enterBP.enterBP(user, report);
        commonPageFeatures.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step91");

        user.systolic = "";
        user.diastolic = "";
        pageObj.enterBP.enterBP(user, report);
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step92");

        user.systolic = "110";
        user.diastolic = "70";
        pageObj.enterBP.enterBP(user, report);
        commonPageFeatures.verifyNextButtonEnabled(report);
        commonPageFeatures.clickNextToPage(pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbu, report);

        pageObj.adbu.clickYesNoToOpenModal("Yes", "ADBU modal",report);
        pageObj.adbu.verifyConfirmButtonDisabled(report);
        report.addScreenshotStep("Step97");

        pageObj.adbu.clickConfirmCheckbox(report);
        pageObj.adbu.verifyConfirmButtonEnabled(report);
        report.addScreenshotStep("Step98");

        pageObj.adbu.clickConfirmCheckbox(report);
        pageObj.adbu.verifyConfirmButtonDisabled(report);
        report.addScreenshotStep("Step99");

        commonPageFeatures.clickXButton(report);
        pageObj.adbu.verifyModalDismissed(null);
        pageObj.adbu.clickYesNoToOpenModal("No", "ADBU confirm modal", report);
        verifyConfirmCheckbox(100, pageObj.adbu);
    }
}
