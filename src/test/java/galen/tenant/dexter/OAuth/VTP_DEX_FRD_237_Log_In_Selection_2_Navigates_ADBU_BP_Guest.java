package galen.tenant.dexter.OAuth;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.*;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterNavigations;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.CheckboxPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_ADBU_BP_Guest extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_ADBU_BP_Guest";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    DexterNavigations navs;
    DexterPageObj po;
    DexterUser user;
    DexterHFWrappers wrapper;

    VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_ADBU_BP_Guest() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    void navToADBU() {
        navs.DDIPath(user, po.antifungal, report);
        wrapper.runAntifungalToADBU(user, po.antifungal, po.oAuthPostReview, report);
        po.oAuthPostReview.chooseAccountTypeAndProgress(user, po.adbubpScreen, report);
    }

    void verifyDDI(String condition, String med, CheckboxPage page, String adbuMed) {
        String adbu = (Objects.equals(adbuMed, ""))?med: adbuMed;
        user.conditionType = page.getCondition(condition);
        wrapper.runDexterHFnoBPNonsmoker(user, po.ddiCondition, report);
        navToADBU();
        po.adbubpScreen.verifyConditionIsListed(adbu, report);
        user.conditionType = user.defaultNone;
    }
    
    @Test
    public void VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_ADBU_BP_Guest_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_237 – Log In Selection 2 Navigates to ADBU + BP (If Triggered) Guest";

        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker();
        po = new DexterPageObj(driver);
        navs = new DexterNavigations(driver);
        wrapper = new DexterHFWrappers(driver);

        po.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        wrapper.runDexterHFnoBPNonsmoker(user, po.obesity, report);
        user.weight = "212";
        po.obesity.enterHeightAndWeight(user, report);
        po.obesity.clickNextToPage(po.ddiCondition, report);
        navToADBU();
        po.adbubpScreen.verifyConditionIsListed(po.adbubpScreen.obesityCondition, report);
        report.addScreenshotStep("Step4_ADBU_BP_Screen");

        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker();
        wrapper.runDexterHFnoBPNonsmoker(user, po.everHadCancer, report);
        po.everHadCancer.clickYesNoNextToPage("Yes", po.cancerList, report);
        po.cancerList.selectCheckboxesAndProgress(user.cancerList, po.bloodPressureMeds, report);
        user.everHadCancer = "Yes";
        wrapper.runDexterHFnoBPNonsmoker(user, po.oAuthPostReview, report);
        po.oAuthPostReview.chooseAccountTypeAndProgress(user, po.adbubpScreen, report);
        po.adbubpScreen.verifyConditionIsListed(po.adbubpScreen.otherCancerCondition, report);
        report.addScreenshotStep("Step9_ADBUBP_OtherCancer ");
        user.everHadCancer = "No";

        user.hepCMeds = po.ddiHepC.getCondition(DDIHepCType.OMBITASVIR.label);
        verifyDDI(DDIConditionType.HEPATITIS_C.label, DDIHepCType.OMBITASVIR.label, po.ddiHepC, "");
        report.addScreenshotStep("Step15_ADBU_Ombitasvir_Screen");

        user.thyroidMeds = po.ddiThyroid.getCondition(DDIThyroidType.LEVOTHYROXINE.label);
        verifyDDI(DDIConditionType.THYROID_DISEASE.label, DDIThyroidType.LEVOTHYROXINE.label, po.ddiThyroid,
                DDIThyroidType.LEVOTHYROXINE.adbuText);
        report.addScreenshotStep("Step21_ADBU_Levothyroxine_Screen");

        user.epBipolarMeds= po.ddiEpBipolar.getCondition(DDIEpBipolarType.BARBITUATES.label);
        verifyDDI(DDIConditionType.EPILEPSY.label, DDIEpBipolarType.BARBITUATES.label, po.ddiEpBipolar, "");
        report.addScreenshotStep("Step27_ADBU_Barbiturates_Screen");

        user.hivMeds = po.ddihiv.getCondition(DDIHIVType.FOSAMPRENAVIR.label);
        verifyDDI(DDIConditionType.HIV.label, DDIHIVType.FOSAMPRENAVIR.label, po.ddihiv, "");
        report.addScreenshotStep("Step33_ADBU_Fosamprenavir_Screen");

        user.highCholMeds = po.ddiHighCholesterol.getCondition(DDIHighCholType.ATORVASTATIN.label);
        verifyDDI(DDIConditionType.HIGH_CHOLESTEROL.label, DDIHighCholType.ATORVASTATIN.label,
                po.ddiHighCholesterol, "");
        po.adbubpScreen.verifyConditionIsListed(po.adbubpScreen.highCholesterolCondition, report);
        report.addScreenshotStep("Step39_ADBU_Atorvastatin_Screen");

        user.antiFungalMeds = po.antifungalMeds.getCondition(AntifungalMedsType.FLUCONAZOLE.label);
        wrapper.runDexterHFnoBPNonsmoker(user, po.antifungal, report);
        po.antifungal.clickYesNoNextToPage("Yes", po.antifungalMeds, report);
        po.antifungalMeds.selectCheckboxAndProgress(AntifungalMedsType.FLUCONAZOLE.label, po.otherMedication, report);
        wrapper.runAntifungalToADBU(user, po.antifungalMeds, po.oAuthPostReview, report);
        po.oAuthPostReview.chooseAccountTypeAndProgress(user, po.adbubpScreen, report);
        po.adbubpScreen.verifyConditionIsListed(AntifungalMedsType.FLUCONAZOLE.label, report);
        report.addScreenshotStep("Step44_ADBU_Fluconazole_Screen");

        wrapper.runDexterHFnoBPNonsmoker(user, po.otherMedication, report);
        po.otherMedication.selectCheckboxAndProgress(DDIOtherMedsType.RIFABUTIN.label, po.gallbladder, report);
        po.gallbladder.clickYesNoNextToPage("No", po.depression, report);
        wrapper.runAntifungalToADBU(user, po.depression, po.oAuthPostReview, report);
        po.oAuthPostReview.chooseAccountTypeAndProgress(user, po.adbubpScreen, report);
        po.adbubpScreen.verifyConditionIsListed(DDIOtherMedsType.RIFABUTIN.label, report);
        report.addScreenshotStep("Step49_ADBU_Rifabutin_Screen");

        user.conditionType = user.defaultNone;
        wrapper.runDexterHFnoBPNonsmoker(user, po.gallbladder, report);
        po.gallbladder.clickYesNoNextToPage("Yes", po.depression, report);
        navs.antiFungalToADBU(user, po.adbubpScreen, report);
        po.adbubpScreen.verifyConditionIsListed(po.adbubpScreen.gallBladderCondition, report);
        report.addScreenshotStep("Step53_ADBU_Gallbladder_Disease_Screen");

        user.depression = "Yes";
        user.diagnosedDepression = "Yes";
        wrapper.runDexterHFnoBPNonsmoker(user, po.depression, report);
        navs.antiFungalToADBU(user, po.oAuthPostReview, report);
        po.oAuthPostReview.chooseAccountTypeAndProgress(user, po.adbubpScreen, report);
        po.adbubpScreen.verifyConditionIsListed(po.adbubpScreen.depressionCondition, report);
        report.addScreenshotStep("Step61_ADBU_Clinical_Depression_Screen");

        user.isAntifungal = "Yes";
        user.weight = "212";
        user.height = "5 feet, 5 inches";
        user.conditionType = po.ddiCondition.getAllButNone();
        user.gallbladder = "Yes";
        user.otherMedicationType = po.otherMedication.getCondition(DDIOtherMedsType.RIFABUTIN.label);
        wrapper.runDexterHFnoBPNonsmoker(user, po.everHadCancer, report);
        po.everHadCancer.clickYesNoNextToPage("Yes", po.cancerList, report);
        po.cancerList.selectCheckboxesAndProgress(user.cancerList, po.bloodPressureMeds, report);
        po.bloodPressureMeds.clickYesNoNextToPage("No", po.cardiacRisk, report);
        po.cardiacRisk.selectCheckboxesAndProgress(user.defaultNone, po.bloodClot, report);
        po.bloodClot.selectCheckboxesAndProgress(user.defaultNone, po.irregularHeartBeat, report);
        po.irregularHeartBeat.clickYesNoNextToPage("No", po.liverCancer, report);
        po.liverCancer.selectCheckboxesAndProgress(user.defaultNone, po.vaginalBleeding, report);
        po.vaginalBleeding.clickYesNoNextToPage("No", po.diabetes, report);
        po.diabetes.clickYesNoNextToPage("No", po.pregnant, report);
        po.pregnant.clickYesNoNextToPage("No", po.breastFeeding, report);
        po.breastFeeding.clickYesNoNextToPage("No", po.pregnancyLoss, report);
        po.pregnancyLoss.clickYesNoNextToPage("No", po.migraines, report);
        po.migraines.clickYesNoNextToPage("No", po.obesity, report);
        po.obesity.enterHeightAndWeight(user, report);
        po.obesity.clickNextToPage(po.ddiCondition, report);
        navs.DDIPath(user, po.antifungal,report);
        navs.antiFungalToADBU(user, po.adbubpScreen, report);
        po.adbubpScreen.verifyConditionIsListed(po.adbubpScreen.obesityCondition, report);
        po.adbubpScreen.verifyConditionIsListed(po.adbubpScreen.otherCancerCondition, report);
        po.adbubpScreen.verifyConditionIsListed(DDIHepCType.OMBITASVIR.label, report);
        po.adbubpScreen.verifyConditionIsListed(DDIThyroidType.LEVOTHYROXINE.adbuText, report);
        po.adbubpScreen.verifyConditionIsListed(DDIEpBipolarType.BARBITUATES.label, report);
        po.adbubpScreen.verifyConditionIsListed(DDIHIVType.FOSAMPRENAVIR.label, report);
        po.adbubpScreen.verifyConditionIsListed(po.adbubpScreen.highCholesterolCondition, report);
        po.adbubpScreen.verifyConditionIsListed(DDIHighCholType.ATORVASTATIN.label, report);
        po.adbubpScreen.verifyConditionIsListed(AntifungalMedsType.FLUCONAZOLE.label, report);
        po.adbubpScreen.verifyConditionIsListed(DDIOtherMedsType.RIFABUTIN.label, report);
        po.adbubpScreen.verifyConditionIsListed(po.adbubpScreen.gallBladderCondition, report);
        po.adbubpScreen.verifyConditionIsListed(po.adbubpScreen.depressionCondition, report);
        report.addScreenshotStep("Step93_ADBU_Screen");
    }
}

