package galen.tenant.dx.Navigation_All;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.SmokeType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_FRD_010_Radio_Button_Default_Unselected extends BaseTest {
    static String OBJECTIVE = "To verify the application shall display all user input controls (i.e. input fields, " +
            "radio buttons, checkboxes, etc) as unpopulated, when screens are initially loaded.";
    static String NOTES = "This protocol contains verification of the following scenario(s):\n" +
            "-\tRadio button default unselected state on the Prior Use Screen\n" +
            "-\tRadio button default unselected state on the Confirm Customer Screen\n" +
            "-\tRadio button default unselected state on the Prevent Pregnancy Screen\n" +
            "-\tRadio button default unselected state on the Menstruation Screen\n" +
            "-\tRadio button default unselected state on the Hormonal BC (A) Screen\n" +
            "-\tRadio button default unselected state on the Smoking and Vape Screen\n" +
            "-\tInput field default empty state on the Smoking and Vape (Date of Birth) Screen\n" +
            "-\tRadio button default unselected state on the Ever Had Cancer Screen\n" +
            "-\tCheckboxes default unselected state on the List of Cancers Screen\n" +
            "-\tRadio button default unselected state on the Blood Pressure Medicine Screen\n" +
            "-\tCheckboxes default unselected state on the Blood Clot Screen\n" +
            "-\tRadio button default unselected state on the Irregular Heartbeat Screen\n" +
            "-\tCheckboxes default unselected state on the Liver Disease Screen\n" +
            "-\tRadio button default unselected state on the Unexplained Vaginal Bleeding Screen\n" +
            "-\tRadio button default unselected state on the Diabetes Screen\n" +
            "-\tRadio button default unselected state on the Pregnant Screen\n" +
            "-\tRadio button default unselected state on the Breastfeeding Screen\n" +
            "-\tRadio button default unselected state on the Pregnancy Loss Screen\n" +
            "-\tRadio button default unselected state on the Migraines with Aura Screen\n" +
            "-\tInput fields default empty state on the Obesity/BMI Screen\n" +
            "-\tCheckboxes default unselected state on the DDI/Conditions Screen\n" +
            "-\tCheckboxes default unselected state on the Hep C Meds Screen\n" +
            "-\tCheckboxes default unselected state on the Thyroid Meds Screen\n" +
            "-\tCheckboxes default unselected state on the Epilepsy or Bipolar Disorder Meds Screen\n" +
            "-\tCheckboxes default unselected state on the HIV Meds Screen\n" +
            "-\tCheckboxes default unselected state on the Cholesterol Meds Screen\n" +
            "-\tRadio button default unselected state on the Antifungal Product Screen\n" +
            "-\tCheckboxes default unselected state on the Antifungal Meds Screen\n" +
            "-\tCheckboxes default unselected state on the Other Meds Screen\n" +
            "-\tRadio button default unselected state on the Gallbladder Screen\n" +
            "-\tRadio button default unselected state on the Depression Screen\n" +
            "-\tRadio button default unselected state on the Clinical Depression Screen \n" +
            "-\tRadio button default unselected state on the Know Numbers Screen\n" +
            "-\tInput fields default empty state on the Enter BP Screen";
    static String REQUIREMENTS = "DEX_FRD_010";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx \n" +
            "HappyFlow_IA_Initial_Assessment_ADBU_noBP_Google.docx";
    String reportName = "VTP_DEX_FRD_010_Radio_Button_Default_Unselected";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    BasicHelpers bh;

    VTP_DEX_FRD_010_Radio_Button_Default_Unselected()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_010_Radio_Button_Default_Unselected_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY,
                PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_010 – Radio Buttons Default State of Unselected";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);
        bh = new BasicHelpers(driver);

        pageObj.pritUnlauthenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.usedProduct, report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("Step2_Prior Use");

        common.clickYesNoNextToPage("Yes", pageObj.orderForSelf,report);
        pageObj.orderForSelf.clickCloseToDismiss(report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("Step3_ConfirmCustomer");

        common.clickYesNoNextToPage(user.orderForSelf,pageObj.pregnancy, report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("Step4_Prevent Pregnancy");

        common.clickYesNoNextToPage(user.preventPregnancy,pageObj.menstrual, report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("5_Menstruation");

        common.clickYesNoNextToPage(user.menstrualPeriod,pageObj.birthControl, report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("6_Birth Control");

        common.clickYesNoNextToPage(user.birthControl,pageObj.smoking, report);
        pageObj.smoking.verifyNoOptionsSelected(report);
        report.addScreenshotStep("7_Smoking");

        pageObj.smoking.selectRadioResponseAndProgress(SmokeType.RARELY_SMOKE.label, pageObj.birthYear,report);
        bh.verifyText(pageObj.birthYear.birthYearField, "Birth Year field", "", report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("8_YOB");

        pageObj.birthYear.fillOutBirthdayAndProgress(user, pageObj.smokingDisclaimer, report);
        common.clickNextToPage(pageObj.everHadCancer, report);
        report.addScreenshotStep("10_Ever Had Cancer");

        common.clickYesNoNextToPage("Yes", pageObj.cancerList,report);
        pageObj.cancerList.verifyNoOptionsSelected(report);
        report.addScreenshotStep("Step11_List of Cancers");

        pageObj.cancerList.selectCheckboxesAndProgress(user.cancerList, pageObj.bloodPressureMeds,report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("12_Blood Pressure Meds");

        common.clickYesNoNextToPage(user.bloodPressureMeds,pageObj.cardiacRisk, report);
        pageObj.cardiacRisk.verifyNoOptionsSelected(report);
        report.addScreenshotStep("13_Cardiac");

        pageObj.cardiacRisk.selectCheckboxesAndProgress(user.chestPainType, pageObj.bloodClot, report);
        pageObj.bloodClot.verifyNoOptionsSelected(report);
        report.addScreenshotStep("14_Blood Clot");

        pageObj.bloodClot.selectCheckboxesAndProgress(user.bloodClot, pageObj.irregularHeartBeat, report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("15_Irregular Heartbeat");

        common.clickYesNoNextToPage(user.irregularHeartBeat, pageObj.liverCancer, report);
        pageObj.liverCancer.verifyNoOptionsSelected(report);
        report.addScreenshotStep("16_Liver Cancer");

        pageObj.liverCancer.selectCheckboxesAndProgress(user.liverCancer, pageObj.vaginalBleeding, report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("17_Vaginal Bleeding");

        common.clickYesNoNextToPage(user.vaginalBleeding,pageObj.diabetes, report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("18_Diabetes");

        common.clickYesNoNextToPage(user.diabetes, pageObj.pregnant, report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("19_Pregnant");

        common.clickYesNoNextToPage(user.pregnant,pageObj.breastFeeding, report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("20_Breastfeeding");

        common.clickYesNoNextToPage(user.breastfeeding,pageObj.pregnancyLoss, report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("21_Pregnancy Loss");

        common.clickYesNoNextToPage(user.pregnancyLoss, pageObj.migraines, report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("22_Migraines");

        common.clickYesNoNextToPage(user.migraines, pageObj.obesity,report);
        bh.verifyText(pageObj.obesity.inputWeight, "Obesity weight field", "", report);
        report.addScreenshotStep("23_Obesity");

        pageObj.obesity.enterHeightAndWeight(user, report);
        pageObj.obesity.clickNextToPage(pageObj.ddiCondition, report);
        pageObj.ddiCondition.verifyNoOptionsSelected(report);
        report.addScreenshotStep("24_DDI Conditions");

        pageObj.ddiCondition.selectCheckboxesAndProgress(pageObj.ddiCondition.getAllButNone(), pageObj.ddiHepC, report);
        pageObj.ddiHepC.verifyNoOptionsSelected(report);
        report.addScreenshotStep("25_HepC");

        pageObj.ddiHepC.selectCheckboxesAndProgress(user.hepCMeds, pageObj.ddiThyroid, report);
        pageObj.ddiThyroid.verifyNoOptionsSelected(report);
        report.addScreenshotStep("26_Thyroid");

        pageObj.ddiThyroid.selectCheckboxesAndProgress(user.thyroidMeds, pageObj.ddiEpBipolar, report);
        pageObj.ddiEpBipolar.verifyNoOptionsSelected(report);
        report.addScreenshotStep("27_Ep");

        pageObj.ddiEpBipolar.selectCheckboxesAndProgress(user.epBipolarMeds, pageObj.ddihiv, report);
        pageObj.ddihiv.verifyNoOptionsSelected(report);
        report.addScreenshotStep("28_HIV");

        pageObj.ddihiv.selectCheckboxesAndProgress(user.hivMeds, pageObj.ddiHighCholesterol, report);
        pageObj.ddiHighCholesterol.verifyNoOptionsSelected(report);
        report.addScreenshotStep("29_HC");

        pageObj.ddiHighCholesterol.selectCheckboxesAndProgress(user.highCholMeds, pageObj.antifungal, report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("30_Antifungal");

        common.clickYesNoNextToPage("Yes", pageObj.antifungalMeds, report);
        pageObj.antifungalMeds.verifyNoOptionsSelected(report);
        report.addScreenshotStep("31_AntifungalMeds");

        pageObj.antifungalMeds.selectCheckboxesAndProgress(user.antiFungalMeds, pageObj.otherMedication, report);
        pageObj.otherMedication.verifyNoOptionsSelected(report);
        report.addScreenshotStep("32_Other Medications");

        pageObj.otherMedication.selectCheckboxesAndProgress(user.otherMedicationType, pageObj.gallbladder, report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("33_Gallbladder");

        common.clickYesNoNextToPage(user.gallbladder, pageObj.depression,report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("34_Depression");

        common.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression,report);
        common.verifyYesNoUnselected(report);
        report.addScreenshotStep("35_Clinical_Depression");

        common.clickYesNoNextToPage(user.diagnosedDepression, pageObj.knowBPNumber,report);
        pageObj.knowBPNumber.verifyNoOptionsSelected(report);
        report.addScreenshotStep("36_KnowBP");

        pageObj.knowBPNumber.selectRadioReponse(user.knowBPType.label, report);
        pageObj.knowBPNumber.clickNext(report);
        pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
        pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
        pageObj.enterBP.verifyAtPage(report);
        bh.verifyText(pageObj.enterBP.inputSystolic, "Systolic field", "", report);
        bh.verifyText(pageObj.enterBP.inputDiastolic, "Diastolic field", "", report);
        report.addScreenshotStep("38_Enter BP");

    }
}
