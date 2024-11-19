package galen.tenant.petros;

import com.itextpdf.io.IOException;
import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosNavigations;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_075_076_077_078_079_Nitrate_Poppers_Use_Screen extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    PetrosUser user;
    String reportName = "VTP_FRD_075_076_077_078_079_Nitrate_Poppers_Use_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_FRD_075_076_077_078_079_Nitrate_Poppers_Use_Screen()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_FRD_075_076_077_078_079_Nitrate_Poppers_Use_Screen_Test() throws IOException, InterruptedException, java.io.IOException {
        
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_075_076_077_078_079_Nitrate_Poppers_Use_Screen";
        PetrosPageObj pageObj = new PetrosPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);
        PetrosNavigations navs = new PetrosNavigations(driver);
        user = new PetrosUser();

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);
        navs.petrosHappyFlowTo(user, pageObj.nitratePoppersUse, report);
        common.verifyYesNoUnsurePresent(report);
        common.verifyNextPresent(report);
        common.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step2_Nitrate_Popper_Screen Next_Disabled", driver);

        common.clickYesNoUnsure("Yes", report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step3_Nitrate_Popper_Screen Next_Enabled", driver);

        common.clickYesNoUnsure("No", report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step4_Nitrate_Popper_Screen Next_Enabled", driver);

        common.clickYesNoUnsure("Not Sure", report);
        common.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step5_Nitrate_Popper_Screen Next_Enabled", driver);

        user.nitratePopperUse="Yes";
        
        navs.petrosHappyFlowTo(user, pageObj.review,report);
        pageObj.review.addressConfirmationsAndProgress(pageObj.kickoutPage, report);
        report.addScreenshotStep("Step8_DNU Page", driver);
        
        navs.petrosHappyFlowTo(user, pageObj.nitratePoppersUse,report);
        common.clickYesNoUnsure("Not Sure", report);
        common.clickNextToPage(pageObj.clarificationOnPoppers, report);
        report.addScreenshotStep("Step10_Nitrate_Clarification Screen", driver);
        
        navs.petrosHappyFlowTo(user, pageObj.nitratePoppersUse,report);
        common.clickYesNoUnsure(user.nitratePopperUse="No", report);
        common.clickNextToPage(pageObj.heartProblemsInstruction, report);
        report.addScreenshotStep("Step12_Heart Problems Intro Screen", driver);

        navs.petrosHappyFlowTo(user, pageObj.review,report);
        pageObj.review.clickNitratePoppersEdit(report);
        common.clickYesNoUnsure("Yes", report);
        common.clickNextToPage(pageObj.review, report);
        pageObj.review.addressConfirmationsAndProgress(pageObj.kickoutPage, report);
        report.addScreenshotStep("Step17_DNU Page", driver);
    }
}
