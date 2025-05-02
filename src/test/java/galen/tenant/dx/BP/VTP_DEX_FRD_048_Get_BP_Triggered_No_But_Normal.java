package galen.tenant.dx.BP;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.BloodPressureType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_048_Get_BP_Triggered_No_But_Normal extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_048_Get_BP_Triggered_No_But_Normal";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_048_Get_BP_Triggered_No_But_Normal()  {
        VERSIONHISTORY.add("1.0;13OCT2022;Initial Test Script;Tester;" +
                "2.0;18JUN2024;Per 567: Remove N/A from Expected Results column when using HappyFlow\n" +
                "Per 591: Update Test Steps for modified assessment and navigation;Tural Aslanov");
    }

    @Test
    public void VTP_DEX_FRD_048_Get_BP_Triggered_No_But_Normal_Test() {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_047 – NO on Know BP Numbers Triggers Get BP Info";

        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.knowBPNumber, report);
        pageObj.knowBPNumber.selectRadioResponseAndProgress(BloodPressureType.No_Know_BP.label,
                pageObj.review, report);
        report.addScreenshotStep("Step2_Editable Summary");

        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        report.addScreenshotStep("Step5_Get BP Numbers");
    }
}
