package galen.tenant.dexter.DDIConditions;

import galen.base.BaseTest;
import galen.enums.tenant.dexter.DDIConditionType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.BasePage;
import galen.pages.common.CheckboxPage;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DDICondition;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class VTP_DEX_FRD_090_091_092_Conditions_Selection extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_090: To verify the DDI/Conditions shall provide controls that require the user to" +
            " select all conditions that they currently have or have had from the following list, or select “None of " +
            "These”:\n" +
            "o\tHepatitis C\n" +
            "o\tThyroid Disease\n" +
            "o\tEpilepsy \n" +
            "o\tBipolar disorder\n" +
            "o\tHIV\n" +
            "o\tHigh Cholesterol\n" +
            "DEX_FRD_091: To verify if the user selects “None of these” on the DDI/Condition Screen, the application " +
            "shall navigate to the Anti-fungal screen.\n" +
            "DEX_FRD_092: To verify if the user selects any of the listed conditions on the DDI/Condition Screen, the " +
            "application shall navigate to a series of individual screens, one for each selected condition, that " +
            "contains a list of DDI Medications for that condition.";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tSelection of None of these navigates to the Conditions/DDI - Antifungal Product Screen\n" +
            "o\tSelection of the following conditions and combinations of navigation (number of selections):\n" +
            "•\tHepatitis C (1)\n" +
            "•\tThyroid Disease (1)\n" +
            "•\tEpilepsy (1)\n" +
            "•\tBipolar disorder (1)\n" +
            "•\tHIV (1)\n" +
            "•\tHigh Cholesterol (1)\n" +
            "•\tHepatitis C, Epilepsy (2)\n" +
            "•\tHepatitis C, Bipolar Disorder (2)\n" +
            "•\tHepatitis C, HIV (2)\n" +
            "•\tHepatitis C, High Cholesterol (2)\n" +
            "•\tThyroid Disease, HIV (2)\n" +
            "•\tThyroid Disease, High Cholesterol (2)\n" +
            "•\tEpilepsy, High Cholesterol (2)\n" +
            "•\tHepatitis C, Thyroid Disease, Epilepsy, Bipolar Disorder, HIV, High Cholesterol (6)\n" +
            "The scenarios listed above were planned with the intent of ensuring each of the screens can navigate " +
            "appropriately the following screen depending on the user selection. \n";
    static String REQUIREMENTS = "DEX_FRD_090, DEX_FRD_091, DEX_FRD_092";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_090_091_092_Conditions_Selection";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    DexterPageObj pageObj;

    VTP_DEX_FRD_090_091_092_Conditions_Selection()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    void verifySingleChoice(String step, DDICondition ddi, DexterUser user, String option, BasePage nextPage) {
        user.conditionType= new ArrayList<>(Arrays.asList(option));
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, ddi, report);
        ddi.selectCheckboxesAndProgress(user.conditionType,nextPage, report);
        report.addScreenshotStep("Step"+step+"_"+option);
    }

    void verifyTwoChoices(String step, DDICondition ddi, DexterUser user,
                          CheckboxPage nextPage, BasePage secondPage) {
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, ddi, report);
        ddi.selectCheckboxesAndProgress(user.conditionType, nextPage, report);
        String none = (!nextPage.equals(pageObj.ddiThyroid))?"None of these":"No thyroid medication";
        nextPage.selectCheckboxesAndProgress(new ArrayList<String>(Arrays.asList(none)), secondPage, report);
        report.addScreenshotStep("Step"+step+"_"+ secondPage.reportText.replace("/","_"));
    }

    @Test
    public void VTP_DEX_FRD_090_091_092_Conditions_Selection_Test() throws Exception {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_090_091_092 – Conditions Screen Selection ";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        pageObj = new DexterPageObj(driver);
        new PritUnlPage(driver).authenticateUserIfRequired();
        ArrayList<String> allButOther = new ArrayList<String>(pageObj.ddiCondition.conditionOptions);
        allButOther.remove("None of these");
        DDICondition ddi = pageObj.ddiCondition;
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        ddi.verifyAllOptionsVisible(report);
        ddi.verifyNoOptionsSelected(report);
        ddi.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step2_InitialState");

        ddi.selectCheckboxesAndProgress(user.conditionType, pageObj.antifungal, report); // user value is None by default
        report.addScreenshotStep("Step3_Antifungal");

        verifySingleChoice("5", ddi, user, DDIConditionType.HEPATITIS_C.label, pageObj.ddiHepC);
        verifySingleChoice("7", ddi, user, DDIConditionType.THYROID_DISEASE.label, pageObj.ddiThyroid);
        verifySingleChoice("9", ddi, user, DDIConditionType.EPILEPSY.label, pageObj.ddiEpBipolar);
        verifySingleChoice("11", ddi, user, DDIConditionType.BIPOLAR_DISORDER.label, pageObj.ddiEpBipolar);
        verifySingleChoice("13", ddi, user, DDIConditionType.HIV.label, pageObj.ddihiv);
        verifySingleChoice("15", ddi, user, DDIConditionType.HIGH_CHOLESTEROL.label, pageObj.ddiHighCholesterol);

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        user.conditionType = new ArrayList<String>(Arrays.asList(DDIConditionType.HEPATITIS_C.label,
                DDIConditionType.EPILEPSY.label));
        verifyTwoChoices("18", ddi, user, pageObj.ddiHepC, pageObj.ddiEpBipolar);
        user.conditionType = new ArrayList<String>(Arrays.asList(DDIConditionType.HEPATITIS_C.label,
                DDIConditionType.BIPOLAR_DISORDER.label));
        verifyTwoChoices("21", ddi, user, pageObj.ddiHepC, pageObj.ddiEpBipolar);
        user.conditionType = new ArrayList<String>(Arrays.asList(DDIConditionType.HEPATITIS_C.label,
                DDIConditionType.HIV.label));
        verifyTwoChoices("24", ddi, user, pageObj.ddiHepC, pageObj.ddihiv);
        user.conditionType = new ArrayList<String>(Arrays.asList(DDIConditionType.HEPATITIS_C.label,
                DDIConditionType.HIGH_CHOLESTEROL.label));
        verifyTwoChoices("27", ddi, user, pageObj.ddiHepC, pageObj.ddiHighCholesterol);

        user.conditionType = new ArrayList<String>(Arrays.asList(DDIConditionType.THYROID_DISEASE.label,
                DDIConditionType.HIV.label));
        verifyTwoChoices("30", ddi, user, pageObj.ddiThyroid, pageObj.ddihiv);
        user.conditionType = new ArrayList<String>(Arrays.asList(DDIConditionType.THYROID_DISEASE.label,
                DDIConditionType.HIGH_CHOLESTEROL.label));
        verifyTwoChoices("33", ddi, user, pageObj.ddiThyroid, pageObj.ddiHighCholesterol);
        user.conditionType = new ArrayList<String>(Arrays.asList(DDIConditionType.EPILEPSY.label,
                DDIConditionType.HIGH_CHOLESTEROL.label));
        verifyTwoChoices("36", ddi, user, pageObj.ddiEpBipolar, pageObj.ddiHighCholesterol);

        user.conditionType=allButOther;
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, ddi, report);
        ddi.selectCheckboxesAndProgress(user.conditionType, pageObj.ddiHepC, report);
        report.addScreenshotStep("Step38_HepC");

        pageObj.ddiHepC.selectCheckboxesAndProgress(user.hepCMeds, pageObj.ddiThyroid, report);
        report.addScreenshotStep("Step39_Thyroid");

        pageObj.ddiHepC.selectCheckboxesAndProgress(user.thyroidMeds, pageObj.ddiEpBipolar, report);
        report.addScreenshotStep("Step40_EpilepsyBipolar");

        pageObj.ddiHepC.selectCheckboxesAndProgress(user.epBipolarMeds, pageObj.ddihiv, report);
        report.addScreenshotStep("Step41_HIV");

        pageObj.ddiHepC.selectCheckboxesAndProgress(user.hivMeds, pageObj.ddiHighCholesterol, report);
        report.addScreenshotStep("Step42_HighCholesterol");

    }
}
