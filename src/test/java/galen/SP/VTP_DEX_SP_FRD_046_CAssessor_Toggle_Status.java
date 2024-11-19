package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.pages.sp.Participants;
import galen.pages.sp.StudyAdminPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_046_CAssessor_Toggle_Status extends BaseTest {
    static String OBJECTIVE = "To verify if a Central Assessor user selects the “Edit” link in the Action column of the Participants screen, the portal shall display a popup screen that allows the user to: \n" +
            "-\tDisable or re-enable the participant account (once account has been setup)";

    static String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tDisable a participant account (account created)\n" +
            "o\tRe-enable a participant account (account created)\n";

    static String REQUIREMENTS = "DEX_SP_FRD_046";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_046_CAssessor_Toggle_Status";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_046_CAssessor_Toggle_Status() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script – Per 219;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_046_CAssessor_Toggle_Status_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_046 – Central Assessor Account Status Toggle";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.CENTRAL_ASSESSOR.email, report);
        String email="james.reale+email1@idea-evolver.com";

        par.clickEditParticipant(email, report);
        par.clickStatusToggle(report);
        report.addScreenshotStep("Step4_Status_Toggle_Off");

        par.clickSaveChangesButton(report);
        par.verifyDisplayedRedDotStatus(email, report);
        report.addScreenshotStep("Step5_Deactivated_User");

        par.clickEditParticipant(email, report);
        par.clickStatusToggle(report);
        report.addScreenshotStep("Step7_Status_Toggle_Off");

        par.clickSaveChangesButton(report);
        par.verifyNotDisplayedRedDotStatus(email, report);
        report.addScreenshotStep("Step8_Activated_User");
    }
}
