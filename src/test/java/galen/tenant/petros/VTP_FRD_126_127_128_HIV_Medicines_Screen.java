package galen.tenant.petros;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosNavigations;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_126_127_128_HIV_Medicines_Screen extends BaseTest {

    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";

    String reportName = "VTP_FRD_126_127_128_HIV_Medicines_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    PetrosUser user;
    BasicHelpers bh;
    PetrosPageObj pageObj;

    VTP_FRD_126_127_128_HIV_Medicines_Screen()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_FRD_126_127_128_HIV_Medicines_Screen_Test() throws IOException, InterruptedException {
        
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_126_127_128_HIV_Medicines_Screen";
        pageObj = new PetrosPageObj(driver);
        bh = new BasicHelpers(driver);
        user = new PetrosUser();
        CommonPageFeatures common = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.hivMedicines, report);

        pageObj.hivMedicines.verifyYesNoPresent(report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step2_Blank_NextDisabled", driver);

        pageObj.hivMedicines.clickYesOrNo("No", report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step3_No_NextEnabled", driver);

        pageObj.hivMedicines.clickYesOrNo("No", report);
        bh.verifyRadioButtonSelected( "No", report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step4_No_NextEnabled", driver);

        common.clickYesOrNo("Yes", report);
        bh.verifyRadioButtonSelected( "Yes", report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step5_Yes_NextEnabled", driver);

        pageObj.hivMedicines.clickYesNoNextToPage("No", pageObj.noncardiacHealthConditions, report);
        report.addScreenshotStep("Step6_Non-cardiac health conditions", driver);

        new PetrosNavigations(driver).petrosHappyFlowTo(user, pageObj.hivMedicines,report);
        common.clickYesNoNextToPage(user.hiv="Yes", pageObj.hivMedicines,report);
        report.addScreenshotStep("Step8_Non-cardiac health conditions", driver);

        new PetrosNavigations(driver).petrosHappyFlowTo(user, pageObj.review, report);
        pageObj.review.addressConfirmationsAndProgress(pageObj.kickoutPage, report);
        report.addScreenshotStep("Step10_DNU Page", driver);

        user.hiv="No";
        new PetrosNavigations(driver).petrosHappyFlowTo(user, pageObj.review, report);
        getHIVMedicineEditLinkFlow(user, report);
    }

    public void getHIVMedicineEditLinkFlow( PetrosUser user, GalenReport report)  {
        pageObj.review.clickHivMedicineEditLink(report);
        pageObj.hivMedicines.verifyAtPage(report);
        user.hiv = "Yes";
        pageObj.hivMedicines.clickYesOrNo(user.hiv, report);
        pageObj.hivMedicines.clickNextToPage(pageObj.review, report);
        pageObj.review.addressConfirmationsAndProgress(pageObj.kickoutPage, report);
        report.addScreenshotStep("Step15_DNU Page");
    }
}
