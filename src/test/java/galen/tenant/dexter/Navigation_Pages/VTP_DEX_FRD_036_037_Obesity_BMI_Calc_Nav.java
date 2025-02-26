package galen.tenant.dexter.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_036_037_Obesity_BMI_Calc_Nav extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_036: If the user’s BMI is less than 35, the application shall navigate to the " +
            "Condition/DDI screen\n" +
            "DEX_FRD_037: If the user’s BMI is equal to or greater than 35, the application shall flag the health survey" +
            " for ADBU and navigate to the Condition/DDI screen\n";
    static String NOTES = "The following scenario(s) are verified in this protocol:\n" +
            "-\tCalculation of BMI less than 35 in the metrics CSV\n" +
            "-\tNavigation from Obesity/BMI to Conditions/DDI Screen for BMI less than 35\n" +
            "-\tCalculation of BMI greater than 35 in the metrics CSV\n" +
            "-\tNavigation from Obesity/BMI to Conditions/DDI Screen for BMI greater than 35\n" +
            "In the scenarios below, using a height of 5 feet, 5 inches, the following weights bring the following " +
            "condition:\n" +
            "-\t210 lbs will calculate to 34.9454702\n" +
            "-\t211 lbs will calculate to 35.1118772 \n";
    static String REQUIREMENTS = "DEX_FRD_036, DEX_FRD_037";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_036_037_Obesity_BMI_Calc_Nav";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_036_037_Obesity_BMI_Calc_Nav() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_036_037_Obesity_BMI_Calc_Nav_Test() throws IOException {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_036_037 – Obesity/BMI Calculation and Navigation ";
        BasicHelpers bh = new BasicHelpers(driver);
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        StudyAdminPageObj sp = new StudyAdminPageObj(driver);

        user.weight="210";
        user.height="5 feet, 5 inches";
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.obesity, report);
        pageObj.obesity.enterHeightAndWeightAndProgress(user, pageObj.ddiCondition,report);
        report.addScreenshotStep("Step2_210_5_5");

        sp.login.load(UrlType.STUDY);
        bh.downloadCSVAndVerify("Step3_BMI", sp, report);
        File testOutput = new File("reports/"+reportName+"/Step3_BMI");
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "BMI", "34.95", report );

        user.weight="211";
        pageObj.pritUnl.load(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.obesity, report);
        pageObj.obesity.enterHeightAndWeightAndProgress(user, pageObj.ddiCondition, report);
        report.addScreenshotStep("Step5_211_5_5");

        pageObj.ddiCondition.selectCheckboxesAndProgress(user.conditionType, pageObj.antifungal, report);
        new DexterHFWrappers(driver).runAntifungalToADBU(user, pageObj.antifungal,pageObj.adbu,report);
        pageObj.adbu.verifyConditionIsListed("BMI", report);
        report.addScreenshotStep("Step7_211_5_5");

        sp.login.load(UrlType.STUDY);
        bh.downloadCSVAndVerify("Step8_BMI_ADBU", sp, report);
        testOutput = new File("reports/"+reportName+"/Step8_BMI_ADBU");
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "InitialOutcome", "ADBU", report );
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "BMI", "35.11", report );
    }
}
