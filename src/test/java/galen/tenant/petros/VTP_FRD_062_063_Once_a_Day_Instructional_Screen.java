package galen.tenant.petros;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosHFWrappers;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_062_063_Once_a_Day_Instructional_Screen extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    PetrosPageObj pageObj;
    PetrosUser user;
    String reportName = "VTP_FRD_062_063_Once_a_Day_Instructional_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_FRD_062_063_Once_a_Day_Instructional_Screen()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_FRD_062_063_Once_a_Day_Instructional_Screen_Test() throws IOException {
        
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_062_063 - Once a Day Instructional Screen";
        pageObj = new PetrosPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);
        CommonPageFeatures common = new CommonPageFeatures(driver);
        user = new PetrosUser();

        new PetrosHFWrappers(driver).executeHappyFlow(user, pageObj.otherEDMeds, REFERENCES, report);
        common.clickYesNoNextToPage( "Yes", pageObj.onceADayInstruction, report);
        pageObj.onceADayInstruction.verifyNextPresent(report);
        report.addScreenshotStep("Step2_InstructionalScreen", driver);

        pageObj.onceADayInstruction.clickNextToPage(pageObj.nitrateUse, report);
        report.addScreenshotStep("Step3_NitrateUseScreen", driver);
    }

}
