package galen.tenant.dx.BP;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.BloodPressureType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import galen.pages.tenant.dx.InitialAssessment.EnterBPEnd;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_148_OK_BP_Confirm_BP_Safe extends BaseTest {

    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_148_OK_BP_Confirm_BP_Safe";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_148_OK_BP_Confirm_BP_Safe() {
        VERSIONHISTORY.add("1.0;28FEB2023;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_148_OK_BP_Confirm_BP_Safe_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_148_OK_BP_Confirm_BP_Safe";
        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);
        EnterBPEnd bpPage = pageObj.enterBPEnd;

        pageObj.pritUnl.authenticateUserIfRequired(URLType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.knowBPNumber, report);

        pageObj.knowBPNumber.selectRadioResponseAndProgress(BloodPressureType.No_Know_BP.label,
                pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal, report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "119";
        user.diastolic = "79";
        bpPage.enterAndVerifyToModal(user, report);
        report.addScreenshotStep("Step6_Month_Modal");

        pageObj.knowBPNumber.clickYesNoModalToPage("Yes", pageObj.purchaseOptions, report);
        report.addScreenshotStep("Step7_Safe_Screen");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.knowBPNumber, report);
        pageObj.knowBPNumber.selectRadioResponseAndProgress(BloodPressureType.No_Know_BP.label,
                pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal, report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);

        user.systolic = "120";
        user.diastolic = "80";
        bpPage.enterAndVerifyToModal(user, report);
        report.addScreenshotStep("Step13_Month_Modal");

        pageObj.knowBPNumber.clickYesNoModalToPage("Yes", pageObj.purchaseOptions, report);
        report.addScreenshotStep("Step14_Safe_Screen");
    }
}
