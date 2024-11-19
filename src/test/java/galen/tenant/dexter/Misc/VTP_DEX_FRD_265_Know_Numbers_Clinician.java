package galen.tenant.dexter.Misc;

import galen.base.BaseTest;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_265_Know_Numbers_Clinician extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_265_Know_Numbers_Clinician";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_265_Know_Numbers_Clinician()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_265_Know_Numbers_Clinician_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_265 – Know Numbers Navigation (Clinician)";

        StudyAdminPageObj sp = new StudyAdminPageObj(driver);
        DexterPageObj dex = new DexterPageObj(driver);

        sp.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        sp.participants.load(UrlType.STUDY);
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
