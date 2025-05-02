package galen.tenant.dx.SinglePageContent;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import galen.pages.tenant.dx.InitialAssessment.ObesityBMI;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VTP_DEX_FRD_032_033_034_035_Obesity_BMI_Fields extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_032: The application provide a control that requires the user to enter their " +
            "current height on the Obesity/BMI Screen, by selecting from a drop-down list within a range of 4’10 " +
            "inches to 6’9 inches, within 1 inch increments\n" +
            "DEX_FRD_033: The application shall provide a control that requires the user to enter their current " +
            "weight as an integer greater than or equal to 40 pounds, on the Obesity/BMI screen.\n" +
            "DEX_FRD_034: If the user enters a current weight value of < 40 pounds on the Obesity/BMI Screen, the " +
            "application shall pre-sent an error message and provide the ability to update the entry.\n" +
            "DEX_FRD_035: After entering current height and weight, if the user selects “Next”, the application " +
            "shall calculate and store the user’s BMI, on the Obesity/BMI Screen.\n";
    static String NOTES = "The following verification scenario(s) are verified in this protocol:\n" +
            "-\tNext button is disabled by default\n" +
            "-\tNext button is disabled if Weight input field is entered but no Height has been selected\n" +
            "-\tHeight is a dropdown field\n" +
            "-\tNext button is enabled after Weight input field is entered and Height dropdown field is selected\n" +
            "-\tInvalid error for entry of 0 in Weight input field\n" +
            "-\tInvalid error for 1 digit value in Weight input field\n" +
            "-\tConfirmation modal triggered by value less than 40 in Weight input field\n" +
            "-\tCalculation of BMI in metrics record";
    static String REQUIREMENTS = "DEX_FRD_032, DEX_FRD_033, DEX_FRD_034, DEX_FRD_035";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_032_033_034_035_Obesity_BMI_Fields";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_032_033_034_035_Obesity_BMI_Fields() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_032_033_034_035_Obesity_BMI_Fields_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_032_033_034_035 – Obesity/BMI Fields";
        BasicHelpers bh = new BasicHelpers(driver);
        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);

        CommonPageFeatures common = new CommonPageFeatures(driver);
        ObesityBMI obesity = pageObj.obesity;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.obesity, report);
        common.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step2_Initial");

        bh.sendTextFlex(pageObj.obesity.inputWeight, "110", "Weight", report);
        common.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step3_WeightOnly");

        List<String> heights = bh.getAllDropdownOptions(obesity.selectHeight);
        for (String height : heights) {
            bh.selectDropDownByText(obesity.selectHeight,height, "Height", report);
            common.verifyNextButtonEnabled(report);
            report.addScreenshotStep(height);
        }

        bh.sendTextFlex(obesity.inputWeight, "0", "Weight", null);
        common.clickNext(report);
        obesity.verifyWeightInputError(report);
        report.addScreenshotStep("Step29_Weight0");

        bh.sendTextFlex(obesity.inputWeight, "9", "Weight", null);
        common.clickNext(report);
        obesity.verifyWeightInputError(report);
        report.addScreenshotStep("Step30_Weight9");

        bh.sendTextFlex(obesity.inputWeight, "39", "Weight", null);
        common.clickNext(report);
        obesity.verifyWeightInputError(report);
        report.addScreenshotStep("Step31_Weight39");

        user.weight="351";
        obesity.enterHeightAndWeight(user, report);
        common.clickNextToPage(pageObj.ddiCondition, report);
        report.addScreenshotStep("Step32_DDI");

        StudyAdminPageObj sp = new StudyAdminPageObj(driver);
        sp.login.load(UrlType.STUDY);
        bh.downloadCSVAndVerify("Step33_BMI", sp, report);

        String testOutputPath = "reports/"+reportName+"/Step33_BMI";
        File testOutput = new File(testOutputPath);
        bh.compareCSVValueByAssessmentID(testOutput,user.assessmentID, "BMI", "58.41", report );
    }
}
