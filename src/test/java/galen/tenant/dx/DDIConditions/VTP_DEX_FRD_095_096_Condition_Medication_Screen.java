package galen.tenant.dx.DDIConditions;

import galen.base.BaseTest;
import galen.enums.tenant.dx.*;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxNavigations;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
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
        VERSIONHISTORY.add(" ; ; ; ");
    }

    public void iterateThroughOptions(DxUser user, DxPageObj pageObj, String c) {
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        new DxNavigations(driver).DDIPath(user, pageObj.antifungal, report);
        report.addScreenshotStep(c+"_Antifungal");
        new DxHFWrappers(driver).runAntifungalToADBU(user, pageObj.antifungal, pageObj.adbu, report);
        if (c.equalsIgnoreCase(DDIThyroidType.LEVOTHYROXINE.label)) {
            pageObj.adbu.verifyConditionIsListed(DDIThyroidType.LEVOTHYROXINE.adbuText, report);
        } else if (c.equalsIgnoreCase(DDIThyroidType.DIFFERENT_THYROID_MEDS.label)) {
            pageObj.adbu.verifyConditionIsListed(DDIThyroidType.DIFFERENT_THYROID_MEDS.adbuText, report);
        } else {
            pageObj.adbu.verifyConditionIsListed(c, report);
        }
        report.addScreenshotStep(c + "_ADBU");
    }

    public void iterateThroughAllOptions(DxUser user, DxPageObj pageObj, ArrayList<String> c, String type) {
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        new DxNavigations(driver).DDIPath(user, pageObj.antifungal, report);
        report.addScreenshotStep(c+"_Antifungal");
        new DxHFWrappers(driver).runAntifungalToADBU(user, pageObj.antifungal, pageObj.adbu, report);
        pageObj.adbu.verifyConditionsListed(c, report);
        report.addScreenshotStep("All_"+type+"ADBU");
    }

    @Test
    public void VTP_DEX_FRD_095_096_Condition_Medication_Screen_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY,
                PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_095_096 – Conditions Medication Selection ";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired();

        // Step 1 - 20
        user.conditionType= new ArrayList<>(Arrays.asList(DDIConditionType.HEPATITIS_C.label));
        for (String c : pageObj.ddiHepC.getAllButNone()) {
            user.hepCMeds = new ArrayList<>(Arrays.asList(c));
            iterateThroughOptions(user,pageObj, c);
        }
        user.hepCMeds = pageObj.ddiHepC.getAllButNone();
        iterateThroughAllOptions(user,pageObj, pageObj.ddiHepC.getAllButNone(), "Hep C");

        // Step 21 -35
        user.conditionType= new ArrayList<>(Arrays.asList(DDIConditionType.THYROID_DISEASE.label));
        for (String c : pageObj.ddiThyroid.getAllButNone()) {
            user.thyroidMeds = new ArrayList<>(Arrays.asList(c));
            iterateThroughOptions(user,pageObj, c);
        }
        user.thyroidMeds = pageObj.ddiThyroid.getAllButNone();
        iterateThroughAllOptions(user,pageObj, pageObj.ddiThyroid.allButNoneThyroidADBU, "Thyroid");

        // Step 36 -70
        user.conditionType= new ArrayList<>(Arrays.asList(DDIConditionType.EPILEPSY.label));
        for (String c : pageObj.ddiEpBipolar.getAllButNone()) {
            user.epBipolarMeds = new ArrayList<>(Arrays.asList(c));
            iterateThroughOptions(user,pageObj, c);
        }
        user.epBipolarMeds = pageObj.ddiEpBipolar.getAllButNone();
        iterateThroughAllOptions(user,pageObj, pageObj.ddiEpBipolar.getAllButNone(), "Epilepsy_Bipolar");

        // Step 71-110
        user.conditionType= new ArrayList<>(Arrays.asList(DDIConditionType.HIV.label));
        for (String c : pageObj.ddihiv.getAllButNone()) {
            user.hivMeds = new ArrayList<>(Arrays.asList(c));
            iterateThroughOptions(user,pageObj, c);
        }
        user.hivMeds = pageObj.ddihiv.getAllButNone();
        iterateThroughAllOptions(user,pageObj, pageObj.ddihiv.getAllButNone(), "HIV");

        // Step 111-130
        user.conditionType= new ArrayList<>(Arrays.asList(DDIConditionType.HIGH_CHOLESTEROL.label));
        for (String c : pageObj.ddiHighCholesterol.getAllButNone()) {
            user.highCholMeds = new ArrayList<>(Arrays.asList(c));
            iterateThroughOptions(user,pageObj, c);
        }
        user.highCholMeds = pageObj.ddiHighCholesterol.getAllButNone();
        iterateThroughAllOptions(user,pageObj, pageObj.ddiHighCholesterol.getAllButNone(), "HighCholesterol");

        // Step 131-139
        user.conditionType= pageObj.ddiCondition.getAllButNone();
        user.hepCMeds=new ArrayList<>(Arrays.asList(DDIHepCType.OMBITASVIR.label));
        user.thyroidMeds =new ArrayList<>(Arrays.asList(DDIThyroidType.LEVOTHYROXINE.label));
        user.epBipolarMeds = new ArrayList<>(Arrays.asList(DDIEpBipolarType.BARBITUATES.label));
        user.hivMeds =new ArrayList<>(Arrays.asList(DDIHIVType.FOSAMPRENAVIR.label));
        user.highCholMeds=new ArrayList<>(Arrays.asList(DDIHighCholType.ATORVASTATIN.label));
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        new DxNavigations(driver).DDIPath(user, pageObj.antifungal, report);
        report.addScreenshotStep("Step137_Antifungal");

        new DxHFWrappers(driver).runAntifungalToADBU(user, pageObj.antifungal, pageObj.adbu, report);
        pageObj.adbu.verifyConditionIsListed(DDIHepCType.OMBITASVIR.label, report);
        pageObj.adbu.verifyConditionIsListed(DDIThyroidType.LEVOTHYROXINE.adbuText, report);
        pageObj.adbu.verifyConditionIsListed(DDIEpBipolarType.BARBITUATES.label, report);
        pageObj.adbu.verifyConditionIsListed(DDIHIVType.FOSAMPRENAVIR.label, report);
        pageObj.adbu.verifyConditionIsListed(DDIHighCholType.ATORVASTATIN.label, report);
        report.addScreenshotStep("Step139_ADBU");

        //140-148 All meds
        user.conditionType= pageObj.ddiCondition.getAllButNone();
        user.hepCMeds=pageObj.ddiHepC.getAllButNone();
        user.thyroidMeds =pageObj.ddiThyroid.getAllButNone();
        user.epBipolarMeds = pageObj.ddiEpBipolar.getAllButNone();
        user.hivMeds =pageObj.ddihiv.getAllButNone();
        user.highCholMeds=pageObj.ddiHighCholesterol.getAllButNone();
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.ddiCondition, report);
        new DxNavigations(driver).DDIPath(user, pageObj.antifungal, report);
        report.addScreenshotStep("Step146_Antifungal");
        new DxHFWrappers(driver).runAntifungalToADBU(user, pageObj.antifungal, pageObj.adbu, report);
        pageObj.adbu.verifyConditionsListed(pageObj.ddiHepC.getAllButNone(), report);
        pageObj.adbu.verifyConditionsListed(pageObj.ddiThyroid.allButNoneThyroidADBU, report);
        pageObj.adbu.verifyConditionsListed(pageObj.ddiEpBipolar.getAllButNone(), report);
        pageObj.adbu.verifyConditionsListed(pageObj.ddihiv.getAllButNone(), report);
        pageObj.adbu.verifyConditionsListed(pageObj.ddiHighCholesterol.getAllButNone(), report);
        report.addScreenshotStep("Step148_ADBU");

        //149-164 Rotate through DDI conditions. No meds on follow-up. Verify at antifungals
        user.hepCMeds=new ArrayList<>(Arrays.asList(DDIHepCType.NONE_OF_THESE.label));
        user.thyroidMeds =new ArrayList<>(Arrays.asList(DDIThyroidType.NO_THYROID_MEDS.label));
        user.epBipolarMeds = new ArrayList<>(Arrays.asList(DDIEpBipolarType.NONE_OF_THESE.label));
        user.hivMeds =new ArrayList<>(Arrays.asList(DDIHIVType.NONE_OF_THESE.label));
        user.highCholMeds=new ArrayList<>(Arrays.asList(DDIHighCholType.NONE_OF_THESE.label));
        for (String c : pageObj.ddiCondition.getAllButNone()) {
            user.conditionType= new ArrayList<>(Arrays.asList(c));
            new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.antifungal, report);
            report.addScreenshotStep(c+"None_Antifungal");
        }

        //165-169 All DDIs, no follow-up
        user.conditionType=pageObj.ddiCondition.getAllButNone();
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.antifungal, report);
        report.addScreenshotStep("Step170_Antifungal");
    }
}
