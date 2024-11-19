package galen.tenant.dexter.ADBU;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dexter.DDIConditionType;
import galen.enums.tenant.dexter.ReviewAnswersLinks;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_212_ADBU_DDI_BackUp_Contraceptives_Screen_Navigation extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_212_ADBU_DDI_BackUp_Contraceptives_Screen_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_212_ADBU_DDI_BackUp_Contraceptives_Screen_Navigation() {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_212_ADBU_DDI_BackUp_Contraceptives_Screen_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_212 – ADBU/DDI BackUp Contraceptives Screen Navigation";

        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_wBP();
        DexterPageObj pageObj = new DexterPageObj(driver);
        user.conditionType = pageObj.ddiCondition.getCondition(DDIConditionType.HIGH_CHOLESTEROL.label);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFADBUwBP(user, pageObj.review, report);

        pageObj.review.clickEditToPage(ReviewAnswersLinks.HC, pageObj.ddiHighCholesterol, report);
        pageObj.ddiHighCholesterol.selectCheckboxesAndProgress(pageObj.ddiHighCholesterol.getAllButNone(), pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbu,report);
        pageObj.adbu.addressConfirmationsAndProgress("Yes", pageObj.adbuddi, report);
        report.addScreenshotStep("Step8_ADBU_DDI_Screen");

        user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_noBP();
        new DexterHFWrappers(driver).runDexterHFADBUNoBP(user, pageObj.review, report);

        pageObj.review.clickEditToPage(ReviewAnswersLinks.HC, pageObj.ddiHighCholesterol, report);
        pageObj.ddiHighCholesterol.selectCheckboxesAndProgress(pageObj.ddiHighCholesterol.getAllButNone(), pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpScreen,report);
        pageObj.adbubpScreen.addressConfirmationsAndProgress("Yes", pageObj.adbuddi, report);
        report.addScreenshotStep("Step16_ADBU_DDI_Screen");
    }
}
