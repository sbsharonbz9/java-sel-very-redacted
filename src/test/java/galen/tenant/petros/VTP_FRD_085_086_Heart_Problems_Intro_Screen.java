package galen.tenant.petros;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosNavigations;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_085_086_Heart_Problems_Intro_Screen extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_FRD_085_086_Heart_Problems_Intro_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    PetrosPageObj pageObj;
    PetrosUser user;

    VTP_FRD_085_086_Heart_Problems_Intro_Screen() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_FRD_085_086_Heart_Problems_Intro_Screen_Test() throws IOException, InterruptedException {
        
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_085_086 – Heart Problems Intro Screen";
        pageObj = new PetrosPageObj(driver);
        this.user = new PetrosUser();
        CommonPageFeatures common = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.heartProblemsInstruction, report);
        common.verifyNextPresent(report);
        report.addScreenshotStep("Step2_InstructionalScreen",driver);
        common.clickNextToPage(pageObj.heartNotSafeForSex, report);
        report.addScreenshotStep("Step3_HeartNotSafeForSex", driver);
    }

}
