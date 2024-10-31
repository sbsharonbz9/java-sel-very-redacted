package galen.tenant.dexter.Navigation_All;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.*;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterNavigations;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.BasePage;
import galen.pages.common.CheckboxPage;
import galen.pages.tenant.dexter.InitialAssessment.DDICondition;
import galen.pages.tenant.dexter.InitialAssessment.DDIThyroid;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_FRD_267_Submit_Button_State extends BaseTest {
    static String OBJECTIVE = "To verify the application button to submit shall only become active once the user has " +
            "input a response (i.e. input field, checkbox, drop down, or radio button), on the current assessment screen" +
            " or modal. ";
    static String NOTES = "This protocol contains verification of the following scenario(s):\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Prior Use Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Confirm Customer Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Prevent Pregnancy Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Menstruation Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Hormonal BC (A) Screen\n" +
            "-\tButton to submit is disabled while in checkbox unselected state on the Hormonal BC Risk Modal\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Smoking and Vape Screen\n" +
            "-\tButton to submit is disabled while in Input field empty state on the Smoking and Vape (Date of Birth) Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Ever Had Cancer Screen\n" +
            "-\tButton to submit is disabled while in Checkboxes unselected state on the List of Cancers Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Blood Pressure Medicine Screen\n" +
            "-\tButton to submit is disabled while in Checkboxes unselected state on the Blood Clot Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Irregular Heartbeat Screen\n" +
            "-\tButton to submit is disabled while in Checkboxes unselected state on the Liver Disease/Liver Cancer Screen\n" +
            "-\tButton to submit is disabled while in radio buttons default unselected state on the Unexplained Vaginal Bleeding Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Diabetes Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Pregnant Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Breastfeeding Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Pregnancy Loss Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Migraines Screen\n" +
            "-\tButton to submit is disabled while in Input fields empty state on the Obesity/BMI Screen\n" +
            "-\tButton to submit is disabled while in Checkboxes unselected state on the DDI/Conditions Screen\n" +
            "-\tButton to submit is disabled while in Checkboxes unselected state on the Hep C Meds Screen\n" +
            "-\tButton to submit is disabled while in Checkboxes unselected state on the Thyroid Meds Screen\n" +
            "-\tButton to submit is disabled while in Checkboxes unselected state on the Epilepsy or Bipolar Disorder Meds Screen\n" +
            "-\tButton to submit is disabled while in Checkboxes unselected state on the HIV Meds Screen\n" +
            "-\tButton to submit is disabled while in Checkboxes unselected state on the Cholesterol Meds Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Antifungal Product Screen\n" +
            "-\tButton to submit is disabled while in Checkboxes unselected state on the Antifungal Meds Screen\n" +
            "-\tButton to submit is disabled while in Checkboxes unselected state on the Other Meds Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Gallbladder Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Depression Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Clinical Depression Screen\n" +
            "-\tButton to submit is disabled while in radio buttons unselected state on the Know Numbers Screen\n" +
            "-\tButton to submit is disabled while in Input fields empty state on the Enter BP Screen\n" +
            "-\tButton to submit is disabled while in checkbox unselected state on the Doctor Approved Confirmation Modal\n" +
            "-\tButton to submit is disabled while in checkbox unselected state on the Doctor Not Approved Confirmation Modal";
    static String REQUIREMENTS = "FRD_267";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    DexterPageObj pageObj;
    DexterUser user;
    String reportName = "VTP_DEX_FRD_267_Submit_Button_State";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_FRD_267_Submit_Button_State()  {
        VERSIONHISTORY.add("1.0;03JUL2024;Initial Test Script;Gulzira Nurseilova");
        VERSIONHISTORY.add("2.0;31JUL2024;Per CADENCE-631: Update Test Steps to align with correct assessment flow;James" +
                " Reale");
    }

    boolean verifyCheckboxSteps(int stepNo, String option, CheckboxPage thisPage, BasePage nextPage) {
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step"+ stepNo+"_Next disabled");

        thisPage.selectCheckboxReponse(option, report);
        commonPageFeatures.verifyNextButtonEnabled(report);
        stepNo++;
        report.addScreenshotStep("Step"+stepNo+"_Next enabled");

        thisPage.selectCheckboxReponse(option, report);
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step"+ stepNo+"_Next disabled2");
        return thisPage.selectCheckboxAndProgress(option,nextPage, report);
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
            commonPageFeatures.clickConfirm(report);
        } else {
            commonPageFeatures.clickNextToPage(nextPage, report);
        }
    }

    void verifyConfirmCheckbox(int step, BasePage page) {
        page.verifyModalDisplayed(report);
        bh.verifyButtonEnabled(bh.getWebElement(page.btnConfirmModal), false, report);
        report.addScreenshotStep("Step"+step);
        step++;
        page.clickConfirmCheckbox(report);
        bh.verifyButtonEnabled(bh.getWebElement(page.btnConfirmModal), true, report);
        report.addScreenshotStep("Step"+step);
        step++;
        page.clickConfirmCheckbox(report);
        bh.verifyButtonEnabled(bh.getWebElement(page.btnConfirmModal), false, report);
        report.addScreenshotStep("Step"+step);
        page.clickConfirmCheckbox(report);
        page.clickConfirm(report);
    }

    @Test
    public void VTP_DEX_FRD_267_Submit_Button_State_Test() throws IOException, InterruptedException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_267 - Submit Button State";
        bh = new BasicHelpers(driver);
        pageObj = new DexterPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        commonPageFeatures = new CommonPageFeatures(driver);
        sleep(1000);
        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_Smoker();

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.usedProduct, report);
        verifyYesNoSteps(2, user.productUsed, pageObj.orderForSelf);

        pageObj.usedProduct.clickCloseButton(report);
        pageObj.orderForSelf.verifyAtPage(report);
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
        bh.clearTextField(bh.getWebElement(pageObj.obesity.inputWeight),report);
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step53_Next disabled");
        pageObj.obesity.enterHeightAndWeight(user, report);
        pageObj.obesity.clickNextToPage(pageObj.ddiCondition, report);
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step54_Next disabled");

        DDICondition ddi = pageObj.ddiCondition;
        ddi.selectCheckboxReponses(ddi.allButNone, report);
        commonPageFeatures.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step55_Next enabled");
        ddi.selectCheckboxReponses(ddi.allButNone, report);
        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step56_Next disabled2");
        pageObj.ddiCondition.selectCheckboxesAndProgress(pageObj.ddiCondition.allButNone, pageObj.ddiHepC,
                report);

        verifyCheckboxSteps(57, DDIHepCType.NONE_OF_THESE.label, pageObj.ddiHepC, pageObj.ddiThyroid);
        verifyCheckboxSteps(60, DDIThyroidType.NO_THYROID_MEDS.label, pageObj.ddiThyroid, pageObj.ddiEpBipolar);
        verifyCheckboxSteps(63, DDIEpBipolarType.NONE_OF_THESE.label, pageObj.ddiEpBipolar, pageObj.ddihiv);
        verifyCheckboxSteps(66, DDIHIVType.NONE_OF_THESE.label, pageObj.ddihiv, pageObj.ddiHighCholesterol);
        verifyCheckboxSteps(69, DDIHighCholType.NONE_OF_THESE.label, pageObj.ddiHighCholesterol, pageObj.antifungal);
        verifyYesNoSteps(72, "Yes", pageObj.antifungalMeds);
        verifyCheckboxSteps(74, AntifungalMedsType.NONE_OF_THESE.label, pageObj.antifungalMeds, pageObj.otherMedication);
        verifyCheckboxSteps(77, DDIOtherMedsType.NONE_OF_THESE.label, pageObj.otherMedication, pageObj.gallbladder);
        verifyYesNoSteps(80, user.gallbladder, pageObj.depression);
        verifyYesNoSteps(82, "Yes", pageObj.diagnosedDepression);
        verifyYesNoSteps(84, "Yes", pageObj.knowBPNumber);

        commonPageFeatures.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step87");
        pageObj.knowBPNumber.selectRadioReponse(BloodPressureType.Yes_Know_BP.label, report);
        report.addScreenshotStep("Step88");
        pageObj.knowBPNumber.clickNext(report);
        pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
        pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
        commonPageFeatures.verifyModalDismissed(report);
        pageObj.enterBP.verifyAtPage(report);

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
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpScreen, report);

        pageObj.adbu.clickYesNoToOpenModal("Yes", "ADBU modal",report);
        bh.verifyButtonEnabled(bh.getWebElement(pageObj.adbu.confirmButton), false, report);
        report.addScreenshotStep("Step97");

        pageObj.adbu.clickConfirmCheckbox(report);
        bh.verifyButtonEnabled(bh.getWebElement(pageObj.adbu.confirmButton), true, report);

        report.addScreenshotStep("Step98");
        pageObj.adbu.clickConfirmCheckbox(report);
        bh.verifyButtonEnabled(bh.getWebElement(pageObj.adbu.confirmButton), false, report);
        report.addScreenshotStep("Step99");
        bh.clickFlex(pageObj.adbu.btnXModal, "Close modal", report);
        pageObj.adbu.verifyModalDismissed(null);
        pageObj.adbu.clickYesNoToOpenModal("No", "ADBU confirm modal", report);

        verifyConfirmCheckbox(100, pageObj.adbu);
    }
}
