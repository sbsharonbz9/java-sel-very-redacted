package galen.tenant.dx.DNU;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.ReviewAnswersLinks;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_030_HormonalBC_No_DNU extends BaseTest {
    static String OBJECTIVE = "If the user submits a denial response on the Hormonal Birth Control B Screen, the " +
            "application shall end the health survey and navigate to the DNU screen for non-menstruating users.";
    static String NOTES = "This protocol contains the following verification scenario(s):\n" +
            "-\tUser entering No on Hormonal BC (B) Screen on the initial run of the health survey triggers DNU Screen\n" +
            "-\tUser entering No on Hormonal BC (B) Screen by navigation of Editable Summary triggers DNU Screen";
    static String REQUIREMENTS = "DEX_FRD_030";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_030_HormonalBC_No_DNU";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_030_HormonalBC_No_DNU()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_030_HormonalBC_No_DNU_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_030 – Hormonal BC Usage No Navigates to DNU Screen";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.menstrual, report);
        pageObj.menstrual.clickYesNoNextToPage("No", pageObj.birthControlB, report);
        pageObj.birthControlB.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        report.addScreenshotStep("Step3_DNU");

        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.review, report);
        pageObj.review.clickEditToPage(ReviewAnswersLinks.MENSTRUAL, pageObj.menstrual, report);
        pageObj.menstrual.clickYesNoNextToPage("No", pageObj.birthControlB, report);
        pageObj.birthControlB.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        report.addScreenshotStep("Step7_DNU");
    }
}
