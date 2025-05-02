package galen.tenant.dx.BP;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_053_3MonthModal_No_GetBP extends BaseTest {
    static String OBJECTIVE = "To verify if the user selects ‘No” on the BP Within 3 Months Modal, the application " +
            "shall flag the health survey for Not Current BP numbers and navigate to the Health Survey Summary Screen";
    static String NOTES = "The following scenario(s) are verified in this protocol:\n" +
            "-\tSubmission of No to BP Numbers being within 3 Months navigates to the Editable Summary Screen\n" +
            "-\tUser sees Get BP Measured Screen for triggering the Not Current BP flag";
    static String REQUIREMENTS = "DEX_FRD_053";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_053_3MonthModal_No_GetBP";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_053_3MonthModal_No_GetBP()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_053_3MonthModal_No_GetBP_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_053 – 3Month Modal No Response Triggers Get BP Flow";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.knowBPNumber, report);
        pageObj.knowBPNumber.clickYesAndAddressModalToPage(pageObj.review, "No", report);
        report.addScreenshotStep("Step3_Editable Summary");

        pageObj.review.addressConfirmations(report);
        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.adbubpNormal,report);
        report.addScreenshotStep("Step6_Get BP Numbers");
    }
}
