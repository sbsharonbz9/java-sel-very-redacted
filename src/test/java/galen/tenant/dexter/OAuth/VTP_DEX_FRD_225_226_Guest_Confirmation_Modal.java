package galen.tenant.dexter.OAuth;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.OAuth;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_225_226_Guest_Confirmation_Modal extends BaseTest {

    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_225_226_Guest_Confirmation_Modal";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_225_226_Guest_Confirmation_Modal() {
        VERSIONHISTORY.add("1.0;09MAR2023;Initial Test Script ;Tester");
    }

    @Test
    public void VTP_DEX_FRD_225_226_Guest_Confirmation_Modal_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_225_226_Guest_Confirmation_Modal";
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DexterPageObj pageObj = new DexterPageObj(driver);
        OAuth signIn = pageObj.oAuth;

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, signIn, report);

        signIn.clickGuestButtonToModal(report);
        signIn.clickXButton(report);
        signIn.verifyModalDismissed(report);
        report.addScreenshotStep("Step_3_Login_Selection");

        signIn.chooseAccountTypeAndProgress(user, pageObj.usedProduct, report);
        report.addScreenshotStep("Step_5_Prior_Use");
    }
}