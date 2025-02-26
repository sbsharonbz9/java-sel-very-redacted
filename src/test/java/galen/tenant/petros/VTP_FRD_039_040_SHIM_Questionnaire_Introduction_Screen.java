package galen.tenant.petros;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosHFWrappers;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_039_040_SHIM_Questionnaire_Introduction_Screen extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    PetrosPageObj pageObj;
    PetrosUser user;
    String reportName = "VTP_FRD_039_040_SHIM_Questionnaire_Introduction_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_FRD_039_040_SHIM_Questionnaire_Introduction_Screen() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_FRD_039_040_SHIM_Questionnaire_Introduction_Screen_Test() throws IOException {
        
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_039_040_SHIM_Questionnaire_Introduction_Screen";
        pageObj = new PetrosPageObj(driver);
        PetrosHFWrappers hf = new PetrosHFWrappers(driver);
        user = new PetrosUser();

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);
        hf.executeHappyFlow(user, pageObj.questionnaireIntroduction, "IA Checkout w Guest User", report);
        pageObj.questionnaireIntroduction.verifyNextPresent(report);
        report.addScreenshotStep("SHIM_Questionnaire_Introduction_Screen",driver);

        pageObj.questionnaire1.clickNextToPage(pageObj.questionnaire1,report);
        report.addScreenshotStep("SHIM Questionnaire - Question 1 ",driver);
    }

}
