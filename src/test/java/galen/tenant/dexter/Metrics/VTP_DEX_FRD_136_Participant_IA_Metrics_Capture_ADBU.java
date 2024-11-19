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
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_136_Participant_IA_Metrics_Capture_ADBU extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_136_Participant_IA_Metrics_Capture_ADBU";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_136_Participant_IA_Metrics_Capture_ADBU() {
        VERSIONHISTORY.add("1.0;10MAR2023;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_136_Participant_IA_Metrics_Capture_ADBU_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_136 – User Initial Assessment Metrics Provided in CSV (ADBU)";
        BasicHelpers bh = new BasicHelpers(driver);
        DexterMetricsRecord dmr = new DexterMetricsRecord();
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_wBP();
        DexterPageObj pageObj = new DexterPageObj(driver);
        StudyAdminPageObj sp = new StudyAdminPageObj(driver);
        CSVHelpers csv = new CSVHelpers(driver);
        ConfigLoader cl = new ConfigLoader();

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.METRICS);
        pageObj.pritUnl.load(UrlType.METRICS);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.usedProduct, report);
        report.addStep("Note assessment id", "Assessment id noted", "Assessment id is "+
                user.assessmentID, true);

        dmr.AssessmentID=driver.getCurrentUrl().split("/")[4].toUpperCase();
        dmr.AssessmentNumber=user.assessmentID;
        user.email = cl.getMetricsEmail();
        dmr.ParticipantID=cl.getMetricsPartID();
        dmr.ExecutedBy=cl.getMetricsPartID();
        dmr.HealthSurveyStartTimestamp = ZonedDateTime.now().toString();
        dmr.PrivacyAcknowledgement="TRUE";
        dmr.PrivacyAcknowledgementTimestamp = ZonedDateTime.now().toString();
        dmr.ViewFullPrivacyNotice="FALSE";

        pageObj.pritUnl.load(UrlType.STUDY);
        bh.downloadIndividualCSVAndVerify("Step2_Individual_CC", dmr.AssessmentNumber, sp, user.email,report);
        String testOutputPath = "reports/"+reportName+"/Step2_Individual_CC";
        File testOutput = new File(testOutputPath);
        csv.verifyAllMetricsValues(dmr, testOutput, report);

        dmr.PriorUse="FALSE";
        dmr.ConfCustomer="TRUE";
        dmr.InitialOutcome = "ADBU";
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
        dmr.EverHadCancer = "TRUE";
        dmr.BreastCancer = "FALSE";
        dmr.EndometrialUterineCancer = "FALSE";
        dmr.Melanoma = "FALSE";
        dmr.OvarianCancer = "FALSE";
        dmr.LungBronchialAdenocarcinoma = "FALSE";
        dmr.Meningioma = "FALSE";
        dmr.LiverCancerListofCancers = "FALSE";
        dmr.OtherCancer = "TRUE";
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
        dmr.HepCMedConditions = "TRUE";
        dmr.ThyroidDisease   = "TRUE";
        dmr.Epilepsy   = "TRUE";
        dmr.BipolarDisorder   = "TRUE";
        dmr.HIV   = "TRUE";
        dmr.HighCholesterol   = "TRUE";
        dmr.MedConditionsNone   = "FALSE";
        dmr.Ombitasvir = "FALSE";
        dmr.Paritaprevir = "FALSE";
        dmr.RitonavirWithDasabuvir ="FALSE";
        dmr.HepCMedsNone  = "TRUE";
        dmr.Levothyroxine =  "FALSE";
        dmr.OtherThyroidMed  = "FALSE";
        dmr.ThyroidMedsNone =  "TRUE";
        dmr.Barbiturates = "FALSE";
        dmr.Felbamate   = "FALSE";
        dmr.Lamotrigine   = "FALSE";
        dmr.Phenytoin   = "FALSE";
        dmr.Primidone   = "FALSE";
        dmr.Rufinamide  = "FALSE";
        dmr.EpilepsyBipolarMedsNone = "TRUE";
        dmr.Fosamprenavir   = "FALSE";
        dmr.Darunavir   = "FALSE";
        dmr.Efavirenz   = "FALSE";
        dmr.Etravirine = "FALSE";
        dmr.Nelfinavir = "FALSE";
        dmr.Nevirapine = "FALSE";
        dmr.Ritonavir = "FALSE";
        dmr.HIVMedsNone = "TRUE";
        dmr.Atorvastatin = "FALSE";
        dmr.Colesevelam = "FALSE";
        dmr.Rosuvastatin = "FALSE";
        dmr.CholesterolMedsNone = "TRUE";
        dmr.AntifungalProducts = "FALSE";
        dmr.Fluconazole =  "N/A";
        dmr.Griseofulvin =  "N/A";
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
        dmr.Depression = "TRUE";
        dmr.ClinicalDepression = "TRUE";
        dmr.ClinicalDepressionModal = "TRUE";
        dmr.KnowsBP = "\"YES, I KNOW MY BLOOD PRESSURE NUMBERS\"";
        dmr.BPConf = "TRUE";
        dmr.SysBP = "115";
        dmr.DiaBP = "75";
        dmr.ConfAnswers = "TRUE";
        dmr.ConfModal = "TRUE";
        dmr.ConfTimestamp = "2024-10-08T13:18:00Z";
        dmr.ADBU = "TRUE";
        dmr.ADBUApprovedModal = "TRUE";
        dmr.ADBUApprovedTimestamp = "2024-10-08T13:18:00Z";
        dmr.ADBUNotApprovedModal = "N/A";
        dmr.ADBUNotApprovedTimestamp = "N/A";
        dmr.FinalBPConf = "N/A";
        dmr.FinalSysBP = "N/A";
        dmr.FinalDiaBP = "N/A";
        dmr.UpdatedInitialOutcome="N/A";

        pageObj.pritUnl.load(UrlType.METRICS);
        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_wBP();
        user.email = cl.getMetricsEmail();
        user.depression="Yes";
        user.diagnosedDepression="Yes";
        user.systolic="115";
        user.diastolic="75";
        new DexterHFWrappers(driver).runDexterHFADBUwBP(user, pageObj.depression, report);
        dmr.AssessmentID=driver.getCurrentUrl().split("/")[4].toUpperCase();
        pageObj.depression.clickYesNoNextToPage("Yes", pageObj.diagnosedDepression, report);
        pageObj.diagnosedDepression.clickYesAndAddressModalToPage(pageObj.knowBPNumber,
                report);
        pageObj.knowBPNumber.clickYesAndAddressModalToPage(pageObj.enterBP, user.measuredIn3Months,report);
        pageObj.enterBP.enterBPAndProgress(user, pageObj.review,report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbu,report);
        pageObj.adbu.addressConfirmationsAndProgress("Yes", pageObj.purchaseOptions, report);

        pageObj.pritUnl.load(UrlType.STUDY);
        dmr.AssessmentNumber= user.assessmentID;
        report.addStep("Note assessment id", "Assessment id noted", "Assessment id is "+
                user.assessmentID, true);

        bh.downloadIndividualCSVAndVerify("Step16_Individual_CC", dmr.AssessmentNumber, sp, user.email,report);
        testOutput = new File("reports/"+reportName+"/Step16_Individual_CC");
        csv.verifyAllMetricsValues(dmr, testOutput, report);

        pageObj.pritUnl.load(UrlType.STUDY);
        bh.downloadCSVAndVerify("Step17_Bulk", sp, report);
        testOutput = new File("reports/"+reportName+"/Step17_Bulk");
        csv.verifyAllMetricsValues(dmr, testOutput, report);
    }
}
