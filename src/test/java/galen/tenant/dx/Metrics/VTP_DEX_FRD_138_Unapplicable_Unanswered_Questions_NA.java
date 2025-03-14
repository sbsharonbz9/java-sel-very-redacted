package galen.tenant.dx.Metrics;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.*;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_FRD_138_Unapplicable_Unanswered_Questions_NA extends BaseTest {
    static String OBJECTIVE = "To verify for any assessment questions that are not applicable to a specific user, the " +
            "application shall populate the associated fields with NA.";
    static String NOTES = "This protocol contains the following verification scenario(s):\n" +
            "-\tUnanswered Metrics Questions contain NA in their respective metrics columns";
    static String REQUIREMENTS = "DEX_FRD_138";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_138_Unapplicable_Unanswered_Questions_NA";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_138_Unapplicable_Unanswered_Questions_NA() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_138_Unapplicable_Unanswered_Questions_NA_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_138 – Unapplicable Assessment Questions Display NA in Metrics";
        BasicHelpers bh = new BasicHelpers(driver);
        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        StudyAdminPageObj sp = new StudyAdminPageObj(driver);

        pageObj.pritUnlauthenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.purchaseOptions, report);
        report.addStep("Note assessment id", "Assessment id noted", "Assessment id is "+
                user.assessmentID, true);

        sp.login.load(UrlType.STUDY);
        bh.downloadCSVAndVerify("Step3_N_A", sp, report);
        String testOutputPath = "reports/"+reportName+"/Step3_N_A";
        File testOutput = new File(testOutputPath);

        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "DNUReason",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "DNUTimestamp",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "HormonalBC(B)",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "HormonalBCRiskModal",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "BirthYear",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "BDthisYear",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "BreastCancer",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "EndometrialUterineCancer",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "Melanoma",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "OvarianCancer",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "LungBronchialAdenocarcinoma",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "Meningioma",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "LiverCancerListofCancers",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "OtherCancer",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIHepCType.OMBITASVIR.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIHepCType.PARITAPREVIR.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "RitonavirWithDasabuvir",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "HepCMedsNone",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID,"Levothyroxine",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID,"OtherThyroidMed" ,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "ThyroidMedsNone",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIEpBipolarType.BARBITUATES.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIEpBipolarType.FELBAMATE.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIEpBipolarType.LAMOTRAGINE.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIEpBipolarType.PHENYTOIN.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIEpBipolarType.PRIMADONE.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIEpBipolarType.RUFINAMIDE.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "EpilepsyBipolarMedsNone",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIHIVType.FOSAMPRENAVIR.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIHIVType.DARUNAVIR.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIHIVType.EFAVIRENZ.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIHIVType.ETRAVIRINE.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIHIVType.NELFINAVIR.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIHIVType.NEVIRAPINE.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIHIVType.RITONAVIR.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "HIVMedsNone",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIHighCholType.ATORVASTATIN.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIHighCholType.COLESEVELAM.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, DDIHighCholType.ROSUVASTATIN.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "CholesterolMedsNone",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, AntifungalMedsType.GRISEOFULVIN.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "GriseofulvinUltramicrocrystalline",
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, AntifungalMedsType.ITRACONAZOLE.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, AntifungalMedsType.KETOCONAZOLE.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, AntifungalMedsType.VORICONAZOLE.label,
                "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "AntifungalMedsNone", "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "ClinicalDepression", "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "ClinicalDepressionModal", "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "ADBU", "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "ADBUApprovedModal", "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "ADBUApprovedTimestamp", "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "ADBUNotApprovedModal", "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "ADBUNotApprovedTimestamp", "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "FinalBPConf", "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "FinalSysBP", "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "FinalDiaBP", "N/A", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "UpdatedInitialOutcome", "N/A", report );
    }
}
