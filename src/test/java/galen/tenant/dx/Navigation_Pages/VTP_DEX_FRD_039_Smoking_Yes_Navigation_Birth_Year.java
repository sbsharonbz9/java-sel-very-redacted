package galen.tenant.dx.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.SmokeType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_039_Smoking_Yes_Navigation_Birth_Year extends BaseTest {
    static String OBJECTIVE = "To verify If the user selects any of the following on Smoking or Vape screen the " +
            "application shall navigate to the Smoking or Vape – Year of Birth Screen:\n" +
            "•\tI smoke or vape regularly\n" +
            "•\tI smoke or vape occasionally\n" +
            "•\tI rarely smoke or vape\n";
    static String NOTES = "The following verification scenario(s) are contained in this protocol:\n" +
            "-\tAfter submitting “I smoke or vape regularly”, Smoking or Vape – Year of Birth Screen displays, and Next " +
            "button is disabled by default due to the required fields are unanswered\n" +
            "-\tYear of Birth input field displays error message if entering less than 4-digits\n" +
            "-\tNext button is enabled when valid 4-digit number is entered\n" +
            "-\tAfter submitting “I smoke or vape occasionally”, Smoking or Vape – Year of Birth Screen displays, and " +
            "Next button is disabled by default due to the required fields are unanswered\n" +
            "-\tAfter submitting “I rarely smoke or vape”, Smoking or Vape – Year of Birth Screen displays, and Next " +
            "button is disabled by default due to the required fields are unanswered\n";
    static String REQUIREMENTS = "DEX_FRD_039";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_039_Smoking_Yes_Navigation_Birth_Year";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_039_Smoking_Yes_Navigation_Birth_Year()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void  VTP_DEX_FRD_039_Smoking_Yes_Navigation_Birth_Year_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_039_Smoking Select Yes Option Navigates to Year of Birth screen";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);
        int currentYear = Year.now().getValue();

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.smoking, report);
        pageObj.smoking.selectRadioResponseAndProgress(SmokeType.SMOKE_REGULARLY.label, pageObj.birthYear, report);
        common.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step2_Year_of_Birth");

        pageObj.birthYear.enterYear("200", report);
        common.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step3_200_Year_of_Birth");

        user.dobYear="";
        user.hadBirthday="Yes";
        pageObj.birthYear.fillOutBirthday(user, report);
        common.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step4_No_Year_Yes");

        pageObj.birthYear.enterYear("0000", report);
        common.clickNext(report);
        pageObj.birthYear.verifyYearErrorDisplayed(report);
        report.addScreenshotStep("Step5_0000_Error");

        pageObj.birthYear.enterYear("199", report);
        common.clickNext(report);
        pageObj.birthYear.verifyYearErrorDisplayed(report);
        report.addScreenshotStep("Step6_199_Error");

        user.dobYear=String.valueOf((currentYear-25));
        pageObj.birthYear.fillOutBirthday(user, report);
        pageObj.birthYear.verifyAllQuestionsAnswered(user, report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step7_Year-25");

        common.clickBackToPage(pageObj.smoking, report);

        pageObj.smoking.selectRadioResponseAndProgress(SmokeType.SMOKE_OCCASIONALLY.label, pageObj.birthYear, report);
        common.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step9_Year_of_Birth");

        pageObj.birthYear.fillOutBirthday(user, report);
        pageObj.birthYear.verifyAllQuestionsAnswered(user, report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step10_Year-25");

        common.clickBackToPage(pageObj.smoking, report);

        pageObj.smoking.selectRadioResponseAndProgress(SmokeType.RARELY_SMOKE.label, pageObj.birthYear, report);
        common.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step12_Year_of_Birth");

        pageObj.birthYear.fillOutBirthday(user, report);
        pageObj.birthYear.verifyAllQuestionsAnswered(user, report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step13_Year-25");
    }
}
