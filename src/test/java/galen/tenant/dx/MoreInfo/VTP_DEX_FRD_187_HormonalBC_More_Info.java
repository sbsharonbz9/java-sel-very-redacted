package galen.tenant.dx.MoreInfo;

import galen.base.BaseTest;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_187_HormonalBC_More_Info extends BaseTest {


    static String OBJECTIVE = "To verify on the Hormonal BC (A) screen, the application shall provide a control which navigates to the Hormonal BC more information modal. ";
    static String NOTES = "None";

    static String REQUIREMENTS = "DEX_FRD_187";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_187_HormonalBC_More_Info";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_187_HormonalBC_More_Info() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_187_HormonalBC_More_Info_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_187_HormonalBC_More_Info";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        pageObj.pritUnlauthenticateUserIfRequired();
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.birthControl, report);
        common.verifyMoreInfoLinkDisplayed(report);
        report.addScreenshotStep("Step2_MoreInfoLink");
        common.clickMoreInfo(report);
        common.verifyMoreInfoDisplayed(report);
        report.addScreenshotStep("Step3_MoreInfoScreen");

    }
}
