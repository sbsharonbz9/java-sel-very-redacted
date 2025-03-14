package galen.tenant.dx.DDIConditions;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxNavigations;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_102_103_104_Other_Medications_Selection extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_102_103_104_Other_Medications_Selection";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_102_103_104_Other_Medications_Selection()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_102_103_104_Other_Medicines_Selection_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY,
                PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_102_103_104 – Other Medications Selection";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);
        ArrayList<String> allButOther = pageObj.otherMedication.getAllButNone();

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.otherMedication, report);
        pageObj.otherMedication.verifyAllOptionsVisible(report);
        common.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step2_InitialState_OtherMeds");

        allButOther.forEach((c)-> {
            new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.otherMedication, report);
            pageObj.otherMedication.selectCheckboxReponse(c, report);
            report.addScreenshotStep(c+"_selected");

            common.clickNextToPage(pageObj.gallbladder, report);
            report.addScreenshotStep(c+"_to_Gallbladder");

            new DxNavigations(driver).antiFungalToADBU(user, pageObj.adbu, null);
            pageObj.adbu.verifyOtherMedIsListed(c, report);
            report.addScreenshotStep("ADBUScreen");
        }
        );

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.otherMedication, report);
        pageObj.otherMedication.selectCheckboxReponses(allButOther, report);
        report.addScreenshotStep("Step28_AllOtherMeds");

        common.clickNextToPage(pageObj.gallbladder, report);
        report.addScreenshotStep("Step29_GallBladderPage");

        new DxHFWrappers(driver).antiFungalToADBU(user, pageObj.adbu, report);
        pageObj.otherMedication.verifyAllOptionsInADBU(report);
        report.addScreenshotStep("Step31_ADBUScreen");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.otherMedication, report);
        pageObj.otherMedication.selectCheckboxReponses(user.defaultNone, report);
        report.addScreenshotStep("Step32_Selected");

        common.clickNextToPage(pageObj.gallbladder, report);
        report.addScreenshotStep("Step33_GallBladderScreen");
    }
}
