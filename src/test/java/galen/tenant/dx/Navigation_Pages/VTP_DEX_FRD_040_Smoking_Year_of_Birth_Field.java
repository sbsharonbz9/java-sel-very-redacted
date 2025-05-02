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

public class VTP_DEX_FRD_040_Smoking_Year_of_Birth_Field extends BaseTest {
    static String OBJECTIVE = "To verify the application shall provide an error message for the following year of birth entries on the Smoking or Vape – Year of Birth Screen:\n" +
            "· If the first 2 digits of the entered year are ≤ 18 (indicating a birth year prior to 1900)\n" +
            "· If the entered year > the current year";
    static String NOTES = "The following verification scenarios are contained in this protocol:\n" +
            "-\tFirst two digits in Birth Year are < 18, triggering error message\n" +
            "-\tFirst two digits in Birth Year are equal to 18, triggering error message\n" +
            "-\tThe birth year > [Current year], triggering error message";
    static String REQUIREMENTS = "DEX_FRD_040";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_040_Smoking_Year_of_Birth_Field";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_040_Smoking_Year_of_Birth_Field()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void  VTP_DEX_FRD_040_Smoking_Year_of_Birth_Field_Test()  {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_040 – Smoking Year of Birth";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);
        int currentYear = Year.now().getValue();

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.smoking, report);
        pageObj.smoking.selectRadioResponseAndProgress(SmokeType.SMOKE_REGULARLY.label, pageObj.birthYear, report);
        user.dobYear="1750";
        user.hadBirthday="Yes";
        pageObj.birthYear.fillOutBirthday(user, report);
        common.clickNext(report);
        pageObj.birthYear.verifyYearErrorDisplayed(report);
        report.addScreenshotStep("Step_3_1750_Error");

        pageObj.birthYear.enterYear("1899", report);
        common.clickNext(report);
        pageObj.birthYear.verifyYearErrorDisplayed(report);
        report.addScreenshotStep("Step_5_1899_Error");

        pageObj.birthYear.enterYear(String.valueOf(currentYear), report);
        common.clickNextToPage(pageObj.smokingDisclaimer, report);
        report.addScreenshotStep("Step_6_SmokingRisks");

        common.clickBackToPage(pageObj.birthYear, report);
        pageObj.birthYear.enterYear("1900", report);
        common.clickNextToPage(pageObj.kickoutPage, report);
        report.addScreenshotStep("Step_8_1900_DNU");
    }
}
