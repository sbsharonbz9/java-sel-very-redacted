package galen.tenant.dx.Misc;

import galen.base.BaseTest;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_265_Know_Numbers_Clinician extends BaseTest {
    static String OBJECTIVE = "To verify the Know Numbers Screen shall provide a control that allows the user to " +
            "navigate to the Previous Product Use Screen, if the user is a clinician";
    static String NOTES="This protocol contains the following verification scenario(s):\n" +
            "-\tClinician user on the Know Numbers screen is navigated to the Prior Use Screen when clicking Next button\n";
    static String REQUIREMENTS = "DEX_FRD_265";
    static String REFERENCES = "N/A";
    String reportName = "VTP_DEX_FRD_265_Know_Numbers_Clinician";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_265_Know_Numbers_Clinician()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_265_Know_Numbers_Clinician_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_265 – Know Numbers Navigation (Clinician)";

        StudyAdminPageObj sp = new StudyAdminPageObj(driver);
        DxPageObj dex = new DxPageObj(driver);

        sp.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        sp.login.logIn(RoleType.CLINICIAN.email, report);
        sp.participants.clickViewRecords(report);
        sp.viewRecords.createNewRecord(report);
        dex.welcomePage.verifyAtPage(report);
        dex.welcomePage.clickBegin(report);
        dex.privacyPage.clickIAcceptBtnToPage(dex.numbers, report);
        dex.numbers.clickNextToPage(dex.usedProduct, report);
        report.addScreenshotStep("Step7_Prior Use");
    }
}
