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

public class VTP_DEX_FRD_230_ADBU_DDI_Backup_Contraceptives_Navigates_To_BP extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    String reportName = "VTP_DEX_FRD_230_ADBU_DDI_Backup_Contraceptives_Navigates_To_BP";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_230_ADBU_DDI_Backup_Contraceptives_Navigates_To_BP() {
        VERSIONHISTORY.add("1.0;24JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_230_ADBU_DDI_Backup_Contraceptives_Navigates_To_BP_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_230 – ADBU DDI BackUp Contraceptives Navigates To BP";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_noBP();
        DxPageObj pageObj = new DxPageObj(driver);
        user.conditionType = pageObj.ddiCondition.getCondition(DDIConditionType.HIV.label);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFADBUNoBP(user, pageObj.review, report);

        pageObj.review.clickEditToPage(ReviewAnswersLinks.HIV, pageObj.ddihiv, report);
        pageObj.ddihiv.selectCheckboxesAndProgress(pageObj.ddihiv.getAllButNone(), pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpScreen,report);
        pageObj.adbu.addressConfirmationsAndProgress("Yes", pageObj.adbubc, report);
        pageObj.adbubc.clickNextToPage(pageObj.adbubpNormal, report);
        pageObj.adbubpNormal.clickGetBPButtonToEnterBP(report);
        report.addScreenshotStep("Step10_Enter_BP_Screen");
    }
}