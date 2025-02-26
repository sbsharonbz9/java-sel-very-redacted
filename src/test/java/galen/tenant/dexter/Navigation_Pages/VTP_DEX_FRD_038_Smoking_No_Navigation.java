package galen.tenant.dexter.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_038_Smoking_No_Navigation extends BaseTest {
    static String OBJECTIVE = "If the user selects \"I don’t smoke or Vape\" on the Smoking or Vape Screen, the " +
            "application shall navigate to the Ever Had Cancer Screen";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_038";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_038_Smoking_No_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_038_Smoking_No_Navigation()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void  VTP_DEX_FRD_038_Smoking_No_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_038 - Smoking Response No Navigation";

        DexterUser user = new DexterUser();
        DexterPageObj pageObj = new DexterPageObj(driver);

        new PritUnlPage(driver).authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.smoking, report);
        pageObj.smoking.selectRadioResponseAndProgress( user.smokingType.label, pageObj.everHadCancer,report);
        report.addScreenshotStep("Step2_Ever Had Cancer");
    }
}
