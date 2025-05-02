package galen.tenant.dx.DDIConditions;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.common.CheckboxPage;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
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
    public void VTP_DEX_FRD_100_101_Antifungal_Medication_Selection_Test()  {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_100_101 – Antifungal Antibacterial Medication Selection ";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        DxHFWrappers dxHFWrappers = new DxHFWrappers(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.antifungal, report);
        common.clickYesNoNextToPage("No", pageObj.otherMedication, report);
        report.addScreenshotStep("Step3_OtherMeds");

        // Steps 4-33
        user.isAntifungal="Yes";
        pageObj.antifungalMeds.getAllButNone().forEach((c) -> {
            dxHFWrappers.runDxHFNonsmokingwBP(user, pageObj.antifungalMeds, report);
            user.antiFungalMeds = new CheckboxPage(driver).getCondition(c);
            pageObj.antifungalMeds.selectCheckboxesAndProgress(user.antiFungalMeds, pageObj.otherMedication, report);
            report.addScreenshotStep(c);

            dxHFWrappers.runAntifungalToADBU(user, pageObj.otherMedication,pageObj.adbu, report);
            pageObj.adbu.verifyConditionIsListed(c, report);
            report.addScreenshotStep(c+"_ADBU");
        });

        //Step 34-38
        dxHFWrappers.runDxHFNonsmokingwBP(user, pageObj.antifungalMeds, report);
        pageObj.antifungalMeds.selectCheckboxesAndProgress(pageObj.antifungalMeds.getAllButNone(),
                pageObj.otherMedication, report);
        report.addScreenshotStep("Step36_OtherMeds");

        dxHFWrappers.runAntifungalToADBU(user, pageObj.otherMedication,pageObj.adbu, report);
        pageObj.antifungalMeds.verifyAllOptionsInADBU(report);
        report.addScreenshotStep("Step38_All_ADBU");
    }
}
