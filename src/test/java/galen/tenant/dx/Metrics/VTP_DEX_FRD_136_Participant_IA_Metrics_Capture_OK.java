package galen.tenant.dx.Metrics;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CSVHelpers;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.*;
import galen.pages.common.PritUnlPage;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import galen.utils.ConfigLoader;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_136_Participant_IA_Metrics_Capture_OK extends BaseTest {
    static String OBJECTIVE = "To verify the application shall collect the following metrics for each assessment:\n" +
            "\n" +
            "·\tAssessmentID – unique assessment ID assigned to the Assessment\n" +
            "·\tAssessmentNumber – unique assessment number that is displayed on the UI for the user to reference\n" +
            "·\tParticipantID – unique study participant ID\n" +
            "·\tExecutedBy – Participant ID or Clinician name who performed the health survey\n" +
            "·\tHealthSurveyStartTimestamp – timestamp of when the user starts taking the health survey\n" +
            "·\tPrivacyAcknowledgement – TRUE upon response\n" +
            "·\tPrivacyAcknowledgementTimestamp – timestamp of when user acknowledges privacy notice\n" +
            "·\tViewFullPrivacyNotice – TRUE upon user viewing full notice\t\n" +
            "·\tPriorUse – TRUE/FALSE upon response\n" +
            "·\tConfCustomer – populated with TRUE/FALSE upon response\n" +
            "·\tInitialOutcome – populated when the user confirms answers as OK, Exit, ADBU, BP, ADBU+BP, DNU or " +
            "Blank if answers are not confirmed\n" +
            "·\tDNUReason – populated with default to N/A, updates to capture DNU reason if outcome is DNU\n" +
            "·\tDNUTimestamp - populated with default to N/A, updates to capture timestamp of outcome if the " +
            "outcome is DNU\n" +
            "·\tPreventPregnancy – populated with TRUE/FALSE upon response\n" +
            "·\tMenstrualPeriods – populated with TRUE/FALSE upon response\n" +
            "·\tHormonalBC(A) – populated with TRUE/FALSE upon response\n" +
            "·\tHormonalBC(B) – populated with TRUE/FALSE upon response\n" +
            "·\tHormonalBCRiskModal – N/A when user selects ‘No’ on HormonalBC(A), TRUE upon response\n" +
            "·\tSmoking – populated with selection upon response\n" +
            "·\tBirthYear – populated with user entered birth year\n" +
            "·\tBDthisYear -– populated with TRUE/FALSE upon response\n" +
            "·\tEverHadCancer – populated with TRUE/FALSE upon response\n" +
            "·\tBreastCancer – N/A when user selects ‘’No’ on EverHadCancer; TRUE when selected from List Of Cancers\n" +
            "·\tEdometrialUterineCancer – N/A when user selects ‘’No’ on EverHadCancer, TRUE when selected from " +
            "List Of Cancers\n" +
            "·\tMelanoma – N/A when user selects ‘’No’ on EverHadCancer; TRUE when selected from List Of Cancers\n" +
            "·\tOvarianCancer – N/A when user selects ‘’No’ on EverHadCancer; TRUE when selected from List Of Cancers\n" +
            "·\tLungBronchialAdenocarcinoma – N/A when user selects ‘’No’ on EverHadCancer; TRUE when selected from List Of" +
            " Cancers\n" +
            "·\tMeningioma – N/A when user selects ‘’No’ on EverHadCancer; TRUE when selected from List Of Cancers\n" +
            "·\tLiverCancerListOfCancers - N/A when user selects ‘’No’ on EverHadCancer; TRUE when selected from List Of" +
            " Cancers\n" +
            "·\tOtherCancer - N/A when user selects ‘’No’ on EverHadCancer; TRUE when selected from List Of Cancers\n" +
            "·\tBPMeds – populated with TRUE/FALSE upon response\n" +
            "·\tChestPain – TRUE when selected from HeartConditions\n" +
            "·\tHeartAttack – TRUE when selected from HeartConditions\n" +
            "·\tStroke – TRUE when selected from HeartConditions\n" +
            "·\tMiniStroke – TRUE when selected from HeartConditions\n" +
            "·\tHeartConditionsNone – TRUE when selected from HeartConditions\n" +
            "·\tBloodClots – TRUE when selected from BloodClots\n" +
            "·\tBloodClottingDisorder – TRUE when selected from BloodClots\n" +
            "·\tFamilyHistoryBloodClottingDisorder – TRUE when selected from BloodClots\n" +
            "·\tBloodClotsNone – TRUE when selected from BloodClots\n" +
            "·\tOtherHeartIssues – populated with TRUE/FALSE upon response\n" +
            "·\tLiverDisease – TRUE when selected from LiverDisease\n" +
            "·\tLiverCancerLiverDisease – TRUE when selected from LiverDisease\n" +
            "·\tHepCLiverDisease – TRUE when selected from LiverDisease\n" +
            "·\tLiverDiseaseNone – TRUE when selected from LiverDisease\n" +
            "·\tUnexplainedVaginalBleeding – populated with TRUE/FALSE upon response\n" +
            "·\tDiabetes – populated with TRUE/FALSE upon response\n" +
            "·\tCurrentlyPregnant – populated with TRUE/FALSE upon response\n" +
            "·\tBreastfeeding – populated with TRUE/FALSE upon response\n" +
            "·\tPregnancyLoss – populated with TRUE/FALSE upon response\n" +
            "·\tMigrainesWithAura – populated with TRUE/FALSE upon response\n" +
            "·\tHeight – populated with user selected height\n" +
            "·\tWeight – populated with user entered weight\n" +
            "·\tBMI – populated with app calculated BMI based on user entered height and weight\n" +
            "·\tHepCMedConditions – TRUE when selected from MedConditions\n" +
            "·\tThyroidDisease – TRUE when selected from MedConditions\n" +
            "·\tEpilepsy – TRUE when selected from MedConditions\n" +
            "·\tBipolarDisorder – TRUE when selected from MedConditions\n" +
            "·\tHIV – TRUE when selected from MedConditions\n" +
            "·\tHighCholesterol – TRUE when selected from MedConditions\n" +
            "·\tMedConditionsNone – TRUE when selected from MedConditions\n" +
            "·\tOmbitasvir – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from HepCMeds\n" +
            "·\tParitaprevir – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from HepCMeds\n" +
            "·\tRitonavirWithDasabuvir – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from " +
            "HepCMeds\n" +
            "·\tHepCMedsNone – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from HepCMeds\n" +
            "·\tLevothyroxine – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from ThyroidMeds\n" +
            "·\tOtherThyroidMed – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from ThyroidMeds\n" +
            "·\tThyroidMedsNone – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from ThyroidMeds\n" +
            "·\tBarbiturates – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from EpilepsyBipolarMeds\n" +
            "·\tFelbamate – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from EpilepsyBipolarMeds\n" +
            "·\tLamotrigine – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from EpilepsyBipolarMeds\n" +
            "·\tPhenytoin – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from EpilepsyBipolarMeds\n" +
            "·\tPrimidone – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from EpilepsyBipolarMeds\n" +
            "·\tRufinamide – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from EpilepsyBipolarMeds\n" +
            "·\tEpilepsyBipolarMedsNone – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from EpilepsyBipolarMeds\n" +
            "·\tFosamprenavir – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from HIVMeds\n" +
            "·\tDarunavir – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from HIVMeds\n" +
            "·\tEfavirenz – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from HIVMeds\n" +
            "·\tEtravirine – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from HIVMeds\n" +
            "·\tNelfinavir – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from HIVMeds\n" +
            "·\tNevirapine – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from HIVMeds\n" +
            "·\tRitonavir – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from HIVMeds\n" +
            "·\tHIVMedsNone – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from HIVMeds\n" +
            "·\tAtorvastatin – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from CholesterolMeds\n" +
            "·\tColesevelam – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from CholesterolMeds\n" +
            "·\tRosuvastatin – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from CholesterolMeds\n" +
            "·\tCholesterolMedsNone – N/A when user selects ‘None of these’ on MedConditions; TRUE when selected from CholesterolMeds\n" +
            "·\tAntifungalProducts – populated with TRUE/FALSE upon response \n" +
            "·\tFluconazole – N/A when user selects ‘No’ on AntifungalProducts; TRUE when selected from AntifungalMeds\n" +
            "·\tGriseofulvin – N/A when user selects ‘No’ on AntifungalProducts; TRUE when selected from AntifungalMeds\n" +
            "·\tGriseofulvinUltramicrocrystalline – N/A when user selects ‘’No’ on AntifungalProducts; TRUE when selected from AntifungalMeds\n" +
            "·\tItraconazole – N/A when user selects ‘No’ on AntifungalProducts; TRUE when selected from AntifungalMeds\n" +
            "·\tKetoconazole – N/A when user selects ‘No’ on AntifungalProducts; TRUE when selected from AntifungalMeds\n" +
            "·\tVoriconazole – N/A when user selects ‘No’ on AntifungalProducts; TRUE when selected from AntifungalMeds\n" +
            "·\tAntifungalMedsNone – N/A when user selects ‘No’ on AntifungalProducts; TRUE when selected from AntifungalMeds\n" +
            "·\tRifabutin – TRUE when selected from OtherMeds\n" +
            "·\tRifampin – TRUE when selected from OtherMeds\n" +
            "·\tPrednisone – TRUE when selected from OtherMeds\n" +
            "·\tTemazepam – TRUE when selected from OtherMeds\n" +
            "·\tStJohnsWort – TRUE when selected from OtherMeds\n" +
            "·\tOtherMedsNone – TRUE when selected from OtherMeds\n" +
            "·\tGallbladderDisease – populated with TRUE/FALSE upon response\n" +
            "·\tDepression – populated with TRUE/FALSE upon response\n" +
            "·\tClinicalDepression – populated with TRUE/FALSE upon response\n" +
            "·\tClinicalDepressionModal – TRUE upon response\n" +
            "·\tKnowsBP – populated with TRUE/FALSE upon response\n" +
            "·\tBPConf – populated with TRUE/FALSE upon response, was BP measured in last 3 months\n" +
            "·\tSysBP – populated with user entered Systolic BP upon response\n" +
            "·\tDiaBP – populated with user entered Diastolic BP upon response \n" +
            "·\tConfAnswers – populated with TRUE upon response\n" +
            "·\tConfModal – populated with TRUE upon response\n" +
            "·\tConfTimestamp – populated with timestamp of when user confirmed answers on ConfModal\n" +
            "·\tADBU – populated with TRUE/FALSE upon response\n" +
            "·\tADBUApprovedModal – N/A when user selects ‘No' on ADBU; populated with TRUE upon response\n" +
            "·\tADBUApprovedTimestamp – N/A when user selects ‘No' on ADBU; populated with timestamp of when user " +
            "selected checkbox and confirmed doctor approval\n" +
            "·\tADBUNotApprovedModal – N/A when user selects ‘Yes' on ADBU; populated with TRUE upon response\n" +
            "·\tADBUNotApprovedTimestamp – N/A when user selects ‘Yes' on ADBU; populated with timestamp of when user " +
            "selected checkbox and confirmed doctor did not approve\n" +
            "·\tFinalBPConf - populated with TRUE/FALSE upon response, was BP measured in last 3 months (if user did not " +
            "initially submit BP, and has no other ADBU designation)\n" +
            "·\tFinalSysBP – populated with user entered Systolic BP upon response (if user did not initially answer, " +
            "and has no other ADBU designation) for initial flow\n" +
            "·\tFinalDiaBP - populated with user entered Diastolic BP upon response (if user did not initially answer, " +
            "and has no other ADBU designation) for initial flow\n" +
            "·\tUpdatedInitialOutcome - N/A when InitialOutcome is OK, DNU, or ADBU; populated with OK when user " +
            "submits acceptable BP numbers on the Final BP Numbers Screen; populated " +
            "with DNU when user submits high or dangerously high BP numbers on the Final BP Numbers Screen\n";
    static String NOTES = "This protocol contains the following verification scenario(s):\n" +
            "-\tCorrect field values are captured for OK scenario";
    static String REQUIREMENTS = "DEX_FRD_136";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_136_Participant_IA_Metrics_Capture_OK";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_136_Participant_IA_Metrics_Capture_OK() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_136_Participant_IA_Metrics_Capture_OK_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_136 – User Initial Assessment Metrics Provided in CSV (OK)";
        BasicHelpers bh = new BasicHelpers(driver);
        DxMetricsRecord dmr = new DxMetricsRecord();
        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        StudyAdminPageObj sp = new StudyAdminPageObj(driver);
        CSVHelpers csv = new CSVHelpers(driver);
        ConfigLoader cl = new ConfigLoader();

        new PritUnlPage(driver).authenticateUserIfRequired(UrlType.METRICS);
        pageObj.pritUnl.load(UrlType.METRICS);
        user.systolic="115";
        user.diastolic="75";
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.purchaseOptions, report);

        dmr.AssessmentID=driver.getCurrentUrl().split("/")[4].toUpperCase();
        user.email = cl.getMetricsEmail();
        dmr.ParticipantID=cl.getMetricsPartID();
        dmr.ExecutedBy=cl.getMetricsPartID();
        dmr.HealthSurveyStartTimestamp = ZonedDateTime.now().toString();
        dmr.PrivacyAcknowledgement="TRUE";
        dmr.PrivacyAcknowledgementTimestamp = LocalDateTime.now().toString();
        dmr.ViewFullPrivacyNotice="FALSE";
        dmr.PriorUse="FALSE";
        dmr.ConfCustomer="TRUE";
        dmr.InitialOutcome = "OK";
        dmr.DNUReason = "N/A";
        dmr.DNUTimestamp = "N/A";
        dmr.PreventPregnancy   = "TRUE";
        dmr.MenstrualPeriods   = "TRUE";
        dmr.HormonalBCA = "FALSE";
        dmr.HormonalBCB = "N/A";
        dmr.HormonalBCRiskModal = "N/A";
        dmr.Smoking = "I DON'T SMOKE OR VAPE";
        dmr.BirthYear = "N/A";
        dmr.BDthisYear = "N/A";
        dmr.BreastCancer = "N/A";
        dmr.EndometrialUterineCancer = "N/A";
        dmr.Melanoma = "N/A";
        dmr.OvarianCancer = "N/A";
        dmr.LungBronchialAdenocarcinoma = "N/A";
        dmr.Meningioma = "N/A";
        dmr.LiverCancerListofCancers = "N/A";
        dmr.OtherCancer = "N/A";
        dmr.BPMeds   = "FALSE";
        dmr.ChestPain   = "FALSE";
        dmr.HeartAttack   = "FALSE";
        dmr.Stroke   = "FALSE";
        dmr.MiniStroke   = "FALSE";
        dmr.HeartConditionsNone   = "TRUE";
        dmr.BloodClots   = "FALSE";
        dmr.BloodClottingDisorder= "FALSE";
        dmr.FamilyHistoryBloodClottingDisorder   = "FALSE";
        dmr.BloodClotsNone   = "TRUE";
        dmr.OtherHeartIssues = "FALSE";
        dmr.LiverDisease = "FALSE";
        dmr.LiverCancerLiverDisease   = "FALSE";
        dmr.HepCLiverDisease   = "FALSE";
        dmr.LiverDiseaseNone   = "TRUE";
        dmr.UnexplainedVaginalBleeding   = "FALSE";
        dmr.Diabetes   = "FALSE";
        dmr.CurrentlyPregnant = "FALSE";
        dmr.Breastfeeding   = "FALSE";
        dmr.PregnancyLoss   = "FALSE";
        dmr.MigrainesWithAura   = "FALSE";
        dmr.Height = "65";
        dmr.Weight = "125";
        dmr.BMI = "20.80";
        dmr.HepCMedConditions = "FALSE";
        dmr.ThyroidDisease   = "FALSE";
        dmr.Epilepsy   = "FALSE";
        dmr.BipolarDisorder   = "FALSE";
        dmr.HIV   = "FALSE";
        dmr.HighCholesterol   = "FALSE";
        dmr.MedConditionsNone   = "TRUE";
        dmr.Ombitasvir = "N/A";
        dmr.Paritaprevir = "N/A";
        dmr.RitonavirWithDasabuvir ="N/A";
        dmr.HepCMedsNone  = "N/A";
        dmr.Levothyroxine =  "N/A";
        dmr.OtherThyroidMed  = "N/A";
        dmr.ThyroidMedsNone =  "N/A";
        dmr.Barbiturates = "N/A";
        dmr.Felbamate   = "N/A";
        dmr.Lamotrigine   = "N/A";
        dmr.Phenytoin   = "N/A";
        dmr.Primidone   = "N/A";
        dmr.Rufinamide  = "N/A";
        dmr.EpilepsyBipolarMedsNone = "N/A";
        dmr.Fosamprenavir   = "N/A";
        dmr.Darunavir   = "N/A";
        dmr.Efavirenz   = "N/A";
        dmr.EverHadCancer="FALSE";
        dmr.Etravirine = "N/A";
        dmr.Nelfinavir = "N/A";
        dmr.Nevirapine = "N/A";
        dmr.Ritonavir = "N/A";
        dmr.HIVMedsNone = "N/A";
        dmr.Atorvastatin = "N/A";
        dmr.Colesevelam = "N/A";
        dmr.Rosuvastatin = "N/A";
        dmr.CholesterolMedsNone = "N/A";
        dmr.AntifungalProducts = "FALSE";
        dmr.Fluconazole = "N/A";
        dmr.Griseofulvin = "N/A";
        dmr.GriseofulvinUltramicrocrystalline = "N/A";
        dmr.Itraconazole = "N/A";
        dmr.Ketoconazole = "N/A";
        dmr.Voriconazole = "N/A";
        dmr.AntiFungalMedsNone = "N/A";
        dmr.Rifabutin = "FALSE";
        dmr.Rifampin = "FALSE";
        dmr.Prednisone = "FALSE";
        dmr.Temazepam = "FALSE";
        dmr.OtherMedsNone = "TRUE";
        dmr.GallbladderDisease = "FALSE";
        dmr.Depression = "FALSE";
        dmr.ClinicalDepression = "N/A";
        dmr.ClinicalDepressionModal = "N/A";
        dmr.KnowsBP = "\"YES, I KNOW MY BLOOD PRESSURE NUMBERS\"";
        dmr.BPConf = "TRUE";
        dmr.SysBP = "115";
        dmr.DiaBP = "75";
        dmr.ConfAnswers = "TRUE";
        dmr.ConfModal = "TRUE";
        dmr.ConfTimestamp = "2024-10-08T13:18:00Z";
        dmr.ADBU = "N/A";
        dmr.ADBUApprovedModal = "N/A";
        dmr.ADBUApprovedTimestamp = "N/A"; 
        dmr.ADBUNotApprovedModal = "N/A";
        dmr.ADBUNotApprovedTimestamp = "N/A";
        dmr.FinalBPConf = "N/A";
        dmr.FinalSysBP = "N/A";
        dmr.FinalDiaBP = "N/A";
        dmr.UpdatedInitialOutcome="N/A";

        pageObj.pritUnl.load(UrlType.STUDY);
        dmr.AssessmentNumber= user.assessmentID;
        report.addStep("Note assessment id", "Assessment id noted", "Assessment id is "+
                user.assessmentID, true);

        bh.downloadIndividualCSVAndVerify("Step3_Individual_CC", dmr.AssessmentNumber, sp, user.email,report);
        File testOutput = new File("reports/"+reportName+"/Step3_Individual_CC");
        csv.verifyAllMetricsValues(dmr, testOutput, report);

        pageObj.pritUnl.load(UrlType.STUDY);
        bh.downloadCSVAndVerify("Step4_Bulk", sp, report);
        testOutput = new File("reports/"+reportName+"/Step4_Bulk");
        csv.verifyAllMetricsValues(dmr, testOutput, report);
    }
}
