package galen.tenant.petros;

import com.itextpdf.io.IOException;
import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosNavigations;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_111_112_113_Pulmonary_Hypertension_Medicines_Screen extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    PetrosUser user;
    String reportName = "VTP_FRD_111_112_113_Pulmonary_Hypertension_Medicines_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_FRD_111_112_113_Pulmonary_Hypertension_Medicines_Screen()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_FRD_111_112_113_Pulmonary_Hypertension_Medicines_Screen_Test() throws IOException, InterruptedException, java.io.IOException {
        
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_111_112_113_Pulmonary_Hypertension_Medicines_Screen";
        PetrosPageObj pageObj = new PetrosPageObj(driver);
        BasicHelpers basicHelpers = new BasicHelpers(driver);
        PetrosNavigations navs = new PetrosNavigations(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);
        user = new PetrosUser();

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);

        navs.petrosHappyFlowTo(user, pageObj.hypertensionsMedicine,report);
        common.verifyYesNoPresent(report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step2_Blank_NextDisabled", driver);

        common.clickYesOrNo("No", report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step3_No_NextEnabled", driver);

        common.clickYesOrNo("No", report);
        basicHelpers.verifyRadioButtonSelected( "No", report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step4_No_NextEnabled", driver);

        common.clickYesOrNo("Yes", report);
        basicHelpers.verifyRadioButtonSelected( "Yes", report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step5_Yes_NextEnabled", driver);

        common.clickYesNoNextToPage("No", pageObj.highBloodPressureMedicines, report);
        report.addScreenshotStep("Step6_High blood pressure meds", driver);
        
        navs.petrosHappyFlowTo(user, pageObj.hypertensionsMedicine,report);
        common.clickYesNoNextToPage("Yes", pageObj.highBloodPressureMedicines, report);
        report.addScreenshotStep("Step8_High blood pressure meds", driver);

        navs.petrosHappyFlowTo(user, pageObj.review, report);
        pageObj.review.addressConfirmationsAndProgress(pageObj.kickoutPage, report);
        report.addScreenshotStep("Step10_DNU Page", driver);

        user.hypertension="No";
        navs.petrosHappyFlowTo(user, pageObj.review, report);
        pageObj.review.clickPulmonaryHypertensionEdit(report);
        pageObj.hypertensionsMedicine.verifyAtPage(report);
        common.clickYesNoNextToPage("Yes", pageObj.review, report);
        pageObj.review.addressConfirmationsAndProgress(pageObj.kickoutPage, report);
        report.addScreenshotStep("Step15_DNU Page", driver);
    }
}
