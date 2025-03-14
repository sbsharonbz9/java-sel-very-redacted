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

public class VTP_DEX_FRD_049_050_Know_BP_More_Info extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_049: To verify On the Enter BP Numbers Screen, the application shall provide a " +
            "link to the blood pressure more in-formation modal.\n" +
            "DEX_FRD_050: To verify On the Enter BP Numbers Screen, if the user selects the more information link" +
            " the application shall navigate to the blood pressure more information modal.\n";
    static String NOTES = "The following verification scenario(s) are contained in this protocol:\n" +
            "-\tMore Info link is displayed on the Enter BP Screen\n" +
            "-\tClicking More Info link on the Enter BP Screen navigates to the More Info Modal";
    static String REQUIREMENTS = "DEX_FRD_049, DEX_FRD_050";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_049_050_Know_BP_More_Info ";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_049_050_Know_BP_More_Info()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_049_050_Know_BP_More_Info_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_049_050 – Know BP Numbers More Info";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired();
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        common.verifyMoreInfoLinkDisplayed(report);
        report.addScreenshotStep("Step2_MoreInfoLink");
        common.clickMoreInfo(report);
        common.verifyMoreInfoDisplayed(report);
        report.addScreenshotStep("Step3_MoreInfoScreen");
    }
}
