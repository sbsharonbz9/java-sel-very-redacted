package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.pages.sp.StudyAdminPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_023_Account_Types_Supported extends BaseTest {
    static String OBJECTIVE = "To verify the following account types shall be supported on the portal:\n" +
            "•\tStudy Staff Lead \n" +
            "•\tStudy Staff\n" +
            "•\tClinician Lead\n" +
            "•\tClinician\n" +
            "•\tCentral Assessor\n" +
            "•\tIE Admin\n";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "o\tUser can login as Admin User\n" +
            "o\tUser can login as Central Assessor User\n" +
            "o\tUser can login as Study Staff Lead User\n" +
            "o\tUser can login as Study Staff User\n" +
            "o\tUser can login as Clinician Lead User\n" +
            "o\tUser can login as Clinician User\n";
    static String REQUIREMENTS = "DEX_SP_FRD_023";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_023_Account_Types_Supported";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_023_Account_Types_Supported()  {
        VERSIONHISTORY.add("1.0;28FEB2023;Per 193: Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_SP_FRD_023_Account_Types_Supported_Test() throws IOException, InterruptedException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_023 – Account Types Supported";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);

        ArrayList<RoleType> roles = new ArrayList<>(Arrays.asList(RoleType.ADMIN, RoleType.CENTRAL_ASSESSOR,
                RoleType.STUDY_STAFF_LEAD, RoleType.STUDY_STAFF, RoleType.CLINICIAN_LEAD, RoleType.CLINICIAN));

        for (RoleType r : roles) {
            pageObj.login.logIn(r.email, report);
            report.addScreenshotStep(r.name()+"Login");
            pageObj.login.logout(report);
        }
    }
}
