package galen.tenant.dexter.DNU;

import galen.base.BaseTest;
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
import java.util.LinkedHashMap;

public class VTP_DEX_FRD_042_Smoking_Birth_Year_35_or_Older_DNU extends BaseTest {
    static String OBJECTIVE = "To verify If the users age is ≥ 35 when entered on the Smoking or Vape – Year of Birth " +
            "Screen, the application shall end the health survey and navigate to the Smoking or Vape ≥ 35 DNU Screen.";
    static String NOTES = "The following verification scenarios are contained in this protocol:\n" +
            "-\tSmoking or Vape ≥ 35 DNU Screen for users over 35 who answered the following on the Smoking or Vape " +
            "Screen:\n" +
            "o\tI smoke or vape regularly\n" +
            "o\tI smoke or vape occasionally \n" +
            "o\tI rarely smoke or vape\n" +
            "-\tSmoking or Vape >= 35 DNU Screen for users who are 35 (answering Yes to already had birthday this year) " +
            "who answered the following on the Smoking Screen:\n" +
            "o\tI smoke or vape regularly\n" +
            "o\tI smoke or vape occasionally \n" +
            "o\tI rarely smoke or vape\n" +
            "-\tSmoking or Vape >= 35 DNU Screen for users who are 35 (answering No to already had birthday this year) " +
            "who answered the following on the Smoking Screen:\n" +
            "o\tI smoke or vape regularly\n" +
            "o\tI smoke or vape occasionally \n" +
            "o\tI rarely smoke or vape";
    static String REQUIREMENTS = "DEX_FRD_042";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_042_Smoking_Birth_Year_35_or_Older_DNU";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    DexterPageObj pageObj;
    CommonPageFeatures common;

    VTP_DEX_FRD_042_Smoking_Birth_Year_35_or_Older_DNU()  {
        VERSIONHISTORY.add("1.0;11NOV2022;Initial Test Script;Name Redacted");
        VERSIONHISTORY.add("2.0;18JUN2024;Per CADENCE-529: Removed N/A from Actual Result column for Happy flow execution related steps\n" +
                "Per CADENCE-591: Update Test Steps for modified assessment and navigation;Name Redacted");

    }

    void loopSmokingOptions(DexterUser user, LinkedHashMap<String,String> smokingSettings) throws InterruptedException {
        for ( HashMap.Entry<String, String> e: smokingSettings.entrySet()) {
            new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.smoking, report);
            pageObj.smoking.selectRadioResponseAndProgress(e.getKey(), pageObj.birthYear, report);
            pageObj.birthYear.fillOutBirthdayAndProgress(user, pageObj.kickoutPage, report);
            report.addScreenshotStep("Step"+ e.getValue()+"_DNU");
        }
    }

    @Test
    public void  VTP_DEX_FRD_042_Smoking_Birth_Year_35_or_Older_DNU_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_042 – Smoker 35 or Over Navigates to DNU Screen";
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        int currentYear = Year.now().getValue();
        user.dobYear=String.valueOf((currentYear-36));
        user.hadBirthday="Yes";
        pageObj = new DexterPageObj(driver);
        common = new CommonPageFeatures(driver);


        // Test data map
        LinkedHashMap<String,String> smokingSettings = new LinkedHashMap<>();
        smokingSettings.put(SmokeType.SMOKE_REGULARLY.label,"3");
        smokingSettings.put(SmokeType.RARELY_SMOKE.label,"6");
        smokingSettings.put(SmokeType.SMOKE_OCCASIONALLY.label,"9");

        new PritUnlPage(driver).authenticateUserIfRequired();
        loopSmokingOptions(user, smokingSettings);

        user.dobYear=String.valueOf((currentYear-35));
        smokingSettings.put(SmokeType.SMOKE_REGULARLY.label,"12");
        smokingSettings.put(SmokeType.RARELY_SMOKE.label,"15");
        smokingSettings.put(SmokeType.SMOKE_OCCASIONALLY.label,"18");

        loopSmokingOptions(user, smokingSettings);

        user.dobYear=String.valueOf((currentYear-36));
        user.hadBirthday="No";
        smokingSettings.put(SmokeType.SMOKE_REGULARLY.label,"21");
        smokingSettings.put(SmokeType.RARELY_SMOKE.label,"24");
        smokingSettings.put(SmokeType.SMOKE_OCCASIONALLY.label,"27");

        loopSmokingOptions(user, smokingSettings);
    }
}
