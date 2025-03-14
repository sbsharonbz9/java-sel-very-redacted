package galen.tenant.dx.OAuth;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
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

public class VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_ADBU_BP_Guest extends BaseTest {
    static String OBJECTIVE = "To verify on the Login Selection screen, if the user selects to continue as guest the application shall navigate to:\n" +
            "-\tADBU Screen if they triggered an ADBU and entered BP\n" +
            "-\tADBU/BP screen if they triggered an ADBU and did not enter BP\n" +
            "-\tBP Screen if they have no ADBU and did not enter BP\n";

    static String NOTES = "This protocol verifies the following scenario(s):\n" +
            "-\tGuest user did not enter BP and triggered ADBU for Obesity navigated from Log In Selection (Post Assessment) Screen to ADBU/BP Screen\n" +
            "-\tGuest user did not enter BP and triggered ADBU for Other Cancer navigated from Log In Selection (Post Assessment) Screen to ADBU/BP Screen\n" +
            "-\tGuest user did not enter BP and triggered ADBU for Hepatitis C Meds navigated from Log In Selection (Post Assessment) Screen to ADBU/BP Screen\n" +
            "-\tGuest user did not enter BP and triggered ADBU for Thyroid Disease Meds navigated from Log In Selection (Post Assessment) Screen to ADBU/BP Screen\n" +
            "-\tGuest user did not enter BP and triggered ADBU for Epilepsy Meds navigated from Log In Selection (Post Assessment) Screen to ADBU/BP Screen\n" +
            "-\tGuest user did not enter BP and triggered ADBU for HIV Meds navigated from Log In Selection (Post Assessment) Screen to ADBU/BP Screen\n" +
            "-\tGuest user did not enter BP and triggered ADBU for High Cholesterol Meds navigated from Log In Selection (Post Assessment) Screen to ADBU/BP Screen\n" +
            "-\tGuest user did not enter BP and triggered ADBU for Antifungal Meds navigated from Log In Selection (Post Assessment) Screen to ADBU/BP Screen\n" +
            "-\tGuest user did not enter BP and triggered ADBU for Other Medicine navigated from Log In Selection (Post Assessment) Screen to ADBU/BP Screen \n" +
            "-\tGuest user did not enter BP and triggered ADBU for Gallbladder navigated from Log In Selection (Post Assessment) Screen to ADBU/BP Screen\n" +
            "-\tGuest user did not enter BP and triggered ADBU for Clinical Depression navigated from Log In Selection (Post Assessment) Screen to ADBU/BP Screen\n" +
            "-\tGuest user did not enter BP and triggered ADBU for Multiple Triggers (All of the above) navigated from Log In Selection (Post Assess-ment) Screen to ADBU/BP Screen\n";
    static String REQUIREMENTS = "DEX_FRD_237";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_noBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_ADBU_BP_Guest";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_ADBU_BP_Guest() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_237_Log_In_Selection_2_Navigates_ADBU_BP_Guest_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_237 – Log In Selection 2 Navigates to ADBU + BP (If Trig-gered) Guest";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        DxHFWrappers dxHf = new DxHFWrappers(driver);

        pageObj.pritUnlauthenticateUserIfRequired(UrlType.DX);
        ArrayList<String> conditions = new ArrayList<String>();
        dxHf.runDxHFnoBPNonsmoker(user, pageObj.obesity, report);
        user.weight = "212";
        pageObj.obesity.enterHeightAndWeight(user, report);
        pageObj.obesity.clickNextToPage(pageObj.ddiCondition, report);
        new DxNavigations(driver).DDIPath(user, pageObj.antifungal, null);
        dxHf.runAntifungalToADBU(user, pageObj.antifungal, pageObj.adbubpScreen, report);
        pageObj.adbubpScreen.verifyConditionIsListed(pageObj.adbu.obesityCondition, report);
        report.addScreenshotStep("Step4_ADBU_BP_Screen");

        user.weight="125";
        user.cancerList= new ArrayList<>(Arrays.asList("Other cancer"));
        user.everHadCancer="Yes";
        dxHf.runDxHFnoBPNonsmoker(user, pageObj.adbubpScreen, report);
        pageObj.adbubpScreen.verifyConditionIsListed("Other cancer", report);
        report.addScreenshotStep("Step9_ADBU_Other_Cancer_Screen");

        user.everHadCancer="No";
        dxHf.runDxHFnoBPNonsmoker(user, pageObj.ddiCondition, report);
        pageObj.ddiCondition.selectCheckboxAndProgress(DDIConditionType.HEPATITIS_C.label, pageObj.ddiHepC, report);
        pageObj.ddiEpBipolar.selectCheckboxAndProgress(DDIHepCType.OMBITASVIR.label, pageObj.antifungal,report);
        dxHf.runAntifungalToADBU(user, pageObj.antifungal, pageObj.adbubpScreen, report);
        pageObj.adbubpScreen.verifyConditionIsListed(DDIHepCType.OMBITASVIR.label, report);
        report.addScreenshotStep("Step15_ADBU_Ombitasvir_Screen");

        dxHf.runDxHFnoBPNonsmoker(user, pageObj.ddiCondition, report);
        pageObj.ddiCondition.selectCheckboxAndProgress(DDIConditionType.THYROID_DISEASE.label, pageObj.ddiThyroid, report);
        pageObj.ddiThyroid.selectCheckboxAndProgress(DDIThyroidType.LEVOTHYROXINE.label, pageObj.antifungal,report);
        dxHf.runAntifungalToADBU(user, pageObj.antifungal, pageObj.adbubpScreen, report);
        pageObj.adbubpScreen.verifyConditionIsListed(DDIThyroidType.LEVOTHYROXINE.adbuText, report);
        report.addScreenshotStep("Step21_ADBU_Levothyroxine_Screen");

        dxHf.runDxHFnoBPNonsmoker(user, pageObj.ddiCondition, report);
        pageObj.ddiCondition.selectCheckboxAndProgress(DDIConditionType.BIPOLAR_DISORDER.label, pageObj.ddiEpBipolar, report);
        pageObj.ddiEpBipolar.selectCheckboxAndProgress(DDIEpBipolarType.BARBITUATES.label, pageObj.antifungal,report);
        dxHf.runAntifungalToADBU(user, pageObj.antifungal, pageObj.adbubpScreen, report);
        pageObj.adbubpScreen.verifyConditionIsListed(DDIEpBipolarType.BARBITUATES.label, report);
        report.addScreenshotStep("Step27_ADBU_Barbiturates_Screen");

        dxHf.runDxHFnoBPNonsmoker(user, pageObj.ddiCondition, report);
        pageObj.ddiCondition.selectCheckboxAndProgress(DDIConditionType.HIV.label, pageObj.ddihiv, report);
        pageObj.ddihiv.selectCheckboxAndProgress(DDIHIVType.FOSAMPRENAVIR.label, pageObj.antifungal,report);
        dxHf.runAntifungalToADBU(user, pageObj.otherMedication, pageObj.adbubpScreen,report);
        pageObj.adbubpScreen.verifyConditionIsListed(DDIHIVType.FOSAMPRENAVIR.label, report);
        report.addScreenshotStep("Step33_ADBU_Fosamprenavir_Screen");

        dxHf.runDxHFnoBPNonsmoker(user, pageObj.ddiCondition, report);
        pageObj.ddiCondition.selectCheckboxAndProgress(DDIConditionType.HIGH_CHOLESTEROL.label, pageObj.ddiHighCholesterol, report);
        pageObj.ddiHighCholesterol.selectCheckboxAndProgress(DDIHighCholType.ATORVASTATIN.label, pageObj.antifungal,report);
        dxHf.runAntifungalToADBU(user, pageObj.otherMedication, pageObj.adbubpScreen,report);
        pageObj.adbubpScreen.verifyConditionIsListed(DDIHighCholType.ATORVASTATIN.label, report);
        report.addScreenshotStep("Step39_ADBU_Atorvastatin_Screen");

        dxHf.runDxHFnoBPNonsmoker(user, pageObj.antifungal, report);
        pageObj.antifungal.clickYesNoNextToPage("Yes", pageObj.antifungalMeds, report);
        pageObj.antifungalMeds.selectCheckboxAndProgress(AntifungalMedsType.FLUCONAZOLE.label,pageObj.otherMedication,
                report);
        dxHf.runAntifungalToADBU(user, pageObj.otherMedication, pageObj.adbubpScreen,report);
        pageObj.adbubpScreen.verifyConditionIsListed(AntifungalMedsType.FLUCONAZOLE.label, report);
        report.addScreenshotStep("Step44_ADBU_Fluconazole_Screen");

        dxHf.runDxHFnoBPNonsmoker(user, pageObj.otherMedication, report);
        conditions.add(DDIOtherMedsType.RIFABUTIN.label);
        pageObj.otherMedication.selectCheckboxReponse(DDIOtherMedsType.RIFABUTIN.label, report);
        pageObj.otherMedication.clickNextToPage(pageObj.gallbladder, report);
        dxHf.runAntifungalToADBU(user, pageObj.gallbladder, pageObj.adbubpScreen,report);
        pageObj.adbubpScreen.verifyConditionIsListed(DDIOtherMedsType.RIFABUTIN.label, report);
        report.addScreenshotStep("Step49_ADBU_Rifabutin_Screen");

        dxHf.runDxHFnoBPNonsmoker(user, pageObj.gallbladder, report);
        pageObj.gallbladder.clickYesNoNextToPage("Yes", pageObj.depression, report);
        dxHf.runAntifungalToADBU(user, pageObj.depression, pageObj.adbubpScreen,report);
        pageObj.adbubpScreen.verifyConditionIsListed(pageObj.adbubpScreen.gallBladderCondition, report);
        report.addScreenshotStep("Step53_ADBU_Gallbladder_Disease_Screen");

        dxHf.runDxHFnoBPNonsmoker(user, pageObj.depression, report);
        pageObj.depression.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression, report);
        pageObj.diagnosedDepression.clickYesAndAddressModalToPage(pageObj.knowBPNumber, report);
        pageObj.knowBPNumber.selectRadioResponseAndProgress(user.knowBPType.label, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuth.chooseAccountType(user, report);
        pageObj.adbubpScreen.verifyAtPage(report);
        pageObj.adbubpScreen.verifyConditionIsListed(pageObj.adbubpScreen.depressionCondition, report);
        report.addScreenshotStep("Step61_ADBU_Clinical_Depression_Screen");

        conditions= new ArrayList<>();
        user.everHadCancer="Yes";
        user.cancerList=new ArrayList<>(Arrays.asList("Other cancer"));
        conditions.add(CancerType.Other_Cancer.label);
        user.weight = "212";
        conditions.add(pageObj.adbu.obesityCondition);
        user.conditionType=pageObj.ddiCondition.getAllButNone();
        user.hepCMeds = new ArrayList<>(Arrays.asList("Ombitasvir"));
        conditions.addAll(user.hepCMeds);
        user.thyroidMeds = new ArrayList<>(Arrays.asList(DDIThyroidType.LEVOTHYROXINE.label));
        conditions.add(DDIThyroidType.LEVOTHYROXINE.adbuText);
        user.epBipolarMeds= new ArrayList<>(Arrays.asList("Barbiturates"));
        conditions.addAll(user.epBipolarMeds);
        user.hivMeds = new ArrayList<>(Arrays.asList("Fosamprenavir"));
        conditions.addAll(user.hivMeds);
        user.highCholMeds = new ArrayList<>(Arrays.asList("Atorvastatin"));
        conditions.addAll(user.highCholMeds);
        user.isAntifungal="Yes";
        user.antiFungalMeds=new ArrayList<>(Arrays.asList("Fluconazole"));
        conditions.addAll(user.antiFungalMeds);
        user.otherMedicationType=new ArrayList<>(Arrays.asList("Rifabutin"));
        conditions.addAll(user.otherMedicationType);

        dxHf.runDxHFnoBPNonsmoker(user, pageObj.adbubpScreen, report);
        pageObj.adbubpScreen.verifyAtPage(report);
        pageObj.adbu.verifyConditionsListed(conditions, report);
        report.addScreenshotStep("Step93_ADBU_Screen");
    }
}

