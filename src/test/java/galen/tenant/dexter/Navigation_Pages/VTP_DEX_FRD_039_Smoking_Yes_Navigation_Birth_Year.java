package galen.tenant.dexter.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.SmokeType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
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
        VERSIONHISTORY.add("1.0;11NOV2022;Initial Test Script;Name Redacted");
        VERSIONHISTORY.add("2.0;18JUN2024;Per CADENCE-529: Removed N/A from Actual Result column for Happy flow execution related steps\n" +
                "Per CADENCE-591: Update Test Steps for modified assessment and navigation;Sharon Graves");
        VERSIONHISTORY.add("3.0;26JUL2024;Per CADENCE-607: Updated Step 4 to reflect the FRD;Name Redacted");
    }

    @Test
    public void  VTP_DEX_FRD_039_Smoking_Yes_Navigation_Birth_Year_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_039_Smoking Select Yes Option Navigates to Year of Birth screen";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);
        int currentYear = Year.now().getValue();

        new PritUnlPage(driver).authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.smoking, report);
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
