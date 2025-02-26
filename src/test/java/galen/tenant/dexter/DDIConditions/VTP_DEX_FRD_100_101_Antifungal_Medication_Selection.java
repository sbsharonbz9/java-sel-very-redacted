package galen.tenant.dexter.DDIConditions;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.CheckboxPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_100_101_Antifungal_Medication_Selection extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_100_101_Antifungal_Medication_Selection";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_100_101_Antifungal_Medication_Selection()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_100_101_Antifungal_Medication_Selection_Test() throws Exception {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_100_101 – Antifungal Antibacterial Medication Selection ";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.antifungal, report);
        common.clickYesNoNextToPage("No", pageObj.otherMedication, report);
        report.addScreenshotStep("Step3_OtherMeds");

        // Steps 4-33
        user.isAntifungal="Yes";
        pageObj.antifungalMeds.getAllButNone().forEach((c) -> {
            new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.antifungalMeds, report);
            user.antiFungalMeds = new CheckboxPage(driver).getCondition(c);
            pageObj.antifungalMeds.selectCheckboxesAndProgress(user.antiFungalMeds, pageObj.otherMedication, report);
            report.addScreenshotStep(c);

            new DexterHFWrappers(driver).runAntifungalToADBU(user, pageObj.otherMedication,pageObj.adbu, report);
            pageObj.adbu.verifyConditionIsListed(c, report);
            report.addScreenshotStep(c+"_ADBU");
        });

        //Step 34-38
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.antifungalMeds, report);
        pageObj.antifungalMeds.selectCheckboxesAndProgress(pageObj.antifungalMeds.getAllButNone(),
                pageObj.otherMedication, report);
        report.addScreenshotStep("Step36_OtherMeds");

        new DexterHFWrappers(driver).runAntifungalToADBU(user, pageObj.otherMedication,pageObj.adbu, report);
        pageObj.antifungalMeds.verifyAllOptionsInADBU(report);
        report.addScreenshotStep("Step38_All_ADBU");
    }
}
