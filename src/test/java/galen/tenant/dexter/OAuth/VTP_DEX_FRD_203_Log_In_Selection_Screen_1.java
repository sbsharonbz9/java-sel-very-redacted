package galen.tenant.dexter.OAuth;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_203_Log_In_Selection_Screen_1 extends BaseTest {

    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_203_Log_In_Selection_Screen_1";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_203_Log_In_Selection_Screen_1() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script ;Tester");
    }

    @Test
    public void VTP_DEX_FRD_203_Log_In_Selection_Screen_1_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_203_Log_In_Selection_Screen_1";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFnoBPNonsmoker(user, pageObj.review, report);

        pageObj.review.addressConfirmations(report);
        report.addScreenshotStep("Step_3_Sign In");
    }
}
