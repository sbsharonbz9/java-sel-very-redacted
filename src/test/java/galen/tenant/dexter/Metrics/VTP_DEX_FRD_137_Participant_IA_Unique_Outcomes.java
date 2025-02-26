package galen.tenant.dexter.Metrics;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.*;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_137_Participant_IA_Unique_Outcomes extends BaseTest {
    static String OBJECTIVE = "To verify the application shall capture a unique metrics record if users:\n" +
            "•\tSuccessfully complete the assessment\n" +
            "•\tUtilize the “exit assessment” modal";
    static String NOTES = "This protocol contains the following verification scenarios:\n" +
            "-\tCapture metrics InitialOutcome of OK for participant deemed as Safe to Use after completing health survey\n" +
            "-\tCapture metrics InitialOutcome of EXIT for participant whom has exited the health survey";
    static String REQUIREMENTS = "DEX_FRD_137";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_137_Participant_IA_Unique_Outcomes";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_137_Participant_IA_Unique_Outcomes() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_137_Participant_IA_Unique_Outcomes_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_137 – Participant Initial Assessment Unique Outcomes";
        BasicHelpers bh = new BasicHelpers(driver);
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        StudyAdminPageObj sp = new StudyAdminPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        new PritUnlPage(driver).authenticateUserIfRequired(UrlType.DEXTER);

        //OK
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.purchaseOptions, report);
        sp.login.load(UrlType.STUDY);
        bh.downloadCSVAndVerify("Step2_CSV_OK", sp, report);
        String testOutputPath = "reports/"+reportName+"/Step2_CSV_OK";
        File testOutput = new File(testOutputPath);
        report.addStep("Note assessment id", "Assessment id noted", "Assessment id is "+
                user.assessmentID, true);
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "InitialOutcome",
                "OK", report );

        //EXIT
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.depression, report);
        common.clickBrowserBackToModal(report);
        bh.verifyClickToPageTransition(pageObj.welcomePage, bh.getWebElement(common.exitLeaveButton),"Leave", report);
        sp.login.load(UrlType.STUDY);
        bh.downloadCSVAndVerify("Step6_CSV_EXIT", sp, report);
        testOutputPath = "reports/"+reportName+"/Step6_CSV_EXIT";
        testOutput = new File(testOutputPath);
        report.addStep("Note assessment id", "Assessment id noted", "Assessment id is "+
                user.assessmentID, true);
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "InitialOutcome",
                "EXIT", report );
    }
}
