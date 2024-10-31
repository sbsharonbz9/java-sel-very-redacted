package galen.tenant.dexter.OAuth;

import galen.base.BaseTest;
import galen.enums.tenant.dexter.BloodPressureType;
import galen.enums.tenant.dexter.DDIThyroidType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_ADBU_Guest extends BaseTest {
    static String OBJECTIVE = "To verify on the Login Selection screen, if the user selects to continue as guest the application shall navigate to:\n" +
            "-\tADBU screen if they triggered an ADBU and entered BP\n" +
            "-\tADBU/BP screen if they triggered and ADBU and did not enter BP\n" +
            "-\tBP Screen if they have no ADBU and did not enter BP\n";

    static String NOTES = "This protocol verifies the following scenario(s):\n" +
            "-\tGuest user entered BP and triggered ADBU for Obesity navigated from Log In Selection (Post Assessment) Screen to ADBU Screen\n" +
            "-\tGuest user entered BP and triggered ADBU for Other Cancer navigated from Log In Selection (Post Assessment) Screen to ADBU Screen\n" +
            "-\tGuest user entered BP and triggered ADBU for Hepatitis C Meds navigated from Log In Selection (Post Assessment) Screen to ADBU Screen\n" +
            "-\tGuest user entered BP and triggered ADBU for Thyroid Disease Meds navigated from Log In Selection (Post Assessment) Screen to ADBU Screen\n" +
            "-\tGuest user entered BP and triggered ADBU for Epilepsy Meds navigated from Log In Selection (Post Assessment) Screen to ADBU Screen\n" +
            "-\tGuest user entered BP and triggered ADBU for HIV Meds navigated from Log In Selection (Post Assessment) Screen to ADBU Screen\n" +
            "-\tGuest user entered BP and triggered ADBU for High Cholesterol Meds navigated from Log In Selection (Post Assessment) Screen to ADBU Screen\n" +
            "-\tGuest user entered BP and triggered ADBU for Antifungal Meds navigated from Log In Selection (Post Assessment) Screen to ADBU Screen\n" +
            "-\tGuest user entered BP and triggered ADBU for Other Medicine navigated from Log In Selection (Post Assessment) Screen to ADBU Screen \n" +
            "-\tGuest user entered BP and triggered ADBU for Gallbladder navigated from Log In Selection (Post Assessment) Screen to ADBU Screen\n" +
            "-\tGuest user entered BP and triggered ADBU for Clinical Depression navigated from Log In Selection (Post Assessment) Screen to ADBU Screen\n" +
            "-\tGuest user entered BP and triggered ADBU for Multiple Triggers (All of the above) navigated from Log In Selection (Post As-sessment) Screen to ADBU Screen\n";

    static String REQUIREMENTS = "DEX_FRD_237";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx;";
    String reportName = "VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_ADBU_Guest";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_ADBU_Guest() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Name Redacted");
    }

    @Test
    public void VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_ADBU_Guest_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "TP_DEX_FRD_237 – Log In Selection (Post Assessment) Navigates to ADBU (If Triggered) Guest";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.obesity, report);
        user.weight = "212";
        pageObj.obesity.enterHeightAndWeight(user, report);
        pageObj.obesity.clickNextToPage(pageObj.ddiCondition, report);

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.review, report);
        pageObj.review.clickConfirm(report);
        pageObj.review.verifyConfirmModalOpen(report);
        pageObj.review.clickFinishToOauth(report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.adbu.verifyAtPage(report);
        report.addScreenshotStep("Step5_ADBU_Screen ");

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.everHadCancer, report);
        pageObj.everHadCancer.clickYesNoNextToPage("Yes", pageObj.cancerList, report);
        pageObj.cancerList.selectCheckboxReponse("Other cancer", report);
        pageObj.cancerList.clickNextToPage(pageObj.bloodPressureMeds, report);

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.review, report);
        pageObj.review.clickConfirm(report);
        pageObj.review.verifyConfirmModalOpen(report);
        pageObj.review.clickFinishToOauth(report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.adbu.verifyAtPage(report);
        report.addScreenshotStep("Step10_ADBU_Screen ");

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        pageObj.ddiCondition.selectCheckboxReponse("Hepatitis C", report);
        pageObj.ddiCondition.clickNextToPage(pageObj.ddiHepC, report);

        pageObj.ddiHepC.selectCheckboxReponse("Ombitasvir", report);
        pageObj.ddiHepC.clickNextToPage(pageObj.antifungal, report);

        pageObj.antifungal.clickYesNoNextToPage("No", pageObj.otherMedication, report);
        pageObj.otherMedication.selectCheckboxesAndProgress(user.otherMedicationType, pageObj.gallbladder, report);
        pageObj.gallbladder.clickYesNoNextToPage(user.gallbladder, pageObj.depression, report);
        pageObj.depression.clickYesNoNextToPage("No", pageObj.knowBPNumber, report);

        pageObj.knowBPNumber.selectRadioReponse(user.knowBPType.label, report);
        pageObj.knowBPNumber.clickNext(report);
        if (user.knowBPType == BloodPressureType.Yes_Know_BP) {
            pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
            pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
            if (user.measuredIn3Months.equals("Yes")) {
                pageObj.enterBP.verifyAtPage(report);
                pageObj.enterBP.enterBP(user, report);
                pageObj.enterBP.clickNext(report);
            }
        }

        pageObj.review.verifyAtPage(report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.adbu.verifyAtPage(report);
        report.addScreenshotStep("Step16_ADBU_Ombitasvir_Screen");

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        pageObj.ddiCondition.selectCheckboxReponse("Thyroid disease", report);
        pageObj.ddiCondition.clickNextToPage(pageObj.ddiThyroid, report);

        pageObj.ddiThyroid.selectCheckboxReponse("Levothyroxine", report);
        pageObj.ddiThyroid.clickNextToPage(pageObj.antifungal, report);

        pageObj.antifungal.clickYesNoNextToPage("No", pageObj.otherMedication, report);
        pageObj.otherMedication.selectCheckboxesAndProgress(user.otherMedicationType, pageObj.gallbladder, report);
        pageObj.gallbladder.clickYesNoNextToPage(user.gallbladder, pageObj.depression, report);
        pageObj.depression.clickYesNoNextToPage("No", pageObj.knowBPNumber, report);

        pageObj.knowBPNumber.selectRadioReponse(user.knowBPType.label, report);
        pageObj.knowBPNumber.clickNext(report);
        if (user.knowBPType == BloodPressureType.Yes_Know_BP) {
            pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
            pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
            if (user.measuredIn3Months.equals("Yes")) {
                pageObj.enterBP.verifyAtPage(report);
                pageObj.enterBP.enterBP(user, report);
                pageObj.enterBP.clickNext(report);
            }
        }

        pageObj.review.verifyAtPage(report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.adbu.verifyAtPage(report);
        report.addScreenshotStep("Step22_ADBU_Levothyroxine_Screen");

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        pageObj.ddiCondition.selectCheckboxReponse("Epilepsy", report);
        pageObj.ddiCondition.clickNextToPage(pageObj.ddiEpBipolar, report);

        pageObj.ddiEpBipolar.selectCheckboxReponse("Barbiturates", report);
        pageObj.ddiEpBipolar.clickNextToPage(pageObj.antifungal, report);

        pageObj.antifungal.clickYesNoNextToPage("No", pageObj.otherMedication, report);
        pageObj.otherMedication.selectCheckboxesAndProgress(user.otherMedicationType, pageObj.gallbladder, report);
        pageObj.gallbladder.clickYesNoNextToPage(user.gallbladder, pageObj.depression, report);
        pageObj.depression.clickYesNoNextToPage("No", pageObj.knowBPNumber, report);

        pageObj.knowBPNumber.selectRadioReponse(user.knowBPType.label, report);
        pageObj.knowBPNumber.clickNext(report);
        if (user.knowBPType == BloodPressureType.Yes_Know_BP) {
            pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
            pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
            if (user.measuredIn3Months.equals("Yes")) {
                pageObj.enterBP.verifyAtPage(report);
                pageObj.enterBP.enterBP(user, report);
                pageObj.enterBP.clickNext(report);
            }
        }

        pageObj.review.verifyAtPage(report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.adbu.verifyAtPage(report);
        report.addScreenshotStep("Step28_ADBU_Barbiturates_Screen");

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        pageObj.ddiCondition.selectCheckboxReponse("HIV", report);
        pageObj.ddiCondition.clickNextToPage(pageObj.ddihiv, report);

        pageObj.ddihiv.selectCheckboxReponse("Fosamprenavir", report);
        pageObj.ddihiv.clickNextToPage(pageObj.antifungal, report);

        pageObj.antifungal.clickYesNoNextToPage("No", pageObj.otherMedication, report);
        pageObj.otherMedication.selectCheckboxesAndProgress(user.otherMedicationType, pageObj.gallbladder, report);
        pageObj.gallbladder.clickYesNoNextToPage(user.gallbladder, pageObj.depression, report);
        pageObj.depression.clickYesNoNextToPage("No", pageObj.knowBPNumber, report);

        pageObj.knowBPNumber.selectRadioReponse(user.knowBPType.label, report);
        pageObj.knowBPNumber.clickNext(report);
        if (user.knowBPType == BloodPressureType.Yes_Know_BP) {
            pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
            pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
            if (user.measuredIn3Months.equals("Yes")) {
                pageObj.enterBP.verifyAtPage(report);
                pageObj.enterBP.enterBP(user, report);
                pageObj.enterBP.clickNext(report);
            }
        }

        pageObj.review.verifyAtPage(report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.adbu.verifyAtPage(report);
        report.addScreenshotStep("Step34_ADBU_Fosamprenavir_Screen");

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        pageObj.ddiCondition.selectCheckboxReponse("High cholesterol", report);
        pageObj.ddiCondition.clickNextToPage(pageObj.ddiHighCholesterol, report);

        pageObj.ddiHighCholesterol.selectCheckboxReponse("Atorvastatin", report);
        pageObj.ddiHighCholesterol.clickNextToPage(pageObj.antifungal, report);

        pageObj.antifungal.clickYesNoNextToPage("No", pageObj.otherMedication, report);
        pageObj.otherMedication.selectCheckboxesAndProgress(user.otherMedicationType, pageObj.gallbladder, report);
        pageObj.gallbladder.clickYesNoNextToPage(user.gallbladder, pageObj.depression, report);
        pageObj.depression.clickYesNoNextToPage("No", pageObj.knowBPNumber, report);

        pageObj.knowBPNumber.selectRadioReponse(user.knowBPType.label, report);
        pageObj.knowBPNumber.clickNext(report);
        if (user.knowBPType == BloodPressureType.Yes_Know_BP) {
            pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
            pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
            if (user.measuredIn3Months.equals("Yes")) {
                pageObj.enterBP.verifyAtPage(report);
                pageObj.enterBP.enterBP(user, report);
                pageObj.enterBP.clickNext(report);
            }
        }

        pageObj.review.verifyAtPage(report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.adbu.verifyAtPage(report);
        report.addScreenshotStep("Step40_ADBU_Atorvastatin_Screen");

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.antifungal, report);
        pageObj.antifungal.clickYesNoNextToPage("Yes", pageObj.antifungalMeds, report);

        pageObj.antifungalMeds.selectCheckboxReponse("Fluconazole", report);
        pageObj.antifungalMeds.clickNextToPage(pageObj.otherMedication, report);

        pageObj.otherMedication.selectCheckboxesAndProgress(user.otherMedicationType, pageObj.gallbladder, report);
        pageObj.gallbladder.clickYesNoNextToPage(user.gallbladder, pageObj.depression, report);
        pageObj.depression.clickYesNoNextToPage("No", pageObj.knowBPNumber, report);

        pageObj.knowBPNumber.selectRadioReponse(user.knowBPType.label, report);
        pageObj.knowBPNumber.clickNext(report);
        if (user.knowBPType == BloodPressureType.Yes_Know_BP) {
            pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
            pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
            if (user.measuredIn3Months.equals("Yes")) {
                pageObj.enterBP.verifyAtPage(report);
                pageObj.enterBP.enterBP(user, report);
                pageObj.enterBP.clickNext(report);
            }
        }

        pageObj.review.verifyAtPage(report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.adbu.verifyAtPage(report);
        report.addScreenshotStep("Step45_ADBU_Fluconazole_Screen");

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.antifungal, report);
        pageObj.antifungal.clickYesNoNextToPage("Yes", pageObj.antifungalMeds, report);

        pageObj.antifungalMeds.selectCheckboxReponse("Griseofulvin", report);
        pageObj.antifungalMeds.clickNextToPage(pageObj.otherMedication, report);

        pageObj.otherMedication.selectCheckboxesAndProgress(user.otherMedicationType, pageObj.gallbladder, report);
        pageObj.gallbladder.clickYesNoNextToPage(user.gallbladder, pageObj.depression, report);
        pageObj.depression.clickYesNoNextToPage("No", pageObj.knowBPNumber, report);

        pageObj.knowBPNumber.selectRadioReponse(user.knowBPType.label, report);
        pageObj.knowBPNumber.clickNext(report);
        if (user.knowBPType == BloodPressureType.Yes_Know_BP) {
            pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
            pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
            if (user.measuredIn3Months.equals("Yes")) {
                pageObj.enterBP.verifyAtPage(report);
                pageObj.enterBP.enterBP(user, report);
                pageObj.enterBP.clickNext(report);
            }
        }

        pageObj.review.verifyAtPage(report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.adbu.verifyAtPage(report);
        report.addScreenshotStep("Step50_ADBU_Griseofulvin_Screen");

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.gallbladder, report);
        pageObj.gallbladder.clickYesNoNextToPage("Yes", pageObj.depression, report);
        pageObj.depression.clickYesNoNextToPage("No", pageObj.knowBPNumber, report);

        pageObj.knowBPNumber.selectRadioReponse(user.knowBPType.label, report);
        pageObj.knowBPNumber.clickNext(report);
        if (user.knowBPType == BloodPressureType.Yes_Know_BP) {
            pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
            pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
            if (user.measuredIn3Months.equals("Yes")) {
                pageObj.enterBP.verifyAtPage(report);
                pageObj.enterBP.enterBP(user, report);
                pageObj.enterBP.clickNext(report);
            }
        }
        pageObj.review.verifyAtPage(report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.adbu.verifyAtPage(report);
        report.addScreenshotStep("Step54_ADBU_Gallbladder_Screen");

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.depression, report);
        pageObj.depression.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression, report);
        pageObj.diagnosedDepression.clickYesOrNo("Yes", report);
        pageObj.diagnosedDepression.clickNext(report);
        pageObj.diagnosedDepression.verifyModalDisplayed(report);
        pageObj.diagnosedDepression.clickConfirm(report);
        sleep(1000);
        pageObj.knowBPNumber.verifyAtPage(report);
        pageObj.knowBPNumber.selectRadioReponse(user.knowBPType.label, report);
        pageObj.knowBPNumber.clickNext(report);
        if (user.knowBPType == BloodPressureType.Yes_Know_BP) {
            pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
            pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
            if (user.measuredIn3Months.equals("Yes")) {
                pageObj.enterBP.verifyAtPage(report);
                pageObj.enterBP.enterBP(user, report);
                pageObj.enterBP.clickNext(report);
            }
        }
        pageObj.review.verifyAtPage(report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.adbu.verifyAtPage(report);
        report.addScreenshotStep("Step64_ADBU_Clinical_Depression_Screen");

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.everHadCancer, report);
        pageObj.everHadCancer.clickYesNoNextToPage("Yes", pageObj.cancerList, report);

        ArrayList<String> cancerOptions = new ArrayList<>(Arrays.asList("Other cancer"));
        pageObj.cancerList.selectCheckboxesAndProgress(cancerOptions, pageObj.bloodPressureMeds, report);
        pageObj.bloodPressureMeds.clickYesNoNextToPage("No", pageObj.cardiacRisk, report);

        ArrayList<String> cardiacOptions = new ArrayList<>(Arrays.asList("None of these"));
        pageObj.cardiacRisk.selectCheckboxesAndProgress(cardiacOptions, pageObj.bloodClot, report);

        ArrayList<String> clotOptions = new ArrayList<>(Arrays.asList("None of these"));
        pageObj.bloodClot.selectCheckboxesAndProgress(clotOptions, pageObj.irregularHeartBeat, report);

        pageObj.irregularHeartBeat.clickYesNoNextToPage("No", pageObj.liverCancer, report);
        ArrayList<String> liverOptions = new ArrayList<>(Arrays.asList("None of these"));
        pageObj.liverCancer.selectCheckboxesAndProgress(liverOptions, pageObj.vaginalBleeding, report);

        pageObj.vaginalBleeding.clickYesNoNextToPage("No", pageObj.diabetes, report);
        pageObj.diabetes.clickYesNoNextToPage("No", pageObj.pregnant, report);
        pageObj.pregnant.clickYesNoNextToPage("No", pageObj.breastFeeding, report);
        pageObj.breastFeeding.clickYesNoNextToPage("No", pageObj.pregnancyLoss, report);
        pageObj.pregnancyLoss.clickYesNoNextToPage("No", pageObj.migraines, report);
        pageObj.migraines.clickYesNoNextToPage("No", pageObj.obesity, report);

        user.height = "5 feet, 5 inches";
        user.weight = "212";
        pageObj.obesity.enterHeightAndWeight(user, report);
        pageObj.obesity.clickNextToPage(pageObj.ddiCondition, report);

        ArrayList<String> allButNone = new ArrayList<>(Arrays.asList("Hepatitis C", "Thyroid disease",
                "Epilepsy", "HIV","High cholesterol"));
        pageObj.ddiCondition.selectCheckboxesAndProgress(allButNone, pageObj.ddiHepC, report);

        ArrayList<String> hepCOptions = new ArrayList<>(Arrays.asList("Ombitasvir"));
        pageObj.ddiHepC.selectCheckboxesAndProgress(hepCOptions, pageObj.ddiThyroid, report);

        ArrayList<String> thyroidOptions = new ArrayList<>(Arrays.asList(DDIThyroidType.LEVOTHYROXINE.label));
        pageObj.ddiThyroid.selectCheckboxesAndProgress(thyroidOptions, pageObj.ddiEpBipolar, report);

        ArrayList<String> epOptions = new ArrayList<>(Arrays.asList("Barbiturates"));
        pageObj.ddiEpBipolar.selectCheckboxesAndProgress(epOptions, pageObj.ddihiv, report);

        ArrayList<String> hivOptions = new ArrayList<>(Arrays.asList("Fosamprenavir"));
        pageObj.ddihiv.selectCheckboxesAndProgress(hivOptions, pageObj.ddiHighCholesterol, report);

        ArrayList<String> highCOptions = new ArrayList<>(Arrays.asList("Atorvastatin"));
        pageObj.ddiHighCholesterol.selectCheckboxesAndProgress(highCOptions, pageObj.antifungal, report);
        pageObj.antifungal.clickYesNoNextToPage("Yes",  pageObj.antifungalMeds, report);

        ArrayList<String> antifungalMedsOptions = new ArrayList<>(Arrays.asList("Fluconazole"));
        pageObj.antifungalMeds.selectCheckboxesAndProgress(antifungalMedsOptions, pageObj.otherMedication, report);

        ArrayList<String> otherMedsOptions = new ArrayList<>(Arrays.asList("Rifabutin"));
        pageObj.otherMedication.selectCheckboxesAndProgress(otherMedsOptions, pageObj.gallbladder, report);
        pageObj.gallbladder.clickYesNoNextToPage("Yes", pageObj.depression, report);
        pageObj.depression.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression, report);
        pageObj.diagnosedDepression.clickYesOrNo("Yes", report);
        pageObj.diagnosedDepression.clickNext(report);
        pageObj.diagnosedDepression.verifyModalDisplayed(report);
        pageObj.diagnosedDepression.clickConfirm(report);
        sleep(1000);
        pageObj.knowBPNumber.verifyAtPage(report);
        pageObj.knowBPNumber.selectRadioReponse(user.knowBPType.label, report);
        pageObj.knowBPNumber.clickNext(report);
        if (user.knowBPType == BloodPressureType.Yes_Know_BP) {
            pageObj.knowBPNumber.verifyModalThreeMonthsOpen(report);
            pageObj.knowBPNumber.clickYesOrNoModal(user.measuredIn3Months, report);
            if (user.measuredIn3Months.equals("Yes")) {
                pageObj.enterBP.verifyAtPage(report);
                pageObj.enterBP.enterBP(user, report);
                pageObj.enterBP.clickNext(report);
            }
        }
        pageObj.review.verifyAtPage(report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.adbu.verifyAtPage(report);
        report.addScreenshotStep("Step97_ADBU_Screen");
    }
}
