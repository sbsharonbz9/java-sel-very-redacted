package galen.tenant.dx.BP;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.BloodPressureType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_144_145_149_GetBP_Screen_Options extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_144: To verify on the Get BP screen, if the user did not previously enter BP and " +
            "did NOT trigger ADBUs, the application shall display a notification that BP is required and provide a link " +
            "for more information on how to check their BP and a link for them to enter BP numbers. \n" +
            "DEX_FRD_145: To verify on the  Get BP screen, if the user who did not previously enter BP selects the link " +
            "to enter BP numbers, the application shall navigate to the Final BP Numbers screen. \n" +
            "DEX_FRD_149: To verify on the  Get BP screen, if the user selects the link for more information on how to " +
            "check their BP, the application shall navigate to the BP more information modal. ";
    static String NOTES = "This protocol contains the following verification scenarios: \n" +
            "Get BP Screen is displayed when user has completed assessment with entered BP and no ADBUs triggered \n" +
            "Get BP Screen displays the following buttons/links: \n" +
            "How to check your blood pressure link  \n" +
            "Enter blood pressure numbers button \n" +
            "How to check your blood pressure link displays the How to Check Modal \n" +
            "Enter blood pressure numbers button navigates to Enter BP - Final BP Screen ";
    static String REQUIREMENTS = "DEX_FRD_144_145_149";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_144_145_149_GetBP_Screen_Options";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_144_145_149_GetBP_Screen_Options() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_144_145_149_GetBP_Screen_Options_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_144_145_149_GetBP_Screen_Options";
        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        new PritUnlPage(driver).authenticateUserIfRequired(URLType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.knowBPNumber, report);
        pageObj.knowBPNumber.selectRadioResponseAndProgress(BloodPressureType.No_Know_BP.label,
                pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal, report);
        report.addScreenshotStep("Step4_Bp Screen");

        common.clickMoreInfoToModal(report);
        report.addScreenshotStep("Step5_MoreInfoScreen");

        pageObj.adbubpNormal.clickMoreInfoBackToModalDismissed(report);
        report.addScreenshotStep("Step7_BP_Screen");
    }
}