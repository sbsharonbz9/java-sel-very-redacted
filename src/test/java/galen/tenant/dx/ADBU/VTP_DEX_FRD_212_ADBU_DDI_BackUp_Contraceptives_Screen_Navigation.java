package galen.tenant.dx.ADBU;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.DDIConditionType;
import galen.enums.tenant.dx.ReviewAnswersLinks;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
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

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_wBP();
        DxPageObj pageObj = new DxPageObj(driver);
        user.conditionType = pageObj.ddiCondition.getCondition(DDIConditionType.HIGH_CHOLESTEROL.label);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFADBUwBP(user, pageObj.review, report);

        pageObj.review.clickEditToPage(ReviewAnswersLinks.HC, pageObj.ddiHighCholesterol, report);
        pageObj.ddiHighCholesterol.selectCheckboxesAndProgress(pageObj.ddiHighCholesterol.getAllButNone(), pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbu,report);
        pageObj.adbu.addressConfirmationsAndProgress("Yes", pageObj.adbuddi, report);
        report.addScreenshotStep("Step8_ADBU_DDI_Screen");

        user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_noBP();
        new DxHFWrappers(driver).runDxHFADBUNoBP(user, pageObj.review, report);

        pageObj.review.clickEditToPage(ReviewAnswersLinks.HC, pageObj.ddiHighCholesterol, report);
        pageObj.ddiHighCholesterol.selectCheckboxesAndProgress(pageObj.ddiHighCholesterol.getAllButNone(), pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpScreen,report);
        pageObj.adbubpScreen.addressConfirmationsAndProgress("Yes", pageObj.adbuddi, report);
        report.addScreenshotStep("Step16_ADBU_DDI_Screen");
    }
}
