package galen.tenant.dx.Navigation_Pages;

import galen.base.BaseTest;
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
import java.util.LinkedHashMap;

public class VTP_DEX_FRD_041_043_Smoking_Birth_Year_Under_35_Navigation extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_041: To verify after the user enters their year of birth and confirms if they’ve " +
            "had their birthday yet this year on the Smoking or Vape – Year of Birth Screen, the application shall " +
            "calculate and store the user’s age.\n" +
            "DEX_FRD_043: To verify if the users age is < 35 when entered on the Smoking or Vape – Year of Birth Screen," +
            " the application shall present a message to the user indicating increased risk and once acknowledged, " +
            "shall navigate to the Ever Had Cancer screen.";
    static String NOTES = "The following verification scenarios are contained in this protocol:\n" +
            "-\tCalculation of age, presentation of message indicating increased risk, and navigation to the Ever Had " +
            "Cancer Screen for users under 35 who answered the following on the Smoking Screen:\n" +
            "o\tI smoke or vape regularly\n" +
            "o\tI smoke or vape occasionally \n" +
            "o\tI rarely smoke or vape\n";
    static String REQUIREMENTS = "DEX_FRD_041, DEX_FRD_043";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_041_043_Smoking_Birth_Year_Under_35_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_041_043_Smoking_Birth_Year_Under_35_Navigation()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void  VTP_DEX_FRD_041_043_Smoking_Birth_Year_Under_35_Navigation_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_041_043 – Smoking (Birth Year) Navigation for User Under 35";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);
        int currentYear = Year.now().getValue();
        user.dobYear=String.valueOf((currentYear-34));

        // Test data map
        LinkedHashMap<String,String[]> smokingSettings = new LinkedHashMap<>();
        smokingSettings.put(SmokeType.SMOKE_REGULARLY.label,new String[] {"3","4"});
        smokingSettings.put(SmokeType.RARELY_SMOKE.label,new String[] {"7","8"});
        smokingSettings.put(SmokeType.SMOKE_OCCASIONALLY.label,new String[] {"9","10"});

        for ( HashMap.Entry<String, String[]> e: smokingSettings.entrySet()) {
            new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.smoking, report);
            pageObj.smoking.selectRadioResponseAndProgress(e.getKey(), pageObj.birthYear, report);

            pageObj.birthYear.fillOutBirthdayAndProgress(user, pageObj.smokingDisclaimer,report);
            report.addScreenshotStep("Step"+ e.getValue()[0]+"_Smoking risks");

            common.clickNextToPage(pageObj.everHadCancer, report);
            report.addScreenshotStep("Step" +  e.getValue()[1] + " EverHadCancer");
        }
    }
}
