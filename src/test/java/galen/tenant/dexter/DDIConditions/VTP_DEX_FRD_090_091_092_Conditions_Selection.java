package galen.tenant.dexter.DDIConditions;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.DDIConditionType;
import galen.enums.tenant.dexter.DDIThyroidType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.BasePage;
import galen.pages.common.CheckboxPage;
import galen.pages.tenant.dexter.InitialAssessment.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class VTP_DEX_FRD_090_091_092_Conditions_Selection extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_090_091_092_Conditions_Selection";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    DexterPageObj pageObj;
    DDICondition ddi;

    VTP_DEX_FRD_090_091_092_Conditions_Selection()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    void verifySingleChoice(String step, DexterUser user, String option, BasePage nextPage) {
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, ddi, report);
        ddi.selectCheckboxAndProgress(option,nextPage, report);
        report.addScreenshotStep("Step"+step+"_"+option);
    }

    void verifyTwoChoices(String step, DexterUser user, CheckboxPage nextPage, BasePage secondPage) {
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, ddi,report);
        ddi.selectCheckboxesAndProgress(user.conditionType, nextPage, report);
        String none = (nextPage.equals(pageObj.ddiThyroid))? DDIThyroidType.NO_THYROID_MEDS.label:
                user.defaultNoneText;
        nextPage.selectCheckboxAndProgress(none, secondPage, report);
        report.addScreenshotStep("Step"+step+"_"+ secondPage.reportText.replace("/","_"));
    }

    @Test
    public void VTP_DEX_FRD_090_091_092_Conditions_Selection_Test() throws Exception {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_090_091_092 – Conditions Screen Selection ";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        pageObj = new DexterPageObj(driver);
        ddi = pageObj.ddiCondition;
        DDIHepC hepC = pageObj.ddiHepC;
        DDIEpBipolar ep = pageObj.ddiEpBipolar;
        DDIThyroid thyroid = pageObj.ddiThyroid;
        DDIHIV hiv = pageObj.ddihiv;
        DDIHighCholesterol hc = pageObj.ddiHighCholesterol;
        
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, ddi,report);
        ddi.verifyAllOptionsVisible(report);
        ddi.verifyNoOptionsSelected(report);
        ddi.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step2_InitialState");

        ddi.selectCheckboxesAndProgress(user.conditionType, pageObj.antifungal, report);
        report.addScreenshotStep("Step3_Antifungal");

        verifySingleChoice("5",  user, DDIConditionType.HEPATITIS_C.label, hepC);
        verifySingleChoice("7", user, DDIConditionType.THYROID_DISEASE.label, thyroid);
        verifySingleChoice("9", user, DDIConditionType.EPILEPSY.label, ep);
        verifySingleChoice("11", user, DDIConditionType.BIPOLAR_DISORDER.label, ep);
        verifySingleChoice("13", user, DDIConditionType.HIV.label, hiv);
        verifySingleChoice("15", user, DDIConditionType.HIGH_CHOLESTEROL.label, hc);

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, ddi, report);

        user.conditionType = new ArrayList<>(Arrays.asList(DDIConditionType.HEPATITIS_C.label,
                DDIConditionType.EPILEPSY.label));
        verifyTwoChoices("18", user, hepC, ep);

        user.conditionType = new ArrayList<>(Arrays.asList(DDIConditionType.HEPATITIS_C.label,
                DDIConditionType.BIPOLAR_DISORDER.label));
        verifyTwoChoices("21", user, hepC, ep);

        user.conditionType = new ArrayList<>(Arrays.asList(DDIConditionType.HEPATITIS_C.label,
                DDIConditionType.HIV.label));
        verifyTwoChoices("24", user, hepC, hiv);

        user.conditionType = new ArrayList<>(Arrays.asList(DDIConditionType.HEPATITIS_C.label,
                DDIConditionType.HIGH_CHOLESTEROL.label));
        verifyTwoChoices("27", user, hepC, hc);

        user.conditionType = new ArrayList<>(Arrays.asList(DDIConditionType.THYROID_DISEASE.label,
                DDIConditionType.HIV.label));
        verifyTwoChoices("30", user, thyroid,hiv);

        user.conditionType = new ArrayList<>(Arrays.asList(DDIConditionType.THYROID_DISEASE.label,
                DDIConditionType.HIGH_CHOLESTEROL.label));
        verifyTwoChoices("33", user, thyroid, hc);

        user.conditionType = new ArrayList<>(Arrays.asList(DDIConditionType.EPILEPSY.label,
                DDIConditionType.HIGH_CHOLESTEROL.label));
        verifyTwoChoices("36", user, ep, hc);

        user.conditionType=ddi.getAllButNone();
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user,ddi,report);

        ddi.selectCheckboxesAndProgress(user.conditionType, hepC, report);
        report.addScreenshotStep("Step38_HepC");

        hepC.selectCheckboxesAndProgress(user.hepCMeds, thyroid, report);
        report.addScreenshotStep("Step39_Thyroid");

        hepC.selectCheckboxesAndProgress(user.thyroidMeds, ep, report);
        report.addScreenshotStep("Step40_EpilepsyBipolar");

        hepC.selectCheckboxesAndProgress(user.epBipolarMeds, hiv, report);
        report.addScreenshotStep("Step41_HIV");

        hepC.selectCheckboxesAndProgress(user.hivMeds, hc, report);
        report.addScreenshotStep("Step42_HighCholesterol");
    }
}
