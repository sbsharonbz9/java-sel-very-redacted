package galen.tenant.dexter.DDIConditions;

import galen.base.BaseTest;
import galen.enums.tenant.dexter.*;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterNavigations;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class VTP_DEX_FRD_095_096_Condition_Medication_Screen extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_095: To verify on the related DDI medications screen, if the user indicates that " +
            "they are taking any of the DDI medications, the application shall flag for ADBU, and navigate to the " +
            "Antifungal Products Screen. \n" +
            "DEX_FRD_096: To verify on the related DDI medications screen, if the user selects “None of these” indicating" +
            " they are not taking any of the DDI medications, the application shall navigate to the Antifungal Products Screen.\n";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tFlagging of ADBU and navigation to the Antifungal Products Screen when the user indicates usage of DDI " +
            "medication(s):\n" +
            "\uF0A7\tHepatitis C\n" +
            "•\tOmbitasvir\n" +
            "•\tParitaprevir\n" +
            "•\tRitonavir with Dasabuvir\n" +
            "•\tAll Hepatitis C Meds\n" +
            "\uF0A7\tThyroid Disease\n" +
            "•\tLevothyroxine\n" +
            "•\tA different thyroid medication\n" +
            "•\tAll Thyroid disease Meds \n" +
            "\uF0A7\tEpilepsy or Bipolar Disorder\n" +
            "•\tBarbiturates\n" +
            "•\tFelbamate\n" +
            "•\tLamotrigine\n" +
            "•\tPhenytoin\n" +
            "•\tPrimidone\n" +
            "•\tRufinamide\n" +
            "•\tAll Epilepsy or Bipolar Disorder Meds\n" +
            "\uF0A7\tHIV\n" +
            "•\tFosamprenavir\n" +
            "•\tDarunavir\n" +
            "•\tEfavirenz\n" +
            "•\tEtravirine\n" +
            "•\tNelfinavir\n" +
            "•\tNevirapine\n" +
            "•\tRitonavir\n" +
            "•\tAll HIV Meds\n" +
            "\uF0A7\tHigh Cholesterol\n" +
            "•\tAtorvastatin\n" +
            "•\tColesevelam\n" +
            "•\tRosuvastatin \n" +
            "•\tAll High Cholesterol Meds\n" +
            "\uF0A7\tAll Conditions/DDI\n" +
            "•\tSingle Medication from each Medication Screen (HepC, Thyroid, Epilepsy, Bipolar Disorder, HIV, High Chol.)\n" +
            "•\tAll Medications from each Medication Screen (HepC, Thyroid, Epilepsy, Bipolar Disorder, HIV, High Chol.)\n" +
            "o\tSelect ‘None of these’ option for single Conditions/DDI Meds Screen navigates to Antifungal Products Screen\n" +
            "\uF0A7\tHepatitis C\n" +
            "\uF0A7\tThyroid Disease\n" +
            "\uF0A7\tEpilepsy or Bipolar Disorder\n" +
            "\uF0A7\tHIV\n" +
            "\uF0A7\tHigh Cholesterol\n" +
            "o\tSelect ‘None of these’ from All Medication Screens (HepC, Thyroid, Epilepsy or Bipolar Disorder, HIV, " +
            "High Chol.) and navigate to Antifungal Products Screen\n";
    static String REQUIREMENTS = "DEX_FRD_095, DEX_FRD_096";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_095_096_Condition_Medication_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_095_096_Condition_Medication_Screen()  {
        VERSIONHISTORY.add("1.0;10NOV2022;Initial Test Script;Name Redacted");
        VERSIONHISTORY.add("2.0;20JUN2024;Per CADENCE-476: Updated Test Steps for FDA changes \n" +
                "Per CADENCE-529: Removed from Actual Result column for Happy flow execution related steps\n" +
                "Per CADENCE-591: Update Test Steps for modified assessment and navigation;Name Redacted");
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
        report.addScreenshotStep(c+"_Antifungal");
        new DexterHFWrappers(driver).runAntifungalToADBU(user, pageObj.antifungal, pageObj.adbu, report);
        pageObj.adbu.verifyConditionsListed(c, report);
        report.addScreenshotStep("All_"+type+"ADBU");
    }

    @Test
    public void VTP_DEX_FRD_095_096_Condition_Medication_Screen_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY,
                PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_095_096 – Conditions Medication Selection ";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        new PritUnlPage(driver).authenticateUserIfRequired();

        // Step 1 - 20
        user.conditionType= new ArrayList<>(Arrays.asList(DDIConditionType.HEPATITIS_C.label));
        for (String c : pageObj.ddiHepC.allButNoneHepC) {
            user.hepCMeds = new ArrayList<>(Arrays.asList(c));
            iterateThroughOptions(user,pageObj, c);
        }
        user.hepCMeds = pageObj.ddiHepC.allButNoneHepC;
        iterateThroughAllOptions(user,pageObj, pageObj.ddiHepC.allButNoneHepC, "Hep C");

        // Step 21 -35
        user.conditionType= new ArrayList<>(Arrays.asList(DDIConditionType.THYROID_DISEASE.label));
        for (String c : pageObj.ddiThyroid.allButNoneThyroid) {
            user.thyroidMeds = new ArrayList<>(Arrays.asList(c));
            iterateThroughOptions(user,pageObj, c);
        }
        user.thyroidMeds = pageObj.ddiThyroid.allButNoneThyroid;
        iterateThroughAllOptions(user,pageObj, pageObj.ddiThyroid.allButNoneThyroidADBU, "Thyroid");

        // Step 36 -70
        user.conditionType= new ArrayList<>(Arrays.asList(DDIConditionType.EPILEPSY.label));
        for (String c : pageObj.ddiEpBipolar.allButNoneBipolar) {
            user.epBipolarMeds = new ArrayList<>(Arrays.asList(c));
            iterateThroughOptions(user,pageObj, c);
        }
        user.epBipolarMeds = pageObj.ddiEpBipolar.allButNoneBipolar;
        iterateThroughAllOptions(user,pageObj, pageObj.ddiEpBipolar.allButNoneBipolar, "Epilepsy_Bipolar");

        // Step 71-110
        user.conditionType= new ArrayList<>(Arrays.asList(DDIConditionType.HIV.label));
        for (String c : pageObj.ddihiv.allButNoneHIV) {
            user.hivMeds = new ArrayList<>(Arrays.asList(c));
            iterateThroughOptions(user,pageObj, c);
        }
        user.hivMeds = pageObj.ddihiv.allButNoneHIV;
        iterateThroughAllOptions(user,pageObj, pageObj.ddihiv.allButNoneHIV, "HIV");

        // Step 111-130
        user.conditionType= new ArrayList<>(Arrays.asList(DDIConditionType.HIGH_CHOLESTEROL.label));
        for (String c : pageObj.ddiHighCholesterol.allButNoneHC) {
            user.highCholMeds = new ArrayList<>(Arrays.asList(c));
            iterateThroughOptions(user,pageObj, c);
        }
        user.highCholMeds = pageObj.ddiHighCholesterol.allButNoneHC;
        iterateThroughAllOptions(user,pageObj, pageObj.ddiHighCholesterol.allButNoneHC, "HighCholesterol");
/*
        // Step 131-139
        user.conditionType= pageObj.ddiCondition.allButNone;
        user.hepCMeds=new ArrayList<>(Arrays.asList(DDIHepCType.OMBITASVIR.label));
        user.thyroidMeds =new ArrayList<>(Arrays.asList(DDIThyroidType.LEVOTHYROXINE.label));
        user.epBipolarMeds = new ArrayList<>(Arrays.asList(DDIEpBipolarType.BARBITUATES.label));
        user.hivMeds =new ArrayList<>(Arrays.asList(DDIHIVType.FOSAMPRENAVIR.label));
        user.highCholMeds=new ArrayList<>(Arrays.asList(DDIHighCholType.ATORVASTATIN.label));
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        new DexterNavigations(driver).DDIPath(user, pageObj.antifungal, report);
        report.addScreenshotStep("Step137_Antifungal");
        new DexterHFWrappers(driver).runAntifungalToADBU(user, pageObj.antifungal, pageObj.adbu, report);
        pageObj.adbu.verifyConditionIsListed(DDIHepCType.OMBITASVIR.label, report);
        pageObj.adbu.verifyConditionIsListed(DDIThyroidType.LEVOTHYROXINE.adbuText, report);
        pageObj.adbu.verifyConditionIsListed(DDIEpBipolarType.BARBITUATES.label, report);
        pageObj.adbu.verifyConditionIsListed(DDIHIVType.FOSAMPRENAVIR.label, report);
        pageObj.adbu.verifyConditionIsListed(DDIHighCholType.ATORVASTATIN.label, report);
        report.addScreenshotStep("Step139_ADBU");

        //140-148 All meds
        user.conditionType= pageObj.ddiCondition.allButNone;
        user.hepCMeds=pageObj.ddiHepC.allButNoneHepC;
        user.thyroidMeds =pageObj.ddiThyroid.allButNoneThyroid;
        user.epBipolarMeds = pageObj.ddiEpBipolar.allButNoneBipolar;
        user.hivMeds =pageObj.ddihiv.allButNoneHIV;
        user.highCholMeds=pageObj.ddiHighCholesterol.allButNoneHC;
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        new DexterNavigations(driver).DDIPath(user, pageObj.antifungal, report);
        report.addScreenshotStep("Step146_Antifungal");
        new DexterHFWrappers(driver).runAntifungalToADBU(user, pageObj.antifungal, pageObj.adbu, report);
        pageObj.adbu.verifyConditionsListed(pageObj.ddiHepC.allButNoneHepC, report);
        pageObj.adbu.verifyConditionsListed(pageObj.ddiThyroid.allButNoneThyroidADBU, report);
        pageObj.adbu.verifyConditionsListed(pageObj.ddiEpBipolar.allButNoneBipolar, report);
        pageObj.adbu.verifyConditionsListed(pageObj.ddihiv.allButNoneHIV, report);
        pageObj.adbu.verifyConditionsListed(pageObj.ddiHighCholesterol.allButNoneHC, report);
        report.addScreenshotStep("Step148_ADBU");

        //149-164 Rotate through DDI conditions. No meds on follow-up. Verify at antifungals
        user.hepCMeds=new ArrayList<>(Arrays.asList(DDIHepCType.NONE_OF_THESE.label));
        user.thyroidMeds =new ArrayList<>(Arrays.asList(DDIThyroidType.NO_THYROID_MEDS.label));
        user.epBipolarMeds = new ArrayList<>(Arrays.asList(DDIEpBipolarType.NONE_OF_THESE.label));
        user.hivMeds =new ArrayList<>(Arrays.asList(DDIHIVType.NONE_OF_THESE.label));
        user.highCholMeds=new ArrayList<>(Arrays.asList(DDIHighCholType.NONE_OF_THESE.label));
        for (String c : pageObj.ddiCondition.allButNone) {
            user.conditionType= new ArrayList<>(Arrays.asList(c));
            new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.antifungal, report);
            report.addScreenshotStep(c+"None_Antifungal");
        }

        //165-169 All DDIs, no follow-up
        user.conditionType=pageObj.ddiCondition.allButNone;
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.antifungal, report);
        report.addScreenshotStep("Step170_Antifungal");
        */

    }
}
