package galen.tenant.dexter.DDIConditions;

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
import java.util.Arrays;
import java.util.HashMap;

public class VTP_DEX_FRD_095_096_Condition_Medication_Screen extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_095_096_Condition_Medication_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_095_096_Condition_Medication_Screen()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    public void iterateThroughOptions(DexterUser user, DexterPageObj pageObj, String c) {
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        new DexterNavigations(driver).DDIPath(user, pageObj.antifungal, report);
        report.addScreenshotStep(c+"_Antifungal");
        new DexterHFWrappers(driver).runAntifungalToADBU(user, pageObj.antifungal, pageObj.adbu, report);
        if (c.equalsIgnoreCase(DDIThyroidType.LEVOTHYROXINE.label)) {
            pageObj.adbu.verifyConditionIsListed(DDIThyroidType.LEVOTHYROXINE.adbuText, report);
        } else if (c.equalsIgnoreCase(DDIThyroidType.DIFFERENT_THYROID_MEDS.label)) {
            pageObj.adbu.verifyConditionIsListed(DDIThyroidType.DIFFERENT_THYROID_MEDS.adbuText, report);
        } else {
            pageObj.adbu.verifyConditionIsListed(c, report);
        }
        report.addScreenshotStep(c + "_ADBU");
    }

    public void iterateThroughAllOptions(DexterUser user, DexterPageObj pageObj, ArrayList<String> c, String type) {
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        new DexterNavigations(driver).DDIPath(user, pageObj.antifungal, report);
        report.addScreenshotStep("All_"+ type + "_Antifungal");
        new DexterHFWrappers(driver).runAntifungalToADBU(user, pageObj.antifungal, pageObj.adbu, report);
        pageObj.adbu.verifyConditionsListed(c, report);
        report.addScreenshotStep("All_"+type+"_ADBU");
    }

    @Test
    public void VTP_DEX_FRD_095_096_Condition_Medication_Screen_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY,
                PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_095_096 – Conditions Medication Selection ";
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        CheckboxPage checkboxPage = new CheckboxPage(driver);

        ArrayList<String> conditionAll=pageObj.ddiCondition.getAllButNone();
        ArrayList<String> hepCAll=pageObj.ddiHepC.getAllButNone();
        ArrayList<String> epBipolarAll=pageObj.ddiEpBipolar.getAllButNone();
        ArrayList<String> hivAll=pageObj.ddihiv.getAllButNone();
        ArrayList<String> hcAll=pageObj.ddiHighCholesterol.getAllButNone();

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);

        // Step 1 - 20
        user.conditionType= checkboxPage.getCondition(DDIConditionType.HEPATITIS_C.label);
        for (String c : hepCAll) {
            user.hepCMeds = checkboxPage.getCondition(c);
            iterateThroughOptions(user,pageObj, c);
        }
        iterateThroughAllOptions(user,pageObj, user.hepCMeds=hepCAll , "Hep C");

        // Step 21 -35
        user.conditionType= checkboxPage.getCondition(DDIConditionType.THYROID_DISEASE.label);
        for (DDIThyroidType c : new ArrayList<>(Arrays.asList(DDIThyroidType.LEVOTHYROXINE,
                DDIThyroidType.DIFFERENT_THYROID_MEDS))) {
            user.thyroidMeds = checkboxPage.getCondition(c.label);
            iterateThroughOptions(user,pageObj, c.adbuText);
        }
        user.thyroidMeds = pageObj.ddiThyroid.getAllButNone();
        iterateThroughAllOptions(user,pageObj, pageObj.ddiThyroid.allButNoneThyroidADBU, "Thyroid");

        // Step 36 -70
        user.conditionType= checkboxPage.getCondition(DDIConditionType.EPILEPSY.label);
        for (String c : epBipolarAll) {
            user.epBipolarMeds = checkboxPage.getCondition(c);
            iterateThroughOptions(user,pageObj, c);
        }
        user.epBipolarMeds = epBipolarAll;
        iterateThroughAllOptions(user,pageObj, user.epBipolarMeds, "Epilepsy_Bipolar");

        // Step 71-110
        user.conditionType= checkboxPage.getCondition(DDIConditionType.HIV.label);
        for (String c : hivAll) {
            user.hivMeds = checkboxPage.getCondition(c);
            iterateThroughOptions(user,pageObj, c);
        }
        iterateThroughAllOptions(user,pageObj, user.hivMeds = hivAll, "HIV");

        // Step 111-130
        user.conditionType= checkboxPage.getCondition(DDIConditionType.HIGH_CHOLESTEROL.label);
        for (String c : hcAll) {
            user.highCholMeds = checkboxPage.getCondition(c);
            iterateThroughOptions(user,pageObj, c);
        }
        iterateThroughAllOptions(user,pageObj, user.highCholMeds=hcAll, "HighCholesterol");

        // Step 131-139
        user.conditionType= conditionAll;
        user.hepCMeds=checkboxPage.getCondition(DDIHepCType.OMBITASVIR.label);
        user.thyroidMeds =checkboxPage.getCondition(DDIThyroidType.LEVOTHYROXINE.label);
        user.epBipolarMeds = checkboxPage.getCondition(DDIEpBipolarType.BARBITUATES.label);
        user.hivMeds =checkboxPage.getCondition(DDIHIVType.FOSAMPRENAVIR.label);
        user.highCholMeds=checkboxPage.getCondition(DDIHighCholType.ATORVASTATIN.label);

        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        pageObj.ddiCondition.verifyAtPage(report);
        new DexterNavigations(driver).DDIPath(user, pageObj.antifungal, report);
        pageObj.antifungal.verifyAtPage(report);
        report.addScreenshotStep("Step137_Antifungal");

        new DexterHFWrappers(driver).runAntifungalToADBU(user, pageObj.antifungal, pageObj.adbu, report);
        pageObj.adbu.verifyConditionIsListed(DDIHepCType.OMBITASVIR.label, report);
        pageObj.adbu.verifyConditionIsListed(DDIThyroidType.LEVOTHYROXINE.adbuText, report);
        pageObj.adbu.verifyConditionIsListed(DDIEpBipolarType.BARBITUATES.label, report);
        pageObj.adbu.verifyConditionIsListed(DDIHIVType.FOSAMPRENAVIR.label, report);
        pageObj.adbu.verifyConditionIsListed(DDIHighCholType.ATORVASTATIN.label, report);
        report.addScreenshotStep("Step139_ADBU");

        //140-148 All meds
        user.conditionType= conditionAll;
        user.hepCMeds=hepCAll;
        user.thyroidMeds =pageObj.ddiThyroid.getAllButNone();
        user.epBipolarMeds = epBipolarAll;
        user.hivMeds = hivAll;
        user.highCholMeds= hcAll;
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        new DexterNavigations(driver).DDIPath(user, pageObj.antifungal, report);
        report.addScreenshotStep("Step146_Antifungal");

        new DexterHFWrappers(driver).runAntifungalToADBU(user, pageObj.antifungal, pageObj.adbu, report);
        pageObj.adbu.verifyConditionsListed(hepCAll, report);
        pageObj.ddiThyroid.verifyAllOptionsInADBU(report);
        pageObj.adbu.verifyConditionsListed(epBipolarAll, report);
        pageObj.adbu.verifyConditionsListed(hivAll, report);
        pageObj.adbu.verifyConditionsListed(hcAll, report);
        report.addScreenshotStep("Step148_ADBU");

        //149-164 Rotate through DDI conditions. No meds on follow-up. Verify at antifungals
        user.hepCMeds=user.defaultNone;
        user.thyroidMeds =checkboxPage.getCondition(DDIThyroidType.NO_THYROID_MEDS.label);
        user.epBipolarMeds = user.defaultNone;
        user.hivMeds =user.defaultNone;
        user.highCholMeds=user.defaultNone;

        for (String c : pageObj.ddiCondition.getAllButNone()) {
            user.conditionType= checkboxPage.getCondition(c);
            new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.antifungal, report);
            report.addScreenshotStep(c+"None_Antifungal");
        }

        //165-169 All DDIs, no follow-up
        user.conditionType=conditionAll;
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.antifungal, report);
        report.addScreenshotStep("Step170_Antifungal");
    }
}
