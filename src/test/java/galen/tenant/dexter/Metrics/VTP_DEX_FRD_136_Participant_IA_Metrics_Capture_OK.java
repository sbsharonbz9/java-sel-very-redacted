package galen.tenant.dexter.Metrics;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CSVHelpers;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterMetricsRecord;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import galen.utils.ConfigLoader;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_136_Participant_IA_Metrics_Capture_OK extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_136_Participant_IA_Metrics_Capture_OK";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_136_Participant_IA_Metrics_Capture_OK() {
        VERSIONHISTORY.add("1.0;10MAR2023;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_136_Participant_IA_Metrics_Capture_OK_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_136 – User Initial Assessment Metrics Provided in CSV (OK)";
        BasicHelpers bh = new BasicHelpers(driver);
        DexterMetricsRecord dmr = new DexterMetricsRecord();
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        StudyAdminPageObj sp = new StudyAdminPageObj(driver);
        CSVHelpers csv = new CSVHelpers(driver);
        ConfigLoader cl = new ConfigLoader();

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.METRICS);
        pageObj.pritUnl.load(UrlType.METRICS);
        user.systolic="115";
        user.diastolic="75";
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.purchaseOptions, report);

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
